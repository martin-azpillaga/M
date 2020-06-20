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
		
		for component in entity.get_children()+[entity]:
			for type in left:
				if component.get_class() == type:
					current[type] = component
					left.erase(type)
	
		if current.size() == archetype.size():
			result.append(current)
	
	return result