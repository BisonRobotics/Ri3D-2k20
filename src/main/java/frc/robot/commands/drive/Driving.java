package frc.robot.commands.drive;

import frc.robot.subsystems.DrivetrainSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class Driving extends Command {

  private final DrivetrainSubsystem drivetrain = DrivetrainSubsystem.getInstance();

  public Driving() {
    requires(drivetrain);
  }

  @Override
  protected void execute() {
    // Yeeeeeeeeeeeeet
    drivetrain.driveeeee();
  }

  @Override
  protected boolean isFinished() {
    return false; // We never stop driving, are you nuts?
  }

}
