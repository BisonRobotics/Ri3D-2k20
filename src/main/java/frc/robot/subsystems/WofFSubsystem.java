package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;


import frc.reuse.hardware.*;
import frc.reuse.subsystems.EnhancedSubsystem;
import frc.robot.config.RobotConfig;

public final class WofFSubsystem extends EnhancedSubsystem
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