package frc.robot.config;

import frc.reuse.config.ButtonFactory;
import frc.reuse.config.ButtonFactory.TRIGGER;
import frc.robot.commands.LEDsChange;
import frc.robot.commands.Shooter.BeltSpeed;
import frc.robot.commands.Shooter.FlywheelPrecent;
import frc.robot.commands.WofF.PrecentSpinner;
import frc.robot.commands.climb.DeployPercent;
import frc.robot.commands.intake.SetPrecentIntake;
import frc.robot.commands.intake.SetPrecentLoader;
import frc.reuse.config.EnhancedIO;
import frc.reuse.config.Xbox;

import edu.wpi.first.wpilibj.GenericHID.Hand;

/** Maps controllers to Commands **/
public final class IO implements EnhancedIO
{
    private static IO instance = null;
    public final Xbox operator;

    /** Create and configure controls for Drive Team */
    private IO()
    {
        operator = new Xbox(0);
    }

    /** Configure each Button to run a Command */
    public void assignButtons()
    {
        ButtonFactory.makeButton(operator, Xbox.BUTTON.LB, TRIGGER.WHILE_HELD, new BeltSpeed(0.10));
        ButtonFactory.makeButton(operator, Xbox.BUTTON.RB, TRIGGER.WHILE_HELD, new FlywheelPrecent(0.1));
        ButtonFactory.makeButton(operator, Xbox.BUTTON.Y, TRIGGER.WHILE_HELD, new SetPrecentLoader(0.1));
        ButtonFactory.makeButton(operator, Xbox.BUTTON.B, TRIGGER.WHILE_HELD, new SetPrecentIntake(0.1));
        ButtonFactory.makeButton(operator, Xbox.BUTTON.A, TRIGGER.WHILE_HELD, new PrecentSpinner(0.1));
        ButtonFactory.makeButton(operator, Xbox.BUTTON.X, TRIGGER.WHILE_HELD, new DeployPercent(0.1));
        ButtonFactory.makeButton(operator, Xbox.BUTTON.START, TRIGGER.WHILE_HELD, new LEDsChange());

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