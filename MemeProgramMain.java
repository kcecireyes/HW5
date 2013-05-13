import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The main program of the meme-related program we're doing for HW5. 
 * @author Cecilia, Alice, Lowell
 * @version 5/13 1:00
 */

public class MemeProgramMain implements ActionListener {
	/**
	 * Begins the locally-run version of our meme-related application!
	 * Creates a MemeProgramUser and initializes the first arbitrary user. 
	 * @param args (Currently requires no command line arguments)
	 */
	
	private static MemeProgramUser user;
	private static JFrame frame;
	private static JLabel label;
	private static JLabel count;
	private static int loadedMemes = 0;
	private static final int totalMemes = 10;
	public static ActionListener listener;
	
	private static JPanel pane;
	private static JTextField userField;
	private static JTextField passField;
	private static JButton button;
	
	public static void main(String[] args) {
		
		user = new MemeProgramUser("Now Arbitrary *and* capricious!", totalMemes);
		
		listener = new MemeProgramMain();
		
		frame = new JFrame("Your Quiz is Loading...");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setSize(600, 600);
		
		frame.setVisible(false);
		frame.setLayout(new BorderLayout());
		label = new JLabel("Prepare to find out how mainstream your memes are!");
		label.setFont(new Font(label.getFont().getFontName(), Font.BOLD, 25));
		count = new JLabel(loadedMemes + "/" + totalMemes);
		count.setFont(new Font(count.getFont().getFontName(), Font.BOLD, 100));
		frame.add(label, BorderLayout.NORTH);
		frame.add(count, BorderLayout.CENTER);
		frame.doLayout();
		frame.validate();
		frame.setVisible(true);
		
//		pane = new JPanel();
//		pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
//		userField = new JTextField("Username");
//		pane.add(userField);
//		passField = new JTextField("Password");
//		pane.add(passField);
//		button = new JButton("Submit");
//		button.addActionListener(listener);
//		pane.add(button);
//		pane.validate();
//		pane.doLayout();
//		frame.add(pane);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		/**
		 * This will update the loading screen whenever it is called.
		 * @param ActionEvent arg0 (Currently requires nothing special.)
		 */
		frame.setVisible(false);
		loadedMemes++;
		count.setText(loadedMemes + "/" + totalMemes);
		frame.validate();
		frame.setVisible(true);
	}
}
