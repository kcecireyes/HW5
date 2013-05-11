import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
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
	
	private static ArrayList<Integer> choices = new ArrayList<Integer>();
	private static int gagTotal = 0;
	private static int imgurTotal = 0;
	
	private JLabel preferenceResult;
	
	public MemeProgramUser (String userName) {
		
		gooey = new MemeProgramGUI(10);
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
			
			if (choice == 0) {
				imgurTotal++;
			}
			
			else {
				gagTotal++;
			}
			
			
			
			
			//***************************************************
			
		} else if (arg0.getActionCommand() == "FINAL_TAB") {
			JPanel last = new JPanel();
			JLabel preferenceResult = new JLabel();
			//TODO Make a JPanel with all of the stuff you want on the last page!
			
			if (imgurTotal>gagTotal) {
				preferenceResult.setText("Congrats! You liked Imgur more than 9Gag.\n" +
						"Out of 10 memes, you chose " + imgurTotal + " from Imgur.");
			}
			
			else if (gagTotal > imgurTotal) {
				preferenceResult.setText("Congrats! You liked 9Gag more than Imgur.\n" +
						"Out of 10 memes, you chose " + gagTotal + " from 9Gag.");
			}
			
			else {
				preferenceResult.setText("Pick a side, man! You showed no preference for either 9Gag" +
						"\n or Imgur. ");
			}
			
			last.add(preferenceResult);
			
			//***************************************************
			
			gooey.addFinalTab(last);
		}
		
		
	}	
	
	
	
}
