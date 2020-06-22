extends Node

export var Value : bool

func get_class(): return "timeout"
func is_class(type): return type == "timeout" || .is_class(type)
