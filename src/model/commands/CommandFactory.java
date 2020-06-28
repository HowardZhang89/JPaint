package model.commands;

import java.awt.Point;

import model.StartAndEndPointMode;
import model.interfaces.IApplicationState;
import model.interfaces.ICommand;
import model.persistence.ShapeList;
import view.EventName;

public class CommandFactory {
	
	public static ICommand getMouseCommand(IApplicationState applicationState, ShapeList shapeList, 
													Point start, Point end){
		
		if(applicationState.getActiveStartAndEndPointMode().equals(StartAndEndPointMode.DRAW)){
			return new DrawShapeCommand(applicationState, shapeList, start, end);   
		}
		
		else if(applicationState.getActiveStartAndEndPointMode().equals(StartAndEndPointMode.SELECT)){
			return new SelectShapeCommand(shapeList, start, end);
		}
		
		else if (applicationState.getActiveStartAndEndPointMode().equals(StartAndEndPointMode.MOVE)){
			return new MoveShapeCommand (shapeList, start, end);
		}
		
		else return null;
	}
	
	public static ICommand getButtonCommand(EventName event, ShapeList shapeList){
		if (event.equals(EventName.COPY)){
			return new CopyShapeCommand(shapeList);
		}
		else if (event.equals(EventName.DELETE)){
			return new DeleteShapeCommand(shapeList);
		}
		else if (event.equals(EventName.PASTE)){
			return new PasteShapeCommand(shapeList);                                   
		}
		else{
			return null;                                
		}
	}
}
