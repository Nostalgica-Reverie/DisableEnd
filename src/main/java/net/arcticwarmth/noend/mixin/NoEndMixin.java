package net.arcticwarmth.noend.mixin;


import net.minecraft.block.BlockState;
import net.minecraft.block.EndPortalBlock;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import static net.arcticwarmth.noend.noend.func.RespawnPosFinder.FindRespawnPos;

@Mixin(EndPortalBlock.class)
public class NoEndMixin {
    @Inject(method = "onEntityCollision", at = @At("HEAD"))
    private void injectOnEntityCollision(BlockState state, World world, BlockPos pos, Entity entity, CallbackInfo ci) {

        if (world.getGameRules().getBoolean(net.arcticwarmth.noend.noend.server.DisableEndServer.DISABLE_END) && world instanceof ServerWorld && entity.canUsePortals() && !entity.hasVehicle() && !entity.hasPassengers() && entity.getWorld().getRegistryKey() != World.END) {

            if(entity.isPlayer()) {

                if(entity.getServer().getPlayerManager().getPlayer(entity.getUuid()).getSpawnPointPosition() != null) {
                    System.out.println(entity.getServer().getPlayerManager().getPlayer(entity.getUuid()).getSpawnPointPosition());
                    double spawnx = entity.getServer().getPlayerManager().getPlayer(entity.getUuid()).getSpawnPointPosition().getX();
                    double spawnz = entity.getServer().getPlayerManager().getPlayer(entity.getUuid()).getSpawnPointPosition().getZ();
                    double spawnY = entity.getServer().getPlayerManager().getPlayer(entity.getUuid()).getSpawnPointPosition().getY();
                    int[] tpos = FindRespawnPos((int)spawnx, (int)spawnY, (int)spawnz, entity.getWorld());
                    entity.teleport(tpos[0], tpos[1], tpos[2]);
                } else {
                    //Add no bed found message
                    double spawnx = entity.getWorld().getSpawnPos().getX();
                    double spawnz = entity.getWorld().getSpawnPos().getZ();
                    double spawnY = entity.getWorld().getSpawnPos().getY();
                    entity.teleport(spawnx, spawnY, spawnz);
                }


                System.out.println("Respawned");
            } else {

                double spawnx = entity.getWorld().getSpawnPos().getX();
                double spawnz = entity.getWorld().getSpawnPos().getZ();
                double spawnY = entity.getWorld().getSpawnPos().getY();
                entity.teleport(spawnx, spawnY, spawnz);
                System.out.println("Teleported");
            }
        }
    }

}
