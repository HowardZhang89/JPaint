package model.strategies;

import java.awt.Color;
import java.awt.Graphics2D;

import model.ColorAdapter;
import model.interfaces.IShape;
import model.persistence.IDrawStrategy;

public class EllipseOutlineStrategy implements IDrawStrategy{
	
	public void draw (IShape shape, Graphics2D graphics){
		// set x and y coordinates
		int x = (int) shape.getStartPoint().getX();
		int y = (int) shape.getStartPoint().getY();
		int width = (int) (shape.getEndPoint().getX() - x);
		int height = (int)(shape.getEndPoint().getY() - y);

		// convert ShapeColor to java.awt.Color type using adapter pattern
		ColorAdapter colorAdapter = new ColorAdapter(shape);
		Color pColor = colorAdapter.getPrimaryColor();
		
		graphics.setColor(pColor);
		graphics.drawOval(x, y, width, height);     // use the graphics passed in from ShapeFactory
	}
}
