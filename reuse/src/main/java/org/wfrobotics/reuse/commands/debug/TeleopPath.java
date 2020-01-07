package frc.reuse.commands.debug;

import frc.reuse.commands.config.ResetPose;
import frc.reuse.commands.drive.DriveOff;
import frc.reuse.commands.drive.DrivePath;
import frc.reuse.config.PathContainer;

import edu.wpi.first.wpilibj.command.CommandGroup;

/** Use to rapidly test path by putting on a teleop button */
public class TeleopPath extends CommandGroup
{
    public TeleopPath(PathContainer path)
    {
        this(path, 15.0);
    }

    /** Add a timeout to keep your path from ramming anything accidently */
    public TeleopPath(PathContainer path, double timeout)
    {
        addSequential(new ResetPose(path));
        addSequential(new DrivePath(path), timeout);
        addSequential(new DriveOff());  // Observe robot state
    }
}