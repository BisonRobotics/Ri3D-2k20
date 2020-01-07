package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;


public class SetPrecentIntake extends CommandBase
{
    private final IntakeSubsystem intake = IntakeSubsystem.getInstance();
    double speed;
    public SetPrecentIntake(double precentSpeed)
    {
        addRequirements(intake);
        this.speed = precentSpeed;
    }
    public void init()
    {
        intake.setIntakeSpeed(speed);
    }

    public void execute() {

    }

    public boolean isFinished() {
        intake.setIntakeSpeed(0);
        return false;
    }

    protected void end()
    {
        intake.setIntakeSpeed(0);

    }
}
