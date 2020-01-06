package org.wfrobotics.robot.commands.intake;

import org.wfrobotics.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class SetPercentDeploy extends Command
{
    private final IntakeSubsystem intake = IntakeSubsystem.getInstance();
    double speed;
    public SetPercentDeploy(double precentSpeed)
    {
        requires(intake);
        this.speed = precentSpeed;
    }
    public void init()
    {
        intake.setPercentDeploy(speed);
    }

    protected void execute()
    {

    }

    protected boolean isFinished()
    {
        intake.setloaderSpeed(0);
        return false;
    }

    protected void end()
    {
        intake.setPercentDeploy(0);
    }
}
