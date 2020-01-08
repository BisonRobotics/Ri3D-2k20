package org.usfirst.frc1337.Ri3D2019.commands;

import org.usfirst.frc1337.Ri3D2019.Robot;

import edu.wpi.first.wpilibj.command.Command;


public class SetPercentDeploy extends Command
{
    double speed;
    public SetPercentDeploy(double precentSpeed)
    {
        requires(Robot.intake);
        this.speed = precentSpeed;
    }
    public void init()
    {
    }

    public void execute() {
        Robot.intake.setWristSpeed(speed);

    }


    public boolean isFinished() {
        return false;
    }

    protected void end()
    {
        Robot.intake.setWristSpeed(0);;
    }
}
