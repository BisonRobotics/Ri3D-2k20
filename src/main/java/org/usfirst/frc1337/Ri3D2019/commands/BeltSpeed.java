package org.usfirst.frc1337.Ri3D2019.commands;

import java.lang.module.ModuleDescriptor.Requires;

import org.usfirst.frc1337.Ri3D2019.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class BeltSpeed extends Command
{
    double speed;
    public BeltSpeed(double precentSpeed)
    {
        requires(Robot.shooter);
        this.speed = precentSpeed;
    }
    protected void init()
    {
        Robot.shooter.setBeltSpeed(speed);
    }

    public void execute() {
        Robot.shooter.setBeltSpeed(speed);
    }

    public boolean isFinished() {
        // Robot.shooter.setBeltSpeed(0);
        return false;
    }

    protected void end()
    {
        Robot.shooter.setBeltSpeed(0);

    }
}
