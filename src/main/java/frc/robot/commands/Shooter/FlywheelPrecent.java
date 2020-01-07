package org.wfrobotics.robot.commands.Shooter;

import org.wfrobotics.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class FlywheelPrecent extends Command
{
    private final ShooterSubsystem shooter = ShooterSubsystem.getInstance();
    double speed;
    public FlywheelPrecent(double precentSpeed)
    {
        requires(shooter);
        this.speed = precentSpeed;
    }
    protected void init()
    {
        shooter.setFlyWheelSpeed(speed);
    }

    protected void execute()
    {

    }

    protected boolean isFinished()
    {
        shooter.setFlyWheelSpeed(0);
        return false;
    }

    protected void end()
    {
        shooter.setFlyWheelSpeed(0);
    }
}
