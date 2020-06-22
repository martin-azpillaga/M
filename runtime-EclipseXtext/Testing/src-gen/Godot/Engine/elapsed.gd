extends Node

export var Value : float

func get_class(): return "elapsed"
func is_class(type): return type == "elapsed" || .is_class(type)
