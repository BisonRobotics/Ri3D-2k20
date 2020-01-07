package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.wfrobotics.reuse.EnhancedRobot;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.reuse.hardware.*;
import frc.reuse.subsystems.EnhancedSubsystem;
import frc.robot.commands.WofF.WofFNone;
import frc.robot.commands.climb.ClimbNone;
import frc.robot.config.RobotConfig;

public final class WofFSubsystem extends EnhancedRobot
{
    static class SingletonHolder
    {
        static WofFSubsystem instance = new WofFSubsystem();
    }
    public static WofFSubsystem getInstance()
    {
        return SingletonHolder.instance;
    }



    TalonSRX spinner;

    public WofFSubsystem()
    {
        final RobotConfig config = RobotConfig.getInstance();
        spinner = TalonFactory.makeTalon(config.wofFConfig.spinner);
    }
    public void setSpinner(double precentSpeed)
    {
        spinner.set(ControlMode.PercentOutput, precentSpeed);
    }

    protected void initDefaultCommand()
    {
        setDefaultCommand(new WofFNone());
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