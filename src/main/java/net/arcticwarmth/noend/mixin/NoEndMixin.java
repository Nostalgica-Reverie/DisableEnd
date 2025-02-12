package net.arcticwarmth.noend.mixin;


import net.arcticwarmth.noend.noend.objects.RespawnLocation;
import net.minecraft.block.BlockState;
import net.minecraft.block.EndPortalBlock;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashSet;
import java.util.Set;

import static net.arcticwarmth.noend.noend.func.RespawnPosFinder.FindRespawnPos;

@Mixin(EndPortalBlock.class)
public class NoEndMixin {
    @Inject(method = "onEntityCollision", at = @At("HEAD"), cancellable = true)
    private void injectOnEntityCollision(BlockState state, World world, BlockPos pos, Entity entity, CallbackInfo ci) {

        if (world.getServer().getGameRules().getBoolean(net.arcticwarmth.noend.noend.server.DisableEndServer.DISABLE_END) && world instanceof ServerWorld && entity.canUsePortals(true) && !entity.hasVehicle() && !entity.hasPassengers() && entity.getWorld().getRegistryKey() != World.END) {
            ci.cancel();
        }
    }

}
