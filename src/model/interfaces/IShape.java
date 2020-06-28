package model.interfaces;

import java.awt.Graphics2D;
import java.awt.Point;

import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;

public interface IShape {

	// getters and setters for variables
	Point getStartPoint();
	void setStartPoint(Point p);
	
	Point getEndPoint();
	void setEndPoint(Point p);
	
	boolean getSelected();
	void setSelected(boolean b);
	
	ShapeColor getPrimaryColor();
	void setPrimaryColor(ShapeColor primaryColor);
	
	ShapeColor getSecondaryColor();
	void setSecondaryColor( ShapeColor secondaryColor);
	
	ShapeShadingType getShapeShadingType();   
	void setShapeShadingType(ShapeShadingType shapeShadingType);
	
	ShapeType getShapeType();                 
	void setShapeType(ShapeType shapeType);
	
	// class method to check if shape overlaps with selected rectangle
	boolean overlaps(Point p1, Point p2);
	void draw(Graphics2D graphics);
}
