package org.wfrobotics.reuse.commands;

import org.wfrobotics.reuse.EnhancedRobot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Alert the Human players to do something. Use in whileheld button
 * @author STEM Alliance of Fargo Moorhead
 */
public class SignalHumanPlayer extends Command
{
    protected void initialize()
    {
        EnhancedRobot.leds.signalHumanPlayer();
    }

    protected boolean isFinished()
    {
        return false;
    }

    protected void end()
    {
        EnhancedRobot.leds.useRobotModeColor();
    }
}
