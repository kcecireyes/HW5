import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

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
	private static int loadedMemes = 0;
	public static final int totalMemes = 10;
	private static JFrame frame = new JFrame("Your Quiz is Loading...");
	private static JLabel label = new JLabel("Prepare to find out how mainstream your memes are!");
	private static JLabel count = new JLabel(loadedMemes + "/" + totalMemes);
	private static JLabel picLabel;
	private static JLabel picLabel2;
	public static ActionListener listener = new MemeProgramMain();

	public static void main(String[] args) {
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		try {
			picLabel = new JLabel(new ImageIcon(ImageIO.read(new File("icon2.jpg"))));
			frame.add(picLabel, BorderLayout.EAST);
			picLabel2 = new JLabel(new ImageIcon(ImageIO.read(new File("icon1.jpg"))));
			frame.add(picLabel2, BorderLayout.WEST);
		} catch (IOException e) {
			//Do nothing. Really, just give it up.
		}
		
		label.setFont(new Font(label.getFont().getFontName(), Font.BOLD, 50));
		count.setFont(new Font(count.getFont().getFontName(), Font.BOLD, 420));
		frame.add(label, BorderLayout.NORTH);
		frame.add(count, BorderLayout.CENTER);
		
		frame.setSize(1300, 800);
		frame.doLayout();
		frame.validate();
		frame.setVisible(true);
		
		user = new MemeProgramUser("Now Arbitrary *and* capricious!", totalMemes);	
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		/**
		 * This will update the loading screen whenever it is called.
		 * @param ActionEvent arg0 (Currently requires nothing special.)
		 */
		loadedMemes++;
		count.setText(loadedMemes + "/" + totalMemes);
		frame.validate();
		if (loadedMemes == totalMemes) {
			frame.dispose();
		}
		
	}
}
