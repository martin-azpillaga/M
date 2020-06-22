extends Node

export var Value : float

func get_class(): return "inputValue"
func is_class(type): return type == "inputValue" || .is_class(type)
