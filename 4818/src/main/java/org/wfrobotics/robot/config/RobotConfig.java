package org.wfrobotics.robot.config;

import org.wfrobotics.reuse.commands.drive.DriveCheesy;
import org.wfrobotics.reuse.config.EnhancedRobotConfig;
import java.util.Optional;

import org.wfrobotics.reuse.config.TalonConfig.ClosedLoopConfig;
import org.wfrobotics.reuse.config.TalonConfig.FollowerConfig;
import org.wfrobotics.reuse.config.TalonConfig.Gains;
import org.wfrobotics.reuse.config.TalonConfig.MasterConfig;
import org.wfrobotics.reuse.config.TankConfig;

import edu.wpi.first.wpilibj.command.Command;

public class RobotConfig extends EnhancedRobotConfig
{

     // Tank
    // _________________________________________________________________________________
    public TankConfig getTankConfig() 
    {
        final TankConfig config = new GreenHorn2k20();

        config.CLOSED_LOOP_ENABLED = false;  // TODO remove after making closed loop an Optional

        config.VELOCITY_MAX = 3500.0 / 2;
        config.VELOCITY_PATH = (int) (config.VELOCITY_MAX * 0.85);
        config.ACCELERATION = config.VELOCITY_PATH ;
        config.STEERING_DRIVE_DISTANCE_P = 0.000022;  // TODO Make drive distance a Optional and its owm config
        config.STEERING_DRIVE_DISTANCE_I = 0.000005;
        config.OPEN_LOOP_RAMP = 0.05; // how fast do you acellerate
        config.MAX_PERCENT_OUT = 1.0;

        double TURN_SCALING = .35;

        // TODO Make closed loop an Optional

        config.CLOSED_LOOP = new ClosedLoopConfig("Tank", 
            new MasterConfig[] {
                // Left
                new MasterConfig(10, true, true, new FollowerConfig(12, false), new FollowerConfig(14, false)),
                // Right
                new MasterConfig(11, false, true, new FollowerConfig(13, false), new FollowerConfig(15, false)),
            },
            new Gains[] {
                new Gains("Velocity", 1, 0.0, 0.0, 0.0, 1023.0 / config.VELOCITY_MAX, 100),
                new Gains("Turn", 0, 1.0, 0.0000, 0.0 * 4.5, 1023.0 / config.VELOCITY_MAX, 100,
                                (int) (config.VELOCITY_MAX * TURN_SCALING), (int) (config.VELOCITY_MAX * TURN_SCALING)),
            }
        );

        config.GEAR_RATIO_LOW = (54.0 / 32.0);
        config.SCRUB = 0.98;
        config.WHEEL_DIAMETER = 6 + 3.0 / 8.0;
        config.WIDTH = 27.0;

        return config;
    }

    public class GreenHorn2k20 extends TankConfig 
    {
        @Override
        public Command getTeleopCommand()
        {
            return new DriveCheesy();
        }
    }

    
    public final double kLinkOnTargetDegrees = 3.0;

    // SuperStructure
    // _________________________________________________________________________________
    public final int kAddressInfraredL = 1;
    public final int kAddressInfraredR = 2;

    // Vision
    // _________________________________________________________________________________
    public final double kVisionP = 0.055;  // March 23rd, linear PID
    public final double kVisionI = 0.0002;  // March 23rd, linear PID
    public final double kVisionD = 0.0;  // March 23rd, linear PID

    // Constructor
    // _________________________________________________________________________________
    protected RobotConfig()
    {
        cameraStream = Optional.of(false);
    }

    //  Helper Methods
    // _________________________________________________________________________________

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