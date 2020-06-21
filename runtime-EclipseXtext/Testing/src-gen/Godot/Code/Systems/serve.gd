extends Node

func _process(delta):
	var root = get_tree().get_root().get_child(0)
	for a in root.entities(["serveAngle","serveLength","z","RigidBody","timeout"]):
		var entity_a = a["entity"]
		var serveAngle_a = a["serveAngle"];
		var serveLength_a = a["serveLength"];
		var z_a = a["z"];
		var velocity_a = a["RigidBody"];
		var timeout_a = a["timeout"];
		if timeout_a.Value:
			var angle = rand_range(serveAngle_a.Value.x,serveAngle_a.Value.y)
			
			var length = rand_range(serveLength_a.Value.x,serveLength_a.Value.y)
			
			velocity_a.linear_velocity = Vector3(cos(angle), sin(angle), z_a.Value) * length
			
			timeout_a.queue_free();
