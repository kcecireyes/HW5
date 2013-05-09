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
	
	public MemeProgramPanel () throws Exception {
		/**
		 * This is the data structure to combine diverse memes into one JPanel.
		 * DO it! Crocodile
		 */
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
		
		while (imgur == null || nineGag == null) {
		    try {
				imgur = new MemeImgur();
				nineGag = new Meme9Gag();
				
				while (imgur.compareTo(nineGag) == 0) {
				    imgur = new MemeImgur();
				    nineGag = new Meme9Gag();
				}
		    } catch (Exception e) {
		    	
		    }
		}
		
		if (imgur.compareTo(nineGag) == -1) {
		    memes[0] = imgur;
		    memes[1] = nineGag;
		} else if (imgur.compareTo(nineGag) == 1) {
		    memes[1] = imgur;
		    memes[0] = nineGag;
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
    
    private JPanel includeContent (JPanel panel, JComponent comp) {
		/**
		 * Takes in a JPanel and appends a JComponent;
		 * @param JPanel panel 
		 * @param JComponent comp
		 * @return JPanel
		 */
		panel.add(comp);
		return panel;
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
		MemeProgramUser.nextTab.actionPerformed(e);
		
	}

	
}
