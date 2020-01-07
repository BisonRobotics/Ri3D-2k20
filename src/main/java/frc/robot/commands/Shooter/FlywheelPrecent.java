package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;


public class FlywheelPrecent extends CommandBase
{
    private final ShooterSubsystem shooter = ShooterSubsystem.getInstance();
    double speed;
    public FlywheelPrecent(double precentSpeed)
    {
        addRequirements(shooter);
        this.speed = precentSpeed;
    }
    protected void init()
    {
        shooter.setFlyWheelSpeed(speed);
    }

    public void execute() {

    }

    public boolean isFinished() {
        shooter.setFlyWheelSpeed(0);
        return false;
    }

    protected void end()
    {
        shooter.setFlyWheelSpeed(0);
    }
}
