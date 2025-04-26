package ucc.mapper;

import com.riot.api.model.MatchDto;
import com.riot.api.model.ParticipantDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ucc.persistence.model.GameMode;
import ucc.persistence.model.Match;
import ucc.persistence.model.Participant;

import java.util.List;

@Mapper
public interface MatchMapper {

    @Mapping(target = "gameMode", source = "info.gameMode")
    @Mapping(target = "id", source = "info.gameId")
    @Mapping(target = "participant", ignore = true)
    Match mapToMatch(MatchDto matchDto);

    @Mapping(target = "gameMode", source = "matchDto.info.gameMode")
    @Mapping(target = "id", source = "matchDto.info.gameId")
    @Mapping(target = "participant", source = "participantDto")
    Match mapToMatch(MatchDto matchDto, ParticipantDto participantDto);

    Participant mapToParticipant(ParticipantDto participantDto);

    List<Participant> mapToParticipantList(List<ParticipantDto> participantDtoList);

    GameMode gameMode(com.riot.api.model.GameMode gameMode);
}
