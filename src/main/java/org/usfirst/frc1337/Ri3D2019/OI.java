package org.usfirst.frc1337.Ri3D2019;

import org.usfirst.frc1337.Ri3D2019.commands.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;
// import edu.wpi.first.wpilibj.Jo
import edu.wpi.first.wpilibj.command.Command;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());

    public JoystickButton elevatorBtn1;
    public JoystickButton intakeBallBtn;
    public JoystickButton elevatorBtn2;
    public JoystickButton elevatorBtn3;
    public JoystickButton outakeBallBtn;
    public JoystickButton frontClimbBtn;
    public JoystickButton frontDeClimbBtn;
    public JoystickButton rearClimbBtn;
    public JoystickButton rearDeClimbBtn;
    public JoystickButton hatchIntakeBtn;
    public JoystickButton hatchOuttakeBtn;
    public JoystickButton sendThatFucker;
    public JoystickButton winchButton;
    public Joystick driver;
    public Joystick driver2;
    public JoystickButton wristBtn;

    public OI() {

        driver = new Joystick(0);
        driver2 = new Joystick(1);
        

        outakeBallBtn = new JoystickButton(driver2, 6); // right bump
        outakeBallBtn.whileHeld(new BeltSpeed(.15));
        intakeBallBtn = new JoystickButton(driver2, 5); // left bumbp
        intakeBallBtn.whileHeld(new BeltSpeed(-.20));

        sendThatFucker = new JoystickButton(driver2, 1);
        sendThatFucker.whileHeld(new FlywheelPrecent(.77));

        sendThatFucker = new JoystickButton(driver2, 4);
        sendThatFucker.whileHeld(new FlywheelPrecent(.85));

        elevatorBtn3 = new JoystickButton(driver, 4);
        elevatorBtn3.whileHeld(new Spinnyboi("raise"));
        elevatorBtn2 = new JoystickButton(driver, 1);
        elevatorBtn2.whileHeld(new Spinnyboi("lower"));

        elevatorBtn1 = new JoystickButton(driver, 1);
        elevatorBtn1.whileHeld(new SetPercentDeploy(0.1));
  
        frontClimbBtn = new JoystickButton(driver2, 3); //x
        frontClimbBtn.whileHeld(new SetPrecentIntake(0.3));
        frontClimbBtn = new JoystickButton(driver2, 2); //b
        frontClimbBtn.whileHeld(new SetPrecentIntake(-0.55));

        frontDeClimbBtn = new JoystickButton(driver, 5); // left bump
        frontDeClimbBtn.whileHeld(new ClimbDeploy(0.4));
        rearClimbBtn = new JoystickButton(driver, 6); // right bump
        rearClimbBtn.whileHeld(new ClimbDeploy(-0.4));

        winchButton = new JoystickButton(driver, 8); //start
        winchButton.whileHeld(new Retract(.5));

        winchButton = new JoystickButton(driver, 7); //back
        winchButton.whileHeld(new Retract(-.1));

        wristBtn = new JoystickButton(driver, 3); //down
        wristBtn.whileHeld(new SetPercentDeploy(0.420));
        
        wristBtn = new JoystickButton(driver, 2); //up
        wristBtn.whileHeld(new SetPercentDeploy(-0.69));

        // rearDeClimbBtn = new JoystickButton(driver, 10);
        // rearDeClimbBtn.whileHeld(new climb(1, -1));
        // hatchIntakeBtn = new JoystickButton(driver, 7);
        // hatchIntakeBtn.whileHeld(new IntakeMeh(1, 0));
        // hatchOuttakeBtn = new JoystickButton(driver, 8);
        // hatchOuttakeBtn.whileHeld(new IntakeMeh(1, 1));


        // SmartDashboard Buttons
        //SmartDashboard.putData("IntakeMeh", new IntakeMeh(0));
        //SmartDashboard.putData("Driving", new Driving());
        //SmartDashboard.putData("ElevateMeh", new ElevateMeh("top"));
    }

    public Joystick getDriver() {
        return driver;
    }

}

