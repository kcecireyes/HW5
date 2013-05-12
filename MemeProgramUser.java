import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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
	
	private static int morePopular = 0;
	private static int lessPopular = 0;
	
	
	
	public MemeProgramUser (String userName, int numberOfMemes) {
		
		gooey = new MemeProgramGUI(numberOfMemes);
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
				lessPopular++;
			}
			
			else {
				morePopular++;
			}
			
			
			
			
			//***************************************************
			
		} else if (arg0.getActionCommand() == "FINAL_TAB") {
			JPanel last = new JPanel();
			JLabel preferenceResult = new JLabel();
			preferenceResult.setFont(new Font(preferenceResult.getFont().getFontName(), Font.BOLD, 25));
			//TODO Make a JPanel with all of the stuff you want on the last page!
				
				if (lessPopular>morePopular) {
					preferenceResult.setText(String.format("<html><div style=\"width:%dpx;\">%s</div><html>", 550, "Congrats! You are a nonconformist, hipster snob.\n" +
							"Out of 10 memes, you chose " + lessPopular + " that were of lesser popularity."));
				}
				
				else if (morePopular > lessPopular) {
					preferenceResult.setText(String.format("<html><div style=\"width:%dpx;\">%s</div><html>", 550, "Congrats! You are a sheep. " +
							"Out of 10 memes, you chose " + morePopular + " that were of greater popularity."));
				}
				
				else {
					preferenceResult.setText(String.format("<html><div style=\"width:%dpx;\">%s</div><html>", 550, "Congrats. You probably don't have a personality."+
							"You chose an equal number of memes that were popular and as you did ones that were less popular. "));
				}
			
			last.add(preferenceResult);
			
			//***************************************************
			
			gooey.addFinalTab(last);
		}
		
		
	}	
	
	
	
}
