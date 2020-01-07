package frc.reuse.commands.drive;

import frc.reuse.subsystems.drive.TankSubsystem;
import frc.reuse.utilities.ConsoleLogger;
import frc.reuse.config.EnhancedIO;
import frc.reuse.EnhancedRobot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/** Safety {@link Command} for {@link TankSubsystem). Toggle or cancel to quit. **/
public class DriveOff extends Command
{
    private final boolean kOpenLoopBrake;

    private final TankSubsystem drive = TankSubsystem.getInstance();
    private final EnhancedIO io = EnhancedRobot.getIO();

    public DriveOff()
    {
        requires(drive);
        kOpenLoopBrake = EnhancedRobot.getConfig().getTankConfig().OPEN_LOOP_BRAKE;
    }

    protected void initialize()
    {
        ConsoleLogger.error("Drive: Off");
        ConsoleLogger.error("Match Time Remaining: " + DriverStation.getInstance().getMatchTime());
        drive.setBrake(true);
    }

    protected void execute()
    {
        drive.driveOpenLoop(0.0, 0.0);
    }

    protected boolean isFinished()
    {
        return io.isDriveOverrideRequested();
    }

    protected void end()  // Applied to 2019 sandstorm
    {
        drive.setBrake(kOpenLoopBrake);
    }
}
