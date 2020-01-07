package frc.reuse.subsystems.drive;

import java.util.ArrayList;
import java.util.List;

import frc.reuse.config.TalonConfig.Gains;
import frc.reuse.config.TankConfig;
import frc.reuse.hardware.TalonChecker;
import frc.reuse.hardware.TalonFactory;
import frc.reuse.hardware.sensors.Gyro;
import frc.reuse.hardware.sensors.GyroPigeon;
import frc.reuse.math.geometry.Kinematics;
import frc.reuse.math.geometry.Pose2d;
import frc.reuse.math.geometry.Twist2d;
import frc.reuse.subsystems.EnhancedSubsystem;
import frc.reuse.subsystems.background.BackgroundUpdate;
import frc.reuse.subsystems.background.BackgroundUpdater;
import frc.reuse.subsystems.drive.PathFollower.Parameters;
import frc.reuse.utilities.ReflectingCSVWriter;
import frc.reuse.RobotStateBase;
import frc.reuse.EnhancedRobot;

import com.ctre.phoenix.motorcontrol.ControlFrame;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.VelocityMeasPeriod;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/** @author STEM Alliance of Fargo Moorhead */
public final class TankSubsystem extends EnhancedSubsystem
{
    private enum TANK_MODE
    {
        OPEN_LOOP,
        LOCK_SETPOINT,
        DISTANCE,
        TURN,
        PATH,
    }

    private final BackgroundUpdate updater = new BackgroundUpdate()
    {
        public void onStartUpdates(boolean isAutonomous)
        {
            synchronized (TankSubsystem.this)
            {
                final boolean brake = isAutonomous || kBrakeOpenLoop;  // Mutually exclusive, so this works

                setBrake(brake);  // Set once, avoids talon brief disables from otherwise setting in commands
                leftMaster.configNeutralDeadband((isAutonomous) ? kDeadbandOpenLoop : 0.0, 0);
                rightMaster.configNeutralDeadband((isAutonomous) ? kDeadbandOpenLoop : 0.0, 0);
                
                zeroEncoders();
                setGyro(0.0);
                state.resetDriveState(Timer.getFPGATimestamp(), new Pose2d());
            }
        }

        public void onBackgroundUpdate()
        {
            synchronized (TankSubsystem.this)
            {
                switch (controlMode)
                {
                    case OPEN_LOOP:
                        break;
                    case LOCK_SETPOINT:
                        break;
                    case TURN:
                        updateTurnToHeading();
                        break;
                    case DISTANCE:
                        updateDriveDistance();
                        break;
                    case PATH:
                        if (pathFollower != null)
                        {
                            updatePath();
                            if (kTuning)  // TODO Remake log each path to print headers each time
                            {
                                log.add(pathFollower.getDebug());
                            }
                        }
                        break;
                    default:
                        System.out.println("Drive Updater: Bad control mode");
                        break;
                }
            }
        }

        public void onStopUpdates()
        {
            leftMaster.neutralOutput();  // Bypass lazy talon
            rightMaster.neutralOutput();
            log.flush();
        }
    };

    private static final boolean kBrakeOpenLoop, kClosedLoopEnabled, kTuning;
    private static final double kDeadbandOpenLoop;
    private static final int kSlotTurnControl, kSlotVelocityControl;
    private static final Parameters kPathConfig;

    private static TankSubsystem instance = new TankSubsystem();
    private final RobotStateBase state = EnhancedRobot.getState();
    private final ReflectingCSVWriter<PathFollower.DebugOutput> log = new ReflectingCSVWriter<PathFollower.DebugOutput>(PathFollower.DebugOutput.class);  // or "/home/lvuser/PATH-FOLLOWER-LOGS.csv",
    private final SteeringController steeringDriveDistance;
    private PathFollower pathFollower;
    private CachedIO cachedIO = new CachedIO();

    private final TalonSRX leftMaster, rightMaster;
    private final ArrayList<BaseMotorController> followers = new ArrayList<BaseMotorController>();
    private final Gyro gyro;

    private TANK_MODE controlMode = TANK_MODE.OPEN_LOOP;
    private double targetDistance, targetHeading, targetDistanceL, targetDistanceR = 0.0;
    private boolean brakeModeEnabled;

    static
    {
        final TankConfig config = EnhancedRobot.getConfig().getTankConfig();
        kBrakeOpenLoop = config.OPEN_LOOP_BRAKE;
        kClosedLoopEnabled = config.CLOSED_LOOP_ENABLED;
        kDeadbandOpenLoop = config.OPEN_LOOP_DEADBAND;
        kSlotVelocityControl = config.CLOSED_LOOP.gainsByName("Velocity").kSlot;
        kSlotTurnControl = config.CLOSED_LOOP.gainsByName("Turn").kSlot;
        kTuning = config.TUNING;
        kPathConfig = config.getPathConfig();   
    }
    
    private TankSubsystem()
    {
        final TankConfig config = EnhancedRobot.getConfig().getTankConfig();
        final List<TalonSRX> masters = TalonFactory.makeClosedLoopTalon(config.CLOSED_LOOP);

        for (TalonSRX master : masters)
        {
            master.setControlFramePeriod(ControlFrame.Control_3_General, 5);
            master.configOpenloopRamp(config.OPEN_LOOP_RAMP, 100);
            master.configNeutralDeadband(kDeadbandOpenLoop, 100);
            master.configPeakOutputForward(config.MAX_PERCENT_OUT);
            master.configPeakOutputReverse(-config.MAX_PERCENT_OUT);

            if (config.CLOSED_LOOP_ENABLED)
            {
                master.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 5, 100);
                master.configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_50Ms, 100);
                master.configVelocityMeasurementWindow(1, 100);
                TalonFactory.configFastErrorReporting(master, kTuning);
            }
            else
            {
                TalonFactory.configOpenLoopOnly(master);
            }
        }
        leftMaster = masters.get(0);
        rightMaster = masters.get(1);

        for (BaseMotorController follower : TalonFactory.makeFollowers(leftMaster, config.CLOSED_LOOP.masters.get(0)))
        {
            followers.add(follower);
        }
        for (BaseMotorController follower : TalonFactory.makeFollowers(rightMaster, config.CLOSED_LOOP.masters.get(1)))
        {
            followers.add(follower);
        }

        gyro = config.getGyroHardware();
        if (gyro instanceof GyroPigeon)
        {
            rightMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_11_UartGadgeteer, 10, 100);
        }
        steeringDriveDistance = new SteeringController(config.STEERING_DRIVE_DISTANCE_P, config.STEERING_DRIVE_DISTANCE_I);
        if (config.CLOSED_LOOP_ENABLED)
        {
            BackgroundUpdater.getInstance().register(updater);
        }

        zeroEncoders();
        brakeModeEnabled = true;  // Opposite to force initial setBrake() to succeed
        setBrake(false);
        setGyro(0.0);
    }

    public static TankSubsystem getInstance()
    {
        return instance;
    }

    protected void initDefaultCommand()
    {
        setDefaultCommand(EnhancedRobot.getConfig().getTankConfig().getTeleopCommand());
    }

    public void cacheSensors(boolean isDisabled)
    {
        if (kClosedLoopEnabled && !isDisabled)
        {
            cachedIO.positionTicksL = leftMaster.getSelectedSensorPosition();
            cachedIO.positionTicksR = rightMaster.getSelectedSensorPosition();
            cachedIO.velocityTicksPer100msL = leftMaster.getSelectedSensorVelocity();
            cachedIO.velocityTicksPer100msR = rightMaster.getSelectedSensorVelocity();
        }
        cachedIO.headingDegrees = gyro.getAngle();
        cachedIO.pitchDegrees = gyro.getPitch();
    }

    public void reportState()
    {
        SmartDashboard.putString("Drive", getCurrentCommandName());
        SmartDashboard.putNumber("Heading", cachedIO.headingDegrees);
        SmartDashboard.putNumber("Pitch", cachedIO.pitchDegrees);
        if (kTuning)
        {
            SmartDashboard.putNumber("Drive Position Native L", cachedIO.positionTicksL);
            SmartDashboard.putNumber("Drive Position Native R", cachedIO.positionTicksR);
            SmartDashboard.putNumber("Drive Velocity Native", cachedIO.velocityTicksPer100msL);
            SmartDashboard.putNumber("Drive Error L", leftMaster.getClosedLoopError());
            SmartDashboard.putNumber("Drive Error R", rightMaster.getClosedLoopError());
            SmartDashboard.putNumber("Drive Raw Speed L", leftMaster.getMotorOutputPercent());
            SmartDashboard.putNumber("Drive Raw Speed R", rightMaster.getMotorOutputPercent());
            log.write();
        }
        if (kClosedLoopEnabled || kTuning)
        {
            final Pose2d odometry = state.getLatestFieldToVehicle().getValue();
            SmartDashboard.putNumber("Robot Pose X", odometry.getTranslation().x());
            SmartDashboard.putNumber("Robot Pose Y", odometry.getTranslation().y());
            SmartDashboard.putNumber("Robot Pose Theta", odometry.getRotation().getDegrees());
            SmartDashboard.putNumber("Robot Velocity", state.getMeasuredVelocity().dx);
            SmartDashboard.putNumber("Distance Driven", state.getDistanceDriven());
        }
    }

    public synchronized void driveOpenLoop(double left, double right)
    {
        controlMode = TANK_MODE.OPEN_LOOP;
        leftMaster.set(ControlMode.PercentOutput, left);
        rightMaster.set(ControlMode.PercentOutput, right);
    }

    public synchronized void turnToHeading(double targetHeading)
    {
        setPIDSlot(kSlotTurnControl);
        this.targetHeading = targetHeading;
        controlMode = TANK_MODE.TURN;
        setTurnSetpoint(targetHeading);  // Extra cycle
    }

    public synchronized void driveDistance(double inchesForward)
    {
        setPIDSlot(kSlotTurnControl);
        targetDistance = inchesForward;
        targetHeading = getGryo();
        steeringDriveDistance.reset(Timer.getFPGATimestamp());
        targetDistanceL = targetDistance + getDistanceInchesL();
        targetDistanceR = targetDistance + getDistanceInchesR();
        state.resetDistanceDriven();
        controlMode = TANK_MODE.DISTANCE;
        updateDriveDistance();  // Extra cycle
    }

    public synchronized void drivePath(Path path, boolean reversed)
    {
        state.resetDistanceDriven();
        pathFollower = new PathFollower(path, reversed, kPathConfig);
        setPIDSlot(kSlotVelocityControl);
        controlMode = TANK_MODE.PATH;
    }

    public synchronized void setBrake(boolean enable)
    {
        if (brakeModeEnabled != enable)
        {
            final NeutralMode mode = (enable) ? NeutralMode.Brake : NeutralMode.Coast;
            leftMaster.setNeutralMode(mode);
            rightMaster.setNeutralMode(mode);
            for (BaseMotorController follower : followers)
            {
                follower.setNeutralMode(mode);
            }
            brakeModeEnabled = enable;
        }
    }

    public synchronized void setGyro(double angle)
    {
        gyro.zeroYaw(angle);
        cachedIO.headingDegrees = angle;
    }

    /** Changes the rate the robot can accelerate. Useful to prevent tipping. */
    public synchronized void setOpenLoopRampRate(double secondsNeutralToFullThrottle)
    {
        leftMaster.configOpenloopRamp(secondsNeutralToFullThrottle);
        rightMaster.configOpenloopRamp(secondsNeutralToFullThrottle);
        for (BaseMotorController follower : followers)
        {
            follower.configOpenloopRamp(secondsNeutralToFullThrottle);
        }
    }

    public synchronized void zeroEncoders()
    {
        leftMaster.setSelectedSensorPosition(0);
        rightMaster.setSelectedSensorPosition(0);
        cachedIO = new CachedIO();
    }

    public double getDistanceInchesL()
    {
        return TankMaths.ticksToInches(cachedIO.positionTicksL);
    }

    public double getDistanceInchesR()
    {
        return TankMaths.ticksToInches(cachedIO.positionTicksR);
    }

    public double getGryo()
    {
        return cachedIO.headingDegrees;
    }

    public double getGyroPitch()
    {
        return cachedIO.pitchDegrees;
    }
    public void zeroGyroPitch()
    {
        gyro.zeroPitch();;
    }

    public double getVeloctiyInchesPerSecondL()
    {
        return TankMaths.ticksToInchesPerSecond(cachedIO.velocityTicksPer100msL);
    }

    public double getVeloctiyInchesPerSecondR()
    {
        return TankMaths.ticksToInchesPerSecond(cachedIO.velocityTicksPer100msR);
    }

    public boolean isGyroOk()
    {
        return gyro.isOk();
    }

    public synchronized boolean onTarget()
    {
        return controlMode == TANK_MODE.LOCK_SETPOINT;
    }

    private void updateTurnToHeading()
    {
        final double heading = getGryo();
        final boolean closeEnough = Math.abs(heading - targetHeading) < 2.5;
        final boolean slowEnough = Math.abs(getVeloctiyInchesPerSecondL()) < 5.0 && Math.abs(getVeloctiyInchesPerSecondR()) < 5.0;

        setTurnSetpoint((closeEnough && slowEnough) ? heading : targetHeading);
        if (closeEnough && slowEnough)
        {
            controlMode = TANK_MODE.LOCK_SETPOINT;
        }
    }

    private void updateDriveDistance()
    {
        final double now = Timer.getFPGATimestamp();
        final boolean doneSteering = Math.abs(state.getDistanceDriven() - targetDistance) < kPathConfig.stop_steering_distance;
        final double adjust = steeringDriveDistance.correctHeading(now, targetHeading, getGryo(), doneSteering);

        leftMaster.set(ControlMode.MotionMagic, targetDistanceL, DemandType.ArbitraryFeedForward, adjust);
        rightMaster.set(ControlMode.MotionMagic, targetDistanceR, DemandType.ArbitraryFeedForward, -adjust);
    }

    private void updatePath()
    {
        final double now = Timer.getFPGATimestamp();
        final Twist2d command = pathFollower.update(now, state.getLatestFieldToVehicle().getValue(), state.getDistanceDriven(), state.getPredictedVelocity().dx);

        if (!pathFollower.isFinished())
        {
            final Kinematics.DriveVelocity setpoint = Kinematics.inverseKinematics(command);  // Inches/s
            setVelocitySetpoint(setpoint.left, setpoint.right, 0.0, 0.0);
        }
        else
        {
            setVelocitySetpoint(0.0, 0.0, 0.0, 0.0);
            controlMode = TANK_MODE.LOCK_SETPOINT;
        }
    }

    private void setTurnSetpoint(double desiredHeading)
    {
        final double relativeAngleOption0 = desiredHeading - getGryo();
        final double invert = -Math.signum(relativeAngleOption0);
        final double relativeAngleOption1 = relativeAngleOption0 + (invert * 360);
        final double relativeDegrees = (Math.abs(relativeAngleOption0) <= Math.abs(relativeAngleOption1)) ? relativeAngleOption0 : relativeAngleOption1;
        final double radialInches = TankMaths.inchesOfWheelTurning(relativeDegrees);
        final double radialTicks = TankMaths.inchesToTicks(radialInches);

        leftMaster.set(ControlMode.MotionMagic, radialTicks + cachedIO.positionTicksL);
        rightMaster.set(ControlMode.MotionMagic, -radialTicks + cachedIO.positionTicksR);
    }

    private void setPIDSlot(int slot)
    {
        leftMaster.selectProfileSlot(slot, 0);
        rightMaster.selectProfileSlot(slot, 0);
    }

    private void setVelocitySetpoint(double inchesPerSecondL, double inchesPerSecondR, double feedforwardL, double feedforwardR)
    {
        final double nativeL = TankMaths.inchesPerSecondToTicks(inchesPerSecondL);
        final double nativeR = TankMaths.inchesPerSecondToTicks(inchesPerSecondR);
        final double max_desired = Math.max(Math.abs(nativeL), Math.abs(nativeR));
        final double scale = max_desired > TankMaths.kVelocityMax ? TankMaths.kVelocityMax / max_desired : 1.0;

        //        System.out.format("%7.2f, %7.2f\n", nativeL, nativeR);
        leftMaster.set(ControlMode.Velocity, nativeL * scale);
        rightMaster.set(ControlMode.Velocity, nativeR * scale);
    }

    public TestReport runFunctionalTest()
    {
        final TankConfig config = EnhancedRobot.getConfig().getTankConfig();
        TestReport report = new TestReport();

        report.add(getDefaultCommand().doesRequire(this), "Default command requires Subsystem");
        report.add(TalonChecker.checkFirmware(leftMaster));
        report.add(TalonChecker.checkFirmware(rightMaster));
        for (BaseMotorController follower : followers)
        {
            report.add(TalonChecker.checkFirmware(follower));
        }
        report.add(TalonChecker.checkClosedLoopConfig(config.CLOSED_LOOP));
        report.add(TalonChecker.checkEncoder(leftMaster));
        report.add(TalonChecker.checkEncoder(rightMaster));
        report.add(TalonChecker.checkFrameRates(leftMaster));
        report.add(TalonChecker.checkFrameRates(rightMaster));
        report.add(TalonChecker.checkSensorPhase(0.20, leftMaster, rightMaster));
        report.add(gyro.isOk(), "Gyro connected");
        report.add(config.CLOSED_LOOP.gains.size() == 2, "Num Gains");
        for (Gains g : config.CLOSED_LOOP.gains)
        {
            System.out.println(g);
        }

        return report;
    }

    private class CachedIO
    {
        public int positionTicksL;
        public int positionTicksR;
        public int velocityTicksPer100msL;
        public int velocityTicksPer100msR;
        public double headingDegrees;
        public double pitchDegrees;    
    }
}