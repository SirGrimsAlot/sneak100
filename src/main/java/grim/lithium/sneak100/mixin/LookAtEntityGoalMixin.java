package grim.lithium.sneak100.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LookAtEntityGoal.class)
public abstract class LookAtEntityGoalMixin {
	@Mutable
	@Shadow
	@Final
	protected float chance;

	@Inject(method = "<init>(Lnet/minecraft/entity/mob/MobEntity;Ljava/lang/Class;FFZ)V", at = @At(value = "TAIL"))
	public void sneak100$constructorInject(MobEntity mob, Class<? extends LivingEntity> targetType, float range, float chance, boolean lookForward, CallbackInfo ci) {
		this.chance = 0f;
	}
}
