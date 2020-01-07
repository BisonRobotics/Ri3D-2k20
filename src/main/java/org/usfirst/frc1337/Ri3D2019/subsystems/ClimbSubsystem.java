package org.usfirst.frc1337.Ri3D2019.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.usfirst.frc1337.Ri3D2019.RobotConfig;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj2.command.Command;

public final class ClimbSubsystem extends Subsystem {
    static class SingletonHolder {
        static ClimbSubsystem instance = new ClimbSubsystem();
    }

    public static ClimbSubsystem getInstance() {
        return SingletonHolder.instance;
    }

    private CANSparkMax motor1;
    private CANSparkMax motor2;
    private CANSparkMax deploy;



    public ClimbSubsystem() {
        final RobotConfig config = RobotConfig.getInstance();
        // Set up the SPARKS
        motor1 = new CANSparkMax(config.climbConfig.climbMaster, MotorType.kBrushless);
        motor2 = new CANSparkMax(config.climbConfig.climbSlave, MotorType.kBrushless);
        deploy = new CANSparkMax(config.climbConfig.upCdayzi, MotorType.kBrushed);

        // Clear any residual bad values
        motor1.restoreFactoryDefaults();
        motor2.restoreFactoryDefaults();
        deploy.restoreFactoryDefaults();
        // Slave motor2 to motor1
        motor2.follow(motor1);
    }

    public void setPrecentSpeedUp(double speed) {
        // Set output speed
        motor1.set(speed);
    }
    public void setPrecentDeploy(double speed) {
        // Set output speed
        deploy.set(speed);
    }

    protected void initDefaultCommand() {
    }

    public void cacheSensors(boolean isDisabled) {

    }

    public void reportState() {
    }

}
