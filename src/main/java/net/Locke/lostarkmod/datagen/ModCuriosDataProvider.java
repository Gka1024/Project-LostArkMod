package net.Locke.lostarkmod.datagen;

import top.theillusivec4.curios.api.CuriosDataProvider;
import top.theillusivec4.curios.api.type.capability.ICurio;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class ModCuriosDataProvider extends CuriosDataProvider {

    public ModCuriosDataProvider(String modId, PackOutput output,
            ExistingFileHelper fileHelper,
            CompletableFuture<HolderLookup.Provider> registries) {
        super(modId, output, fileHelper, registries);
    }

    @Override
    public void generate(HolderLookup.Provider registries, ExistingFileHelper fileHelper) {
        this.createEntities("lostark_slots")
            .addPlayer()
            .addSlots("ability_stone", "bracelet_lostark", "earings_lostark", "necklace_lostark", "rings_lostark", "sholder_lostark");

        this.createSlot("sholder_lostark")
        .order(1)
        .size(1)
        .addCosmetic(false)
        .icon(new ResourceLocation("lostarkmod", "slot/sholder_lostark_icon")) // 아이콘
        .renderToggle(false) // 렌더 토글 여부
        .dropRule(ICurio.DropRule.ALWAYS_KEEP);

        this.createSlot("necklace_lostark")
        .order(2)
        .size(1)
        .addCosmetic(false)
        .icon(new ResourceLocation("lostarkmod", "slot/necklace_lostark_icon")) // 아이콘
        .renderToggle(false) // 렌더 토글 여부
        .dropRule(ICurio.DropRule.ALWAYS_KEEP);

        this.createSlot("earings_lostark")
        .order(3)
        .size(2)
        .addCosmetic(false)
        .icon(new ResourceLocation("lostarkmod", "slot/earing_lostark_icon")) // 아이콘
        .renderToggle(false) // 렌더 토글 여부
        .dropRule(ICurio.DropRule.ALWAYS_KEEP);

        this.createSlot("rings_lostark")
        .order(4)
        .size(2)
        .addCosmetic(false)
        .icon(new ResourceLocation("lostarkmod", "slot/ring_lostark_icon")) // 아이콘
        .renderToggle(false) // 렌더 토글 여부
        .dropRule(ICurio.DropRule.ALWAYS_KEEP);

        this.createSlot("ability_stone") // 슬롯의 식별자 및 파일 이름
        .order(5) // 주문
        .size(1) // 크기
        .addCosmetic(false) // 장식 슬롯 여부
        .icon(new ResourceLocation("lostarkmod", "slot/ability_stone_icon")) // 아이콘
        .renderToggle(false) // 렌더 토글 여부
        .dropRule(ICurio.DropRule.ALWAYS_KEEP);

        this.createSlot("bracelet_lostark") // 슬롯의 식별자 및 파일 이름
        .order(6) // 주문
        .size(1) // 크기
        .addCosmetic(false) // 장식 슬롯 여부
        .icon(new ResourceLocation("lostarkmod", "slot/bracelet_lostark_icon")) // 아이콘
        .renderToggle(false) // 렌더 토글 여부
        .dropRule(ICurio.DropRule.ALWAYS_KEEP);

    }
}