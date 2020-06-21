extends Node

export var Value: String

func get_class(): return "template"
func is_class(type): return type == get_class() || .is_class(type)
