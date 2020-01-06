package org.wfrobotics.robot;

import org.wfrobotics.reuse.RobotStateBase;

/** Preferred provider of global, formatted state about the robot. Commands can get information from one place rather than from multiple subsystems. **/
public final class RobotState extends RobotStateBase
{
    public static RobotState getInstance()
    {
        return instance;
    }

    private static final RobotState instance = new RobotState();

    public RobotState()
    {
        super();
    }

    public void reportState()
    {
        super.reportState();
    }

    protected synchronized void resetRobotSpecificState()
    {

    }

}