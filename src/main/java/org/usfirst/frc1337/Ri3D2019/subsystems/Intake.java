package org.usfirst.frc1337.Ri3D2019.subsystems;

import org.usfirst.frc1337.Ri3D2019.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import com.revrobotics.*;

public class Intake extends Subsystem {

    private Spark intakyBoiBall;
    private Spark intakyBoiHatch;

    public Intake() {
        intakyBoiBall = new Spark(1);
        addChild("IntakyBoiBall",intakyBoiBall);
        intakyBoiBall.setInverted(false);
        intakyBoiBall.setBounds(2, 2, 1.5, 1, 1);

        intakyBoiHatch = new Spark(0);
        addChild("IntakyBoiHatch",intakyBoiHatch);
        intakyBoiHatch.setInverted(false);
        intakyBoiHatch.setBounds(2, 2, 1.5, 1, 1);
    }

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    public void intake(int intake){
        if(intake == 0){intakyBoiBall.setSpeed(.6);}
        if(intake == 1){intakyBoiHatch.setSpeed(.6);}
    }

    public void outtake(int intake){
        if(intake == 0){intakyBoiBall.setSpeed(-.6);}
        if(intake == 1){intakyBoiHatch.setSpeed(-.6);}
    }

    public void shutdown(){
        intakyBoiBall.setSpeed(0);
        intakyBoiHatch.setSpeed(0);
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}

