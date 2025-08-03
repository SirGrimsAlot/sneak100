package grim.lithium.sneak100.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
	public LivingEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}

	@Inject(method = "canSee", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/Vec3d;distanceTo(Lnet/minecraft/util/math/Vec3d;)D"), locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
	private void sneak100$canSeeInject(Entity entity, CallbackInfoReturnable<Boolean> cir, @NotNull Vec3d livingEntityPosition, Vec3d otherEntityPosition) {
		final var directionToOtherEntity = livingEntityPosition.subtract(otherEntityPosition);
		final var rotationDifferenceToOtherEntity = Math.toDegrees(directionToOtherEntity.subtract(this.getRotationVector()).getY());

		// Check if you're in the mob's vision cone
		if (rotationDifferenceToOtherEntity >= 10d) {
			cir.setReturnValue(false);
		}
	}
}
