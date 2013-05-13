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
	private static int imgcount=0;
	private static int votecount=0;
	private static int totalUpvotes=0;
//	private ArrayList<String> imageURLs = new ArrayList<String>();
	private ArrayList<String> jpgs = new ArrayList<String>();
	private ArrayList<String> imageNames = new ArrayList<String>();
	private ArrayList<Integer> imageVotes = new ArrayList<Integer>(); 

	private Elements iURLs;
	
	public MemeImgur() throws Exception {
    	/**
		 * Creates a Meme object, as far as the pieces of it which will be displayed.
		 * @param none
		 */

    	super(website);
    	imageURL = null;	

		//parses main page and gets the URLs of images on the front page
//    	Elements images = rootElements.select("div#content")
//    	.select("div#imagelist").select("div.posts").select("a[href]"); 		
    	Elements images = rootElements.select("div#content").select("div#imagelist").select("div.posts").select("img");
    	for (Element image: images) {
    		String tooltipHtml = image.attr("title");
    		String[] split = tooltipHtml.split("<p>");
    	
    	
//		System.out.println("these are some " + images); //debugging
//		for (int i=0;i<images.size();i++){
//			imageURLs.add(images.get(i).attr("abs:href"));
//		}	
//
//		for (int j=0;j<imageURLs.size();j++) { //goes into the arrayList of image URLs and fetches their content
//			try{
//				Document doc = Jsoup.connect(imageURLs.get(j)).get(); //selects each of the image URLs of the front page and gets the content
//				Elements indivImages = doc.select("div#content").select("div.panel");
//				Element jpgUrl = indivImages.select("div#image").select("img[src$=.jpg]").first();
//				if (jpgUrl!=null){
//					imageNames.add(indivImages.select("h2").text());
//					imageVotes.add(indivImages.select("div#under-image").select("div.info").select("div.left").first().text());
//				}
//				//System.out.println("these are the contents of jpgUrl " + jpgUrl);
//				if ((jpgUrl != null) && (jpgUrl.absUrl("src")!=null)) {
//					String jp = jpgUrl.absUrl("src");
//					if (!jp.equals(null)) {
//						jpgs.add(jp);
//					}
//				}
//			}
    	
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
    	
//				catch(Exception e){
//					e.printStackTrace();
//				}
//		}
    	
		System.out.println("these are names' sizes " + imageNames.size() +"\n");
		//System.out.println("these are upvotes ... " + imageVotes.get(1) + " " + imageVotes.get(2) + "\n");	
	}

	@Override
	public String findImage() {
		while (imageURL == null) {	
			//TODO FIND the clean, complete link to the meme image in a procedural way.
			imageURL=jpgs.get(count);
		}
		imgcount++;
		return imageURL;	
	}
	
	@Override
	public boolean findImageName() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean cleanImageName() {
		// TODO Auto-generated method stub
		super.imageName = imageNames.get(count);
		count++;
		return true;
	}

	@Override
	public boolean findUpvote() {
		return true;
	}

	@Override
	public boolean cleanUpvote() {
		for (int i=0; i>imageVotes.size(); i++) {
//			int vote = Integer.parseInt(imageVotes.get(i));
			int vote = imageVotes.get(i);
			totalUpvotes+=vote;
			double upvotePercentd = (vote / totalUpvotes) * 100;
			int upvotePercent = (int)upvotePercentd;
			super.upvote = upvotePercent;
		}
		return true;
		
	// 	for (int i=0; i>imageVotes.size(); i++) {
	// 		int tempVote = Integer.parseInt(imageVotes.get(i));
	// 		totalUpvotes+=tempVote;
	// 	}
	// 	int vote = Integer.parseInt(imageVotes.get(votecount));
	// 	double upvotePercentd = (vote / totalUpvotes) * 100;
	// 	int upvotePercent = (int) upvotePercentd;
	// 	super.upvote = upvotePercent;
	// 	votecount++;
	// 	return true;
	 }

}
