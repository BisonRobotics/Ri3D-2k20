package frc.reuse.commands.wait;

import frc.reuse.math.geometry.Translation2d;
import frc.reuse.EnhancedRobot;
import frc.reuse.RobotStateBase;

import edu.wpi.first.wpilibj.command.Command;

public class WaitUntilInArea extends Command
{
    protected final RobotStateBase state = EnhancedRobot.getState();
    protected final double top, bottom, left, right;

    /** isFinished when the robot is <b>in this area</b> of the field */
    public WaitUntilInArea(double top, double bottom, double left, double right)
    {
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
    }

    protected boolean isFinished()
    {
        final Translation2d position = state.getLatestFieldToVehicle().getValue().getTranslation();
        final boolean vertically = position.y() < top && position.y() > bottom;
        final boolean horizontally = position.x() > left && position.x() < right;

        return vertically && horizontally;
    }
}
