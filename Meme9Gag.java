import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

/**
 * This Meme object will handle all memes from 9Gag!
 * @author Cecilia, Alice, Lowell --- I hope this is working  
 * @version 4/28 1:00
 */

public class Meme9Gag extends MemeProgramMeme implements Comparable<MemeProgramMeme> {
	
	private final static String website = "http://www.9gag.com/";
	private String imageURL;
	private ArrayList<String> images = new ArrayList<String>();
	private ArrayList<String> imageNames = new ArrayList<String>();
	private ArrayList<String> upvotes = new ArrayList<String>();
	private static int totalVotes;
	private static int imageCallCount = 0;
	private static int imageNameCallCount = 0;
	private static int voteCallCount = 0;
    public Meme9Gag() throws Exception {
    	/**
		 * Creates a Meme object, as far as the pieces of it which will be displayed.
		 * @param none
		 */
		super(website);
		imageURL = null;
	}

	/**
	 * This method scrapes 9GAG for all jpg images. There will be duplicates in 
	 * sets of 3, so it will put every third one in an ArrayList.
	 * @return ArrayList<String> of image links
	 */
    
    private ArrayList<String> scrapeImages() {
    	
    	Document gag;
		try {
			
			gag = Jsoup.connect(website).get();
		
		Elements jpgs = gag.select("img[src$=.jpg]");
		for (int i = 0; i<jpgs.size(); i++)  {
			String jpgURL = jpgs.get(i).absUrl("src");
			if (jpgURL.contains("_tp_")==false) {
		images.add(jpgURL);
			}
		//	System.out.println(testurl);
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return images;
    }
    
    /**
     * This method scrapes 9GAG for all meme tags (that little piece of text
     * that appears next to the meme image. There will be duplicates in 
	 * sets of 3, so it will put every third one in an ArrayList.
     * @return ArrayList<String> of a the image names
     */
    private ArrayList<String> scrapeImageNames() {
    	Document gag;
		try {
			
			gag = Jsoup.connect(website).get();
		
		Elements names = gag.select("[alt]");
	
	//	System.out.println(names.text());
		for (int i = 3; i<names.size()-3; i= i+3) {
			String name = names.get(i).attr("alt");
	
			imageNames.add(name);
			//System.out.println(name);
		
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return imageNames;
    }
    
    /**
     * This method scrapes 9GAG for each meme's votes, and adds it to an ArrayList.
     * It also adds this vote value to the totalVotes variable.
     * @return ArrayList<String> of votes
     */
    
	private ArrayList<String> scrapeUpvotes() {
		totalVotes = 0;
    	Document gag;
		try {
			
			gag = Jsoup.connect(website).get();
		
		Elements votes = gag.select("span");
	
		for (int i = 0; i<votes.size(); i++) {
			String upvote = votes.get(i).attr("votes");
		if (upvote.isEmpty()==false){
		totalVotes = totalVotes + Integer.parseInt(upvote);
			upvotes.add(upvote);
		}
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return upvotes;
    }
    
	/**
	 * This method gets the imageURL from the ArrayList of imageURLs in the spot
	 * of the method call count. It then updates the call count.
	 * @return imageURL
	 */
    @Override
	public String findImage() {
		images = scrapeImages();
		imageURL = images.get(imageCallCount);
//		imageURL = "http://d24w6bsrhbeh9d.cloudfront.net/photo/abX9B9O_460s.jpg";
		
		imageCallCount = imageCallCount + 1;
		System.out.println(imageURL);
		return imageURL;
	}
    
    /**This method doesn't do jack.
     * 
     */
	@Override
	public boolean findImageName() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * This method gets the image name from the ArrayList of image names in the spot
	 * of the method call count. It then updates the call count.
	 * 
	 */
	@Override
	public boolean cleanImageName() {
		// TODO Auto-generated method stub
		imageNames = scrapeImageNames();
		String imageName = imageNames.get(imageNameCallCount);
			
		super.imageName = imageName;
		imageNameCallCount = imageNameCallCount + 1;
		return true;
	}
	/**
	 * This method doesn't do jack.
	 */
	@Override
	public boolean findUpvote() {
		// TODO Auto-generated method stub Interior crocodile alligator, I drive a chevrolet movie theater.
		return true;
	}
/**
 * This method takes the upvotes for a particular meme in the ArrayList
 * at the call count and divides it by the totalVotes. This value is multiplied
 * by 100 to get the percent which is sent to the super.
 */
	@Override
	public boolean cleanUpvote() {
		// TODO Auto-generated method stub
		upvotes = scrapeUpvotes();
		double upvote = Integer.parseInt(upvotes.get(voteCallCount));
		double upvotePercentd = (upvote / totalVotes) * 100;
		int upvotePercent = (int)upvotePercentd;
//		System.out.println(upvotePercent);
		
		super.upvote = upvotePercent;
		voteCallCount = voteCallCount + 1;
		
		return true;
	}

}
