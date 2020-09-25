using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using Unity.Entities;
using Unity.Physics;
using Unity.Transforms;
using Unity.Collections;
using Unity.Mathematics;

public class Test : SystemBase
{
    protected override void OnUpdate()
    {
        var physicsWorldSystem = World.DefaultGameObjectInjectionWorld.GetExistingSystem<Unity.Physics.Systems.BuildPhysicsWorld>();
        var collisionWorld = physicsWorldSystem.PhysicsWorld.CollisionWorld;

        Entities.ForEach((PhysicsCollider collider, Translation translation) =>
        {
            unsafe
            {
                var pointer = collider.ColliderPtr;
                var collisions = new NativeList<ColliderCastHit>(Allocator.Temp);
                collisionWorld.CastCollider(new ColliderCastInput
                {
                    Collider = pointer,
                    Orientation = quaternion.identity,
                    Start = new float3(0,0,0),
                    End = new float3(0,0,0)
                }, ref collisions);

                Debug.Log(collisions.Length);

                collisions.Dispose();
            }
        }).WithoutBurst().Run();
    }
}
