package org.usfirst.frc1337.Ri3D2019.subsystems;


import org.usfirst.frc1337.Ri3D2019.Robot;
import org.usfirst.frc1337.Ri3D2019.commands.Driving;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

// This is the way we brush our teeth, brush our teeth, brush our teeth

public class Drivetrain extends Subsystem {

  // Create drive component objects
  private CANSparkMax left1;
  private CANSparkMax left2;
  private CANSparkMax right1;
  private CANSparkMax right2;
  private DifferentialDrive robotDrive;

    public Drivetrain() {

    // Create motors
    left1 = new CANSparkMax(17, MotorType.kBrushless);
    left2 = new CANSparkMax(12, MotorType.kBrushless);
    right1 = new CANSparkMax(13, MotorType.kBrushless);
    right2 = new CANSparkMax(14, MotorType.kBrushless);

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
    robotDrive.setMaxOutput(.8); // default is FULL SEND
    robotDrive.setRightSideInverted(true); // maybe will need to do this
    robotDrive.setDeadband(0.06);
        
    }

    public void driveeeee(){
        robotDrive.arcadeDrive(Robot.oi.driver.getRawAxis(1) * -1, Robot.oi.driver.getRawAxis(4), true);
        //differentialDrive.tankDrive(Robot.oi.driver.getRawAxis(1), Robot.oi.driver.getRawAxis(5), false);
        //System.out.println(Robot.oi.driver.getY());
        //\System.out.println(Robot.oi.driver.getX());
    }

    @Override
    public void initDefaultCommand() {
            setDefaultCommand(new Driving());

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

}

