package frc.robot.config;
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
        public int rightMaster = 13;
        public int rightSlave = 14;
        public int leftMaster = 17;
        public int leftSlave = 12;
        public double maxSpeed = 1.0; //exactly what it sounds like
        // Set this to true if bot only spins when you try to go forward or back
        public boolean rightSideInverted = false;
        // You'll need to increase this if the drive is twitchy at idle, but .06 should be fine
        public double deadband = 0; //.06
    }
    public class ShooterConfig
    {
        public int belt = 22;
        public int flywheel = 25;

    }
    public class ClimbConfig
    {
        public int climbMaster = 10;
        public int climbSlave = 16;

        public int upCdayzi = 21;

    }
    public class WofFConfig
    {
        public int spinner = 23;
    }
    public class IntakeConfig
    {
        public int intake = 24;
        public int loader = 14;
        public int deploy = 20;
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