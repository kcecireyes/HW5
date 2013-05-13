import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;

/**
 * The Meme Panel object standardizing how memes are displayed to a user!
 * @author Cecilia, Alice, Lowell
 * @version 5/13 1:00
 */

public class MemeProgramPanel implements ActionListener {
	
	private MemeProgramMeme[] memes;
	private ArrayList<ArrayList<JComponent>> components;
	private JPanel outerPanel;
	private JPanel[] innerPanels;
	private JScrollPane[] innerScrollPanels;
	private static int panelsLiked = 0;
	private static int panelsMade = 0;
	private final int thisPanel;
	private static final JButton hidden = new JButton("");
	
	public MemeProgramPanel () throws Exception {
		/**
		 * This is the data structure to combine diverse memes into one JPanel.
		 */
		thisPanel = panelsMade;
		memes = new MemeProgramMeme[2];
		initializeMemes();
		
		components = new ArrayList<ArrayList<JComponent>>(2);
		components.add(0, new ArrayList<JComponent>(0));
		components.add(1, new ArrayList<JComponent>(0));
		initializeComponents();
		
		outerPanel = new JPanel();
		outerPanel.setLayout(new BoxLayout(outerPanel, BoxLayout.LINE_AXIS));
		innerPanels = new JPanel[2];
		innerScrollPanels = new JScrollPane[2];
		
		for (int i = 0; i < 2; i++) {
			innerPanels[i] = new JPanel();
			innerScrollPanels[i] = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			
			innerPanels[i].setLayout(new BoxLayout(innerPanels[i], BoxLayout.PAGE_AXIS));
			innerScrollPanels[i].setLayout(new ScrollPaneLayout());
			
			for (JComponent j : components.get(i)) {
				innerPanels[i].add(j);
			}
			
			innerScrollPanels[i].setViewportView(innerPanels[i]);
			innerScrollPanels[i].validate();
			outerPanel.add(innerScrollPanels[i]);	
		}
		
		System.out.println("Your newest panel is ready to add to the GUI!");
		MemeProgramMain.listener.actionPerformed(new ActionEvent(hidden, panelsMade, "Panel: " + panelsMade + " Loaded"));
		panelsMade++;
		
	}
	
	public JPanel getPanel() {
		/**
		 * Gets a display ready for a user to compare different memes within.
		 * @return JPanel the next panel, which will display a pair of memes!
		 */
		return outerPanel;
	}
	
	/**
	* Initialized the array "memes" with values, so that meme[0] is less than meme[1]
	* @param none
	*/
	private void initializeMemes() throws Exception{
		MemeProgramMeme imgur = null;
		MemeProgramMeme nineGag = null;
		
		while (imgur == null) {
			try {
				imgur = new MemeImgur();
			} catch (Exception f) {
				f.printStackTrace();
				imgur = null;
			}
		}
		
		while (nineGag == null) {
			try {
				nineGag = new Meme9Gag();
			} catch (IndexOutOfBoundsException e) {
				//Calls the alternate constructor for nineGag, accessing 10 more memes.
				nineGag = new Meme9Gag(true);
			} catch (Exception f) {				
				f.printStackTrace();
				nineGag = null;
			}
		}

		if (imgur.compareTo(nineGag) == -1) {
//			System.out.println("I am here aaaah! in -1");
			memes[0] = imgur;
			memes[1] = nineGag;
		} else if (imgur.compareTo(nineGag) == 1) {
//			System.out.println("I am here aaaah! in 1");
			memes[1] = imgur;
			memes[0] = nineGag;
		} else {
//			System.out.println("I am here aaaah! in else");
			throw new Exception();
		}
		
		System.out.println("Memes successfully initialized for your newest Panel!");
	}

	private void initializeComponents() throws Exception{
		/**
		 * Sets up the components for each half of the displayed panel.
		 * @param none
		 */
		Image[] images = new Image[2];
		JLabel[] picLabels = new JLabel[2];
		
		String[] imageNames = new String[2];
		JLabel[] labels = new JLabel[2];
		JButton[] buttons = new JButton[2];
		
		for (int i = 0; i < 2; i++) {
			images[i] = memes[i].getImage();
			imageNames[i] = memes[i].getImageName();

			labels[i] = new JLabel(String.format("<html><div style=\"width:%dpx;\">%s</div><html>", 550, imageNames[i]));
			labels[i].setFont(new Font(labels[i].getFont().getFontName(), Font.BOLD, 25));
			labels[i].revalidate();
			
			buttons[i] = new JButton("Like");
			buttons[i].addActionListener(this);
			buttons[i].setFont(new Font(buttons[i].getFont().getFontName(), Font.BOLD, 25));
			buttons[i].revalidate();
			
			picLabels[i] = new JLabel(new ImageIcon(images[i]));

			components.set(i, includeComponent(components.get(i), (JComponent)labels[i]));
			components.set(i, includeComponent(components.get(i), (JComponent)buttons[i]));
			components.set(i, includeComponent(components.get(i), (JComponent)picLabels[i]));
			
		}
	}

	private ArrayList<JComponent> includeComponent (ArrayList<JComponent> list, JComponent comp) {
		/**
		 * Takes in an ArrayList<JComponent> and appends a JComponent;
		 * @param ArrayList<JComponent> list corresponding to a panel's components 
		 * @param JComponent comp
			 * @return ArrayList<JComponent> list
			 */
		list.add(comp);
		return list;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//This parses the choice of meme from the latest panel only.
		if (thisPanel == panelsLiked){
			for (int i = 0; i < 2; i++) {
				if (components.get(i).contains(e.getSource())){
					int id = i;
					panelsLiked++;
					ActionEvent temp = new ActionEvent(e.getSource(), id, "NEW_TAB", (panelsLiked));
					MemeProgramUser.userListener.actionPerformed(temp);
				}
			}
		}
		//Now this will tell it what to do at the very last panel.
		if (panelsLiked == panelsMade){
			ActionEvent temp = new ActionEvent(e.getSource(), panelsMade, "FINAL_TAB");
			MemeProgramUser.userListener.actionPerformed(temp);
		}
	}

	
}
