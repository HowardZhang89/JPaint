package model.shapes;

import java.awt.Point;

import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.interfaces.IShape;

public class ShapeFactory {

	public static IShape getShape(Point start, Point end, ShapeColor primaryColor, ShapeColor secondaryColor, 
			ShapeShadingType shapeShadingType, ShapeType shapeType){
		
		
		// Case 1: return a rectangle
		if(shapeType.equals(ShapeType.RECTANGLE)){
			return new Rectangle(start, end, primaryColor, secondaryColor, shapeShadingType, shapeType);       
		}
		// Case 2: return an ellipse
		else if(shapeType.equals(ShapeType.ELLIPSE)){
			return new Ellipse(start, end, primaryColor, secondaryColor, shapeShadingType, shapeType);
		}
		
		// Case 3: return a triangle
		else if(shapeType.equals(ShapeType.TRIANGLE)){
			return new Triangle(start, end, primaryColor, secondaryColor, shapeShadingType, shapeType);
		}
		else {
			return null;
		}
	}
	

	
}
