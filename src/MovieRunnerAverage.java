import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerAverage {

	
		public void printAverageRatings() {
			//String moviefile = "data/ratedmovies_short.csv";
			String moviefile = "data/ratedmoviesfull.csv";
			//String moviefile = "data/quizMovieFile.csv";
			//String raterfile = "data/ratings_short.csv";
			String raterfile = "data/ratings.csv";
			//String raterfile = "data/quizRatingFile.csv";
			
			SecondRatings sr = new SecondRatings(moviefile, raterfile);
			System.out.println("# movies: " + sr.getMovieSize());
			System.out.println("# raters: " + sr.getRaterSize());
			
			int minimalRaters = 20;
			ArrayList<Rating> ratingList = sr.getAverageRatings(minimalRaters);
			System.out.println("# valid movies: " + ratingList.size());
			Collections.sort(ratingList);
			for (Rating curr : ratingList) {
				System.out.println(curr.getValue() + "\t\t\t" + sr.getTitle(curr.getItem()));
			}
			
		}
		
		public int getLowestIdx(ArrayList<Rating> ratingList, int from) {
			double lowestRating = 0;
			int lowestIdx = 0;
			for (int i = from; i < ratingList.size(); i++) {
				if (ratingList.get(i).getValue() < lowestRating) {
					lowestRating = ratingList.get(i).getValue();
					lowestIdx = i;
				}
			}
			return lowestIdx;
		}
		
		public void testGetAverageByID() {
			String moviefile = "data/ratedmovies_short.csv";
			//String moviefile = "data/ratedmoviesfull.csv:'
			String raterfile = "data/ratings_short.csv";
			//String raterfile = "data/ratings.csv";
			SecondRatings sr = new SecondRatings(moviefile, raterfile);
//			System.out.println(sr.getMovieSize());
//			System.out.println(sr.getRaterSize());
			
			String movieID = "1798709";
			int minimalRaters = 3;
			double avg = sr.getAverageByID(movieID, minimalRaters);
			System.out.println(avg);
		}
		
		public void getAverageRatingOneMovie() {
			//String moviefile = "data/ratedmovies_short.csv";
			String moviefile = "data/ratedmoviesfull.csv";
			//String moviefile = "data/quizMovieFile.csv";
			
			//String raterfile = "data/ratings_short.csv";
			String raterfile = "data/ratings.csv";
			//String raterfile = "data/quizRatingFile.csv";
			SecondRatings sr = new SecondRatings(moviefile, raterfile);
			
			String title = "Vacation";
			int minimalRaters = 3;
			String id = sr.getID(title);
			double avg = sr.getAverageByID(id, minimalRaters);
			System.out.print(title + " average rating is: " + avg);
		}

		
		public static void main(String[] args) {
			MovieRunnerAverage result = new MovieRunnerAverage();
			result.printAverageRatings();
		}
}
