package org.usfirst.frc1337.Ri3D2019.subsystems;

import org.usfirst.frc1337.Ri3D2019.RobotConfig;
import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Intake extends Subsystem {

    private WPI_TalonSRX wrist;
    private WPI_TalonSRX wheels;
    private WPI_TalonSRX index;

    public Intake() {
        final RobotConfig config = RobotConfig.getInstance();
        wheels =   new WPI_TalonSRX(config.intakeConfig.intake);
        wrist = new WPI_TalonSRX(config.intakeConfig.loader);
        index = new WPI_TalonSRX(config.intakeConfig.loader);

    }

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
    public void setIntakeSpeed(double precentSpeed)
    {
        wheels.set(ControlMode.PercentOutput, precentSpeed);
    }
    public void setIndexSpeed(double precentSpeed)
    {
        index.set(ControlMode.PercentOutput, precentSpeed);
    }
    public void setWristSpeed(double precentSpeed)
    {
        wrist.set(ControlMode.PercentOutput, precentSpeed);
    }

    public void shutdown(){

    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}

