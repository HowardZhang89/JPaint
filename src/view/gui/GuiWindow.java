package view.gui;

import java.util.HashMap;

import java.util.Map;
import java.util.NoSuchElementException;

import javax.swing.*;
import javax.swing.border.*;

import view.EventName;
import view.interfaces.IGuiWindow;

import java.awt.*;

/* 
 * GuiWindow is the window that pops out once we start a process. The first thing that Main does is initialize this window. 
 * When we call the constructor "IGuiWindow guiWindow = new GuiWindow(new PaintCanvas()) the following happens: 
 * 
 * 		1. Create a PaintCanvas() 
 * 				a. PaintCanvas() is a subclass of JComponent, which is used to create custom UIs
 * 				b. PaintCanvas() implements the IPaintCanvas interface, which requires a "GetGraphic2d()" method
 * 		
 * 		2. Create a GuiWindow that performs the actions in the constructor:
 * 				a. Sets the default properties such as visibility, window size, Window name
 * 				b. CreateWindow():
 * 						1. CreateBackgroundPanel()
 * 						2. CreateMenu()
 * 								Create a button panel
 * 								For every eventName, create a button with that eventName and add it to button panel 
 * 						3. Add the button panel to the background panel
 */
public class GuiWindow extends JFrame implements IGuiWindow {
    private final int defaultWidth = 1200;
    private final int defaultHeight = 800;
    private final String defaultTitle = "JPaint";
    private final Insets defaultButtonDimensions 
    	= new Insets(5, 8, 5, 8);                // determines the size of the buttons basically (top, left, bottom, right)
    private final Map<EventName, JButton> eventButtons = new HashMap<>();
    private final PaintCanvas canvas;

    public GuiWindow(PaintCanvas canvas){
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(defaultTitle);
        setSize(defaultWidth, defaultHeight);
        JPanel window = createWindow();
        this.canvas = canvas;
        window.add(canvas, BorderLayout.CENTER);
		validate();
    }

    @Override
	public JButton getButton(EventName eventName) {
		if(!eventButtons.containsKey(eventName))
			throw new NoSuchElementException("No button exists for action " + eventName.toString());
		
		return eventButtons.get(eventName);
	}
    
	private JPanel createWindow() {
		JPanel contentPane = createBackgroundPanel();    
        JPanel buttonPanel = createMenu();
        contentPane.add(buttonPanel, BorderLayout.NORTH);
        return contentPane;
	}

    private JPanel createMenu() {
        JPanel buttonPanel = createButtonPanel();

        for(EventName eventName : EventName.values()){
            addButtonToPanel(eventName, buttonPanel);
        }

        return buttonPanel;
    }

	private void addButtonToPanel(EventName eventName, JPanel panel) {
		JButton newButton = createButton(eventName);
        eventButtons.put(eventName, newButton);
        panel.add(newButton);
	}

	private JButton createButton(EventName eventName) {
		JButton newButton = new JButton(eventName.toString());
		newButton.setForeground(Color.BLACK);
		newButton.setBackground(Color.WHITE);
        newButton.setBorder(createButtonBorder());                 // add that button border we created to the button
		return newButton;
	}

	private Border createButtonBorder() {
        Border line = new LineBorder(Color.BLACK);                 // set the line border color of our buttons
        Border margin = new EmptyBorder(defaultButtonDimensions);  // creates the inside border using default button size (insets)
    	return new CompoundBorder(line, margin);                   // returns a border to put around out buttons
	}

	private JPanel createButtonPanel() {
		JPanel panel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        panel.setBackground(Color.lightGray);              // set the background color of the panel behind the buttons
		return panel;
	}

    private JPanel createBackgroundPanel() {
    	// Create a new JPanel, set border/layout/background color properties to ones we choose, and then return it 	
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));   // sets how wide the border is (top, left, bottom, right)
        contentPane.setLayout(new BorderLayout(0, 0));        // sets layout manager for this container
        contentPane.setBackground(Color.WHITE);               // sets background color (eg. Color.RED makes background red). 
        setContentPane(contentPane);                          // set the properties of contentPane;
        return contentPane;
    }
}
