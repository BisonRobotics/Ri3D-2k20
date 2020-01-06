package org.wfrobotics.reuse.config;

import java.util.Optional;

import org.wfrobotics.reuse.config.TankConfig.TankConfigSupplier;

public abstract class EnhancedRobotConfig implements TankConfigSupplier
{
    /** Stream frames to SmartDashboard for heads up view of robot's perspective */
    public Optional<Boolean> cameraStream = Optional.of(true);
    /** Coprocessor config, leave null if you don't have vision */
	public Optional<VisionConfig> vision = Optional.empty();
}