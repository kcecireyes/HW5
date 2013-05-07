import javax.swing.*;

/**
 * The main graphical portion of the meme-related program we're doing for HW5. 
 * @author Cecilia, Alice, Lowell
 * @version 5/4 2:15
 */

public class MemeProgramGUI {
	
	private JFrame frame;
	private JTabbedPane tabbedPane;
	private int quizLength;
	private JComponent[] panels;
	private MemeProgramPanel[] memePanels;
	private Icon[] icons;
	
	public MemeProgramGUI (int length) {
		
		quizLength = length;
		frame = new JFrame("The Best Meme-Sraping App Ever!");
		tabbedPane = new JTabbedPane(JTabbedPane.NORTH, JTabbedPane.SCROLL_TAB_LAYOUT);
		
		//TODO Add a first panel of some sort.
		
		
		//************************************
		
		panels = new JComponent[quizLength];
		memePanels = new MemeProgramPanel[quizLength];
		icons = new Icon[quizLength];
		for (int i = 0; i < quizLength; i++) {
			memePanels[i] = new MemeProgramPanel();
			panels[i] = memePanels[i].getPanel();
			icons[i] = new ImageIcon("icon0.jpg");
			tabbedPane.addTab(("Page " + (i + 1) + "!"), icons[i], panels[i]);
		}
		
		tabbedPane.validate();
		tabbedPane.doLayout();
		frame.add(tabbedPane);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1300, 1200);
		frame.setVisible(true);
		
	}
	
}
