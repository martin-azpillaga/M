extends Node

export var Value: Vector3

func get_class(): return "baseVelocity"
func is_class(type): return type == get_class() || .is_class(type)
