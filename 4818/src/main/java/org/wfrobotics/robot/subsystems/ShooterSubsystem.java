package org.wfrobotics.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.wfrobotics.reuse.hardware.TalonFactory;
import org.wfrobotics.reuse.subsystems.EnhancedSubsystem;
import org.wfrobotics.robot.commands.Shooter.ShootNone;
import org.wfrobotics.robot.config.RobotConfig;

public final class ShooterSubsystem extends EnhancedSubsystem
{
    static class SingletonHolder
    {
        static ShooterSubsystem instance = new ShooterSubsystem();
    }
    public static ShooterSubsystem getInstance()
    {
        return SingletonHolder.instance;
    }

    TalonSRX belt;
    TalonSRX flywheel;

    public ShooterSubsystem()
    {
        final RobotConfig config = RobotConfig.getInstance();
        belt = TalonFactory.makeTalon(config.shooterConfig.belt);
        flywheel = TalonFactory.makeTalon(config.shooterConfig.flywheel);
    }
    public void setBeltSpeed(double precentSpeed)
    {
        belt.set(ControlMode.PercentOutput, precentSpeed);
    }
    public void setFlyWheelSpeed(double precentSpeed)
    {
        flywheel.set(ControlMode.PercentOutput, precentSpeed);
    }
    
    protected void initDefaultCommand()
    {
        setDefaultCommand(new ShootNone());
    }

    public void cacheSensors(boolean isDisabled)
    {

    }

    public void reportState()
    {

    }

    public TestReport runFunctionalTest()
    {
        TestReport report = new TestReport();

        report.add(getDefaultCommand().doesRequire(this));

        return report;
    }  
}