package model.commands;

import java.awt.Point;
import java.util.ArrayList;

import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.persistence.ShapeList;
import model.shapes.ShapeFactory;
import view.interfaces.IUndoable;

class PasteShapeCommand implements ICommand, IUndoable{

	// instance variables
	private ShapeList shapeList;
	private ArrayList<IShape> pasteShapeArray = new ArrayList<IShape>();

	// constructor
	public PasteShapeCommand (ShapeList shapeList){
		this.shapeList = shapeList;
	}

	// Adds every selected shape to our copiedShapes array 
	public void createPasteShapes(ArrayList<IShape> copiedShapes){

		// for each selected shape, move it by the offsets by creating a new shape with those offsets
		for (IShape shape : copiedShapes){      // iterate over all shapes that are in ShapeLists's copy array

			// create new Shape shifted by (150, 150) 
			Point newStartPoint = new Point((int) shape.getStartPoint().getX() + 150, (int)shape.getStartPoint().getY() + 150);  // default width/height is 1200:800
			Point newEndPoint = new Point((int) shape.getEndPoint().getX() + 150, (int) shape.getEndPoint().getY() + 150);
			IShape newShape = ShapeFactory.getShape(newStartPoint, newEndPoint, shape.getPrimaryColor(), shape.getSecondaryColor(), 
					shape.getShapeShadingType(), shape.getShapeType());
			// add the new Shape to our array which we will add to shapeList later
			pasteShapeArray.add(newShape);
		}        
	}

	
	@Override
	public void run() {
		//copies every selected shape and creates a new shape with offset (150, 150) from original
		ArrayList<IShape> shapeListCopiedArray = shapeList.getCopiedArray();
		createPasteShapes(shapeListCopiedArray);  // sees which shapes are copied, and creates a paste shape starting at offset 150, 150 from original
		
		if(! pasteShapeArray.isEmpty()){
			shapeList.unselectAll();                // so only newly added shapes stay selected
			for (IShape shape : pasteShapeArray){
				System.out.println("Pasting shape: " + shape);
				shapeList.add(shape);                      // add to main shape array
			}
			CommandHistory.add(this);
		}
		else
			System.err.println("No available shapes to paste.");
	}
	
	@Override
	public void undo() {
		System.out.println("Undoing previous Paste command.");
		for(IShape shape : pasteShapeArray){
			shapeList.remove(shape);
		}

	}

	@Override
	public void redo() {
		System.out.println("Redoing previous Paste command.");
		shapeList.unselectAll();                // so only newly added shapes stay selected
		for (IShape shape : pasteShapeArray){
			System.out.println("Pasting shape: " + shape);
			shapeList.add(shape);                      // add to main shape array
		}
	}



}
