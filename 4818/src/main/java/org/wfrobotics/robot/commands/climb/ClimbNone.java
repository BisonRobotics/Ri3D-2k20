package org.wfrobotics.robot.commands.climb;

import org.wfrobotics.robot.subsystems.ClimbSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class ClimbNone extends Command
{
    private final ClimbSubsystem climb = ClimbSubsystem.getInstance();

    public ClimbNone()
    {
        requires(climb);
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
