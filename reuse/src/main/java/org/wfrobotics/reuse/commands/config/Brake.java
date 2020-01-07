package frc.reuse.commands.config;

import frc.reuse.subsystems.drive.TankSubsystem;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class Brake extends InstantCommand
{
    private final boolean request;

    public Brake(boolean enable)
    {
        request = enable;
    }

    protected void initialize()
    {
        TankSubsystem.getInstance().setBrake(request);
    }
}
