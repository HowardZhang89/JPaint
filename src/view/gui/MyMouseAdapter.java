package view.gui;

import java.awt.Point;
import java.awt.event.MouseEvent;

import model.StartAndEndPointMode;
import model.commands.CommandFactory;
import model.interfaces.IApplicationState;
import model.interfaces.ICommand;
import model.persistence.ShapeList;


public class MyMouseAdapter extends java.awt.event.MouseAdapter {
	
	IApplicationState applicationState;
	ShapeList shapeList;
	Point startPoint;
	Point endPoint;
	
	public MyMouseAdapter(IApplicationState applicationState, ShapeList shapeList){
		this.applicationState = applicationState;
		this.shapeList = shapeList;
	}
	
	
	@Override
	public void mousePressed(MouseEvent e) {
		startPoint = e.getPoint();	
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {

		endPoint = e.getPoint();
		
		// recalibrate points if we're in draw or select mode
		if(! applicationState.getActiveStartAndEndPointMode().equals(StartAndEndPointMode.MOVE)){    	// could consider using the State pattern if I have more time left over
			adjustStartAndEndPoints();
		}
		
		// determine which command to use based on application state
		ICommand shapeCommand = CommandFactory.getMouseCommand(applicationState, shapeList, startPoint, endPoint);  
		shapeCommand.run();
			
	}
	
	// this way it does not matter how to MouseAdapter scans in the point, the ShapeFactory guarantees the shape is drawn correctly
		private void adjustStartAndEndPoints(){
			// normal draw
			if(startPoint.getX() <= endPoint.getX() && startPoint.getY() <= endPoint.getY()) 
				return;  
			// bottom left to top right draw
			else if (startPoint.getX() <= endPoint.getX() && startPoint.getY() >= endPoint.getY()){         
				Point newStart = new Point((int) startPoint.getX(), (int) endPoint.getY());
				Point newEnd = new Point((int) endPoint.getX(), (int) startPoint.getY());
				startPoint = newStart;
				endPoint = newEnd;
			}
			// top right to bottom left draw
			else if (startPoint.getX() >= endPoint.getX() && startPoint.getY() <= endPoint.getY()){
				Point newStart =  new Point((int) endPoint.getX(), (int) startPoint.getY());
				Point newEnd = new Point((int) startPoint.getX(), (int) endPoint.getY());
				startPoint = newStart;
				endPoint = newEnd;
			}
			// bottom right to top left draw
			else{
				Point newStart = endPoint;
				Point newEnd = startPoint;
				startPoint = newStart;
				endPoint = newEnd;
			}
		}
		
	 
}
