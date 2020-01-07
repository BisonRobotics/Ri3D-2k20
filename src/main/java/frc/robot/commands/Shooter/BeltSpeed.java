package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class BeltSpeed extends CommandBase
{
    private final ShooterSubsystem shooter = ShooterSubsystem.getInstance();
    double speed;
    public BeltSpeed(double precentSpeed)
    {
        addRequirements(shooter);
        this.speed = precentSpeed;
    }
    protected void init()
    {
        shooter.setBeltSpeed(speed);
    }

    public void execute() {

    }

    public boolean isFinished() {
        shooter.setBeltSpeed(0);
        return false;
    }

    protected void end()
    {
        shooter.setBeltSpeed(0);

    }
}
