extends Node

export var Value : String

func get_class(): return "input"
func is_class(type): return type == "input" || .is_class(type)
