extends Node

func _ready():
	var root = get_tree().get_root().get_child(0)
	for entity in root.entities(["RigidBody2D","inputValue","baseVelocity"]):
		var body = entity["RigidBody2D"]
		var base = entity["baseVelocity"]
		var input = entity["inputValue"]
		
		print(base.Value)
		print(input.Value)
		
		body.linear_velocity = Vector2(base.Value.x * input.Value, base.Value.y * input.Value)
	
