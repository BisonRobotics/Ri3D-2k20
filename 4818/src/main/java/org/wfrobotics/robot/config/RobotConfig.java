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
        public int leftSlace = 3;
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