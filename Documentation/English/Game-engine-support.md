# Game engine support

Currently, only the [Unity](
https://unity.com/) game engine is supported with the hybrid Unity ECS approach. However, the framework is built with extensibility in mind.

## Unity support

The following properties of built-in components are supported: 

* Transforms
    * Position
    * Rotation
    * Scale
    * ScreenPosition
  
* Rendering
    * 2D
        * Sprite
        * Material

    * 3D
        * Mesh
        * Material

    * Camera
        * Field of view
        * Orthographic
        * Near and far planes

    * UI
        * Text
        * Font

* Physics
    * Colliders
        * Extents
        * Radius
  
    * Rigidbodies
        * Mass
        * Friction
        * Restitution
        * Kinematic 
        * Body

* Time
    * Timers
    * Animation?

* Artificial intelligence

* Input
    * Keyboard
    * Mouse
    * Gamepad

* Audio
    * Audio Listener
    * Audio Source
        * Clip
        * Volume
        * Pitch
  
* Networking