package frc.reuse.commands;

import frc.reuse.PrototypeEnhancedRobot;

import edu.wpi.first.wpilibj2.command.*;

/**
 * Alert the Human players to do something. Use in whileheld button
 * @author STEM Alliance of Fargo Moorhead
 */
public class SignalHumanPlayer extends CommandBase
{
    public void initialize() {
        PrototypeEnhancedRobot.leds.signalHumanPlayer();
    }

    public boolean isFinished() {
        return false;
    }

    protected void end()
    {
        PrototypeEnhancedRobot.leds.useRobotModeColor();
    }
}
