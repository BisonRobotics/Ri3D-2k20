package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;


public class IntakeNone extends CommandBase
{
    private final IntakeSubsystem intake = IntakeSubsystem.getInstance();

    public IntakeNone()
    {
        addRequirements(intake);
    }

    public void execute() {

    }

    public boolean isFinished() {
        return false;
    }

    protected void end()
    {

    }
}
