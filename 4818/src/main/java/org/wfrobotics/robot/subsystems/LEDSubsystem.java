// package org.wfrobotics.robot.subsystems;

// import com.ctre.phoenix.motorcontrol.ControlMode;
// import com.ctre.phoenix.motorcontrol.can.TalonSRX;

// import org.wfrobotics.reuse.hardware.TalonFactory;
// import org.wfrobotics.reuse.subsystems.EnhancedSubsystem;
// import org.wfrobotics.robot.commands.climb.ClimbNone;
// import org.wfrobotics.robot.config.RobotConfig;

// import edu.wpi.first.wpilibj.Spark;

// public final class LEDSubsystem extends EnhancedSubsystem
// {
//     static class SingletonHolder
//     {
//         static LEDSubsystem instance = new LEDSubsystem();
//     }
//     public static LEDSubsystem getInstance()
//     {
//         return SingletonHolder.instance;
//     }

//     Spark Leds;
//     public LEDSubsystem()
//     {
//         final RobotConfig config = RobotConfig.getInstance();
//         Leds = new Spark(35);
//     }

//     public void changeColor(int color)
//     {
//         Leds.set(speed);
//     }
//     protected void initDefaultCommand()
//     {
//         setDefaultCommand(new ClimbNone());
//     }

//     public void cacheSensors(boolean isDisabled)
//     {

//     }

//     public void reportState()
//     {
//     }

//     public TestReport runFunctionalTest()
//     {
//         TestReport report = new TestReport();

//         report.add(getDefaultCommand().doesRequire(this));

//         return report;
//     }  
// }