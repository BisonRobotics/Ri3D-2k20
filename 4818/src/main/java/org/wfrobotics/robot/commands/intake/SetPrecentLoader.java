package org.wfrobotics.robot.commands.intake;

import org.wfrobotics.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class SetPrecentLoader extends Command
{
    private final IntakeSubsystem intake = IntakeSubsystem.getInstance();
    double speed;
    public SetPrecentLoader(double precentSpeed)
    {
        requires(intake);
        this.speed = precentSpeed;
    }
    public void init()
    {
        intake.setloaderSpeed(speed);
    }

    protected void execute()
    {

    }

    protected boolean isFinished()
    {
        return false;
    }

    protected void end()
    {

    }
}
