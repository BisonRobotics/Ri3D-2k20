package org.wfrobotics.robot;

import org.wfrobotics.reuse.EnhancedRobot;
import org.wfrobotics.reuse.PrototypeEnhancedRobot;
import org.wfrobotics.robot.config.IO;
import org.wfrobotics.robot.subsystems.ClimbSubsystem;
import org.wfrobotics.robot.subsystems.DrivetrainSubsystem;
import org.wfrobotics.robot.subsystems.DrivetrainSubsystem;
import org.wfrobotics.robot.subsystems.IntakeSubsystem;
import org.wfrobotics.robot.subsystems.LEDSubsystem;
import org.wfrobotics.robot.subsystems.ShooterSubsystem;
import org.wfrobotics.robot.subsystems.WofFSubsystem;


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
        // subsystems.register(ClimbSubsystem.getInstance());
        subsystems.register(IntakeSubsystem.getInstance());
        subsystems.register(ShooterSubsystem.getInstance());
        subsystems.register(WofFSubsystem.getInstance());
        // subsystems.register(DrivetrainSubsystem.getInstance());
        subsystems.register(LEDSubsystem.getInstance());

    }

    @Override
    public void autonomousInit()
    {
        
    }
}
