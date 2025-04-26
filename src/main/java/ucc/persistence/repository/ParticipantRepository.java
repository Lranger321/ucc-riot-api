package ucc.persistence.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import ucc.persistence.model.Participant;

import java.util.List;

@Mapper
public interface ParticipantRepository {

    @Insert("""
            <script>
            INSERT INTO participants (
                        match_id,
                        champion_name,
                        kills,
                        deaths,
                        assists,
                        team_id,
                        win,
                        team_position,
                        riot_id_game_name,
                        placement,
                        penta_kills,
                        quadra_kills,
                        total_damage_dealt_to_champions
                    ) VALUES
                    <foreach collection="participants" item="participant" separator=",">
                        (
                            #{matchId},
                            #{participant.championName},
                            #{participant.kills},
                            #{participant.deaths},
                            #{participant.assists},
                            #{participant.teamId},
                            #{participant.win},
                            #{participant.teamPosition},
                            #{participant.riotIdGameName},
                            #{participant.placement},
                            #{participant.pentaKills},
                            #{participant.quadraKills},
                            #{participant.totalDamageDealtToChampions}
                        )
                    </foreach>
            </script>
            """)
    void saveAll(@Param("participants") List<Participant> participants,
                 @Param("matchId") String matchId);
}
