package model.commands;

import java.util.ArrayList;

import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.persistence.ShapeList;

class CopyShapeCommand implements ICommand{         // copy command is not IUndoable, I didn't see a point

	// instance variables
	private ShapeList shapeList;

	private ArrayList<IShape> copiedArray = new ArrayList<IShape>();

	// constructor
	public CopyShapeCommand (ShapeList shapeList){
		this.shapeList = shapeList;
	}

	// Adds every selected shape to our copiedShapes array 
	public void getSelectedShapesForCopy(ShapeList shapeList){

		// for each selected shape, move it by the offsets by creating a new shape with those offsets
		for (IShape shape : shapeList.getShapeArray()){
			if(shape.getSelected()){
				// add the shape to our copiedArray
				copiedArray.add(shape);
			}        
		}
	}
	
	@Override
	public void run() {
		System.out.println("Running copy shape command.");
		getSelectedShapesForCopy(shapeList);   //iterate through shapes in ShapeList. If selected, add it to ShapeList's copiedShapesArray
		if(! this.copiedArray.isEmpty()){
			ArrayList<IShape> shapeListCopiedArray = shapeList.getCopiedArray();
			shapeListCopiedArray.clear();                   // overwrites previous copied shapes

			for (IShape shape : this.copiedArray){
				System.out.println("Copying shape: " + shape);
				shapeListCopiedArray.add(shape);
			}

		}
		else
			System.err.println("Copy Command: No shapes selected!");
	}
	
	// no undo()  redo() because copyCommand is not IUndoable. Doesn't make sense. 
}
