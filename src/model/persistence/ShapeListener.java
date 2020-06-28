package model.persistence;

import java.awt.Color;
import java.awt.Graphics2D;

import model.interfaces.IObserver;
import model.interfaces.IShape;
import view.gui.PaintCanvas;

public class ShapeListener implements IObserver{
	
	//instance variables
	ShapeList shapeList;
	PaintCanvas paintCanvas;
	
	//constructor
	public ShapeListener (ShapeList shapeList, PaintCanvas paintCanvas){
		this.shapeList = shapeList;
		this.paintCanvas = paintCanvas;
	}
	
	@Override
    public void update() {
    	Graphics2D graphics2d = paintCanvas.getGraphics2D();                        // this is the only place a create Graphics2D objects
    	
    	graphics2d.setBackground(Color.WHITE);                                         // otherwise the background color turns light grey
    	graphics2d.clearRect(0, 0, paintCanvas.getWidth(), paintCanvas.getHeight());   // hack: draw a big white rectangle to cover everything up instead of clearing
    	for(IShape shape : shapeList.getShapeArray()){
    		shape.draw(graphics2d);
    	}
    }

}
