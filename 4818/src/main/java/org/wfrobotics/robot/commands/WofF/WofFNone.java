package org.wfrobotics.robot.commands.WofF;

import org.wfrobotics.robot.subsystems.WofFSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class WofFNone extends Command
{
    private final WofFSubsystem WofF = WofFSubsystem.getInstance();

    public WofFNone()
    {
        requires(WofF);
    }

    protected void execute()
    {
      WofF.setSpinner(0);
    }

    protected boolean isFinished()
    {
        return false;
    }

    protected void end()
    {
        
    }
}
