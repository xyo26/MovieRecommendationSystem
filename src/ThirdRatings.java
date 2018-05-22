import java.util.ArrayList;
import java.util.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }

	public ThirdRatings(String ratingsfile) {
		// TODO Auto-generated constructor stub
		FirstRatings fr = new FirstRatings();
		myRaters = fr.loadRaters(ratingsfile);
	}
	
	public double getAverageByID(String id, int minimalRaters) {
		double sum = 0;
		int ratingNum = 0;
		for (Rater curr : myRaters) {
			if (curr.hasRating(id)) {
				sum = sum + curr.getRating(id);
				ratingNum++;
			}
		}
		if (ratingNum >= minimalRaters) {
			//System.out.println(sum + "/" + ratingNum);
			return sum/ratingNum;
		} else {
			return 0.0;
		}
	}
	
	public ArrayList<Rating> getAverageRatings(int minimalRaters) {
		ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
		ArrayList<Rating> ratingList = new ArrayList<Rating>();
		for (String currMovieID : movies) {
			double currAvg = getAverageByID(currMovieID, minimalRaters);
			if (currAvg != 0.0) {
				Rating rating = new Rating(currMovieID, currAvg);
				ratingList.add(rating);
			}
		}
		return ratingList;
	}
	
	public int getRaterSize() {
		return myRaters.size();
	}
	
	public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
		ArrayList<Rating> ratingList = new ArrayList<Rating>();
		ArrayList<String> filteredList = MovieDatabase.filterBy(filterCriteria);
		
		for (String currMovieID : filteredList) {
			double currAvg = getAverageByID(currMovieID, minimalRaters);
			if (currAvg != 0.0) {
				Rating rating = new Rating(currMovieID, currAvg);
				ratingList.add(rating);
			}
		}
		return ratingList;
	}
}
