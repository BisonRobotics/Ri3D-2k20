package frc.robot.commands.intake;

import frc.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class SetPrecentLoader extends Command
{
    private final IntakeSubsystem intake = IntakeSubsystem.getInstance();
    double speed;
    public SetPrecentLoader(double precentSpeed)
    {
        requires(intake);
        this.speed = precentSpeed;
    }
    public void init()
    {
        intake.setloaderSpeed(speed);
    }

    protected void execute()
    {

    }

    protected boolean isFinished()
    {
        intake.setloaderSpeed(0);
        return false;
    }

    protected void end()
    {
        intake.setloaderSpeed(0);
    }
}
