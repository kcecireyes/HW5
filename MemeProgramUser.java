import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

/**
 * The user class of the meme-related program we're doing for HW5. 
 * @author Cecilia, Alice, Lowell
 * @version 5/4 2:00
 */


/**
* Creates a new GUI for the user specified in main (user 1)
* @param String userName
*/

public class MemeProgramUser implements ActionListener{
	
	public static MemeProgramGUI gooey;
	public static ActionListener nextTab;
	
	public MemeProgramUser (String userName) {
		
		gooey = new MemeProgramGUI(4);
		nextTab = this;
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		//I'm using a custom ActionEvent in Panel to send this info here.
		
		if (arg0.getActionCommand() == "NEW_TAB") {
			gooey.addTab();
			int panel = arg0.getModifiers();
			int choice = arg0.getID();
			System.out.println("This is the meme that was chosen!: " + choice);
			System.out.println("This is the panel it was chosen on!: " + panel);
			
			//TODO filter out information from the passed object.
			
			
			
			
			
			//***************************************************
			
		} else if (arg0.getActionCommand() == "FINAL_TAB") {
			JPanel last = new JPanel();
			
			//TODO Make a JPanel with all of the stuff you want on the last page!
			
			
			
			
			
			//***************************************************
			
			gooey.addFinalTab(last);
		}
		
		
	}	
	
	
	
}
