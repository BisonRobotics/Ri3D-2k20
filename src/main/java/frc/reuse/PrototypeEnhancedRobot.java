package frc.reuse;

import frc.reuse.config.EnhancedIO;
import frc.reuse.hardware.LEDs;
import frc.reuse.hardware.NoLEDs;
import frc.reuse.subsystems.SubsystemRunner;
import frc.reuse.subsystems.background.BackgroundUpdater;
import frc.reuse.utilities.ConsoleLogger;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;

/**
 * Base robot for STEM Alliance FRC teams
 * @author STEM Alliance of Fargo Moorhead
 * */
public abstract class PrototypeEnhancedRobot extends TimedRobot
{
    protected final BackgroundUpdater backgroundUpdater = BackgroundUpdater.getInstance();
    protected final SubsystemRunner subsystems = SubsystemRunner.getInstance();
   // protected final AutoRunner autos = AutoRunner.getInstance();

    public static LEDs leds = new NoLEDs();

    /** Register subsystems specific to this robot with {@link SubsystemRunner}. Reuse subsystems (ex: {@link TankSubsystem}) are automatically registered. */
    protected abstract void registerRobotSpecific();
   
    protected static RobotStateBase state;
    //protected static EnhancedRobotConfig config;
    protected static EnhancedIO buttons;

    protected PrototypeEnhancedRobot(RobotStateBase state_, EnhancedIO buttons_)
    {
        super();
        //config = config_;
        state = state_;
        buttons = buttons_;
    }

    public static RobotStateBase getState() { return state; }
    public static EnhancedIO getIO() { return buttons; }

    @Override
    public void robotInit()
    {
        registerRobotSpecific();
        
        subsystems.registerReporter(state);
        subsystems.registerReporter(backgroundUpdater);
        subsystems.registerReporter(ConsoleLogger.getInstance());
        // subsystems.registerReporter(AutoTune.getInstance());

        buttons.assignButtons();                // Initialize Buttons after subsystems
    }

    @Override
    public void autonomousInit()
    {
        leds.setForAuto(m_ds.getAlliance());
        backgroundUpdater.start(true);

    }

    @Override
    public void teleopInit()
    {

        leds.setForTeleop();
        backgroundUpdater.start(false);
    }

    @Override
    public void disabledInit()
    {
        backgroundUpdater.stop();
    }

    @Override
    public void testInit()
    {

        leds.signalHumanPlayer();  // Pit safety
        boolean result = subsystems.runFunctionalTests();
        leds.signalFunctionalTestResult(result);
        Timer.delay(3.0);  // Display result
    }

    @Override
    public void autonomousPeriodic()
    {
        subsystems.update();
    }

    @Override
    public void teleopPeriodic()
    {
        subsystems.update();
    }

    @Override
    public void disabledPeriodic()
    {
        subsystems.update();
    }

    @Override
    public void testPeriodic()
    {

    }
}
