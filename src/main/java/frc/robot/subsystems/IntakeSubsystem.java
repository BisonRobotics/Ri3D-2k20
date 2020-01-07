package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.reuse.subsystems.EnhancedSubsystem;

import frc.robot.commands.intake.IntakeNone;

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
        //final RobotConfig config = RobotConfig.getInstance();
        intake = new TalonSRX(24);
        intake.setInverted(true);

        loader = new TalonSRX(14);
        loader.setInverted(true);

        deploy = new TalonSRX(20);
        deploy.setInverted(true);
    }
    public void setPercentDeploy(double percentSpeed)
    {
        deploy.set(ControlMode.PercentOutput, 0.6);
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