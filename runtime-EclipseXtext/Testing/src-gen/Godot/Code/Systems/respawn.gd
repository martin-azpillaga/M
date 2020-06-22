extends Node

func _process(delta):
	var root = get_tree().get_root().get_child(0)
	for a in root.entities(["template"]):
		var entity_a = a["entity"]
		var template_a = a["template"];
		for b in root.entities(["collisions"]):
			var entity_b = b["entity"]
			var collisions_b = b["collisions"];
			if entity_a in collisions_b.Value:
				root.get_node("Entities").add_child(load(template_a.Value).instance());
				entity_a.queue_free();
