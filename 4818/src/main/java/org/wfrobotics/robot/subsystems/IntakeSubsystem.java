package org.wfrobotics.robot.subsystems;

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

    public IntakeSubsystem()
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