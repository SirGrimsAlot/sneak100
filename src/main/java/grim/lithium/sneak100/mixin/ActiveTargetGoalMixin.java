package grim.lithium.sneak100.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.TrackTargetGoal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ActiveTargetGoal.class)
public abstract class ActiveTargetGoalMixin extends TrackTargetGoal {
	public ActiveTargetGoalMixin(MobEntity mob, boolean checkVisibility) {
		super(mob, checkVisibility);
	}

	@WrapOperation(method = "findClosestTarget", at = {@At(value = "INVOKE", target = "Lnet/minecraft/world/World;getClosestEntity(Ljava/util/List;Lnet/minecraft/entity/ai/TargetPredicate;Lnet/minecraft/entity/LivingEntity;DDD)Lnet/minecraft/entity/LivingEntity;"),})
	private @Nullable LivingEntity sneak100$findClosestTargetInject(World instance, List<?> entityList, TargetPredicate targetPredicate, LivingEntity entity, double x, double y, double z, @NotNull Operation<LivingEntity> original) {
		var closestEntity = original.call(instance, entityList, targetPredicate, entity, x, y, z);
		return this.mob.canSee(closestEntity) ? closestEntity : null;
	}

	@WrapOperation(method = "findClosestTarget", at = {@At(value = "INVOKE", target = "Lnet/minecraft/world/World;getClosestPlayer(Lnet/minecraft/entity/ai/TargetPredicate;Lnet/minecraft/entity/LivingEntity;DDD)Lnet/minecraft/entity/player/PlayerEntity;")})
	private @Nullable PlayerEntity sneak100$findClosestTargetPlayerInject(World instance, TargetPredicate targetPredicate, LivingEntity entity, double x, double y, double z, @NotNull Operation<PlayerEntity> original) {
//		var closestEntity = original.call(instance, targetPredicate, entity, x, y, z);
//		return this.mob.canSee(closestEntity) ? closestEntity : null;

		return null;
	}

//	@Inject(method = "method_31503", at = @At(value = "RETURN"), cancellable = true)
//	private static void sneak100$checkLivingEntityVisibilityBeforeAddingToTheList(LivingEntity livingEntity, @NotNull CallbackInfoReturnable<Boolean> cir) {
//		cir.setReturnValue(this.mob.canSee(livingEntity));
//	}
}
