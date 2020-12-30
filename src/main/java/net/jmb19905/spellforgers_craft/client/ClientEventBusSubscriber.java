package net.jmb19905.spellforgers_craft.client;

import net.jmb19905.spellforgers_craft.SpellforgersCraft;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ClientEventBusSubscriber {

    public static int currentMana = 0;
    public static int currentMaxMana = 100;

    @SubscribeEvent
    public static void renderGameOverlay(RenderGameOverlayEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        ClientPlayerEntity player = mc.player;
        ResourceLocation manaBarResource = new ResourceLocation(SpellforgersCraft.MODID, "textures/gui/mana_bar.png");
        mc.textureManager.bindTexture(manaBarResource);
        AbstractGui.blit(event.getMatrixStack(), 10, mc.getMainWindow().getScaledHeight() - 10, 0, 0, 101, 5, 128, 64);
        float tenthOfBar = 101f / currentMaxMana;
        AbstractGui.blit(event.getMatrixStack(), 10, mc.getMainWindow().getScaledHeight() - 10, 0, 5, (int) (tenthOfBar * currentMana), 5, 128, 64);
    }
}