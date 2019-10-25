package m.transformation

import java.util.ArrayList
import java.util.HashMap
import java.util.HashSet


		
class Simplex 
{
	public ArrayList<ArrayList<Float>> matrix
	public ArrayList<HashMap<String,Float>> inequalities = new ArrayList<HashMap<String,Float>>
	public ArrayList<HashMap<String,Float>> equalities = new ArrayList<HashMap<String,Float>>
	public HashMap<String,Float> objective = new HashMap<String,Float>
	public HashMap<String,Float> solution = new HashMap<String,Float>
	public ArrayList<String> ordered = new ArrayList<String>
	
	new (ArrayList<ArrayList<Float>> matrix)
	{
		this.matrix = matrix	
	}
	
	new(){}
	
	def reduce()
	{
		var sustitute = equalities.findFirst[it.keySet.size == 2]
		while (sustitute !== null)
		{
			equalities.remove(sustitute)
			
			var key = sustitute.keySet.findFirst[it !== 'value']
			var value = sustitute.get('value')/sustitute.get(key)
			
			solution.put(key,value)
			
			for (equality : equalities)
			{
				if (equality.containsKey(key))
				{
					equality.put('value', equality.get('value')-value*equality.get(key))
					equality.remove(key)
				}
			}
			for (inequality : inequalities)
			{
				if (inequality.containsKey(key))
				{
					inequality.put('value', inequality.get('value')-value*inequality.get(key))
					inequality.remove(key)
				}
			}
			if (objective.containsKey(key))
			{
				objective.remove(key)
			}
			
			sustitute = equalities.findFirst[it.keySet.size == 2]
		}
	}
	
	def equalitiesToInequalities()
	{
		for (equality : equalities)
		{
			inequalities.add(equality)
			var negative = new HashMap<String,Float>
			for (key : equality.keySet)
			{
				negative.put(key, -equality.get(key))
			}
			inequalities.add(negative)
		}
	}
	
	def convert()
	{
		var variables = new HashSet<String>
		variables.addAll(objective.keySet)
		for (inequality : inequalities)
		{
			variables.addAll(inequality.keySet)
		}
		variables.remove('value')
		var slacks = inequalities.size
		ordered = new ArrayList<String>
		ordered.addAll(variables)
		
		matrix = new ArrayList<ArrayList<Float>>
		var o = new ArrayList<Float>
		for (v : ordered)
		{
			if (objective.containsKey(v))
			{
				o.add(-objective.get(v))
			}
			else
			{
				o.add(0f)
			}
		}
		for (var i = 0; i < slacks; i++)
		{
			o.add(0f)
		}
		if (objective.containsKey('value'))
		{
			o.add(-objective.get('value'))
		}
		else
		{
			o.add(0f)
		}
		matrix.add(o)
		
		for (var i = 0; i < inequalities.size; i++)
		{
			var inequality = inequalities.get(i)
			var list = new ArrayList<Float>
			for (v : ordered)
			{
				if (inequality.containsKey(v))
				{
					list.add(inequality.get(v))
				}
				else
				{
					list.add(0f)
				}
			}
			for (var s = 0; s < slacks; s++)
			{
				if (s != i)
				{
					list.add(0f)
				}
				else
				{
					list.add(1f)
				}
			}
			if (inequality.containsKey('value'))
			{
				list.add(inequality.get('value'))
			}
			else
			{
				list.add(0f)
			}
			matrix.add(list)
		}
	}
	
	def solve()
	{
		// Select column
		var minValue = 0f
		var column = 0
		var objectiveRow = matrix.last
		for (var c = 0; c < objectiveRow.size; c++)
		{
			var value = objectiveRow.get(c)
			if (value < minValue)
			{
				minValue = value
				column = c
			}
		}
		
		while (minValue < 0)
		{
			// select row
			var minFactor = Float.MAX_VALUE
			var row = 0
			for (var r = 0; r < matrix.size-1; r++)
			{
				var value = matrix.get(r).last / matrix.get(r).get(column)
				if (value < minFactor)
				{
					minFactor = value
					row = r
				}
			}
			// set row column to one
			var selectedRow = matrix.get(row)
			var selectedCell = matrix.get(row).get(column)
			for (var c = 0; c < selectedRow.size; c++)
			{
				if (c == column)
				{
					selectedRow.set(c,1f)
				}
				else
				{
					selectedRow.set(c, selectedRow.get(c)/selectedCell)
				}
			}
			
			// make other lines zero
			for (var r = 0; r < matrix.size; r++)
			{
				var rowToChange = matrix.get(r)
				var factor = rowToChange.get(column)
				if (r != row)
				{
					for (var c = 0; c < rowToChange.size; c++)
					{
						if (c == column)
						{
							rowToChange.set(c, 0f)
						}
						else
						{
							rowToChange.set(c, rowToChange.get(c)-selectedRow.get(c)*factor)
						}
					}
				}
			}
			minValue = 0
			for (var c = 0; c < objectiveRow.size; c++)
			{
				var value = objectiveRow.get(c)
				if (value < minValue)
				{
					minValue = value
					column = c
				}
			}
		}
		for (var v = 0; v < ordered.size; v++)
		{
			for (var r = 0; r < matrix.size; r++)
			{
				var currentRow = matrix.get(r)
				if (currentRow.get(v) == 1f)
				{
					solution.put(ordered.get(v),currentRow.last)
				}
			}
		}
		return matrix
	}
	
}

/*
		var simplex = new Simplex
		var objective = new HashMap<String,Float>
		objective.put('a',1f)
		objective.put('b',1f)
		objective.put('c',1f)
		objective.put('value',0f)
		simplex.objective = objective
		var first = new HashMap<String,Float>
		first.put('c',1f)
		first.put('value',2f)
		simplex.equalities.add(first)
		var second = new HashMap<String,Float>
		second.put('a',1f)
		second.put('b',1f)
		second.put('c',-1f)
		second.put('value',0f)
		simplex.equalities.add(second)
		var bineq = new HashMap<String,Float>
		bineq.put('b',1f)
		bineq.put('value',3f)
		simplex.inequalities.add(bineq)
		var aineq = new HashMap<String,Float>
		aineq.put('a',1f)
		aineq.put('value',2f)
		simplex.inequalities.add(aineq)
		simplex.reduce
		simplex.equalitiesToInequalities
		simplex.convert
		simplex.solve
		
		var one = new ArrayList<Float>
		one.addAll(#[-1f,-1f,0f,0f,0f,0f])
		var two = new ArrayList<Float>
		two.addAll(#[1f,0f,1f,0f,0f,3f])
		var three = new ArrayList<Float>
		three.addAll(#[0f,1f,0f,1f,0f,4f])
		var four = new ArrayList<Float>
		four.addAll(#[1f,1f,0f,0f,1f,10f])
				
		var matrix = new ArrayList<ArrayList<Float>>
		matrix.addAll(#[one,two,three,four])
		var s = new Simplex
		s.ordered.addAll(#['a','b'])
		s.matrix = matrix
		s.solve()
		*/