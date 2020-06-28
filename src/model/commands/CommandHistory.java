package model.commands;

import java.util.Stack;

import view.interfaces.IUndoable;

// Based on Professor Sharpe's BankAccount Command example in class
public class CommandHistory {
	
	private static final Stack<IUndoable> undoStack = new Stack<IUndoable>();
	private static final Stack<IUndoable> redoStack = new Stack<IUndoable>();	
	
	public static void add(IUndoable cmd) {
		undoStack.push(cmd);
		redoStack.clear();
	}
	
	public static void undo() {
		boolean result = !undoStack.empty();           // test if undoStack is empty
		if (result) {                       
			IUndoable command = undoStack.pop();
			redoStack.push(command);
			command.undo();
		}
		else 
			System.err.println("Nothing available to undo.");
	}

	public static void redo() {
		boolean result = !redoStack.empty();         // test if redo stack is empty
		if (result) {
			IUndoable command = redoStack.pop();
			undoStack.push(command);
			command.redo();
		}
		else
			System.err.println("Nothing available to redo.");
	}
}