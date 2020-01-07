package frc.robot.commands.WofF;

import frc.robot.subsystems.WofFSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class PrecentSpinner extends Command
{
    private final WofFSubsystem WofF = WofFSubsystem.getInstance();
    double speed;
    public PrecentSpinner(double precentSpeed)
    {
        requires(WofF);
        this.speed = precentSpeed;
    }
    
    protected void init( )
    {
        WofF.setSpinner(speed);

    }
    protected void execute()
    {
    }

    protected boolean isFinished()
    {
        WofF.setSpinner(0);

        return false;
    }

    protected void end()
    {
        WofF.setSpinner(0);

    }
}
