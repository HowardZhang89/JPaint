package model.strategies;

import java.awt.Color;
import java.awt.Graphics2D;

import model.ColorAdapter;
import model.interfaces.IShape;
import model.persistence.IDrawStrategy;

public class TriangleOutlineStrategy implements IDrawStrategy{
	
	@Override
	public void draw(IShape shape, Graphics2D graphics){  
		
		// set x and y coordinates
		int x1 = (int) shape.getStartPoint().getX();
		int x2 = (int) shape.getEndPoint().getX();
		int xCorner = (x2 >= x1) ? x1 : x2;              // if x2 > x1, use x1, else use x2

		
		int y1 = (int) shape.getStartPoint().getY();
		int y2 = (int) shape.getEndPoint().getY();
		int yCorner = (y2 >= y1) ? y2 : y1;        // if y2 > y1, use y2, else use y1 as the corner (note in Java, the lower the y, the bigger the value)
		
		
		int xPoints[] = {x1, x2, xCorner};     
		int yPoints[] = {y1, y2, yCorner};
		int nPoints = xPoints.length;
		
		// convert ShapeColor to java.awt.Color type
		ColorAdapter colorAdapter = new ColorAdapter(shape);
		Color pColor = colorAdapter.getPrimaryColor();
		
		// draw outline
		graphics.setColor(pColor);
		graphics.drawPolygon(xPoints, yPoints, nPoints);
	}


}