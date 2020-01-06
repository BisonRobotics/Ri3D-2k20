package org.wfrobotics.reuse.config;

import org.wfrobotics.reuse.utilities.ConsoleLogger;
import org.wfrobotics.reuse.utilities.FileUtils;

/**
 * Picks the {@link RobotConfig} to use for this robot based on a file saved to the RoboRIO
 * @author STEM Alliance of Fargo Moorhead
 */
public class RobotConfigPicker
{
    private static EnhancedRobotConfig explicitDefault = null;

    /**
     * Read the contents of the text file on the Rio at ~/config.txt to match to a config class's name
     * and set the activeConfig which can be grabbed via {@link #get()}. Will print an error to the
     * driver station and select the first config in the array if it can't match a name.
     * @param configs Array of {@link RobotConfig} configs
     */
    public static EnhancedRobotConfig get(EnhancedRobotConfig[] configs)
    {
        return get(configs, "~/config.txt");
    }

    /**
     * Read the contents of the text file on the Rio at {@code file} to match to a config class's name
     * and set the activeConfig which can be grabbed via {@link #get()}. Will print an error to the
     * driver station and select the first config in the array if it can't match a name.
     * @param configs Array of {@link RobotConfigBase} configs
     * @param file name of config file to look in
     */
    public static EnhancedRobotConfig get(EnhancedRobotConfig[] configs, String file)
    {
        String path = "";
        String rcName = "";

        if(!FileUtils.exists(file))
        {
            return useDefault(file, configs[0]);
        }
        path = FileUtils.joinUserFolder(file);
        if(!FileUtils.exists(path))
        {
            return useDefault(path, configs[0]);
        }
        rcName = FileUtils.readLine(path);

        for (EnhancedRobotConfig robotConfig : configs)
        {
            if(robotConfig.getClass().getSimpleName().toLowerCase().equals(rcName.toLowerCase()))
            {
                ConsoleLogger.info("Config: " + robotConfig.getClass().getSimpleName());
                return robotConfig;
            }
        }
        return useDefault(rcName, configs[0]);
    }

    public static void setDefault(EnhancedRobotConfig config)
    {
        explicitDefault = config;
    }

    private static EnhancedRobotConfig useDefault(String path, EnhancedRobotConfig implicitDefault)
    {
        EnhancedRobotConfig result = (explicitDefault != null) ? explicitDefault : implicitDefault;
        ConsoleLogger.error("Config: No match for: " + path);
        ConsoleLogger.warning("Config: Using default: " + result.getClass().getSimpleName());
        return result;
    }
}
