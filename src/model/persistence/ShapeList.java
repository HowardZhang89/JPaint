package model.persistence;

import java.util.ArrayList;

import model.interfaces.IObservable;
import model.interfaces.IObserver;
import model.interfaces.IShape;

public class ShapeList implements IObservable {
	
	// instance variable
	private ArrayList<IShape> mainShapeArray;           // used to store our shapes
	private ArrayList<IShape> copiedShapesArray;        // the only way I could think of for letting Copy commands communicate with Paste commands, bad design?
	private ArrayList<IObserver> observers;         // ShapeListener class gets notified of any changes to ShapeList
													// ShapeListener then proceeds to redraw the shapes in ShapeList to the PaintCanvas
	// constructor
	public ShapeList(){
		mainShapeArray = new ArrayList<IShape>();       // initialize ArrayLists
		copiedShapesArray = new ArrayList<IShape>();
		observers = new ArrayList<IObserver>();   
	}
	
	// getters (no setters)
	public ArrayList<IShape> getShapeArray(){
		return mainShapeArray;
	}
	public ArrayList<IShape> getCopiedArray(){
		return copiedShapesArray;
	}
	
	// main shape Array methods
	public void add(IShape shape){
		mainShapeArray.add(shape);
		notifyObservers();
	}
	
	public void remove(IShape shape){
		mainShapeArray.remove(shape);
		notifyObservers();
	}
	
	public void unselectAll(){                  // useful for allowing only the most recently added shape to stay selected
		for(IShape shape : mainShapeArray)
			shape.setSelected(false);
	}
	
	// Methods for my IObservable interface 
	public void registerObserver(IObserver observer) {     // add an observer (PaintCanvas)
		observers.add(observer);
	}

	public void removeObserver(IObserver observer) {       // remove an observer (PaintCanvas)
		observers.remove(observer);
		
	}

	public void notifyObservers() {
		for (IObserver observer : observers){   // for all PaintCanvas observers (we only have one in this case)
			observer.update();                  // tell PaintCanvas to update itself based on ShapeList
		}
	
	}
	
	
}
