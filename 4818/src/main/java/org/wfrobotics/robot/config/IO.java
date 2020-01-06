package org.wfrobotics.robot.config;

import org.wfrobotics.reuse.config.ButtonFactory;
import org.wfrobotics.reuse.config.ButtonFactory.TRIGGER;
import org.wfrobotics.robot.commands.Shooter.BeltSpeed;
import org.wfrobotics.robot.commands.Shooter.FlywheelPrecent;
import org.wfrobotics.robot.commands.WofF.PrecentSpinner;
import org.wfrobotics.robot.commands.climb.DeployPercent;
import org.wfrobotics.robot.commands.intake.SetPrecentIntake;
import org.wfrobotics.robot.commands.intake.SetPrecentLoader;
import org.wfrobotics.reuse.config.EnhancedIO;
import org.wfrobotics.reuse.config.Xbox;

import edu.wpi.first.wpilibj.GenericHID.Hand;

/** Maps controllers to Commands **/
public final class IO implements EnhancedIO
{
    private static IO instance = null;
    private final Xbox operator;

    /** Create and configure controls for Drive Team */
    private IO()
    {
        operator = new Xbox(3);
    }

    /** Configure each Button to run a Command */
    public void assignButtons()
    {
        ButtonFactory.makeButton(operator, Xbox.BUTTON.LB, TRIGGER.WHILE_HELD, new BeltSpeed(0.5));
        ButtonFactory.makeButton(operator, Xbox.BUTTON.LB, TRIGGER.WHILE_HELD, new FlywheelPrecent(1.0));
        ButtonFactory.makeButton(operator, Xbox.BUTTON.LB, TRIGGER.WHILE_HELD, new SetPrecentLoader(0.5));
        ButtonFactory.makeButton(operator, Xbox.BUTTON.LB, TRIGGER.WHILE_HELD, new SetPrecentIntake(0.8));
        ButtonFactory.makeButton(operator, Xbox.BUTTON.LB, TRIGGER.WHILE_HELD, new PrecentSpinner(1.0));
        ButtonFactory.makeButton(operator, Xbox.BUTTON.LB, TRIGGER.WHILE_HELD, new DeployPercent(0.5));
    }

    public double getTurn()
    {
        return 0;
    }
    public double getThrottle()
    {
        return 0;
    }
    public boolean getDriveQuickTurn()
    {
        return false;
    }



    // ------------------------ Reuse ------------------------

    public static IO getInstance()
    {
        if (instance == null)
        {
            instance = new IO();
        }
        return instance;
    }

    public boolean isDriveOverrideRequested()
    {
        return Math.abs(getThrottle()) > 0.15 || Math.abs(getTurn()) > 0.15;
    }

    public void setRumble(boolean rumble)
    {
        double state = (rumble) ? 1.0 : 0.0;
        operator.setRumble(Hand.kLeft, state);
        operator.setRumble(Hand.kRight, state);
    }
}