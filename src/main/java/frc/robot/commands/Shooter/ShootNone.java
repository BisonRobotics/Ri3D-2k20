package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;


public class ShootNone extends CommandBase
{
    private final ShooterSubsystem shooter = ShooterSubsystem.getInstance();

    public ShootNone()
    {
        addRequirements(shooter);
    }

    public void execute() {
        shooter.setBeltSpeed(0);
        shooter.setFlyWheelSpeed(0);
    }

    public boolean isFinished() {
        return false;
    }

    protected void end()
    {
        
    }
}
