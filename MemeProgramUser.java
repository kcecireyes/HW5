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
			if (choice == 0) {
				lessPopular++;
			} else {
				morePopular++;
			}
			
		} else if (arg0.getActionCommand() == "FINAL_TAB") {
			JPanel last = new JPanel();
			last.setLayout(new BoxLayout(last, BoxLayout.PAGE_AXIS));
			
			JLabel preferenceResult = new JLabel("");
			last.add(preferenceResult);
			preferenceResult.setFont(new Font(preferenceResult.getFont().getFontName(), Font.BOLD, 45));
			
			if (lessPopular > morePopular) {
				preferenceResult.setText(String.format("<html><div style=\"width:%dpx;\">%s</div><html>", 900, "Congrats! \n You are a nonconformist, hipster snob.\n" +"<\b" +
						" Out of "+ MemeProgramMain.totalMemes +" memes, you chose " + lessPopular + " that were of lesser popularity."));
				try {
					JLabel challenge = new JLabel(new ImageIcon(ImageIO.read(new File("icon4.jpg"))));
					last.add(challenge);
				} catch (IOException e) {
					//Do nothing. Really, just give it up.
				}
			} else if (morePopular > lessPopular) {
				preferenceResult.setText(String.format("<html><div style=\"width:%dpx;\">%s</div><html>", 900, "< \b" + "Congrats! \n You are actually a sheep.\n" + "<\b"+
						" Out of "+ MemeProgramMain.totalMemes +" memes, you chose " + morePopular + " that were of greater popularity."));
				try {
					JLabel challenge2 = new JLabel(new ImageIcon(ImageIO.read(new File("icon5.jpg"))));
					last.add(challenge2);
				} catch (IOException e) {
					//Do nothing. Really, just give it up.
				}
			} else {
				preferenceResult.setText(String.format("<html><div style=\"width:%dpx;\">%s</div><html>", 900, "Congrats. \n You probably don't have a personality.\n"+"<\b" +
						" You chose an equal number of memes that were popular and as you did ones that were less popular. "));
				try {
					JLabel challenge3 = new JLabel(new ImageIcon(ImageIO.read(new File("icon3.jpg"))));
					last.add(challenge3);
					JLabel challenge4 = new JLabel(new ImageIcon(ImageIO.read(new File("icon6.jpg")).getScaledInstance(400, 400, 0)));
					last.add(challenge4);
				} catch (IOException e) {
					//Do nothing. Really, just give it up.
				}
			}
			
			last.doLayout();
			last.validate();
			gooey.addFinalTab(last);
			
		}
		
	}	
	
}
