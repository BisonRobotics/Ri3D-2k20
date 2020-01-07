package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.reuse.subsystems.EnhancedSubsystem;
import frc.robot.commands.drive.*;
import frc.robot.config.RobotConfig;
import frc.robot.config.IO;

public class DrivetrainSubsystem extends EnhancedSubsystem {

  static class SingletonHolder {
    static DrivetrainSubsystem instance = new DrivetrainSubsystem();
  }

  public static DrivetrainSubsystem getInstance() {
    return SingletonHolder.instance;
  }

  // Create drive component objects
  private CANSparkMax left1;
  private CANSparkMax left2;
  private CANSparkMax right1;
  private CANSparkMax right2;
  private DifferentialDrive robotDrive;
  final IO OIConfig;

  public DrivetrainSubsystem() {
    final RobotConfig config = RobotConfig.getInstance();
    OIConfig = IO.getInstance();

    // Create motors
    left1 = new CANSparkMax(config.driveConfig.leftMaster, MotorType.kBrushless);
    left2 = new CANSparkMax(config.driveConfig.leftSlave, MotorType.kBrushless);
    right1 = new CANSparkMax(config.driveConfig.rightMaster, MotorType.kBrushless);
    right2 = new CANSparkMax(config.driveConfig.rightSlave, MotorType.kBrushless);

    // Clear any residual bad values
    left1.restoreFactoryDefaults();
    left2.restoreFactoryDefaults();
    right1.restoreFactoryDefaults();
    right2.restoreFactoryDefaults();

    // Set master-slave bindings
    left2.follow(left1);
    right2.follow(right1);

    // Initialize the WPI drivetrain object with our motors
    robotDrive = new DifferentialDrive(left1, right1); // only need to set the masters
    // Set some values for the drivetrain control
    robotDrive.setSafetyEnabled(true); // enable motor safety
    robotDrive.setExpiration(0.1); // timeout for motor safety checks
    robotDrive.setMaxOutput(config.driveConfig.maxSpeed); // default is FULL SEND
    robotDrive.setRightSideInverted(config.driveConfig.rightSideInverted); // maybe will need to do this
    robotDrive.setDeadband(config.driveConfig.deadband);
  }

  // Main robot driving control
  public void driveeeee() {
    robotDrive.arcadeDrive(OIConfig.operator.getAxis(frc.reuse.config.Xbox.AXIS.LEFT_X), OIConfig.operator.getAxis(frc.reuse.config.Xbox.AXIS.LEFT_Y), true); // true for squared inputs
  }
  
  public void cacheSensors(boolean isDisabled) {

  }

  public void reportState() {
  }

  protected void initDefaultCommand()
  {
      setDefaultCommand(new Driving());
  }

  public TestReport runFunctionalTest() {
      TestReport report = new TestReport();

      return report;
  }
}