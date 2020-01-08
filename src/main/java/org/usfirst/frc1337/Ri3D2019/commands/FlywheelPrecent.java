package org.usfirst.frc1337.Ri3D2019.commands;

import org.usfirst.frc1337.Ri3D2019.Robot;

import edu.wpi.first.wpilibj.command.Command;


public class FlywheelPrecent extends Command
{
    double speed;
    public FlywheelPrecent(double precentSpeed)
    {
        requires(Robot.shooter);
        this.speed = precentSpeed;
    }
    protected void init()
    {
        Robot.shooter.setFlyWheelSpeed(speed);
    }

    public void execute() {
        Robot.shooter.setFlyWheelSpeed(speed);
    }

    public boolean isFinished() {
        // Robot.shooter.setFlyWheelSpeed(0);
        return false;
    }

    protected void end()
    {
        Robot.shooter.setFlyWheelSpeed(0);
    }
}
