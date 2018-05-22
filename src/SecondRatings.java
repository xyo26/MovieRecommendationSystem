
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }

	public SecondRatings(String moviefile, String ratingsfile) {
		// TODO Auto-generated constructor stub
		FirstRatings fr = new FirstRatings();
		myMovies = fr.loadMovies(moviefile);
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
		ArrayList<Rating> ratingList = new ArrayList<Rating>();
		for (Movie currMovie : myMovies) {
			String currID = currMovie.getID();
			//System.out.println("\t" + currID);
			double currAvg = getAverageByID(currID, minimalRaters);
			//System.out.println(currAvg);
			if (currAvg != 0.0) {
				Rating rating = new Rating(currID, currAvg);
				ratingList.add(rating);
			}
		}
		return ratingList;
	}
	
	public String getTitle(String id) {
		for (Movie curr : myMovies) {
			if (curr.getID().equals(id)) {
				return curr.getTitle();
			}
		}
		return "***** Movie with id: " + id + " is not found *****";
	}
	
	public int getMovieSize() {
		return myMovies.size();
	}
	
	public int getRaterSize() {
		return myRaters.size();
	}
    
	public String getID(String title) {
		for (Movie curr : myMovies) {
			if (curr.getTitle().equals(title)) {
				return curr.getID();
			}
		}
		return "***** NO SUCH TITLE *****";
	}
}
