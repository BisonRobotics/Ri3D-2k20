package frc.robot.commands.WofF;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.WofFSubsystem;


public class WofFNone extends CommandBase
{
    private final WofFSubsystem WofF = WofFSubsystem.getInstance();

    public WofFNone()
    {
        addRequirements(WofF);
    }

    private void addRequirements(WofFSubsystem wofF2) {
    }

    public void execute() {
        WofF.setSpinner(0);
    }

    public boolean isFinished() {
        return false;
    }

    protected void end()
    {
        
    }
}
