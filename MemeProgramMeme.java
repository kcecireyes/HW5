import java.awt.Image;
import java.net.URL;

import javax.imageio.ImageIO;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * The abstract Meme object standardizing how memes are scraped from the web.
 * @author Lowell
 * @version 4/28 1:00
 */

public abstract class MemeProgramMeme implements Comparable<MemeProgramMeme> {
	
	protected final String rootWebsite;
	protected Document rootDoc;
	protected Elements rootElements;
	protected URL url;
	
	protected Image image;
	protected boolean imageValidity;
	
	protected String imageName;
	protected boolean imageNameValidity;
	
	protected int upvote;
	protected boolean upvoteValidity;
	
	public MemeProgramMeme(String website) throws Exception{
		/**
		 * Creates a Meme object, as far as the pieces of it which will be displayed.
		 */
		rootWebsite = website;
		rootDoc = Jsoup.connect(rootWebsite).timeout(20000).followRedirects(true).get();
		rootElements = rootDoc.getAllElements();
	}
	
	public String getURLName() {
		/**
		 * The properly formatted name of the Website the meme came from.
		 * @return String rootWebsite supplies the source name.
		 */
		return rootWebsite;
	}
	
	public Image getImage() {
		/**
		 * If there is a valid image in the object, it will return it.
		 * @return Image the meme
		 */
		if (isValidImage()) {
			 return image; 
		}
		return null;
	}
	
	public String getImageName() {
		/**
		 * If there is a valid image name in the object, it will return it.
		 * @return String the text
		 */
		if (isValidImageName()) {
			 return imageName; 
		}
		return null;
	}
	
	public int getUpvote() {
		/**
		 * If there is a measure of popularity in the object, it will return it.
		 * @return int the normalized Popularity of the meme
		 */
		if (isValidUpvote()) {
			 return upvote; 
		}
		return 0;
	}
	
	public boolean isValidImage() {
		/**
		 * Finds an appropriate image to submit to the program, and formats it.
		 */
		imageValidity = loadImage(findImage()) && cleanImage();
		return imageValidity;
	}
	
	public boolean isValidImageName() {
		/**
		 * Finds the name or text of the selected meme, and formats it.
		 */
		imageNameValidity = findImageName() && cleanImageName();
		return imageNameValidity;
	}
	
	public boolean isValidUpvote() {
		/**
		 * Finds popularity of the selected meme, and normalizes it.
		 */
		upvoteValidity = findUpvote() && cleanUpvote();
		return upvoteValidity;
	}
	
	@Override
	public int compareTo(MemeProgramMeme o) {
		/**
		 * Compares one meme to another based on upvote!
		 * @param MemeProgramMeme o Another meme to compare this to!
		 * @return int 0 if equal, -1 if this<that, 1 if this>that.
		 */
		if (this.getUpvote() == o.getUpvote()){
			return 0;
		} else if(this.getUpvote() < o.getUpvote()) {
			return -1;
		} else {
			return 1;
		}
	}
	
	/**
	 * Must parse a unique meme from the website this object caters to.
	 */
	public abstract String findImage();
	
	public boolean loadImage(String imageURL) {
		/**
		 * Must obtain the image and save it to this object in some way.
		 * @param String The complete, validated, url of the desired meme image.
		 * @return boolean It's true if the reading process is successful.
		 */
		try {
			url = new URL(imageURL);
		    image = ImageIO.read(url);
		} catch (Exception e) {
			return false;
		} 
		return true;
	}
	
	
	public boolean cleanImage() {
		/**
		 * Must format the image to an appropriate size for the program.
		 * Try to get it to fit in a size like 100x100 or 200x200...
		 * @return boolean It's true if the resizing process is successful.
		 */
		try {
			image = image.getScaledInstance(550, -1, 0);
		} catch (Exception e) {
			return false;
		} 
		return true;
	}
	
	/**
	 * Must parse the name of this object's meme image.
	 * @return boolean True if image text can be found.
	 */
	public abstract boolean findImageName();


	
	/**
	 * Must take the name, then make it human-readable and short.
	 * @return boolean True if image text can be human-readable.
	 */
	public abstract boolean cleanImageName();

	
	
	/**
	 * Must parse the upvotes of this object's meme image.
	 * @return boolean True if the upvote amount can be found.
	 */
	public abstract boolean findUpvote();

	
	
	/**
	 * Must take the upvotes, then normalize them for comparison.
	 * @return boolean True if the upvotes can be normalized.
	 */
	public abstract boolean cleanUpvote();

	
	
	
} //End MemeProgramMeme
