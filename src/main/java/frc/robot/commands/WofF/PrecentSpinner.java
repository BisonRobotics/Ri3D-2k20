package frc.robot.commands.WofF;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.WofFSubsystem;


public class PrecentSpinner extends CommandBase
{
    private final WofFSubsystem WofF = WofFSubsystem.getInstance();
    double speed;
    public PrecentSpinner(double precentSpeed)
    {
        addRequirements(WofF);
        this.speed = precentSpeed;
    }
    

    protected void init()
    {
        WofF.setSpinner(speed);

    }
    
    public void execute() {
    }

    public boolean isFinished() {
        WofF.setSpinner(0);

        return false;
    }

    protected void end()
    {
        WofF.setSpinner(0);

    }
}
