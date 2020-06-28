package model.commands;

import java.awt.Point;

import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.interfaces.IApplicationState;
import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.persistence.ShapeList;
import model.shapes.ShapeFactory;
import view.interfaces.IUndoable;

class DrawShapeCommand implements ICommand, IUndoable {  // package private, use factory to create commands
	
	// instance variables
	private IApplicationState applicationState;
	private ShapeList shapeList;
	private Point start;
	private Point end;
	private IShape shape;

	
	//constructor
	public DrawShapeCommand(IApplicationState applicationState, ShapeList shapeList, Point start, Point end){
		this.applicationState = applicationState;
		this.shapeList = shapeList;
		this.start = start;
		this.end = end;	
	}
	
	// CreateShape(): calls the ShapeFactory to return the correct shape with the proper start and end points
	private IShape CreateShape(IApplicationState applicationState, Point start, Point end){
		
		// get values based on application state
		ShapeColor primaryColor = applicationState.getActivePrimaryColor();
		ShapeColor secondaryColor = applicationState.getActiveSecondaryColor();
		ShapeShadingType shapeShadingType = applicationState.getActiveShapeShadingType();
		ShapeType shapeType = applicationState.getActiveShapeType();
		
		//build the shape (but not builder pattern)
		return ShapeFactory.getShape(start, end, primaryColor, secondaryColor, shapeShadingType, shapeType);
	}
	
	// satisfy ICommand interface
	public void run(){
		
		this.shape = CreateShape(applicationState, start, end);         // moved shape to be an instance variable
		System.out.println("Drawing new shape: " + shape);
		shapeList.unselectAll();
		shape.setSelected(true);
		shapeList.add(shape);
		
		CommandHistory.add(this);
	}

	@Override
	public void undo() {
		System.out.println("Undoing previous Draw command.");
		shapeList.remove(this.shape);
	}

	@Override
	public void redo() {
		System.out.println("Redoing previous Draw command.");
		shapeList.add(this.shape);
	}
}
