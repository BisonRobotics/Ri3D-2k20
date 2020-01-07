package org.usfirst.frc1337.Ri3D2019.commands;


import org.usfirst.frc1337.Ri3D2019.Robot;

import edu.wpi.first.wpilibj.command.Command;

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
        Robot.belt.setBeltSpeed(speed);
    }

    public void execute() {

    }

    public boolean isFinished() {
        Robot.shooter.setBeltSpeed(0);
        return false;
    }

    protected void end()
    {
        Robot.shooter.setBeltSpeed(0);

    }
}
