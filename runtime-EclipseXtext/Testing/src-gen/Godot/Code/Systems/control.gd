extends Node

func _process(delta):
	var root = get_tree().get_root().get_child(0)
	for a in root.entities(["RigidBody","position"]):
		var entity_a = a["entity"]
		var velocity_a = a["RigidBody"];
		var position_a = a["position"];
		velocity_a.linear_velocity = position_a.Value
