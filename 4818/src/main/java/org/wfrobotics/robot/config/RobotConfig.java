package org.wfrobotics.robot.config;
public class RobotConfig
{
    public DriveConfig driveConfig;
    public ShooterConfig shooterConfig;
    public ClimbConfig climbConfig;
    public WofFConfig wofFConfig;
    public IntakeConfig intakeConfig;

    public RobotConfig()
    {
        driveConfig = new DriveConfig();
        shooterConfig = new ShooterConfig();
        climbConfig = new ClimbConfig();
        wofFConfig = new WofFConfig();
        intakeConfig = new IntakeConfig();
    }
    public class DriveConfig
    {
        public int rightMaster =  0;
        public int rightSlave = 1;
        public int leftMaster = 2;
        public int leftSlave = 3;
        public double maxSpeed = 1.0; //exactly what it sounds like
        // Set this to true if bot only spins when you try to go forward or back
        public boolean rightSideInverted = false;
        // You'll need to increase this if the drive is twitchy at idle, but .06 should be fine
        public double deadband = 0.06;
    }
    public class ShooterConfig
    {
        public int belt = 4;
        public int flywheel = 7;

    }
    public class ClimbConfig
    {
        public int climbMaster = 5;
        public int climbSlave = 7;

        public int upCdayzi = 9;

    }
    public class WofFConfig
    {
        public int spinner = 5;
    }
    public class IntakeConfig
    {
        public int intake = 6;
        public int loader = 9;
    }

    private static RobotConfig instance = null;
    public static RobotConfig getInstance()
    {
        if (instance == null)
        {
                instance = new RobotConfig();
        }
        return instance;
    }
}