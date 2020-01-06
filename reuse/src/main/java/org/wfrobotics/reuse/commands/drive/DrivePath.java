package org.wfrobotics.reuse.commands.drive;

import org.wfrobotics.reuse.config.PathContainer;
import org.wfrobotics.reuse.subsystems.drive.Path;
import org.wfrobotics.reuse.subsystems.drive.TankSubsystem;
import org.wfrobotics.reuse.config.EnhancedIO;
import org.wfrobotics.reuse.EnhancedRobot;

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
