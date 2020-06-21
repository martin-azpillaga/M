extends Node

export var Value : float

func get_class(): return "timer"
func is_class(type): return type == "timer" || .is_class(type)
