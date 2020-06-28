package model.strategies;

import java.awt.Color;
import java.awt.Graphics2D;

import model.ColorAdapter;
import model.interfaces.IShape;
import model.persistence.IDrawStrategy;

public class TriangleOutlineAndFilledInStrategy implements IDrawStrategy{
	
	@Override
	public void draw(IShape shape, Graphics2D graphics){  
		
		// set x and y coordinates
		int x1 = (int) shape.getStartPoint().getX();
		int x2 = (int) shape.getEndPoint().getX();
		int xCorner = (x2 >= x1) ? x1 : x2;              // if x2 > x1, use x1, else use x2;

		
		int y1 = (int) shape.getStartPoint().getY();
		int y2 = (int) shape.getEndPoint().getY();
		int yCorner = (y2 >= y1) ? y2 : y1;              // if y2 > y1, use y2, else use y1 as the corner
		
		
		int xPoints[] = {x1, x2, xCorner};     
		int yPoints[] = {y1, y2, yCorner};
		int nPoints = xPoints.length;
		
		// convert ShapeColor to java.awt.Color type using adapter pattern
		ColorAdapter colorAdapter = new ColorAdapter(shape);
		Color pColor = colorAdapter.getPrimaryColor();
		Color sColor = colorAdapter.getSecondaryColor();

		// draw filled triangle               
		graphics.setColor(sColor);
		graphics.fillPolygon(xPoints, yPoints, nPoints);

		//draw outline
		graphics.setColor(pColor);
		graphics.drawPolygon(xPoints, yPoints, nPoints);
	}

	
}