package net.Locke.lostarkmod.particle.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.TextureSheetParticle;

public class InvisibleParticles extends TextureSheetParticle{

    protected InvisibleParticles(ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed,
            double pZSpeed) {
        super(pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed);
        this.alpha = 0;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.NO_RENDER;
    }

    

}
