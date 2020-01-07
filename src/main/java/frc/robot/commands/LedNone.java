package org.wfrobotics.robot.commands;

import org.wfrobotics.robot.subsystems.LEDSubsystem;
import org.wfrobotics.robot.subsystems.WofFSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class LedNone extends Command
{
    private final LEDSubsystem led = LEDSubsystem.getInstance();

    public LedNone()
    {
        requires(led);
    }

    protected void execute()
    {
      led.changeColor();
    }

    protected boolean isFinished()
    {
        return false;
    }

    protected void end()
    {
        
    }
}
