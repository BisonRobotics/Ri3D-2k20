package frc.robot.commands.intake;

import frc.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeNone extends Command
{
    private final IntakeSubsystem intake = IntakeSubsystem.getInstance();

    public IntakeNone()
    {
        requires(intake);
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
