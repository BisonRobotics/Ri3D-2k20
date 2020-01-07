package frc.reuse.commands.config;

import frc.reuse.subsystems.drive.TankSubsystem;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class GyroZero extends InstantCommand
{
    protected void initialize()
    {
        TankSubsystem.getInstance().setGyro(0.0);
    }
}
