package frc.test;

import frc.reuse.math.geometry.Kinematics;
import frc.reuse.math.geometry.Twist2d;
import frc.reuse.utilities.ConsoleLogger;

public class KinematicsTest
{
    public static void main(String[] args)
    {
        Twist2d twist = Kinematics.forwardKinematics(10, 10);

        System.out.println(System.getProperty("user.home"));
        System.out.println(System.getProperty("os.name"));
        ConsoleLogger.getInstance().reportState();
        System.out.println(twist.dx);
        System.out.println(twist.dy);
        System.out.println(twist.dtheta);
    }
}
