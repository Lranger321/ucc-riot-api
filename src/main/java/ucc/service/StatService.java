package ucc.service;

import ucc.dto.PlayerStat;

import java.util.List;

public interface StatService {

    List<PlayerStat> getPlayersStat();

    List<PlayerStat> getPlayersStatForPlayers(List<String> players);
}
