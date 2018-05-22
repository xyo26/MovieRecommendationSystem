import java.util.ArrayList;
import java.util.Collections;

public class FourthRatings {
	
	public double getAverageByID(String id, int minimalRaters) {
		double sum = 0;
		int ratingNum = 0;
		
		for (Rater curr : RaterDatabase.getRaters()) {
			if (curr.hasRating(id)) {
				sum = sum + curr.getRating(id);
				ratingNum++;
			}
		}
		if (ratingNum >= minimalRaters) {
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
	
	public int getRaterSize() {
		return RaterDatabase.getRaters().size();
	}
	
	private double dotProduct(Rater me, Rater r) {
		double ans = 0;
		ArrayList<String> meMovieIDs = me.getItemsRated();
		for (String curr : meMovieIDs) {
			if (me.hasRating(curr) && r.hasRating(curr)) {
				ans += (me.getRating(curr)-5)*(r.getRating(curr)-5);
			}
		}
		return ans;		
	}
	
	private ArrayList<Rating> getSimilarities(String id) {
		ArrayList<Rating> similarRaterList = new ArrayList<Rating>();
		Rater meRater = RaterDatabase.getRater(id);
		for (Rater curr : RaterDatabase.getRaters()) {
			double currProduct = dotProduct(meRater, curr);
			if (currProduct > 0 && !curr.getID().equals(id)) {
				similarRaterList.add(new Rating(curr.getID(), currProduct));
			}
		}
		Collections.sort(similarRaterList, Collections.reverseOrder());
		return similarRaterList;
	}
	
	public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
		ArrayList<Rating> recommendList = new ArrayList<Rating>();
		ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
		ArrayList<Rating> similarList = getSimilarities(id);
		if (similarList.size() < numSimilarRaters) {
            numSimilarRaters = similarList.size();
        }
        for (String currMovieID : movies) {
        		double totRating = 0;
        		int numRater = 0;
        		for (int i = 0; i < numSimilarRaters; i++) {
        			String raterID = similarList.get(i).getItem();
        			double weight = similarList.get(i).getValue(); 
        			if (RaterDatabase.getRater(raterID).hasRating(currMovieID)) {
        				numRater++;
        				totRating += weight*RaterDatabase.getRater(raterID).getRating(currMovieID);
        			}
        		}
        		if (numRater >= minimalRaters) {
        			recommendList.add(new Rating(currMovieID, totRating/numRater));
        		}
		}
        Collections.sort(recommendList,Collections.reverseOrder());
		return recommendList;
	}

	public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria) {
		ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
		ArrayList<Rating> recommendList = new ArrayList<Rating>();
		ArrayList<Rating> similarList = getSimilarities(id);
		if (similarList.size() < numSimilarRaters) {
            numSimilarRaters = similarList.size();
        }
        for (String currMovieID : movies) {
        		double totRating = 0;
        		int numRater = 0;
        		for (int i = 0; i < numSimilarRaters; i++) {
        			String raterID = similarList.get(i).getItem();
        			double weight = similarList.get(i).getValue(); 
        			if (RaterDatabase.getRater(raterID).hasRating(currMovieID)) {
        				numRater++;
        				totRating += weight*RaterDatabase.getRater(raterID).getRating(currMovieID);
        			}
        		}
        		if (numRater >= minimalRaters) {
        			recommendList.add(new Rating(currMovieID, totRating/numRater));
        		}
		}
        Collections.sort(recommendList,Collections.reverseOrder());
		return recommendList;
	}
	
}
