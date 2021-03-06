package org.usfirst.frc1337.Ri3D2019.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc1337.Ri3D2019.RobotConfig;

import edu.wpi.first.wpilibj.command.Subsystem;


public final class ShooterSubsystem extends Subsystem
{
    static class SingletonHolder
    {
        static ShooterSubsystem instance = new ShooterSubsystem();
    }
    public static ShooterSubsystem getInstance()
    {
        return SingletonHolder.instance;
    }

    private WPI_TalonSRX belt;
    private WPI_TalonSRX flywheel;


    public ShooterSubsystem()
    {
        final RobotConfig config = RobotConfig.getInstance();
        belt =   new WPI_TalonSRX(config.shooterConfig.belt);
        flywheel = new WPI_TalonSRX(config.shooterConfig.flywheel);
    }
    public void setBeltSpeed(double precentSpeed)
    {
        belt.set(ControlMode.PercentOutput, precentSpeed);
    }
    public void setFlyWheelSpeed(double precentSpeed)
    {
        flywheel.set(ControlMode.PercentOutput, precentSpeed);
    }
    
    public void cacheSensors(boolean isDisabled)
    {

    }
    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub
        
    }
    public void reportState()
    {

    }
}