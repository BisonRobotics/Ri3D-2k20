package org.usfirst.frc1337.Ri3D2019.commands;


import org.usfirst.frc1337.Ri3D2019.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Retract extends Command
{
    double speed;
    public Retract(double precentSpeed)
    {
        requires(Robot.climb);
        this.speed = precentSpeed;
    }
    protected void init()
    {
        Robot.climb.setPrecentSpeedUp(speed);;    
    }

    public void execute() {

    }

    public boolean isFinished() {
        Robot.climb.setPrecentSpeedUp(0);;    
        return false;
    }

    protected void end()
    {
        Robot.climb.setPrecentSpeedUp(0);;    

    }
}
