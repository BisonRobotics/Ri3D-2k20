package frc.robot.subsystems;

import frc.reuse.subsystems.EnhancedSubsystem;
import frc.robot.commands.LedNone;

import edu.wpi.first.wpilibj.Spark;

public final class LEDSubsystem extends EnhancedSubsystem
{
    static class SingletonHolder
    {
        static LEDSubsystem instance = new LEDSubsystem();
    }
    public static LEDSubsystem getInstance()
    {
        return SingletonHolder.instance;
    }

    Spark Leds;
    public LEDSubsystem()
    {
        Leds = new Spark(0);
    }

    public void changeColor()
    {
        Leds.set(BlinkinDict.Color.GOLD.value);
	}
	
	public void changeColor1()
    {
        Leds.set(BlinkinDict.Color.AQUA.value);
	}
	
	public void changeColor2()
    {
        Leds.set(BlinkinDict.Color.RED.value);
    }

    protected void initDefaultCommand()
    {
        setDefaultCommand(new LedNone());
    }

    public void cacheSensors(boolean isDisabled)
    {

    }

    public void reportState()
    {
    }

    public TestReport runFunctionalTest()
    {
        TestReport report = new TestReport();


        return report;
    }
   
}  

class BlinkinDict {
	public enum Color {
		BLACK(0.99),
		DARK_GRAY(0.97),
		GRAY(0.95),
		WHITE(0.93),
		VIOLET(0.91),
		BLUE_VIOLET(0.89),
		BLUE(0.87),
		DARK_BLUE(0.85),
		SKY_BLUE(0.83),
		AQUA(0.81),
		BLUE_GREEN(0.79),
		GREEN(0.77),
		DARK_GREEN(0.75),
		LIME(0.73),
		LAWN_GREEN(0.71),
		YELLOW(0.69),
		GOLD(0.67),
		ORANGE(0.65),
		RED_ORANGE(0.63),
		RED(0.61),
		DARK_RED(0.59),
		HOT_PINK(0.57),
		SINELON_COLOR1ONCOLOR2(0.55),
		COLORWAVES_COLOR1ONCOLOR2(0.53),
		TWINKLES_COLOR1ONCOLOR2(0.51),
		DEFAULT_COLOR1ONCOLOR2(0.49),
		BLEND_COLOR2ONCOLOR1(0.47),
		BLEND_COLOR1ONCOLOR2(0.45),
		BEATSPERMINUTE_COLOR1ONCOLOR2(0.43),
		COLORGRADIENT_COLOR1ONCOLOR2(0.41),
		SPARKLE_COLOR2ONCOLOR1(0.39),
		SPARKLE_COLOR1ONCOLOR2(0.37),
		STROBE_TEAMCOLOR2(0.35),
		SHOT_TEAMCOLOR2(0.33),
		BREATH_FAST_TEAMCOLOR2(0.31),
		BREATH_SLOW_TEAMCOLOR2(0.29),
		HEARTBEAT_FAST_TEAMCOLOR2(0.27),
		HEARTBEAT_MEDIUM_TEAMCOLOR2(0.25),
		HEARTBEAT_SLOW_TEAMCOLOR2(0.23),
		LIGHTCHASE_TEAMCOLOR2(0.21),
		LARSONSCANNER_TEAMCOLOR2(0.19),
		BLEND_TEAMCOLOR2(0.17),
		STROBE_TEAMCOLOR1(0.15),
		SHOT_TEAMCOLOR1(0.13),
		BREATH_FAST_TEAMCOLOR1(0.11),
		BREATH_SLOW_TEAMCOLOR1(0.09),
		HEARTBEAT_FAST_TEAMCOLOR1(0.07),
		HEARTBEAT_MEDIUM_TEAMCOLOR1(0.05),
		HEARTBEAT_SLOW_TEAMCOLOR1(0.03),
		LIGHTCHASE_TEAMCOLOR1(0.01),
		LARSONSCANNER_TEAMCOLOR1(-0.01),
		BLEND_TEAMCOLOR1(-0.03),
		STROBE_WHITE(-0.05),
		STROBE_BLUE(-0.07),
		STROBE_RED(-0.09),
		BREATH_GRAY(-0.11),
		BREATH_WHITE(-0.13),
		BREATH_BLUE(-0.15),
		BREATH_RED(-0.17),
		HEARTBEAT_GRAY(-0.19),
		HEARTBEAT_WHITE(-0.21),
		HEARTBEAT_BLUE(-0.23),
		HEARTBEAT_RED(-0.25),
		LIGHTCHASE_GRAY(-0.27),
		LIGHTCHASE_BLUE(-0.29),
		LIGHTCHASE_RED(-0.31),
		LARSONSCANNER_GRAY(-0.33),
		LARSONSCANNER_RED(-0.35),
		COLORWAVES_FOREST_PALETTE(-0.37),
		COLORWAVES_LAVA_PALETTE(-0.39),
		COLORWAVES_OCEAN_PALETTE(-0.41),
		COLORWAVES_PARTY_PALETTE(-0.43),
		COLORWAVES_RAINBOW_PALETTE(-0.45),
		TWINKLES_FOREST_PALETTE(-0.47),
		TWINKLES_LAVA_PALETTE(-0.49),
		TWINKLES_OCEAN_PALETTE(-0.51),
		TWINKLES_PARTY_PALETTE(-0.53),
		TWINKLES_RAINBOW_PALETTE(-0.55),
		FIRE_LARGE(-0.57),
		FIRE_MEDIUM(-0.59),
		BEATSPERMINTUTE_FOREST_PALETTE(-0.61),
		BEATSPERMINTUTE_LAVA_PALETTE(-0.63),
		BEATSPERMINTUTE_OCEAN_PALETTE(-0.65),
		BEATSPERMINTUTE_PARTY_PALETTE(-0.67),
		BEATSPERMINTUTE_RAINBOW_PALETTE(-0.69),
		SINELON_FOREST_PALETTE(-0.71),
		SINELON_LAVA_PALETTE(-0.73),
		SINELON_OCEAN_PALETTE(-0.75),
		SINELON_PARTY_PALETTE(-0.77),
		SINELON_RAINBOW_PALETTE(-0.79),
		SHOT_WHITE(-0.81),
		SHOT_BLUE(-0.83),
		SHOT_RED(-0.85),
		CONFETTI(-0.87),
		RAINBOW_GLITTER(-0.89),
		RAINBOW_FOREST_PALETTE(-0.91),
		RAINBOW_LAVA_PALETTE(-0.93),
		RAINBOW_OCEAN_PALETTE(-0.95),
		RAINBOW_PARTY_PALETTE(-0.97),
		RAINBOW_RAINBOW_PALETTE(-0.99);
		public double value;
		
		private Color(double value) {
			this.value = value;
		}
	}
}