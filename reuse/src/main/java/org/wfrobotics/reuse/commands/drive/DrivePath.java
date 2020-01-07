package frc.reuse.commands.drive;

import frc.reuse.config.PathContainer;
import frc.reuse.subsystems.drive.Path;
import frc.reuse.subsystems.drive.TankSubsystem;
import frc.reuse.config.EnhancedIO;
import frc.reuse.EnhancedRobot;

import edu.wpi.first.wpilibj.command.Command;

/** Drive a smooth path connecting waypoints on the field */
public final class DrivePath extends Command
{
    private final TankSubsystem drive = TankSubsystem.getInstance();
    private final EnhancedIO io = EnhancedRobot.getIO();
    private final PathContainer container;
    private final Path path;

    public DrivePath(PathContainer path)
    {
        requires(drive);
        container = path;
        this.path = container.buildPath();
    }

    // TODO Second constructor to replace DriveDistance

    protected void initialize()
    {
        drive.setBrake(true);
        drive.drivePath(path, container.isReversed());
    }

    protected boolean isFinished()
    {
        return drive.onTarget() || io.isDriveOverrideRequested();
    }

    protected void interrupted()
    {
        System.out.println("Path Interrupted");
        drive.driveOpenLoop(0.0, 0.0);
    }
}
