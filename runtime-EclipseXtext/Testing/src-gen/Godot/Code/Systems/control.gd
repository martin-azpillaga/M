extends Node

func _process(delta):
	var root = get_tree().get_root().get_child(0)
	for a in root.entities(["inputValue","baseVelocity","RigidBody"]):
		var entity_a = a["entity"]
		var inputValue_a = a["inputValue"];
		var baseVelocity_a = a["baseVelocity"];
		var velocity_a = a["RigidBody"];
		velocity_a.linear_velocity = baseVelocity_a.Value * inputValue_a.Value
