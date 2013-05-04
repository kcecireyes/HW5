import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

/**
 * This Meme object will handle all memes from 9Gag!
 * @author Cecilia, Alice, Lowell  
 * @version 4/28 1:00
 */

public class Meme9Gag extends MemeProgramMeme implements Comparable<MemeProgramMeme> {
	
	private final static String website = "http://www.9gag.com/";
	private String imageURL;
	
    public Meme9Gag() throws Exception {
    	/**
		 * Creates a Meme object, as far as the pieces of it which will be displayed.
		 * @param none
		 */
		super(website);
		imageURL = null;
	}

	//@Override
	public String findImage() {
		while (imageURL == null) {	
			//TODO FIND the clean, complete link to the meme image in a procedural way.
			
			imageURL = "http://d24w6bsrhbeh9d.cloudfront.net/photo/ay7GV3q_700b_v1.jpg";
			
			//TODO Did you set imageURL equal to the real, nice meme's address?
		}
		return imageURL;
	}

	//@Override
	public boolean findImageName() {
		// TODO Auto-generated method stub
		return true;
	}

	//@Override
	public boolean cleanImageName() {
		// TODO Auto-generated method stub
		super.imageName = "Sample Meme";
		return true;
	}

	//@Override
	public boolean findUpvote() {
		// TODO Auto-generated method stub
		return true;
	}

	//@Override
	public boolean cleanUpvote() {
		// TODO Auto-generated method stub
		super.upvote = 11;
		return true;
	}		
}
