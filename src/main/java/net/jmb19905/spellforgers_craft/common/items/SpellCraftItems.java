package net.jmb19905.spellforgers_craft.common.items;

import net.jmb19905.spellforgers_craft.SpellforgersCraft;
import net.jmb19905.spellforgers_craft.common.fluids.SpellCraftFluids;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SpellCraftItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SpellforgersCraft.MODID);

    public static final RegistryObject<BucketItem> MANA_BUCKET = ITEMS.register("mana_bucket", () -> new BucketItem(SpellCraftFluids.MANA_FLUID, new Item.Properties().maxStackSize(1).group(SpellforgersCraft.SpellforgersCraftItemGroup.instance)));

    public static final RegistryObject<Item> MANA_BOTTLE = ITEMS.register("mana_bottle", ManaBottle::new);

    public static final RegistryObject<Item> BLANK_SPELLBOOK = ITEMS.register("blank_spellbook", () -> new Item(new Item.Properties().rarity(Rarity.RARE).maxStackSize(64).group(SpellforgersCraft.SpellforgersCraftItemGroup.instance)));

    public static final RegistryObject<Item> MANAMETER = ITEMS.register("manameter", ManameterItem::new);

    public static final RegistryObject<Item> BASIC_MANA_RING = ITEMS.register("basic_mana_ring",() -> new ManaRing(1200));

    public static final RegistryObject<Item> ADVANCED_MANA_RING = ITEMS.register("advanced_mana_ring", () -> new ManaRing(20));

}
