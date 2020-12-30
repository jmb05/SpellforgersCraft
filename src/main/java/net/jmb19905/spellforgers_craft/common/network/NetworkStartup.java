package net.jmb19905.spellforgers_craft.common.network;

import net.jmb19905.spellforgers_craft.SpellforgersCraft;
import net.jmb19905.spellforgers_craft.client.network.ClientMessageHandler;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

import java.util.Optional;

public class NetworkStartup {

    public static SimpleChannel simpleChannel;

    public static final byte MANA_MESSAGE_ID = 99;

    public static final String MESSAGE_PROTOCOL_VERSION = "1.0";

    public static final ResourceLocation simpleChannelResource = new ResourceLocation(SpellforgersCraft.MODID, "sfc_channel");

    public static void onCommonSetupEvent(FMLCommonSetupEvent event){
        simpleChannel = NetworkRegistry.newSimpleChannel(simpleChannelResource, () -> MESSAGE_PROTOCOL_VERSION,
                ClientMessageHandler::isThisProtocolAcceptedByClient,
                MessageHandler::isThisProtocolAcceptedByServer);

        simpleChannel.registerMessage(MANA_MESSAGE_ID, ManaMessage.class,
                ManaMessage::encode, ManaMessage::decode,
                ClientMessageHandler::onMessageReceived,
                Optional.of(NetworkDirection.PLAY_TO_CLIENT));
    }

}
