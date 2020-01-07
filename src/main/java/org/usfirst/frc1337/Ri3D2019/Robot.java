package org.usfirst.frc1337.Ri3D2019;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import org.usfirst.frc1337.Ri3D2019.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in 
 * the project.
 */
public class Robot extends TimedRobot {

    Command autonomousCommand;
    SendableChooser<Command> chooser = new SendableChooser<>();

    public static OI oi;

    public static Drivetrain drivetrain;
    public static WoF spinner;
    public static Intake intake;
    public static ShooterSubsystem shooter;
    public static ClimbSubsystem climb;
    public static LEDSubsystem led;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {

        drivetrain = new Drivetrain();
        spinner = new WoF();
        intake = new Intake();
        shooter = new ShooterSubsystem();
        climb = new ClimbSubsystem();
        led = new LEDSubsystem();

        oi = new OI();

        // SmartDashboard.putData("Auto mode", chooser);
        // SmartDashboard.putData("Drive motor test", new Driving());
        // SmartDashboard.putBoolean("Invert Control Direction", false);
        // SmartDashboard.putNumber("Speed (0 to 1 range)", 0);
        // SmartDashboard.putBoolean("Invert Control Direction", false);
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    @Override
    public void disabledInit(){

    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
        autonomousCommand = chooser.getSelected();
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    @Override
    public void robotPeriodic() {
        //MotorSafety.checkMotors();
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
}
