package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;


public class SetPrecentLoader extends CommandBase
{
    private final IntakeSubsystem intake = IntakeSubsystem.getInstance();
    double speed;
    public SetPrecentLoader(double precentSpeed)
    {
        addRequirements(intake);
        this.speed = precentSpeed;
    }
    public void init()
    {
        intake.setloaderSpeed(speed);
    }

    public void execute() {

    }

    public boolean isFinished() {
        intake.setloaderSpeed(0);
        return false;
    }

    protected void end()
    {
        intake.setloaderSpeed(0);
    }
}
