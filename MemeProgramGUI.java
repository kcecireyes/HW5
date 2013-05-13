import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * The main graphical portion of the meme-related program we're doing for HW5. 
 * @author Cecilia, Alice, Lowell
 * @version 5/13 1:00
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
		tabbedPane.setAutoscrolls(true);
		
		panels = new JComponent[quizLength];
		memePanels = new MemeProgramPanel[quizLength];
		icons = new Icon[quizLength];
		for (int i = 0; i < quizLength; i++) {
			memePanels[i] = null;
			while (memePanels[i] == null) {
				try {
					memePanels[i] = new MemeProgramPanel();
				} catch (IndexOutOfBoundsException d) {
					memePanels[i] = memePanels[i-1];
					quizLength--;
				} catch (Exception e) {
					System.out.println(e.getMessage());
				} 
			}
			panels[i] = memePanels[i].getPanel();
			icons[i] = new ImageIcon("icon0.jpg");
		}
		
		tabbedPane.validate();
		tabbedPane.doLayout();
		frame.add(tabbedPane);
		frame.validate();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1500, 1000);
		frame.setPreferredSize(frame.getSize());
		
		pagesDisplayed = 0;
		addTab();
		
	}
	
	public void addTab() {
		if (pagesDisplayed < quizLength) {
			System.out.println("Adding a tab no. " + (pagesDisplayed +1) + "...");
			tabbedPane.addTab(("Page " + (pagesDisplayed + 1) + "!"), icons[pagesDisplayed], panels[pagesDisplayed]);
			
			frame.setVisible(false);
			tabbedPane.revalidate();
			tabbedPane.doLayout();
			frame.validate();
			
			tabbedPane.setSelectedIndex(pagesDisplayed);
			frame.setVisible(true);
			pagesDisplayed++;
			
		}
	}
	
	public void addFinalTab(JPanel tempPanel) {
		frame.setVisible(false);
		
		tabbedPane.addTab("Final Page" , icons[0], tempPanel);
		tabbedPane.revalidate();
		tabbedPane.doLayout();
		frame.validate();
		tabbedPane.setSelectedIndex(pagesDisplayed);
		
		frame.setVisible(true);
	}
	
	
}
