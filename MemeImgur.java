import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 * This Meme object is trying to push imgur changes
 * @author Cecilia, Alice, Lowell  
 * @version 4/28 1:00
 */

public class MemeImgur extends MemeProgramMeme implements Comparable<MemeProgramMeme> {

	private final static String website = "http://www.imgur.com/";
	private String imageURL;
	private static int count=0;
	private static int totalUpvotes=0;
	private static int imgcount=0;
	
	private ArrayList<String> jpgs = new ArrayList<String>(0);
	private ArrayList<String> imageNames = new ArrayList<String>(0);
	private ArrayList<Integer> imageVotes = new ArrayList<Integer>(0); 
	
	public MemeImgur() throws Exception {
    	/**
		 * Creates a Meme object, as far as the pieces of it which will be displayed.
		 * @param none
		 */

    	super(website);
    	imageURL = null;	

    	if (jpgs.size() == 0) {
	    	Elements images = rootElements.select("div#content").select("div#imagelist").select("div.posts").select("img");
	    	for (Element image: images) {
	    		String tooltipHtml = image.attr("title");
	    		String[] split = tooltipHtml.split("<p>");
	    		// This is because gifs don't have a title.
		    	if (split.length == 2) {
				    String thumbnailUrl = image.attr("abs:src");
				    String fullSizeUrl = thumbnailUrl.replace("b.jpg", ".png");
		    	    jpgs.add(fullSizeUrl);
					imageNames.add(split[0]);
		    	    Document parsedHtml = Jsoup.parse("<p>" + split[1]);
		    	    int points = Integer.parseInt(parsedHtml.select("span").first().text().replace(",", ""));
					imageVotes.add(points);
				}
			}
    	}
    	
	}

	@Override
	public String findImage() {
		while (imageURL == null) {	
			imageURL=jpgs.get(count);
		}
		imgcount++;
		return imageURL;	
	}
	
	@Override
	public boolean cleanImageName() {
		super.imageName = imageNames.get(count);
		count++;
		return true;
	}

	@Override
	public boolean cleanUpvote() {
		if (totalUpvotes == 0) {
			for (int i=0; i < MemeProgramMain.totalMemes; i++) {
				int vote = imageVotes.get(i);
				totalUpvotes += vote;
			}
		}
		for (int i=0; i < MemeProgramMain.totalMemes; i++) {
			if (i == imgcount) {
				double vote = imageVotes.get(i);
				double upvotePercentd = (vote / (double) totalUpvotes) * 100.0;
				int upvotePercent = (int) upvotePercentd;
				super.upvote = upvotePercent;
			}
		}
		return true;
	 }

}
