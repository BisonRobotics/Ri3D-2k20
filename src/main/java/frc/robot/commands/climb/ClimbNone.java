package frc.robot.commands.climb;

import frc.robot.subsystems.ClimbSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ClimbNone extends CommandBase
{
    private final ClimbSubsystem climb = ClimbSubsystem.getInstance();

    public ClimbNone()
    {
        addRequirements(climb);
    }


    public void execute() {

    }

    public boolean isFinished() {
        return false;
    }

    protected void end()
    {
        
    }
}
