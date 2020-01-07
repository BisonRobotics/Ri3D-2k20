package frc.reuse.commands.drive;

import frc.reuse.subsystems.drive.TankSubsystem;
import frc.reuse.EnhancedRobot;
import frc.reuse.RobotStateBase;
import frc.reuse.config.EnhancedIO;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/** Turn robot to angle **/
public class TurnToHeading extends Command
{
    private final boolean kOpenLoopBrake;

    protected final RobotStateBase state = EnhancedRobot.getState();
    protected final TankSubsystem drive = TankSubsystem.getInstance();
    protected final EnhancedIO io = EnhancedRobot.getIO();

    protected double heading;
    protected boolean gyroOk;

    public TurnToHeading(double headingFieldRelative)
    {
        requires(drive);
        kOpenLoopBrake = EnhancedRobot.getConfig().getTankConfig().OPEN_LOOP_BRAKE;
        heading = headingFieldRelative;
    }

    protected void initialize()
    {
        gyroOk = drive.isGyroOk();  // TODO Move to AutoMode, cancel parent group method?
        if (gyroOk)
        {
            drive.setBrake(true);
            drive.turnToHeading(heading);  // Extra robot iteration of progress
        }
    }

    protected boolean isFinished()
    {
        return !gyroOk || drive.onTarget() || io.isDriveOverrideRequested();
    }

    protected void end()
    {
        final boolean inTeleop = DriverStation.getInstance().isOperatorControl();
        if (inTeleop)
        {
            drive.setBrake(kOpenLoopBrake);
        }
    }
}