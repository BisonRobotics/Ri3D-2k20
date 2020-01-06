package org.wfrobotics.robot.subsystems;

import org.wfrobotics.reuse.subsystems.EnhancedSubsystem;
import org.wfrobotics.robot.commands.climb.ClimbNone;
import org.wfrobotics.robot.config.RobotConfig;

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

    public WofFSubsystem()
    {
        final RobotConfig config = RobotConfig.getInstance();
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