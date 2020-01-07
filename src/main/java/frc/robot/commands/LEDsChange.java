package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LEDSubsystem;

public class LEDsChange extends CommandBase
{
    private final LEDSubsystem led = LEDSubsystem.getInstance();

    public LEDsChange()
    {
        addRequirements(led);
    }

    public void execute() {
        // /led.changeColor();
        led.changeColor1();
    }

    public boolean isFinished() {
      end();
      return true;
    }

    protected void end()
    {
        led.changeColor2();
    }
}
