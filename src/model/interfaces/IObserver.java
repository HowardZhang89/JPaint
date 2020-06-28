package model.interfaces;


public interface IObserver {
	// a bit redundant since we only have one observer (PaintCanvas), but doing it for pedagogical purposes
	void update();

}
