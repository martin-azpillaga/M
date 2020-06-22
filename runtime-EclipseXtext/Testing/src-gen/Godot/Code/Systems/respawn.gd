extends Node

func _process(delta):
	var root = get_tree().get_root().get_child(0)
	for a in root.entities(["Spatial","RigidBody"]):
		var entity_a = a["entity"]
		var position_a = a["Spatial"];
		var velocity_a = a["RigidBody"];
		for b in root.entities(["collisions","respawnPoint","respawnVelocity"]):
			var entity_b = b["entity"]
			var collisions_b = b["collisions"];
			var respawnPoint_b = b["respawnPoint"];
			var respawnVelocity_b = b["respawnVelocity"];
			if entity_a in collisions_b.Value:
				position_a.translation = respawnPoint_b.Value
				
				velocity_a.linear_velocity = respawnVelocity_b.Value
				
				entity_a.add_child(preload("res://Engine/timeout.gd").new());
