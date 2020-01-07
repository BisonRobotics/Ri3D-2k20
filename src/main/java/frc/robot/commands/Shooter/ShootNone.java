package org.wfrobotics.robot.commands.Shooter;

import org.wfrobotics.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class ShootNone extends Command
{
    private final ShooterSubsystem shooter = ShooterSubsystem.getInstance();

    public ShootNone()
    {
        requires(shooter);
    }

    protected void execute()
    {
        shooter.setBeltSpeed(0);
        shooter.setFlyWheelSpeed(0);
    }

    protected boolean isFinished()
    {
        return false;
    }

    protected void end()
    {
        
    }
}
