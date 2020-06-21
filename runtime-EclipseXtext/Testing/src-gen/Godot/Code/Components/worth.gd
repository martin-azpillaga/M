extends Node

export var Value: float

func get_class(): return "worth"
func is_class(type): return type == get_class() || .is_class(type)
