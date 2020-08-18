using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using Unity.Mathematics;
using Unity.Jobs;
using Unity.Burst;
using Unity.Collections;
using System.Linq;

public class Burst : MonoBehaviour
{
    // Gets updated whenever an entity is created or destroyed, not every frame
    List<GameObject> gos;

    NativeList<control_a_read> control_a_reads;
    NativeList<control_a_write> control_a_writes;
    List<control_a_go> control_a_gameObjects;
    
    void Start()
    {

        control_a_reads = new NativeList<control_a_read>(Allocator.Persistent);
        control_a_writes = new NativeList<control_a_write>(Allocator.Persistent);
        control_a_gameObjects = new List<control_a_go>();
    }

    void OnDestroy()
    {
        control_a_reads.Dispose();
        control_a_writes.Dispose();
    }

    void Update()
    {
        gos = Object.FindObjectsOfType<Transform>().Select(x=>x.gameObject).ToList();
        
        foreach (var go in gos)
        {
            var rigidbody = go.GetComponent<Rigidbody>();
            var baseVel = go.GetComponent<M._base>();
            var readInput = go.GetComponent<M.readInput>();

            if (rigidbody != null && baseVel != null && readInput != null)
            {
                control_a_reads.Add(new control_a_read{baseVel = baseVel.Value, readInput = readInput.Value});
                control_a_gameObjects.Add(new control_a_go{rigidbody = rigidbody});
            }
        }
        
        var job = new Control{ reads = control_a_reads, writes = control_a_writes};

        var handle = job.Schedule();

        handle.Complete();

        for (var i = 0; i < control_a_writes.Length; i++)
        {
            var a = control_a_writes[i];
            control_a_gameObjects[a.index].rigidbody.velocity = a.velocity;
        }

        control_a_reads.Clear();
        control_a_writes.Clear();
    }
}


struct Control : IJob
{
    public NativeList<control_a_read> reads;
    public NativeList<control_a_write> writes;

    public void Execute()
    {
        for (var i = 0; i < reads.Length; i++)
        {
            var a = reads[i];
            var write = new control_a_write{velocity = a.baseVel * a.readInput, index = i};
            writes.Add(write);
        }
    }
}

struct control_a_read
{
    public float3 baseVel;
    public float readInput;
}

struct control_a_write
{
    public float3 velocity;
    public int index;
}

struct control_a_go
{
    public Rigidbody rigidbody;
}