package main;



import controller.IJPaintController;
import controller.JPaintController;
import model.commands.CommandHistory;
import model.persistence.ApplicationState;
import model.persistence.ShapeList;
import model.persistence.ShapeListener;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.MyMouseAdapter;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.IUiModule;


public class Main {
    public static void main(String[] args){
    	PaintCanvas paintCanvas = new PaintCanvas();            //extends JComponent
        IGuiWindow guiWindow = new GuiWindow(paintCanvas);      //extends JFrame
        IUiModule uiModule = new Gui(guiWindow);                                    // View
        ApplicationState appState = new ApplicationState(uiModule);                 // Model
        ShapeList shapeList = new ShapeList();                                      // Model  create ShapeList for storing shapes
        CommandHistory commandHistory = new CommandHistory();                       // Model  for storing previous commands
        IJPaintController controller = new JPaintController(uiModule, appState, shapeList);    // Controller: model and view both depend on Controller
        controller.setup();
        ShapeListener shapeListener = new ShapeListener(shapeList, paintCanvas);
        shapeList.registerObserver(shapeListener);                 // paintCanvas listens for changes to ShapeList
        MyMouseAdapter myMouseAdapter = new MyMouseAdapter(appState, shapeList);    // create Mouse Adapter (user friendly implementation of MouseListener etc.)
        paintCanvas.addMouseListener(myMouseAdapter);            // paintCanvas listens for changes from Mouse Adapter
        
    }
}
