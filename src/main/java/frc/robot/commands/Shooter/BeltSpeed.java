package frc.robot.commands.Shooter;

import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class BeltSpeed extends Command
{
    private final ShooterSubsystem shooter = ShooterSubsystem.getInstance();
    double speed;
    public BeltSpeed(double precentSpeed)
    {
        requires(shooter);
        this.speed = precentSpeed;
    }
    protected void init()
    {
        shooter.setBeltSpeed(speed);
    }

    protected void execute()
    {

    }

    protected boolean isFinished()
    {
        shooter.setBeltSpeed(0);
        return false;
    }

    protected void end()
    {
        shooter.setBeltSpeed(0);

    }
}
