package net.jmb19905.spellforgers_craft.client.network;

import net.jmb19905.spellforgers_craft.client.ClientEventBusSubscriber;
import net.jmb19905.spellforgers_craft.common.network.ManaMessage;
import net.jmb19905.spellforgers_craft.common.network.NetworkStartup;
import net.minecraft.client.world.ClientWorld;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.LogicalSidedProvider;
import net.minecraftforge.fml.network.NetworkEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.function.Supplier;

public class ClientMessageHandler {

    private static final Logger LOGGER = LogManager.getLogger();

    public static void onMessageReceived(final ManaMessage message, Supplier<NetworkEvent.Context> ctxSupplier) {
        NetworkEvent.Context ctx = ctxSupplier.get();
        LogicalSide sideReceived = ctx.getDirection().getReceptionSide();
        ctx.setPacketHandled(true);

        if (sideReceived != LogicalSide.CLIENT) {
            LOGGER.warn("TargetEffectMessageToClient received on wrong side:" + ctx.getDirection().getReceptionSide());
            return;
        }

        if (!message.isMessageValid()) {
            LOGGER.warn("TargetEffectMessageToClient was invalid" + message.toString());
            return;
        }

        ctx.enqueueWork(() -> processMessage(message));
    }

    private static void processMessage(ManaMessage message) {
        ClientEventBusSubscriber.currentMana = message.getMana();
        ClientEventBusSubscriber.currentMaxMana = message.getMaxMana();
    }

    public static boolean isThisProtocolAcceptedByClient(String protocolVersion) {
        return NetworkStartup.MESSAGE_PROTOCOL_VERSION.equals(protocolVersion);
    }


}
