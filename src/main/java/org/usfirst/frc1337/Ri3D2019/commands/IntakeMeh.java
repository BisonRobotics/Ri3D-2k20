package org.usfirst.frc1337.Ri3D2019.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc1337.Ri3D2019.Robot;

public class IntakeMeh extends Command {

    private int Intake;
    private int direction;

    public IntakeMeh(int intake, int direction) {
        requires(Robot.intake);
        this.Intake = intake;
        this.direction = direction;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        if(direction == 0){Robot.intake.intake(Intake);}
        
        if(direction == 1){Robot.intake.outtake(Intake);}
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.intake.shutdown();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        Robot.intake.shutdown();
    }
}
