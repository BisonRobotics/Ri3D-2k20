package org.wfrobotics.robot.commands.Shooter;

import org.wfrobotics.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class ShootNone extends Command
{
    private final IntakeSubsystem intake = IntakeSubsystem.getInstance();

    public ShootNone()
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
