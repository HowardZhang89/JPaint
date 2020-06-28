package model.commands;

import java.awt.Point;

import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.persistence.ShapeList;

class SelectShapeCommand implements ICommand{                 // package private, use factory to create Commands

	// instance variables
		private ShapeList shapeList;
		private Point start;       // top left start of selection box
		private Point end;         // bottom right of selection box
		
		//constructor
		public SelectShapeCommand(ShapeList shapeList, Point start, Point end){
			this.shapeList = shapeList;
			this.start = start;
			this.end = end;
		}
		
		// Selects a shape based on MouseHandler coordinates. Applies logic to resolve point collisions. 
		private void selectShape(Point selectionStart, Point selectionEnd){
			for (IShape shape : shapeList.getShapeArray()){
				if(shape.overlaps(selectionStart, selectionEnd)){
					shape.setSelected(true);
					System.out.println("Shape " + shape + " selected status: " + shape.getSelected());
				}
				else shape.setSelected(false);
			}
		}
		
		@Override
		public void run(){
			selectShape (start, end);
		}
}
