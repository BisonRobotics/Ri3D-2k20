package frc.robot.commands.drive;

import frc.robot.subsystems.DrivetrainSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class Driving extends CommandBase {

  private final DrivetrainSubsystem drivetrain = DrivetrainSubsystem.getInstance();

  public Driving() {
    addRequirements(drivetrain);
  }

  @Override
  public void execute() {
    // Yeeeeeeeeeeeeet
    drivetrain.driveeeee();
  }

  @Override
  public boolean isFinished() {
    return false; // We never stop driving, are you nuts?
  }

}
