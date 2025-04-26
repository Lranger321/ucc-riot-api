package ucc.persistence.repository;

import org.apache.ibatis.annotations.*;
import ucc.persistence.model.Player;

import java.util.List;

@Mapper
public interface PlayerRepository {

    @Select("""
            SELECT puuid,
                   name,
                   tag_line
            FROM players
            """)
    @ResultMap("playerResultMap")
    List<Player> getRegisteredPlayers();

    @Select("""
            <script>
            SELECT puuid,
                   name,
                   tag_line
            FROM players
            WHERE name IN (
             <foreach collection="players" item="player" separator=",">
             #{player}
             </foreach>
                        )
            </script>
            """)
    @ResultMap("playerResultMap")
    List<Player> getRegisteredPlayersByName(@Param("players") List<String> players);

    @Insert("""
            INSERT INTO players(puuid, name, tag_line)
            VALUES (#{player.puuid}, #{player.gameName}, #{player.tagLine})
            """)
    void regPlayer(@Param("player") Player player);

    @Delete("""
            DELETE players
            WHERE puuid = #{puuid}
            """)
    void deletePlayer(String puuid);
}
