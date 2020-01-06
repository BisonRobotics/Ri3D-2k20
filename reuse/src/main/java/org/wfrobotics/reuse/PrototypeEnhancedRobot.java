package org.wfrobotics.reuse;

import org.wfrobotics.reuse.config.AutoFactory;
import org.wfrobotics.reuse.config.AutoRunner;
import org.wfrobotics.reuse.config.EnhancedIO;
import org.wfrobotics.reuse.config.EnhancedRobotConfig;
import org.wfrobotics.reuse.hardware.LEDs;
import org.wfrobotics.reuse.hardware.NoLEDs;
import org.wfrobotics.reuse.subsystems.SubsystemRunner;
import org.wfrobotics.reuse.subsystems.background.BackgroundUpdater;
import org.wfrobotics.reuse.subsystems.drive.TankSubsystem;
import org.wfrobotics.reuse.utilities.ConsoleLogger;
import org.wfrobotics.reuse.utilities.DashboardView;

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
    protected final AutoRunner autos = AutoRunner.getInstance();

    public static LEDs leds = new NoLEDs();

    /** Register subsystems specific to this robot with {@link SubsystemRunner}. Reuse subsystems (ex: {@link TankSubsystem}) are automatically registered. */
    protected abstract void registerRobotSpecific();
   
    protected static RobotStateBase state;
    protected static EnhancedRobotConfig config;
    protected static EnhancedIO buttons;

    protected PrototypeEnhancedRobot(EnhancedRobotConfig config_, RobotStateBase state_, EnhancedIO buttons_)
    {
        super();
        config = config_;
        state = state_;
        buttons = buttons_;
    }
    protected PrototypeEnhancedRobot(RobotStateBase state_, EnhancedIO buttons_)
    {
        super();
        state = state_;
        buttons = buttons_;
    }

    public static RobotStateBase getState() { return state; }
    public static EnhancedRobotConfig getConfig() { return config; }
    public static EnhancedIO getIO() { return buttons; }

    @Override
    public void robotInit()
    {
        registerRobotSpecific();
        
        subsystems.registerReporter(state);
        subsystems.registerReporter(backgroundUpdater);
        subsystems.registerReporter(ConsoleLogger.getInstance());
        // subsystems.registerReporter(AutoTune.getInstance());
        subsystems.registerTest(AutoFactory.getInstance());

        buttons.assignButtons();                // Initialize Buttons after subsystems
        AutoFactory.getInstance().onSelectionChanged();  // Set default auto mode
    }

    @Override
    public void autonomousInit()
    {
        leds.setForAuto(m_ds.getAlliance());
        backgroundUpdater.start(true);

        autos.startMode();
    }

    @Override
    public void teleopInit()
    {
        autos.stopMode();

        leds.setForTeleop();
        backgroundUpdater.start(false);
    }

    @Override
    public void disabledInit()
    {
        autos.stopMode();
        backgroundUpdater.stop();
    }

    @Override
    public void testInit()
    {
        autos.stopMode();

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
