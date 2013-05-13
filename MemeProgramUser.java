import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The user class of the meme-related program we're doing for HW5. 
 * @author Cecilia, Alice, Lowell
 * @version 5/13 1:00
 */

public class MemeProgramUser implements ActionListener{
	
	public static MemeProgramGUI gooey;
	public static ActionListener userListener;
	private static int morePopular = 0;
	private static int lessPopular = 0;
	
	public MemeProgramUser (String userName, int numberOfMemes) {
		/**
		* Creates a new GUI for the user specified in main (user 1)
		* @param String userName
		*/
		gooey = new MemeProgramGUI(numberOfMemes);
		userListener = this;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		/**
		* I'm using a custom ActionEvent in MemeProgramPanel to send information here.
		* @param ActionEvent arg0 Looks for either "New" or "Final" tab commands, and integers.
		*/
		
		if (arg0.getActionCommand() == "NEW_TAB") {
			
			gooey.addTab();
			int panel = arg0.getModifiers();
			int choice = arg0.getID();
			System.out.println("This is the meme that was chosen!: " + choice);
			System.out.println("This is the panel it was chosen on!: " + panel);
			if (choice == 0) {
				lessPopular++;
			} else {
				morePopular++;
			}
			
		} else if (arg0.getActionCommand() == "FINAL_TAB") {
			JPanel last = new JPanel();
			last.setLayout(new BoxLayout(last, BoxLayout.PAGE_AXIS));
			
			JLabel preferenceResult = new JLabel();
			preferenceResult.setFont(new Font(preferenceResult.getFont().getFontName(), Font.BOLD, 45));
			if (lessPopular>morePopular) {
				preferenceResult.setText(String.format("<html><div style=\"width:%dpx;\">%s</div><html>", 900, "Congrats! \n You are a nonconformist, hipster snob.\n" +"<\b" +
						" Out of "+ MemeProgramMain.totalMemes +" memes, you chose " + lessPopular + " that were of lesser popularity."));
			} else if (morePopular > lessPopular) {
				preferenceResult.setText(String.format("<html><div style=\"width:%dpx;\">%s</div><html>", 900, "< \b" + "Congrats! \n You are actually a sheep.\n" + "<\b"+
						" Out of "+ MemeProgramMain.totalMemes +" memes, you chose " + morePopular + " that were of greater popularity."));
			} else {
				preferenceResult.setText(String.format("<html><div style=\"width:%dpx;\">%s</div><html>", 900, "Congrats. \n You probably don't have a personality.\n"+"<\b" +
						" You chose an equal number of memes that were popular and as you did ones that were less popular. "));
			}
			last.add(preferenceResult);
			
			try {
				JLabel challenge = new JLabel(new ImageIcon(ImageIO.read(new File("icon3.jpg"))));
				last.add(challenge);
			} catch (IOException e) {
				//Do nothing. Really, just give it up.
			}
			
			gooey.addFinalTab(last);
			
		}
		
	}	
	
}
