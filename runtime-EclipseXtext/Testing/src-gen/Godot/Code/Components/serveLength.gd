extends Node

export var Value: Vector2

func get_class(): return "serveLength"
func is_class(type): return type == get_class() || .is_class(type)
