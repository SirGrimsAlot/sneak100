package grim.lithium.sneak100;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sneak100 implements ModInitializer {
	public static final String MOD_ID = "sneak100";
	public static final String MOD_NAME = "Sneak 100";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Loading {}.", MOD_NAME);
	}
}
