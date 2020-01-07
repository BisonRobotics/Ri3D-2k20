package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.reuse.hardware.TalonFactory;
import frc.reuse.subsystems.EnhancedSubsystem;

import frc.robot.commands.intake.IntakeNone;
import frc.robot.config.RobotConfig;

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
    TalonSRX deploy;

    public IntakeSubsystem()
    {
        final RobotConfig config = RobotConfig.getInstance();
        intake = TalonFactory.makeTalon(config.intakeConfig.intake);
        loader = TalonFactory.makeTalon(config.intakeConfig.loader);
        deploy = TalonFactory.makeTalon(config.intakeConfig.deploy);
    }
    public void setPercentDeploy(double percentSpeed)
    {
        intake.set(ControlMode.PercentOutput, percentSpeed);
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
        setDefaultCommand(new IntakeNone());
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

        return report;
    }  
}