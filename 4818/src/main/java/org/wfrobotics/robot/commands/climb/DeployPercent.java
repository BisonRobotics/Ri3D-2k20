package org.wfrobotics.robot.commands.climb;

import org.wfrobotics.robot.subsystems.ClimbSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class DeployPercent extends Command
{
    private final ClimbSubsystem climb = ClimbSubsystem.getInstance();
    double speed;
    public DeployPercent(double precentSpeed)
    {
        requires(climb);
        this.speed = precentSpeed;
    }
    protected void init()
    {
        climb.setPrecentSpeedUp(speed);
    }

    protected void execute()
    {

    }

    protected boolean isFinished()
    {
        climb.setPrecentSpeedUp(0);
        return false;
    }

    protected void end()
    {
        
    }
}
