package m.generator

import m.model.Game
import org.eclipse.xtext.generator.IFileSystemAccess2
import m.model.Function
import m.model.Value
import java.util.List
import m.model.Statement
import m.model.Expression
import m.model.Binary
import m.model.Unary
import m.model.Delegation
import m.model.Cell
import m.model.Block
import m.model.Assignment
import m.model.Application

class Blocks
{
	def void generate(Game game, IFileSystemAccess2 fileSystem)
	{
		fileSystem.generateFile('Blocks/all.blocks',
		'''
		<xml xmlns="http://www.w3.org/1999/xhtml">
		  «game.functions.map[codeF].join('\n')»
		</xml>
		''')
	}
	
	def private codeF(Function function)
	{
		'''
		<block type="function">
		  <field name="name">«function.name»</field>
		  <value name="parameters">
		    «function.parameters.code(0)»
		  </value>
		  <statement name="statements">
		    «codeS(0, function.statements)»
		  </statement>
		  <value name="result">
		    «function.result.codeE»
		  </value>
		</block>
		'''
	}
	
	def private String code(List<Value> parameters, int index)
	{
		if (index < parameters.size)
		{
			'''
			<block type="parameter">
			  <field name="name">«parameters.get(index).name»</field>
			  <value name="next">
			    «parameters.code(index+1)»
			  </value>
			</block>
		    '''
		}
	}
	
	def private String codeS(int index, List<Statement> statements)
	{
		if (index < statements.size)
		{
			var statement = statements.get(index)
			if (statement instanceof Block)
			{
				'''
				<block type="block">
				  <field name="name">«statement.name»</field>
				  <value name="exoression">
				    «statement.expression.codeE»
				  </value>
				  <statement name="statements">
				    «codeS(0, statement.statements)»
				  </statement>
				  <next>
				    «codeS(index+1, statements)»
				  </next>
				</block>
				'''
			}
			else if (statement instanceof Assignment)
			{
				'''
				<block type="assignment">
				  <value name="atom">
				    «statement.atom.codeE»
				  </value>
				  <value name="expression">
				    «statement.expression.codeE»
				  </value>
				  <next>
				    «codeS(index+1, statements)»
				  </next>
				</block>
				'''
			}
			else if (statement instanceof Delegation)
			{
				'''
				<block type="delegation">
				  <value name="application">
				    «statement.application.codeE»
				  </value>
				  <next>
				    «codeS(index+1, statements)»
				  </next>
				</block>
				'''
			}
		}
	}
	
	def private String codeE(Expression expression)
	{
		if (expression instanceof Binary)
		{
			'''
			<block type="binary">
			  <field name="operator">«expression.operator»</field>
			  <value name="left">
			    «expression.left.codeE»
			  </value>
			  <value name="right">
			    «expression.right.codeE»
			  </value>
			</block>
			'''
		}
		else if (expression instanceof Unary)
		{
			'''
			<block type="unary">
			  <field name="operator">«expression.operator»</field>
			  <value name="expression">
			    «expression.expression.codeE»
			  </value>
			</block>
			'''
		}
		else if (expression instanceof Application)
		{
			'''
			<block type="application">
			  <field name="name">«expression.name»</field>
			  <value name="argument">
			  «expression.arguments.codeArguments(0)»
			  </value>
			</block>
			'''
		}
		else if (expression instanceof Value)
		{
			'''
			<block type="value">
			  <field name="name">«expression.name»</field>
			</block>
			'''
		}
		else if (expression instanceof Cell)
		{
			'''
			<block type="cell">
			  <field name="entity">«expression.entity»</field>
			  <field name="component">«expression.component»</field>
			</block>
			'''
		}
	}
	
	def private String codeArguments(List<Expression> arguments, int index)
	{
		if (index >= arguments.size)
		{

		}
		else if (index == arguments.size-1)
		{
			arguments.get(index).codeE
		}
		else
		{
			'''
			<block type="connector">
			  <value name="current">
			    «arguments.get(index).codeE»
			  </value>
			  <value name="next">
			    «arguments.codeArguments(index+1)»
			  </value>
			</block>
			'''
		}
	}
}