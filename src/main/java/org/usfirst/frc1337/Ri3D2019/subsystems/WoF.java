package org.usfirst.frc1337.Ri3D2019.subsystems;

import org.usfirst.frc1337.Ri3D2019.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class WoF extends Subsystem {

    private WPI_TalonSRX liftyBoi;

    public WoF() {
        liftyBoi = new WPI_TalonSRX(23);
        addChild("LiftyBoi",liftyBoi);
        liftyBoi.setInverted(true);
    }

    @Override
    public void initDefaultCommand() {
    }

    public void raise(double speed){
        liftyBoi.set(speed);
    }

    public void lower(double speed){
        liftyBoi.set(speed * -1);
    }

    public void shutdown(){
        liftyBoi.set(0);
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop
        //liftyBoi.setInverted(SmartDashboard.getBoolean("Invert Control Direction", false));
    }

}

