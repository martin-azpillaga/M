# Data oriented design

Data oriented design is a programming paradigm published in the early 2000's. Since its inception it has proven to be specially well suited for game development projects.

## Core principles

As the name suggests, this paradigm shifts the focus from the code to the actual data that represents your project. This is achieved by the fundamental principle of data oriented design:

* the data and the logic are completely separated.

Entity componen system (ECS for short) frameworks that handle the memory layout and the data accesses. Components hold data, systems hold logic, entities are abstract agregations of components that can be understood as a single discernable object in the game.


## Benefits

Easier to multithread.

Cache friendly. Huge performance boost.

Mantainability thanks to separation of concerns. No spaghetti code.

Freedom for designers and artists. No matter how a scene or world is set up, the code will not crash as the code cannot make any assumptions about the state of the world.

### Components
All the data of your game is represented by components. Each component has a name and a type. The type defines the possible values that can be stored in such entity. Number, text, image or mesh are all instances of component types. 
Let's consider a component named 
Once the component type has been decided, you can create multiple instances of that component. 

### Entities


    ball has mass 10, position 0 0, appearance sprite.cute.

#### Relations

Relations are special kinds of entities.

### Systems

Systems are the part of the game that define the interactions.

    respawn:
    for all entity a with enter respawnSensor
    {
        a.position = a.respawnPoint
    }
