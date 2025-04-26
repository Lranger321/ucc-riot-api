package ucc.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GameMode {
    CLASSIC("CLASSIC"),
    ARAM("ARAM"),
    ONEFORALL("ONEFORALL"),
    ASCENSION("ASCENSION"),
    URF("URF"),
    DOOMBOTS("DOOMBOTS"),
    NIGHTMARE_BOT("NIGHTMARE_BOT"),
    SHOWDOWN("SHOWDOWN"),
    BOT("BOT"),
    TUTORIAL("TUTORIAL"),
    CHERRY("CHERRY");

    private final String value;
}
