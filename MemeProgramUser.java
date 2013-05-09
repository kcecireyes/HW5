import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	
	private static MemeProgramGUI gooey;
	public static ActionListener nextTab;
	
	public MemeProgramUser (String userName) {
		
		gooey = new MemeProgramGUI(5);
		nextTab = this;
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("Button Clicked!");
		
		gooey.addTab();
		
		
	}	
	
	
	
}
