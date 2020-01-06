package org.wfrobotics.robot.commands.intake;

import org.wfrobotics.robot.subsystems.IntakeSubsystem;

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
