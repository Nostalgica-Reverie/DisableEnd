package net.arcticwarmth.noend.noend.func;

import net.arcticwarmth.noend.noend.ConfigMgr;
import net.minecraft.registry.Registries;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static net.arcticwarmth.noend.noend.DisableEnd.CONFIG;

public class RespawnPosFinder {

    private static String[] respawnBlocks = CONFIG.respawnblocks();
    public static int[] FindRespawnPos(int X, int Y, int Z, World world) {

        for (int i = 0; i < respawnBlocks.length; i++) {
            if(Registries.BLOCK.getId(world.getBlockState(new BlockPos(X, Y, Z)).getBlock()).toString().equals(respawnBlocks[i])) {
                return new int[]{X, Y, Z};
            }
        }
        int[] pos = new int[]{world.getSpawnPos().getX(), world.getSpawnPos().getY(), world.getSpawnPos().getZ()};
        return pos;
    }
}
