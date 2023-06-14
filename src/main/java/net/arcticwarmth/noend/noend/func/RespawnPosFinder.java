package net.arcticwarmth.noend.noend.func;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
public class RespawnPosFinder {
    static String[] KnownEndings = {"bed", "respawn_anchor"};
    public static int[] FindRespawnPos(int X, int Y, int Z, World world) {
        for (int i = 0; i < KnownEndings.length; i++) {
            if(world.getBlockState(new BlockPos(X, Y, Z)).getBlock().getName().toString().split("'")[1].endsWith(KnownEndings[i])) {
                return new int[]{X, Y, Z};
            }
        }
        int[] pos = new int[3];
        pos[0] = world.getSpawnPos().getX();
        pos[1] = world.getSpawnPos().getY();
        pos[2] = world.getSpawnPos().getZ();
        return pos;
    }
}
