package dylan.gilbey.item;

import dylan.gilbey.TestMod;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModItems {

    public static final Item COTTON = registerItem("cotton", Item::new, new Item.Settings());

    // Item registering helper method
    public static Item registerItem(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        // Create the item key.
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(TestMod.MOD_ID, name));
        // Create the item instance.
        Item item = itemFactory.apply(settings.registryKey(itemKey));
        // Register the item.
        Registry.register(Registries.ITEM, itemKey, item);

        return item;
    }


    public static void registerModItems() {
        TestMod.LOGGER.info("Registering items for " + TestMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(COTTON);
        });
    }
}