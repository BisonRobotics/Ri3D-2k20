package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LEDSubsystem;

public class LedNone extends CommandBase
{
    private final LEDSubsystem led = LEDSubsystem.getInstance();

    public LedNone()
    {
        addRequirements(led);
    }

    public void execute() {
        led.changeColor();
    }

    public boolean isFinished() {
        return false;
    }

    protected void end()
    {
        
    }
}
