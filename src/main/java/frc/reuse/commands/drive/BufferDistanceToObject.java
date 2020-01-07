package org.wfrobotics.reuse.commands.drive;

import java.util.function.DoubleSupplier;

import org.wfrobotics.reuse.EnhancedRobot;
import org.wfrobotics.reuse.config.EnhancedIO;
import org.wfrobotics.reuse.subsystems.drive.TankSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/** Use distance sensor to drive to a fixed <b>distance away from an object</b> */
public class BufferDistanceToObject extends Command
{
    private final double target;
    private final double tol;
    private final double max;

    private final TankSubsystem drive = TankSubsystem.getInstance();
    protected final EnhancedIO io = EnhancedRobot.getIO();
    private final DoubleSupplier sensor;

    public BufferDistanceToObject(DoubleSupplier sensor, double targetDistanceAway, double maxDistance, double tol, double timeout)
    {
        requires(drive);
        this.sensor = sensor;
        target = targetDistanceAway;
        this.tol = tol;
        max = maxDistance;
        setTimeout(timeout);
    }

    protected void initialize()
    {
        drive.driveDistance(remaining());
    }

    protected void execute()
    {
        drive.driveDistance(remaining());
    }

    protected boolean isFinished()
    {
        return Math.abs(remaining()) < tol || isTimedOut() || io.isDriveOverrideRequested();
    }

    private double remaining()
    {
        return Math.min(max, sensor.getAsDouble() - target);
    }
}
