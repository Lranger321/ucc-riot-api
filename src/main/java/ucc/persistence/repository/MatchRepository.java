package ucc.persistence.repository;

import org.apache.ibatis.annotations.*;
import ucc.persistence.model.Match;

@Mapper
public interface MatchRepository {

    @Insert("""
            INSERT INTO matches (id, game_mode)
                    VALUES (#{id}, #{gameMode})
            """)
    void saveMatch(Match match);

    @Select("""
            SELECT EXISTS(SELECT 1 FROM matches WHERE id = #{matchId})
            """)
    Boolean containsMatch(String matchId);

    @Select("""
                SELECT
                    m.id,
                    m.game_mode,
                    p.champion_name         AS p_champion_name,
                    p.kills                  AS p_kills,
                    p.deaths                 AS p_deaths,
                    p.assists                AS p_assists,
                    p.team_id                AS p_team_id,
                    p.win                    AS p_win,
                    p.placement              AS p_placement,
                    p.team_position          AS p_team_position,
                    p.riot_id_game_name      AS p_riot_id_game_name,
                    p.penta_kills            AS p_penta_kills,
                    p.quadra_kills           AS p_quadra_kills,
                    p.total_damage_dealt_to_champions AS p_total_damage_dealt_to_champions
                FROM matches m
                INNER JOIN participants p ON m.id = p.match_id
                WHERE m.id = #{matchId}
                  AND p.riot_id_game_name = #{name}
            """)
    @ResultMap("matchResultMap")
    Match getMatchByIdAndPlayerName(
            @Param("matchId") String matchId,
            @Param("name") String name
    );
}
