package net.Locke.lostarkmod.command.party;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import net.minecraft.server.level.ServerPlayer;

public class PartyManager {
    private static final Map<String, Set<UUID>> parties = new HashMap<>(); // 파티 이름과 플레이어 UUID 집합

    // 플레이어가 속한 파티 이름 반환
    public static String getPartyOfPlayer(ServerPlayer player) {
        UUID playerUUID = player.getUUID();
        for (Map.Entry<String, Set<UUID>> entry : parties.entrySet()) {
            if (entry.getValue().contains(playerUUID)) {
                return entry.getKey(); // 해당 플레이어가 속한 파티 이름 반환
            }
        }
        return null; // 속한 파티가 없을 경우
    }

    // 파티에서 플레이어 제거
    public static boolean removePlayerFromParty(ServerPlayer player, String partyName) {
        UUID playerUUID = player.getUUID();
        Set<UUID> partyMembers = parties.get(partyName);

        if (partyMembers != null && partyMembers.contains(playerUUID)) {
            partyMembers.remove(playerUUID); // 플레이어 제거

            // 파티가 비었으면 삭제
            if (partyMembers.isEmpty()) {
                parties.remove(partyName);
            }
            return true;
        }
        return false;
    }

    // 파티에 플레이어를 추가하는 메서드
    public static void addPlayerToParty(ServerPlayer player, String partyName) {
        parties.computeIfAbsent(partyName, k -> new HashSet<>()).add(player.getUUID());
    }

    // 파티장을 변경하기 위해 해시맵을 변경하는 메서드
    public static void changePartyChief(ServerPlayer player, String originalName)
    {
        String newName = player.getName().toString();
        Set<UUID> set = parties.get(originalName);

        parties.remove(originalName);
        parties.put(newName, set);
    }
}
