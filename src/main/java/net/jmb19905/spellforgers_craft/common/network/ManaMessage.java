package net.jmb19905.spellforgers_craft.common.network;

import net.minecraft.network.PacketBuffer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ManaMessage {

    private int mana;
    private int maxMana;

    private boolean messageValid;

    private static final Logger LOGGER = LogManager.getLogger();

    public ManaMessage(int mana, int maxMana){
        this.mana = mana;
        this.maxMana = maxMana;
        messageValid = true;
    }

    /**
     * Used by the message handler
     */
    public ManaMessage(){
        messageValid = false;
    }

    public int getMana() {
        return mana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public boolean isMessageValid() {
        return messageValid;
    }

    public static ManaMessage decode(PacketBuffer buf){
        ManaMessage out = new ManaMessage();
        try {
            int mana = buf.readInt();
            int maxMana = buf.readInt();
            out.mana = mana;
            out.maxMana = maxMana;
        }catch (IllegalArgumentException | IndexOutOfBoundsException e){
            LOGGER.warn("Exception while decoding ManaMessage: " + e);
            return out;
        }
        out.messageValid = true;
        return out;
    }

    public void encode(PacketBuffer buf){
        if(!messageValid) return;
        buf.writeInt(mana);
        buf.writeInt(maxMana);
    }

    @Override
    public String toString() {
        return "ManaMessage[" +
                "mana=" + mana +
                ", maxMana=" + maxMana +
                ']';
    }
}
