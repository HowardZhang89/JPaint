package model.commands;

import java.awt.Point;
import java.util.ArrayList;

import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.persistence.ShapeList;
import model.shapes.ShapeFactory;
import view.interfaces.IUndoable;

class MoveShapeCommand implements ICommand, IUndoable{

	// instance variables
	private ShapeList shapeList;
	private Point start;       // top left start of selection box
	private Point end;         // bottom right of selection box
	
	// used to store new and removed shapes
	ArrayList<IShape> newShapeArray = new ArrayList<IShape>();
	ArrayList<IShape> removedShapesArray = new ArrayList<IShape>();

	//constructor
	public MoveShapeCommand(ShapeList shapeList, Point start, Point end){
		this.shapeList = shapeList;
		this.start = start;
		this.end = end;
	}

	// Selects a shape based on MouseHandler coordinates. Applies logic to resolve point collisions. 
	private void getOldandNewShapesForMoveCommand(ShapeList shapeList, Point start, Point end){
		
		// find the offsets of the mouse drag
		double dx = end.getX() - start.getX();
		double dy = end.getY() - start.getY();
		

		// for each selected shape, move it by the offsets by creating a new shape with those offsets
		for (IShape shape : shapeList.getShapeArray()){
			if(shape.getSelected()){
				Point newStartPoint = new Point((int)(shape.getStartPoint().getX() + dx), (int)(shape.getStartPoint().getY() + dy));
				Point newEndPoint = new Point((int)(shape.getEndPoint().getX() + dx), (int)(shape.getEndPoint().getY() + dy));
				IShape newShape = ShapeFactory.getShape(newStartPoint, newEndPoint, shape.getPrimaryColor(), shape.getSecondaryColor(), 
														shape.getShapeShadingType(), shape.getShapeType());
				
				newShapeArray.add(newShape);
				removedShapesArray.add(shape);
			}        
		}
		
	
	}

	@Override
	public void run() {
		getOldandNewShapesForMoveCommand(this.shapeList, start, end);
		shapeList.unselectAll();               // so only copied shapes are selected
		// update ShapeList
		for(IShape shape : removedShapesArray){
			shapeList.remove(shape);
		}
		
		for(IShape shape : newShapeArray){
			shapeList.add(shape);
		}
		
		CommandHistory.add(this);
	}

	@Override
	public void undo() {
		System.out.println("Undoing previous Move command.");
		// remove added shapes
		for(IShape shape: newShapeArray){
			shapeList.remove(shape);
		}
		// add back original shapes
		shapeList.unselectAll();
		for(IShape shape: removedShapesArray){
			shapeList.add(shape);
		}
	}

	@Override
	public void redo() {
		System.out.println("Redoing previous Move command.");
		// add back new shapes
		shapeList.unselectAll();
		for(IShape shape : removedShapesArray){
			shapeList.remove(shape);
		}
		// remove old shapes
		for(IShape shape : newShapeArray){
			shapeList.add(shape);
		}
	}

}
