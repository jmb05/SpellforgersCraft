package net.jmb19905.spellforgers_craft.core;

import net.jmb19905.spellforgers_craft.common.capability.Mana;
import net.jmb19905.spellforgers_craft.common.capability.ManaCapability;
import net.jmb19905.spellforgers_craft.common.fluids.SpellCraftFluids;
import net.jmb19905.spellforgers_craft.common.items.SpellCraftItems;
import net.jmb19905.spellforgers_craft.common.network.ManaMessage;
import net.jmb19905.spellforgers_craft.common.network.NetworkStartup;
import net.jmb19905.spellforgers_craft.common.recipes.fluid.FluidRecipe;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.HashMap;

public class SpellCraftEventBusSubscriber {

    @SubscribeEvent
    public static void onPlayerRightClick(PlayerInteractEvent.RightClickItem event){
        System.out.println("Fired ModEventBusSubscriber#onPlayerRightClick");
        World world = event.getWorld();
        PlayerEntity playerEntity = event.getPlayer();
        ItemStack heldItemStack = playerEntity.getHeldItemMainhand();
        if(heldItemStack.getItem() == Items.GLASS_BOTTLE){
            BlockRayTraceResult rayTrace = Util.rayTraceBlocks(world, playerEntity, RayTraceContext.FluidMode.SOURCE_ONLY, 5);
            if(world.getBlockState(rayTrace.getPos()).getBlock() == SpellCraftFluids.MANA_FLUID_BLOCK.get()) {
                if (!playerEntity.isCreative()) {
                    heldItemStack.setCount(heldItemStack.getCount() - 1);
                }
                playerEntity.addItemStackToInventory(new ItemStack(SpellCraftItems.MANA_BOTTLE.get()));
            }
        }
    }

    @SubscribeEvent
    public static void onEntityJoin(EntityJoinWorldEvent event){
        if(!event.getWorld().isRemote){
            if(event.getEntity() instanceof ItemEntity){
                ItemEntity itemEntity = (ItemEntity) event.getEntity();
                for(IRecipe<?> irecipe : event.getWorld().getRecipeManager().getRecipes()){
                    if(irecipe instanceof FluidRecipe){
                        FluidRecipe recipe = (FluidRecipe) irecipe;
                        for(ItemStack itemStack : recipe.getInput().getMatchingStacks()){
                            if(itemEntity.getItem().getItem() == itemStack.getItem() && !(itemEntity instanceof FluidCraftableItemEntity)){
                                event.setCanceled(true);
                                FluidCraftableItemEntity specialItemEntity = new FluidCraftableItemEntity(event.getWorld(), itemEntity.getPosX(), itemEntity.getPosY(), itemEntity.getPosZ(), itemEntity.getItem(), recipe.getFluid(), recipe.getRecipeOutput().getItem(), recipe.getKnockback());
                                specialItemEntity.setDelay(recipe.getDelay());
                                specialItemEntity.setPickupDelay(40);
                                specialItemEntity.setMotion(itemEntity.getMotion());
                                event.getWorld().addEntity(specialItemEntity);
                            }
                        }
                    }
                }
            }else if(event.getEntity() instanceof ServerPlayerEntity){
                ServerPlayerEntity playerEntity = (ServerPlayerEntity) event.getEntity();
                Mana mana = playerEntity.getCapability(ManaCapability.CAPABILITY_MANA).orElse(null);
                ManaMessage msg = new ManaMessage(mana.getMana(), mana.getMaxMana());
                NetworkStartup.simpleChannel.send(PacketDistributor.PLAYER.with(() -> playerEntity), msg);
            }
        }
    }

    private static final HashMap<PlayerEntity, Integer> lastMana = new HashMap<>();
    private static final HashMap<PlayerEntity, Integer> lastMaxMana = new HashMap<>();

    @SubscribeEvent
    public static void onWorldTick(TickEvent.WorldTickEvent event){
        World world = event.world;
        if(!world.isRemote){
            for(PlayerEntity playerEntity : world.getPlayers()){
                if(playerEntity instanceof ServerPlayerEntity){
                    Mana mana = playerEntity.getCapability(ManaCapability.CAPABILITY_MANA).orElse(null);
                    try {
                        if (lastMana.get(playerEntity) != mana.getMana() || lastMaxMana.get(playerEntity) != mana.getMaxMana()) {
                            ManaMessage msg = new ManaMessage(mana.getMana(), mana.getMaxMana());
                            NetworkStartup.simpleChannel.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) playerEntity), msg);
                        }
                    }catch (NullPointerException e){
                        //happens on the first game tick
                    }
                    lastMana.put(playerEntity, mana.getMana());
                    lastMaxMana.put(playerEntity, mana.getMaxMana());
                }
            }
        }
    }

}
