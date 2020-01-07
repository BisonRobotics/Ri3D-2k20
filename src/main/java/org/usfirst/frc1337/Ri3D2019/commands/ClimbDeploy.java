package org.usfirst.frc1337.Ri3D2019.commands;


import org.usfirst.frc1337.Ri3D2019.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClimbDeploy extends Command
{
    double speed;
    public ClimbDeploy(double precentSpeed)
    {
        requires(Robot.climb);
        this.speed = precentSpeed;
    }
    protected void init()
    {
        Robot.climb.setPrecentDeploy(speed);    
    }

    public void execute() {
        Robot.climb.setPrecentDeploy(speed);
    }

    public boolean isFinished() {
        // Robot.climb.setPrecentDeploy(0);    
        return false;
    }

    protected void end()
    {
        Robot.climb.setPrecentDeploy(0);    

    }
}
