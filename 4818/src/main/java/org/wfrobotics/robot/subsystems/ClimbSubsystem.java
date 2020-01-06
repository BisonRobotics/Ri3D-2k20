package org.wfrobotics.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.wfrobotics.reuse.hardware.TalonFactory;
import org.wfrobotics.reuse.subsystems.EnhancedSubsystem;
import org.wfrobotics.robot.commands.climb.ClimbNone;
import org.wfrobotics.robot.config.RobotConfig;

public final class ClimbSubsystem extends EnhancedSubsystem
{
    static class SingletonHolder
    {
        static ClimbSubsystem instance = new ClimbSubsystem();
    }
    public static ClimbSubsystem getInstance()
    {
        return SingletonHolder.instance;
    }

    TalonSRX deploy;

    public ClimbSubsystem()
    {
        final RobotConfig config = RobotConfig.getInstance();
        deploy = TalonFactory.makeTalon(config.climbConfig.upCdayzi);
    }
    public void setPrecentSpeedUp(double speed)
    {
        deploy.set(ControlMode.PercentOutput, speed);
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
