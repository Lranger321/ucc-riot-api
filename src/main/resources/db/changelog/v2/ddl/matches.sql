-- liquibase formatted sql

-- changeset lranger:matches
CREATE TABLE matches (
    id VARCHAR(255) PRIMARY KEY,
    game_mode VARCHAR(50) NOT NULL
);

CREATE TABLE participants (
    match_id VARCHAR(255) NOT NULL,
    champion_name VARCHAR(255) NOT NULL,
    kills INT NOT NULL,
    deaths INT NOT NULL,
    assists INT NOT NULL,
    team_id INT NOT NULL,
    win BOOLEAN NOT NULL,
    team_position VARCHAR(10),
    riot_id_game_name VARCHAR(255) NOT NULL,
    placement INT,
    penta_kills INT,
    quadra_kills INT,
    total_damage_dealt_to_champions INT NOT NULL,
    PRIMARY KEY (match_id, riot_id_game_name),
    FOREIGN KEY (match_id) REFERENCES matches(id) ON DELETE CASCADE
);