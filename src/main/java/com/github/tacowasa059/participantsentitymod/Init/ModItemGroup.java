package com.github.tacowasa059.participantsentitymod.Init;

import com.github.tacowasa059.participantsentitymod.ParticipantsEntityMod;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class ModItemGroup extends ItemGroup {

    public static final ModItemGroup ParticipantsEntity = new ModItemGroup(ModItemGroup.GROUPS.length,
            ParticipantsEntityMod.MOD_ID);

    public ModItemGroup(int index, String label) {
        super(index, label);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(Items.PLAYER_HEAD);
    }

}