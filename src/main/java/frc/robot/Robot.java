package frc.robot;

import frc.reuse.PrototypeEnhancedRobot;
import frc.robot.config.IO;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.WofFSubsystem;


/**
 * Robot: <TBD Robot Name> - 2019
 * @author Team 4818 The Herd<p>STEM Alliance of Fargo Moorhead
 * */
public final class Robot extends PrototypeEnhancedRobot
{
    public Robot()
    {
        super(RobotState.getInstance(), IO.getInstance());
    }

    protected void registerRobotSpecific()
    {
        subsystems.register(ClimbSubsystem.getInstance());
        subsystems.register(IntakeSubsystem.getInstance());
        subsystems.register(ShooterSubsystem.getInstance());
        subsystems.register(WofFSubsystem.getInstance());
        subsystems.register(DrivetrainSubsystem.getInstance());
        subsystems.register(LEDSubsystem.getInstance());

    }

    @Override
    public void autonomousInit()
    {
        
    }
}
