package frc.reuse.config;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;

/**
 * @author Team 4818 WFRobotics
 */
public class SpudJoystick
{
    public static enum AXIS
    {
        X(0),
        Y(1),
        Z(2),
        TWIST(3);

        public final int value;

        private AXIS(int value) { this.value = value; }
        public int get() { return value; }
    }

    public static enum BUTTON
    {
        TRIGGER(1),
        THUMB_SIDE(2),
        THUMB_BOTTOM_LEFT(3),
        THUMB_BOTTOM_RIGHT(4),
        THUMB_TOP_LEFT(5),
        THUMB_TOP_RIGHT(6),
        BASE_TOP_LEFT(7),
        BASE_TOP_RIGHT(8),
        BASE_MIDDLE_LEFT(9),
        BASE_MIDDLE_RIGHT(10),
        BASE_BOTTOM_LEFT(11),
        BASE_BOTTOM_RIGHT(12);

        private final int value;

        private BUTTON(int value) { this.value = value; }
        public int get() { return value; }
    }

    public static enum HAT
    {
        NONE(-1),
        UP(0),
        UP_RIGHT(45),
        RIGHT(90),
        DOWN_RIGHT(135),
        DOWN(180),
        DOWN_LEFT(225),
        LEFT(270),
        UP_LEFT(315);

        public final int value;

        private HAT(int value) { this.value = value; }
        public int get() { return value; }
    }

    private final edu.wpi.first.wpilibj.Joystick hw;

    public SpudJoystick(int driveStationUSBPort)
    {
        hw = new edu.wpi.first.wpilibj.Joystick(driveStationUSBPort);
    }

    /**
     * Get value
     * @return value (-1 to 1)
     */
    public double getRawAxis(int axis)
    {
        return hw.getRawAxis(axis);
    }

    /**
     * Get value
     * @return value (-1 to 1)
     */
    public double getX()
    {
        return getRawAxis(AXIS.X.get());
    }

    /**
     * Get value
     * @return value (-1 to 1)
     */
    public double getY()
    {
        return getRawAxis(AXIS.Y.get());
    }

    public double getThrottle()
    {
        return hw.getThrottle();
    }

    public boolean getButtonPressed(BUTTON button)
    {
        return hw.getRawButton(button.value);
    }

    public boolean getButtonPressed(HAT direction)
    {
        return hw.getPOV(0) == direction.get();
    }

    public int getHat()
    {
        return hw.getPOV(0);
    }

    public void setRumble(Hand side, double value)
    {
        RumbleType r = (side == Hand.kLeft) ? RumbleType.kLeftRumble : RumbleType.kRightRumble;
        hw.setRumble(r, value);
    }
}