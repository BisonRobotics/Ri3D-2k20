package frc.robot.commands.climb;

import frc.robot.subsystems.ClimbSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class DeployPercent extends CommandBase
{
    private final ClimbSubsystem climb = ClimbSubsystem.getInstance();
    double speed;
    public DeployPercent(double precentSpeed)
    {
        addRequirements(climb);
        this.speed = precentSpeed;
    }
    protected void init()
    {
        climb.setPrecentSpeedUp(speed);
    }

    public void execute() {

    }

    public boolean isFinished() {
        climb.setPrecentSpeedUp(0);
        return false;
    }

    protected void end()
    {
        climb.setPrecentSpeedUp(0);

    }
}
