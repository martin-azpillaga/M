# Data oriented design

Data oriented design is a programming paradigm published in the early 2000's. Since its inception it has proven to be specially well suited for game development projects.

## Principles

Clear separation between data and logic.

### Entities

All the data of your game is represented by entities.

    ball has mass 10, position 0 0, appearance sprite.cute.

### Relations

Relations are special kinds of entities.

### Systems

Systems are the part of the game that define the interactions.

    respawn:
    for all entity a with enter respawnSensor
    {
        a.position = a.respawnPoint
    }
