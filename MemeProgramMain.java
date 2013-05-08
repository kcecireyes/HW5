import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The main program of the meme-related program we're doing for HW5. 
 * @author Cecilia, Alice, Lowell
 * @version 5/4 2:00
 */

public class MemeProgramMain implements ActionListener {
	/**
	 * Alice's Comment is better than your comment. No. Not really. Yours are also nice.
	 * Begins the locally-run version of our meme-related application!
	 * Creates an array of users, and initializes the first user. 
	 * @param args (Currently requires no command line arguments)
	 */
	
	static MemeProgramUser[] users = new MemeProgramUser[1];
	static JFrame frame;
	static JPanel pane;
	static JTextField userField;
	static JTextField passField;
	static JButton button;
	static ActionListener listener;
	
	public static void main(String[] args) {
		frame = new JFrame("The Best Meme-Scraping App Ever!");
		pane = new JPanel();
		pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
		
		userField = new JTextField("Enter your Username here.");
		pane.add(userField);
		passField = new JTextField("Enter your Password here.");
		pane.add(passField);
		button = new JButton("Submit");
		listener = new MemeProgramMain();
		button.addActionListener(listener);
		pane.add(button);
		
		pane.validate();
		pane.doLayout();
		frame.add(pane);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 300);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		frame.setVisible(false);
		for (MemeProgramUser i : users){
			i = new MemeProgramUser(userField.getText());
//			frame.setVisible(true);
		}
	}
}
