package controller;

import model.commands.CommandFactory;
import model.commands.CommandHistory;
import model.interfaces.IApplicationState;
import model.persistence.ShapeList;
import view.EventName;
import view.interfaces.IUiModule;

public class JPaintController implements IJPaintController {
	// Acts as a bridge between the Model and View interface. Needs references to Model Classes so it can change them based on the View commands.
    private final IUiModule uiModule;                         // GUI
    private final IApplicationState applicationState;         // the current mode 
    private ShapeList shapeList;                              // the list of shapes drawn

    public JPaintController(IUiModule uiModule, IApplicationState applicationState, ShapeList shapeList) {
        this.uiModule = uiModule;
        this.applicationState = applicationState;
        this.shapeList = shapeList;
    }

    @Override
    public void setup() {
        setupEvents();
    }

    private void setupEvents() {
        uiModule.addEvent(EventName.CHOOSE_SHAPE, () -> applicationState.setActiveShape());
        uiModule.addEvent(EventName.CHOOSE_PRIMARY_COLOR, () -> applicationState.setActivePrimaryColor());
        uiModule.addEvent(EventName.CHOOSE_SECONDARY_COLOR, () -> applicationState.setActiveSecondaryColor());
        uiModule.addEvent(EventName.CHOOSE_SHADING_TYPE, () -> applicationState.setActiveShadingType());
        uiModule.addEvent(EventName.CHOOSE_START_POINT_ENDPOINT_MODE, () -> applicationState.setActiveStartAndEndPointMode());
        
        uiModule.addEvent(EventName.COPY, () -> CommandFactory.getButtonCommand(EventName.COPY, shapeList).run());
        uiModule.addEvent(EventName.DELETE, () -> CommandFactory.getButtonCommand(EventName.DELETE, shapeList).run());
        uiModule.addEvent(EventName.PASTE, () -> CommandFactory.getButtonCommand(EventName.PASTE, shapeList).run());
        uiModule.addEvent(EventName.REDO, () -> CommandHistory.redo());
        uiModule.addEvent(EventName.UNDO, () -> CommandHistory.undo());
        }
}
