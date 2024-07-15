package net.arcticwarmth.noend.noend.objects;

import net.minecraft.server.world.ServerWorld;

public class RespawnLocation {

    public int[] position;
    public ServerWorld world;

    public RespawnLocation(int[] position, ServerWorld world) {
        this.position = position;
        this.world = world;
    }
}
