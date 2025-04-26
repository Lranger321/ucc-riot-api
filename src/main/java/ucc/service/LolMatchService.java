package ucc.service;

import com.ucc.api.model.PlayerDto;
import ucc.persistence.model.Match;
import ucc.riot.model.request.MatchesVariable;

import java.util.List;

public interface LolMatchService {

    List<Match> getMatchesForPlayer(PlayerDto player, MatchesVariable matchesVariable);
}
