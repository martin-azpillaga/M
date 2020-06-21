extends Node

func _process(delta):
	var root = get_tree().get_root().get_child(0)
	for a in root.entities(["worth"]):
		var entity_a = a["entity"]
		var worth_a = a["worth"];
		for b in root.entities(["number","collisions"]):
			var entity_b = b["entity"]
			var number_b = b["number"];
			var collisions_b = b["collisions"];
			if entity_a in collisions_b.Value:
				number_b.Value = number_b.Value + worth_a.Value
