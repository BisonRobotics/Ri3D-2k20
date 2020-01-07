package frc.test;

import org.junit.Test;
import frc.reuse.commands.config.AutoConfig;
import frc.reuse.commands.wrapper.AutoMode;
import frc.reuse.config.AutoFactory;
import frc.reuse.config.AutoFactory.DelaySelect;
import frc.reuse.config.AutoRunner;
import frc.reuse.config.AutoSelection;
import frc.reuse.utilities.ConsoleLogger;
import frc.reuse.utilities.Testable;

public class AutoRunnerTest
{
    static class ModeFoo extends AutoMode { }
    static class ModeBar extends AutoMode { }

    static class ModeSelect extends AutoSelection<AutoMode>
    {
        protected AutoMode[] options()
        {
            return new AutoMode[] {
                new ModeFoo(),
                new ModeBar(),
            };
        }

        protected void apply()
        {
            AutoFactory.getInstance().mode = get();
        }
    }

    public static enum POSITION { LEFT, CENTER, RIGHT, };

    public static class PositionSelect extends AutoSelection<POSITION>
    {
        protected POSITION[] options()
        {
            return new POSITION[] {POSITION.LEFT, POSITION.CENTER, POSITION.RIGHT};
        }

        protected void apply() { }
    }

    public static ModeSelect modes = new ModeSelect();
    public static DelaySelect delays = new DelaySelect();
    public static PositionSelect positions = new PositionSelect();

    @Test
    public void testModes()
    {
        AutoRunner runner = AutoRunner.getInstance();
        AutoFactory factory = AutoFactory.getInstance();
        AutoConfig cmdMode = factory.makeCommand(modes);
        AutoConfig cmdDelay = factory.makeCommand(delays);
        AutoConfig cmdPosition = factory.makeCommand(positions);

        for(int index = 0; index < 3; index++)
        {
            cmdMode.initialize();
            cmdDelay.initialize();
            cmdPosition .initialize();
        }

        Testable.run(factory);
        ConsoleLogger.getInstance().reportState();
        System.out.println(runner);
        runner.stopMode();
    }
}
