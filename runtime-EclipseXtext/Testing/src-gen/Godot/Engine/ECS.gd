extends Node

func entities(archetype):
	var result = []
	
	var root : Node = get_tree().get_root().get_children()[0]
	var entities = root.get_node("Entities")
	
	for entity in entities.get_children():
		var left = []
		for v in archetype:
			left.append(v)
		
		var current = {}
		
		for component in [entity]+entity.get_children():
			var found = []
			for type in left:
				if component.is_class(type):
					current[type] = component
					found.append(type)
			for f in found:
				left.erase(f)
		if current.size() == archetype.size():
			current["entity"] = entity
			result.append(current)
	return result

func _process(delta):
	for entity in entities(["timer","elapsed","timeout"]):
		var timer = entity["timer"]
		var elapsed = entity["elapsed"]
		var timeout = entity["timeout"]
		
		var next = elapsed.Value + delta
		elapsed.Value = fmod(next, timer.Value)
		
		timeout.Value = next >= timer.Value
	
	for entity in entities(["input","inputValue"]):
		var input = entity["input"]
		var inputValue = entity["inputValue"]
		
		var actions = input.Value.split("-")
		
		var n = actions.size()
		
		if n == 1:
			inputValue.Value = Input.get_action_strength(input.Value)
		elif n == 2:
			var plus = actions[0]
			var minus = actions[1]
			
			inputValue.Value = Input.get_action_strength(plus) - Input.get_action_strength(minus)

	for entity in entities(["RigidBody","collisions"]):
		var body = entity["RigidBody"]
		var collisions = entity["collisions"]
		
		collisions.Value = body.get_colliding_bodies()
		if not collisions.Value.empty():
			print(collisions.Value)
	
	for a in entities(["number", "label"]):
		var number = a["number"]
		var label = a["label"]
		for b in entities(["Label"]):
			var ui = b["Label"]
			if ui.name == label.Value:
				ui.text = str(number.Value)
