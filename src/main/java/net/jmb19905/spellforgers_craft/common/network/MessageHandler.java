package net.jmb19905.spellforgers_craft.common.network;

public class MessageHandler {

    public static boolean isThisProtocolAcceptedByServer(String protocolVersion) {
        return NetworkStartup.MESSAGE_PROTOCOL_VERSION.equals(protocolVersion);
    }

}
