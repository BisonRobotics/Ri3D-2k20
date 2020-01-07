package frc.robot.commands.climb;

import frc.robot.subsystems.ClimbSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class ClimbGoseUp extends Command
{
    private final ClimbSubsystem climb = ClimbSubsystem.getInstance();
    double speed;
    public ClimbGoseUp(double percentSpeed)
    {
        requires(climb);
    }


    protected void execute()
    {
        climb.setPrecentSpeedUp(speed);
    }

    protected boolean isFinished()
    {
        return false;
    }

    protected void end()
    {
        climb.setPrecentSpeedUp(0);
    }
}
