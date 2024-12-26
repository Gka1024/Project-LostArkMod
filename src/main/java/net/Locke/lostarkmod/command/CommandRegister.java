package net.Locke.lostarkmod.command;

import com.mojang.brigadier.CommandDispatcher;

import net.Locke.lostarkmod.command.party.PartyCommand;
import net.minecraft.commands.CommandSourceStack;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CommandRegister {
    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();
        PartyCommand.register(dispatcher);
    }
}