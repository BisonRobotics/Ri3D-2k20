package org.wfrobotics.robot.subsystems;

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

    public ShooterSubsystem()
    {
        final RobotConfig config = RobotConfig.getInstance();
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