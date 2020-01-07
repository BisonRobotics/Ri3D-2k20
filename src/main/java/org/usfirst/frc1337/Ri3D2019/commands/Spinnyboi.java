package org.usfirst.frc1337.Ri3D2019.commands;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc1337.Ri3D2019.Robot;


public class Spinnyboi extends Command {

    private String position;

    // Overloaded constructor to allow for picking position
    public Spinnyboi(String position) {
    requires(Robot.spinner);
    this.position = position;

    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        if(position == "raise"){
            Robot.spinner.raise(.4);
        }
        else{
            Robot.spinner.lower(.4);
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.spinner.shutdown();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        Robot.spinner.shutdown();
    }
}
