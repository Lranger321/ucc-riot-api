package ucc.service;

import ucc.dto.PlayerStat;

import java.util.List;

public interface MatchService {

    List<PlayerStat> getPlayersStat();
}
