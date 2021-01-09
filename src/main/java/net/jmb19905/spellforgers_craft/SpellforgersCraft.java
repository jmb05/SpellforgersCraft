package net.jmb19905.spellforgers_craft;

import net.jmb19905.spellforgers_craft.client.ClientEventBusSubscriber;
import net.jmb19905.spellforgers_craft.common.blocks.SpellCraftBlocks;
import net.jmb19905.spellforgers_craft.common.capability.CapabilityAttachEventHandler;
import net.jmb19905.spellforgers_craft.common.capability.ManaCapability;
import net.jmb19905.spellforgers_craft.common.fluids.SpellCraftFluids;
import net.jmb19905.spellforgers_craft.common.items.SpellCraftItems;
import net.jmb19905.spellforgers_craft.common.network.NetworkStartup;
import net.jmb19905.spellforgers_craft.common.particles.SpellCraftParticles;
import net.jmb19905.spellforgers_craft.common.recipes.SpellCraftRecipes;
import net.jmb19905.spellforgers_craft.core.compatability.ExternalMods;
import net.jmb19905.spellforgers_craft.core.SpellCraftEventBusSubscriber;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;

import javax.annotation.Nonnull;
import java.util.Objects;

@Mod(SpellforgersCraft.MODID)
@Mod.EventBusSubscriber(modid = SpellforgersCraft.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SpellforgersCraft {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "spellforgers_craft";
    public static SpellforgersCraft instance;

    public SpellforgersCraft(){
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        SpellCraftParticles.PARTICLE_TYPES.register(modEventBus);
        SpellCraftItems.ITEMS.register(modEventBus);
        SpellCraftRecipes.RECIPE_SERIALIZERS.register(modEventBus);
        SpellCraftFluids.FLUIDS.register(modEventBus);
        SpellCraftBlocks.BLOCKS.register(modEventBus);

        instance = this;
    }

    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();

        SpellCraftBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)
                .filter(block -> !(block instanceof FlowingFluidBlock))
                .forEach(block -> {
                    final Item.Properties properties = new Item.Properties().group(SpellforgersCraftItemGroup.instance);
                    final BlockItem blockItem = new BlockItem(block, properties);
                    blockItem.setRegistryName(Objects.requireNonNull(block.getRegistryName()));
                    registry.register(blockItem);
                });

        LOGGER.debug("Registered BlockItems!");
    }

    @SubscribeEvent
    public static void onCommonSetupEvent(FMLCommonSetupEvent event){
        ManaCapability.register();

        NetworkStartup.onCommonSetupEvent(event);

        MinecraftForge.EVENT_BUS.register(instance);
        MinecraftForge.EVENT_BUS.register(SpellCraftEventBusSubscriber.class);
        MinecraftForge.EVENT_BUS.register(CapabilityAttachEventHandler.class);
        MinecraftForge.EVENT_BUS.register(ClientEventBusSubscriber.class);

        if(ExternalMods.CURIOS.isLoaded()) {
            InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.RING.getMessageBuilder().size(3).build());
        }
    }

    public static class SpellforgersCraftItemGroup extends ItemGroup{

        public static final ItemGroup instance = new SpellforgersCraftItemGroup(ItemGroup.GROUPS.length, "spellforgers_craft_tab");

        private SpellforgersCraftItemGroup(int index, String label) {
            super(index, label);
        }

        @Nonnull
        @Override
        public ItemStack createIcon() {
            return new ItemStack(SpellCraftItems.MANA_BUCKET.get());
        }
    }

}
