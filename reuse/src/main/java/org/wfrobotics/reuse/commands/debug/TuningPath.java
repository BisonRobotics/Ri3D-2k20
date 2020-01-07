package frc.reuse.commands.debug;

import java.util.ArrayList;

import frc.reuse.commands.config.ResetPose;
import frc.reuse.commands.drive.DrivePath;
import frc.reuse.config.PathBuilder;
import frc.reuse.config.PathBuilder.Waypoint;
import frc.reuse.config.PathContainer;
import frc.reuse.math.geometry.Pose2d;
import frc.reuse.math.geometry.Rotation2d;
import frc.reuse.math.geometry.Translation2d;
import frc.reuse.subsystems.drive.Path;
import frc.reuse.subsystems.drive.TankMaths;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class TuningPath extends CommandGroup
{
    public TuningPath()
    {
        PathContainer pathTune = new PathTune(17.0 * 12.0, false);
        PathContainer pathReset = new PathTune(-15.0 * 12.0, true);

        this.addSequential(new ResetPose(pathTune));
        this.addSequential(new DrivePath(pathTune));
        this.addSequential(new WaitCommand(4.0));

        // Reset
        this.addSequential(new ResetPose(pathReset));
        this.addSequential(new DrivePath(pathReset));
    }

    public class PathTune implements PathContainer
    {
        double dist;
        boolean reverse;

        public PathTune(double inches, boolean reverse)
        {
            dist = inches;
            this.reverse = reverse;
        }

        public Path buildPath()
        {
            double velocity = TankMaths.kVelocityMaxInchesPerSecond;
            ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
            sWaypoints.add(new Waypoint(0, 0, 0, 0));
            sWaypoints.add(new Waypoint(dist, 0 * 12, 0 * 12, velocity));

            return PathBuilder.buildPathFromWaypoints(sWaypoints);
        }

        public Pose2d getStartPose()
        {
            return new Pose2d(new Translation2d(0, 0), Rotation2d.fromDegrees(0.0));
        }

        public boolean isReversed()
        {
            return reverse;
        }
    }
}
