package org.wfrobotics.reuse.commands.drive;

import org.wfrobotics.reuse.subsystems.drive.TankSubsystem;
import org.wfrobotics.reuse.EnhancedRobot;
import org.wfrobotics.reuse.RobotStateBase;
import org.wfrobotics.reuse.config.EnhancedIO;

import edu.wpi.first.wpilibj.command.Command;

/** Drive robot forwards/reverse a distance */
public class DriveDistance extends Command
{
    protected final RobotStateBase state = EnhancedRobot.getState();
    protected final TankSubsystem drive = TankSubsystem.getInstance();
    protected final EnhancedIO io = EnhancedRobot.getIO();

    private double settledSamples;  // Allow PID to work, TODO have subsystem latch once reached instead
    protected double desired;
    protected final double tol = .05;

    public DriveDistance(double inchesForward)
    {
        requires(drive);
        desired = inchesForward;
    }

    protected void initialize()
    {
        drive.setBrake(true);
        settledSamples = 0;
        drive.driveDistance(desired);
    }

    protected boolean isFinished()
    {
        final double error = (desired - state.getDistanceDriven()) / desired;

        if (Math.abs(error) < tol)
        {
            settledSamples++;
        }
        else
        {
            settledSamples = 0;
        }
        return settledSamples > 5  || io.isDriveOverrideRequested();
    }
}
