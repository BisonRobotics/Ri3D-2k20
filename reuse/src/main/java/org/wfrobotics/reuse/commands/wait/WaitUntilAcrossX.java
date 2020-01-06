package org.wfrobotics.reuse.commands.wait;

import org.wfrobotics.reuse.EnhancedRobot;
import org.wfrobotics.reuse.RobotStateBase;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class WaitUntilAcrossX extends Command
{
    private final RobotStateBase state = EnhancedRobot.getState();
    private final double inchesX;

    /** isFinished when the robot is <b>forwards enough</b> on the field */
    public WaitUntilAcrossX(double delayInchesX)
    {
        inchesX = delayInchesX;
    }

    protected boolean isFinished()
    {
        return state.getFieldToVehicle(Timer.getFPGATimestamp()).getTranslation().x() > inchesX;
    }
}
