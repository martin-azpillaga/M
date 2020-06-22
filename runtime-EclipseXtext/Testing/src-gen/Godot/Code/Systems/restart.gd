extends Node

func _process(delta):
	var root = get_tree().get_root().get_child(0)
	for a in root.entities(["number","maxNumber"]):
		var entity_a = a["entity"]
		var number_a = a["number"];
		var maxNumber_a = a["maxNumber"];
		if number_a.Value > maxNumber_a.Value:
			for b in root.entities(["number","resetNumber"]):
				print("restart")
				var entity_b = b["entity"]
				var number_b = b["number"];
				var resetNumber_b = b["resetNumber"];
				number_b.Value = resetNumber_b.Value
