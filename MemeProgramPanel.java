import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

/**
 * The Meme Panel object standardizing how memes are displayed to a user!
 * @author Cecilia, Alice, Lowell
 * @version 4/28 1:00
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
		    	System.out.println(f.getMessage());
		    	imgur = null;
		    }
		}
		while (nineGag == null) {
		    try {
				nineGag = new Meme9Gag();
		    } catch (IndexOutOfBoundsException e) {
			    nineGag = new Meme9Gag(true);
		    } catch (Exception f) {
		    	System.out.println(f.getMessage());
				nineGag = null;
		    }
		}
		
		if (imgur.compareTo(nineGag) == -1) {
		    memes[0] = imgur;
		    memes[1] = nineGag;
		} else if (imgur.compareTo(nineGag) == 1) {
		    memes[1] = imgur;
		    memes[0] = nineGag;
		} else {
			throw new Exception();
		}
		
		System.out.println(memes[0].toString());
		System.out.println(memes[1].toString());
		
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
		    
		    System.out.println(images[i].toString());
		    picLabels[i] = new JLabel(new ImageIcon(images[i]));
		    
		    labels[i] = new JLabel(imageNames[i]);
		    
		    buttons[i] = new JButton("Like!");
		    buttons[i].addActionListener(this);
		    
		    components.set(i, includeComponent(components.get(i), (JComponent)labels[i]));
		    components.set(i, includeComponent(components.get(i), (JComponent)picLabels[i]));
		    components.set(i, includeComponent(components.get(i), (JComponent)buttons[i]));
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
//		System.out.println("Button Pressed. Processing...");
		if (thisPanel == panelsLiked){
			for (int i = 0; i < 2; i++) {
				if (components.get(i).contains(e.getSource())){
					int id = i;
					ActionEvent temp = new ActionEvent(e.getSource(), id, "NEW_TAB", (panelsLiked+1));
					MemeProgramUser.nextTab.actionPerformed(temp);
					panelsLiked++;
				}
			}
		}
		//Now this will tell it what to do at the very last panel.
		if (panelsLiked == panelsMade){
			ActionEvent temp = new ActionEvent(e.getSource(), panelsMade, "FINAL_TAB");
			MemeProgramUser.nextTab.actionPerformed(temp);
//			panelsLiked++;
		}
	}

	
}
