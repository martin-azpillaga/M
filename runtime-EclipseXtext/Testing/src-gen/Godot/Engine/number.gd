extends Node

export var Value : float

func get_class(): return "number"
func is_class(type): return type == "number" || .is_class(type)
