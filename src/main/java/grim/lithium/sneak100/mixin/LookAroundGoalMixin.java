package grim.lithium.sneak100.mixin;

import net.minecraft.entity.ai.goal.LookAroundGoal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(LookAroundGoal.class)
public class LookAroundGoalMixin {
	@ModifyConstant(method = "canStart", constant = @Constant(floatValue = 0.02f))
	private float sneak100$canStartInject(float canStartChance) {
		return 0f;
	}
}
