package net.Locke.lostarkmod.command.party;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;

public class PartyCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                net.minecraft.commands.Commands.literal("party")
                        .then(net.minecraft.commands.Commands.literal("create")
                                .executes(context -> createParty(context.getSource())))
                        .then(net.minecraft.commands.Commands.literal("join")
                                .then(net.minecraft.commands.Commands
                                        .argument("player", net.minecraft.commands.arguments.EntityArgument.player())
                                        .executes(context -> joinParty(context.getSource(),
                                                net.minecraft.commands.arguments.EntityArgument.getPlayer(context,
                                                        "player")))))
                        .then(net.minecraft.commands.Commands.literal("leave")
                                .executes(context -> leaveParty(context.getSource())))
                        .then(net.minecraft.commands.Commands.literal("give")
                                .then(net.minecraft.commands.Commands
                                        .argument("player", net.minecraft.commands.arguments.EntityArgument.player())
                                        .executes(context -> giveParty(context.getSource(),
                                                net.minecraft.commands.arguments.EntityArgument.getPlayer(context,
                                                        "player")))))
                        .then(net.minecraft.commands.Commands.literal("kick")
                                .then(net.minecraft.commands.Commands
                                        .argument("player", net.minecraft.commands.arguments.EntityArgument.player())
                                        .executes(context -> kickOnParty(context.getSource(),
                                                net.minecraft.commands.arguments.EntityArgument.getPlayer(context,
                                                        "player"))))));
    }

    private static int createParty(CommandSourceStack source) {
        ServerPlayer player = source.getPlayer();
        if (player != null) {
            PartyManager.addPlayerToParty(player, player.getName().toString());
            source.sendSuccess(() -> Component.literal("파티가 생성되었습니다."), true);
        }
        return 1;
    }

    private static int joinParty(CommandSourceStack source, ServerPlayer targetPlayer) {
        ServerPlayer player = source.getPlayer();
        if (player != null) {
            PartyManager.addPlayerToParty(player, targetPlayer.getName().toString());
            source.sendSuccess(() -> Component.literal(targetPlayer.getName().getString() + "의 파티에 참가했습니다."), true);
        }
        return 1;
    }

    private static int leaveParty(CommandSourceStack source) {
        ServerPlayer player = source.getPlayer();
        String partyName = PartyManager.getPartyOfPlayer(player);

        if (partyName == null) {
            source.sendSuccess(() -> Component.literal("파티에 속해있지 않습니다."), false);
            return 0;
        }

        boolean isRemoved = PartyManager.removePlayerFromParty(player, partyName);
        if (isRemoved) {
            source.sendSuccess(() -> Component.literal("파티를 탈퇴했습니다."), true);
        } else {
            source.sendFailure(Component.literal("파티를 탈퇴하는데 실패했읍니다. 관리자에게 문의 ㄱㄱ"));
        }

        return 1;
    }

    private static int kickOnParty(CommandSourceStack source, ServerPlayer targetPlayer)
    {
        return 1;
    }

    private static int giveParty(CommandSourceStack source, ServerPlayer targetPlayer) {
        ServerPlayer player = source.getPlayer();

        if (!player.getName().toString().equalsIgnoreCase(PartyManager.getPartyOfPlayer(player))) {
            source.sendFailure(Component.literal("파티장이 아닙니다!"));
            return 0;
        }

        if (player.getName().toString().equalsIgnoreCase(targetPlayer.getName().toString())) {
            source.sendFailure(Component.literal("본인에게 줄 수 없습니다!"));
            return 0;
        }

        PartyManager.changePartyChief(targetPlayer, player.getName().toString());
        source.sendSuccess(() -> Component.literal("파티장을 변경했습니다."), false);

        return 1;
    }
}