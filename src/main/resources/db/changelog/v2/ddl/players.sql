-- liquibase formatted sql

-- changeset author:lranger
CREATE TABLE players (
    puuid VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    tag_line VARCHAR(255) NOT NULL
);