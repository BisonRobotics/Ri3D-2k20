package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;


public class SetPercentDeploy extends CommandBase
{
    private final IntakeSubsystem intake = IntakeSubsystem.getInstance();
    double speed;
    public SetPercentDeploy(double precentSpeed)
    {
        addRequirements(intake);
        this.speed = precentSpeed;
    }
    public void init()
    {
        intake.setPercentDeploy(speed);
    }

    public void execute() {

    }

    public boolean isFinished() {
        intake.setloaderSpeed(0);
        return false;
    }

    protected void end()
    {
        intake.setPercentDeploy(0);
    }
}
