 import java.awt.Dimension;

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
	private int pagesDisplayed;
	
	public MemeProgramGUI (int length) {
		
		quizLength = length;
		frame = new JFrame("The Best Meme-Scraping App Ever!");
		tabbedPane = new JTabbedPane(JTabbedPane.NORTH, JTabbedPane.SCROLL_TAB_LAYOUT);
		
		panels = new JComponent[quizLength];
		memePanels = new MemeProgramPanel[quizLength];
		icons = new Icon[quizLength];
		for (int i = 0; i < quizLength; i++) {
			memePanels[i] = null;
			while (memePanels[i] == null) {
				try {
					memePanels[i] = new MemeProgramPanel();
				} catch (Exception e) {
					
				}
			}
			panels[i] = memePanels[i].getPanel();
			icons[i] = new ImageIcon("icon0.jpg");
//			tabbedPane.addTab(("Page " + (i + 1) + "!"), icons[i], panels[i]);
		}
		
		
		tabbedPane.validate();
		tabbedPane.doLayout();
		frame.add(tabbedPane);
		frame.validate();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(1200, 900));
		frame.setSize(frame.getPreferredSize());
		
		pagesDisplayed = 0;
		addTab();
		
	}
	
	public void addTab() {
		if (pagesDisplayed < quizLength) {
			System.out.println("Adding a Tab! #" + (pagesDisplayed +1) + "...");
			tabbedPane.addTab(("Page " + (pagesDisplayed + 1) + "!"), icons[pagesDisplayed], panels[pagesDisplayed]);
			pagesDisplayed++;
			
			frame.setVisible(false);
			tabbedPane.revalidate();
			tabbedPane.doLayout();
			frame.revalidate();
			frame.setVisible(true);
		}
	}
	
	public void addFinalTab(JPanel tempPanel) {
		frame.setVisible(false);
		//TODO add in a last panel including statistics and fun stuff!
		
		tabbedPane.addTab("Final Page" , icons[0], tempPanel);
		tabbedPane.revalidate();
		tabbedPane.doLayout();
		frame.revalidate();
		frame.setVisible(true);
	}
	
	
}
