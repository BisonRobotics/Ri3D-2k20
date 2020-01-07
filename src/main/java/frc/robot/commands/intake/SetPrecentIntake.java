package frc.robot.commands.intake;

import frc.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class SetPrecentIntake extends Command
{
    private final IntakeSubsystem intake = IntakeSubsystem.getInstance();
    double speed;
    public SetPrecentIntake(double precentSpeed)
    {
        requires(intake);
        this.speed = precentSpeed;
    }
    public void init()
    {
        intake.setIntakeSpeed(speed);
    }

    protected void execute()
    {

    }

    protected boolean isFinished()
    {
        intake.setIntakeSpeed(0);
        return false;
    }

    protected void end()
    {
        intake.setIntakeSpeed(0);

    }
}
