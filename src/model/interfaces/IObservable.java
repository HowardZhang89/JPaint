package model.interfaces;

public interface IObservable {
	
	// My version of the Observable interface
	// A bit redundant since we only have one PaintCanvas (Observer) and one ShapeList (Observable), but doing it for pedagogical purposes
	
	void registerObserver(IObserver observer);
	void removeObserver(IObserver observer);
	void notifyObservers();
}
