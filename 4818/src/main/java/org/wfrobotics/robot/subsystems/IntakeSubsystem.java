package org.wfrobotics.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.wfrobotics.reuse.hardware.TalonFactory;
import org.wfrobotics.reuse.subsystems.EnhancedSubsystem;
import org.wfrobotics.robot.commands.climb.ClimbNone;
import org.wfrobotics.robot.config.RobotConfig;

public final class IntakeSubsystem extends EnhancedSubsystem
{
    static class SingletonHolder
    {
        static IntakeSubsystem instance = new IntakeSubsystem();
    }
    public static IntakeSubsystem getInstance()
    {
        return SingletonHolder.instance;
    }

    TalonSRX intake;
    TalonSRX loader;

    public IntakeSubsystem()
    {
        final RobotConfig config = RobotConfig.getInstance();
        intake = TalonFactory.makeTalon(config.intakeConfig.intake);
        loader = TalonFactory.makeTalon(config.intakeConfig.loader);
    }
    public void setIntakeSpeed(double precentSpeed)
    {
        intake.set(ControlMode.PercentOutput, precentSpeed);
    }
    public void setloaderSpeed(double precentSpeed)
    {
        loader.set(ControlMode.PercentOutput, precentSpeed);
    }

    protected void initDefaultCommand()
    {
        setDefaultCommand(new ClimbNone());
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