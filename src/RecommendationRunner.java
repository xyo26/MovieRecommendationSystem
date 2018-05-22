import java.util.ArrayList;
import java.util.Collections;

public class RecommendationRunner implements Recommender {

	@Override
	public ArrayList<String> getItemsToRate() {
		// TODO Auto-generated method stub
		MovieDatabase.initialize("ratedmoviesfull.csv");
		ArrayList<String> movieList = MovieDatabase.filterBy(new TrueFilter());
		Collections.shuffle(movieList);
		int maxListLen = 15;
		movieList = new ArrayList<String>(movieList.subList(0, maxListLen));
		return movieList;
	}

	@Override
	public void printRecommendationsFor(String webRaterID) {
		// TODO Auto-generated method stub
		FourthRatings fr = new FourthRatings();
		int numSimilarRaters = 20;
		int minimalRaters = 5;
		ArrayList<Rating> ratingList = fr.getSimilarRatings(webRaterID, numSimilarRaters, minimalRaters);
		int minListLen = 1;
		int maxListLen = 15;
		if (ratingList.size() < minListLen) {
			System.out.println("No recommendation matching your ratings. Please rate again.");
		}
		if (ratingList.size() > maxListLen) {
			ratingList = new ArrayList<Rating>(ratingList.subList(0, maxListLen));
		}
		StringBuilder buf = new StringBuilder();
		buf.append("<html>" +
		           "<body>" +
					"<style>" + 
					"TABLE {" + 
					"   border: 1px solid black;" + 
					"   border-collapse: collapse;" + 
					"   margin: 0 auto;" + 
					"}" + 
					"TD {" + 
					"   border: 1px solid black;" + 
					"   padding: 5px;" + 
					"   vertical-align: middle;" + 
					"}" + 
					"TH {" + 
					"   border: 1px solid black;" + 
					"   padding: 5px;" + 
					"}" + 
					"TR:nth-child(even) {" + 
					"    background-color: #f2f2f2" + 
					"}" + 
					"</style>" + 
				   "<table>" +
		           "<tr>" +
		           "<th> </th>" +
		           "<th>Movie</th>" +
		           "<th>Year</th>" +
		           "<th>Director</th>" +
		           "<th>Genre</th>" +
		           "</tr>");
		for (int i = 0; i < ratingList.size(); i++) {
		    String currMovieID = ratingList.get(i).getItem();
			buf.append("<tr><td>")
		       .append("<a href=" + MovieDatabase.getPoster(currMovieID) + ">" + "<img src=" + MovieDatabase.getPoster(currMovieID) + " width=\"10%\"> </a>")
		       .append("</td><td>")
		       .append(MovieDatabase.getTitle(currMovieID))
		       .append("</td><td>")
		       .append(MovieDatabase.getYear(currMovieID))
		       .append("</td><td>")
		       .append(MovieDatabase.getGenres(currMovieID))
		       .append("</td><td>")
		       .append(MovieDatabase.getCountry(currMovieID))
		       .append("</td></tr>");
		}
		buf.append("</table>" +
		           "</body>" +
		           "</html>");
		String html = buf.toString();
		System.out.println(html);
	}

}
