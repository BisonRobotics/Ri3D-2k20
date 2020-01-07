/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc1337.Ri3D2019.subsystems;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class climber extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private Spark climberFront;
  private Spark climberRear;

  public climber(){
    climberFront = new Spark(2);
    addChild("ClimberFront",climberFront);
    climberFront.setInverted(true);
    climberFront.setBounds(2, 2, 1.5, 1, 1);

    climberRear = new Spark(3);
    addChild("ClimberRear",climberRear);
    climberRear.setInverted(true);
    climberRear.setBounds(2, 2, 1.5, 1, 1);
  }

  public void climbFront(int direction){
    if(direction == 1)
    climberFront.set(.3);

    if(direction == -1)
    climberFront.set(-.3);
  }

  public void climbRear(int direction){
    if(direction == 1)
    climberRear.set(.3);

    if(direction == -1)
    climberRear.set(-.3);
  }

  public void shutdown(){
    climberFront.set(0);
    climberRear.set(0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
