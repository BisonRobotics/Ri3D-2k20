package frc.robot.commands.climb;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimbSubsystem;

public class ClimbGoseUp extends CommandBase
{
    private final ClimbSubsystem climb = ClimbSubsystem.getInstance();
    double speed;
    public ClimbGoseUp(double percentSpeed)
    {
        addRequirements(climb);
    }


    public void execute() {
        climb.setPrecentSpeedUp(speed);
    }

    public boolean isFinished() {
        return false;
    }

    protected void end()
    {
        climb.setPrecentSpeedUp(0);
    }
}
