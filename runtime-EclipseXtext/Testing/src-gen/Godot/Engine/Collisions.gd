extends Node

export var Value : Array

func get_class(): return "collisions"
func is_class(type): return type == "collisions" || .is_class(type)
