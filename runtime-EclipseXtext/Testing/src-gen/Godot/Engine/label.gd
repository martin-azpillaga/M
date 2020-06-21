extends Node

export var Value : String

func get_class(): return "label"
func is_class(type): return type == "label" || .is_class(type)
