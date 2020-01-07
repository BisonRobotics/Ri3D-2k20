package frc.reuse.commands.drive;

import frc.reuse.subsystems.drive.TankSubsystem;
import frc.reuse.EnhancedRobot;
import frc.reuse.config.EnhancedIO;

import edu.wpi.first.wpilibj.command.Command;

/** Bang bang control. Not a command you usually want outside rare, special situations */
public class DriveOpenLoop extends Command
{
    private final TankSubsystem drive;
    private final EnhancedIO io = EnhancedRobot.getIO();
    private final double power;

    public DriveOpenLoop(double percentVoltage)
    {
        drive = TankSubsystem.getInstance();
        requires(drive);
        power = percentVoltage;
    }

    protected void initialize()
    {
        drive.setBrake(true);
    }

    protected void execute()
    {
        drive.driveOpenLoop(power, power);
    }

    protected boolean isFinished()
    {
        return io.isDriveOverrideRequested();
    }
}
