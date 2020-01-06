package org.wfrobotics.robot.commands;

import org.wfrobotics.robot.subsystems.ExampleVision;

import edu.wpi.first.wpilibj.command.Command;

/** Selects the vision camera who's view is not obstructed by the other subsystems */
public final class DummyVisionCommand extends Command
{

    private final ExampleVision vision = ExampleVision.getInstance();

    public DummyVisionCommand()
    {
        requires(vision);
    }

    protected void execute()
    {
        vision.setModeTape();
    }

    protected boolean isFinished()
    {
        return false;
    }

}