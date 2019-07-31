# Game engine support

Currently, only the [Unity](
https://unity.com/) game engine is supported with the hybrid Unity ECS approach. However, the framework is built with extensibility in mind.

## Unity support

The following properties of built-in components are supported: 

* Transforms
    * Position
    * Rotation
    * Scale
  
* Rendering
    * 2D
        * Sprite
        * Material
        * Sorting order

    * 3D
        * Mesh
        * Material

    * Camera
        * View angle
        * View distance
        * Orthographic
        * Near and far planes
        * Clear color
        * Perspective

    * UI
        * Text
        * Font
        * Image
        * Button
        * Canvas
        * Canvas scaler
    * Lighting
        * Emission
        * Intensity
        * Range
        * Angle

* Physics
    * Colliders
        * Extents
        * Radius
        * Ray
    * Kinematics
        * Velocity
        * Acceleration
        * Force
  
    * Rigidbodies
        * Mass
        * Friction
        * Restitution
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
    * Ears
    * Audio Source
        * Clip
        * Volume
        * Pitch
  
* Networking