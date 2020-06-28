package model.commands;

import java.util.ArrayList;

import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.persistence.ShapeList;
import view.interfaces.IUndoable;

class DeleteShapeCommand implements ICommand, IUndoable{
	// instance variables
	private ShapeList shapeList;
	private ArrayList<IShape> deletedShapesArray= new ArrayList<IShape>();

	// constructor
	public DeleteShapeCommand (ShapeList shapeList){
		this.shapeList = shapeList;
	}

	// Adds every selected shape to our copiedShapes array 
	public void getDeleteShapes(ShapeList shapeList){

		// for each selected shape, move it by the offsets by creating a new shape with those offsets
		for (IShape shape : shapeList.getShapeArray()){
			if(shape.getSelected()){
				deletedShapesArray.add(shape);
			}        
		}
	}

	@Override
	public void run() {
		// removes every selected shape from ShapeList and moves them to the deletedShapesArray
		System.out.println("Running delete shape command.");
		getDeleteShapes(shapeList);	             // and store them in deletedShapesArray
		if( ! this.deletedShapesArray.isEmpty()){
			for(IShape shape : deletedShapesArray){
				System.out.println("Deleting shape: " + shape);
				shapeList.remove(shape);            // will notify ShapeLists's observers
			}
			CommandHistory.add(this);
		}
		else 
			System.err.println("Delete Command: No shapes selected!");
	}

	@Override
	public void undo() {
		System.out.println("Undoing previous delete command.");
		shapeList.unselectAll();                    // so only shapes added back are selected
		for(IShape shape : deletedShapesArray){
			System.out.println("Undeleting shape: " + shape);
			shapeList.add(shape);              // calling this.add() instead of mainShapeArray.add() because former notifies observers
		}


	}

	@Override
	public void redo() {
		for(IShape shape : deletedShapesArray){
			System.out.println("Redeleting shape: " + shape);
			shapeList.remove(shape);               // will notify ShapeLists's observers
		}
	}
}
