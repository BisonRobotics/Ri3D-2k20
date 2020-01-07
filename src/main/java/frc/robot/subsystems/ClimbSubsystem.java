package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.Command;
import frc.reuse.subsystems.EnhancedSubsystem;
import frc.robot.commands.climb.ClimbNone;
import frc.robot.config.RobotConfig;

public final class ClimbSubsystem extends EnhancedSubsystem {
    static class SingletonHolder {
        static ClimbSubsystem instance = new ClimbSubsystem();
    }

    public static ClimbSubsystem getInstance() {
        return SingletonHolder.instance;
    }

    private CANSparkMax motor1;
    private CANSparkMax motor2;

    public ClimbSubsystem() {
        final RobotConfig config = RobotConfig.getInstance();
        // Set up the SPARKS
        motor1 = new CANSparkMax(config.climbConfig.climbMaster, MotorType.kBrushless);
        motor2 = new CANSparkMax(config.climbConfig.climbSlave, MotorType.kBrushless);

        // Clear any residual bad values
        motor1.restoreFactoryDefaults();
        motor2.restoreFactoryDefaults();

        // Slave motor2 to motor1
        motor2.follow(motor1);
    }

    public void setPrecentSpeedUp(double speed) {
        // Set output speed
        motor1.set(speed);
    }

    protected void initDefaultCommand() {
        setDefaultCommand((Command) new ClimbNone());
    }

    public void cacheSensors(boolean isDisabled) {

    }

    public void reportState() {
    }

    public TestReport runFunctionalTest() {
        TestReport report = new TestReport();


        return report;
    }
}
