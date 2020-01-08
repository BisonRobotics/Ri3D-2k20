package org.usfirst.frc1337.Ri3D2019.commands;

import org.usfirst.frc1337.Ri3D2019.Robot;

import edu.wpi.first.wpilibj.command.Command;


public class SetPrecentIntake extends Command
{
    double speed;
    public SetPrecentIntake(double precentSpeed)
    {
        requires(Robot.intake);
        this.speed = precentSpeed;
    }
    public void init()
    {
        Robot.intake.setIntakeSpeed(speed);
        Robot.intake.setIndexSpeed(speed);
    }

    public void execute() {
        Robot.intake.setIntakeSpeed(speed);
    }

    public boolean isFinished() {
        // Robot.intake.setIntakeSpeed(0);
        return false;
    }

    protected void end()
    {
        Robot.intake.setIntakeSpeed(0);

    }
}
