import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerSimilarRatings {
	
	public void printAverageRatings() {
		//String moviefile = "data/ratedmovies_short.csv";
		String moviefile = "data/ratedmoviesfull.csv";
		//String raterfile = "data/ratings_short.csv";
		String raterfile = "data/ratings.csv";
		
		MovieDatabase mdb = new MovieDatabase();
		mdb.initialize(moviefile);
		System.out.println("# movies: " + mdb.size());
		RaterDatabase rdb = new RaterDatabase();
		rdb.initialize(raterfile);
		System.out.println("# raters: " + rdb.size());
		
		FourthRatings fr = new FourthRatings();		
		
		int minimalRaters = 12;
		ArrayList<Rating> ratingList = fr.getAverageRatings(minimalRaters);
		System.out.println("# valid movies: " + ratingList.size());
		Collections.sort(ratingList);
		for (Rating curr : ratingList) {
			System.out.println(curr.getValue() + "\t" + mdb.getTitle(curr.getItem()));
		}	
	}
	
	public void printAverageRatingsByYearAfterAndGenre() {
		//String moviefile = "data/ratedmovies_short.csv";
		String moviefile = "data/ratedmoviesfull.csv";
		//String raterfile = "data/ratings_short.csv";
		String raterfile = "data/ratings.csv";
		
		MovieDatabase mdb = new MovieDatabase();
		mdb.initialize(moviefile);
		System.out.println("# movies: " + mdb.size());
		RaterDatabase rdb = new RaterDatabase();
		rdb.initialize(raterfile);
		System.out.println("# raters: " + rdb.size());
		
		FourthRatings fr = new FourthRatings();
		
		int minimalRaters = 8;
		int year = 1990;
		String genre = "Drama";
		Filter yearAfterFilter = new YearAfterFilter(year);
		Filter genreFilter = new GenreFilter(genre);
		AllFilters filterCriteria = new AllFilters();
		filterCriteria.addFilter(genreFilter);
		filterCriteria.addFilter(yearAfterFilter);
		
		ArrayList<Rating> ratingList = fr.getAverageRatingsByFilter(minimalRaters, filterCriteria);
		System.out.println("# valid movies: " + ratingList.size());
		//sortOrderRating(ratingList);
		Collections.sort(ratingList);
		for (Rating curr : ratingList) {
			System.out.println(curr.getValue() + "\t" + mdb.getYear(curr.getItem()) + "\t" + mdb.getTitle(curr.getItem()) + "\n\t" + mdb.getGenres(curr.getItem()));
		}	
	}
	
	public void printSimilarRatings() {
		//String moviefile = "data/ratedmovies_short.csv";
		String moviefile = "data/ratedmoviesfull.csv";
		//String raterfile = "data/ratings_short.csv";
		String raterfile = "data/ratings.csv";
				
		RaterDatabase rdb = new RaterDatabase();
		rdb.initialize(raterfile);
		System.out.println("# raters: " + rdb.size());
		MovieDatabase mdb = new MovieDatabase();
		mdb.initialize(moviefile);
		System.out.println("# movies: " + mdb.size());
				
		FourthRatings fr = new FourthRatings();		
		
		String raterID = "337";
		int minimalRaters = 3;
		int numSimilarRaters = 10;
		ArrayList<Rating> ratingList = fr.getSimilarRatings(raterID, numSimilarRaters, minimalRaters);
		System.out.println("# valid movies: " + ratingList.size());
		for (Rating curr : ratingList) {
			System.out.println(curr.getValue() + "\t" + mdb.getTitle(curr.getItem()));
		}
	}

	public void printSimilarRatingsByGenre() {
		//String moviefile = "data/ratedmovies_short.csv";
		String moviefile = "data/ratedmoviesfull.csv";
		//String raterfile = "data/ratings_short.csv";
		String raterfile = "data/ratings.csv";
		
		MovieDatabase mdb = new MovieDatabase();
		mdb.initialize(moviefile);
		System.out.println("# movies: " + mdb.size());
		RaterDatabase rdb = new RaterDatabase();
		rdb.initialize(raterfile);
		System.out.println("# raters: " + rdb.size());
		
		FourthRatings fr = new FourthRatings();
		
		int minimalRaters = 5;
		String raterID = "964";
		int numSimilarRaters = 20;
		String genre = "Mystery";
		Filter filterCriteria = new GenreFilter(genre);
		
		ArrayList<Rating> ratingList = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, filterCriteria);
		System.out.println("# valid movies: " + ratingList.size());
		//sortOrderRating(ratingList);
		Collections.sort(ratingList, Collections.reverseOrder());
		for (Rating curr : ratingList) {
			System.out.println(curr.getValue() + "\t" + mdb.getTitle(curr.getItem()) + "\n\t" + mdb.getGenres(curr.getItem()));
		}	
	}
	
	public void printSimilarRatingsByDirector() {
		//String moviefile = "data/ratedmovies_short.csv";
		String moviefile = "data/ratedmoviesfull.csv";
		//String raterfile = "data/ratings_short.csv";
		String raterfile = "data/ratings.csv";
		
		MovieDatabase mdb = new MovieDatabase();
		mdb.initialize(moviefile);
		System.out.println("# movies: " + mdb.size());
		RaterDatabase rdb = new RaterDatabase();
		rdb.initialize(raterfile);
		System.out.println("# raters: " + rdb.size());
		
		FourthRatings fr = new FourthRatings();
		
		int minimalRaters = 2;
		String raterID = "120";
		int numSimilarRaters = 10;
		String directors = "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh";
		Filter filterCriteria = new DirectorsFilter(directors);
		
		ArrayList<Rating> ratingList = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, filterCriteria);
		System.out.println("# valid movies: " + ratingList.size());
		//sortOrderRating(ratingList);
		Collections.sort(ratingList, Collections.reverseOrder());
		for (Rating curr : ratingList) {
			System.out.println(curr.getValue() + "\t" + mdb.getTitle(curr.getItem()) + "\n\t" + mdb.getGenres(curr.getItem()));
		}	
	}
	
	public void printSimilarRatingsByGenreAndMinutes() {
		//String moviefile = "data/ratedmovies_short.csv";
		String moviefile = "data/ratedmoviesfull.csv";
		//String raterfile = "data/ratings_short.csv";
		String raterfile = "data/ratings.csv";
		
		MovieDatabase mdb = new MovieDatabase();
		mdb.initialize(moviefile);
		System.out.println("# movies: " + mdb.size());
		RaterDatabase rdb = new RaterDatabase();
		rdb.initialize(raterfile);
		System.out.println("# raters: " + rdb.size());
		
		FourthRatings fr = new FourthRatings();
		
		int minimalRaters = 3;
		String raterID = "168";
		int numSimilarRaters = 10;
		int min = 80;
		int max = 160;
		String genre = "Drama";
		Filter genreFilter = new GenreFilter(genre);
		Filter minutesFilter = new MinutesFilter(min, max);
		AllFilters filterCriteria = new AllFilters();
		filterCriteria.addFilter(genreFilter);
		filterCriteria.addFilter(minutesFilter);
		
		ArrayList<Rating> ratingList = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, filterCriteria);
		System.out.println("# valid movies: " + ratingList.size());
		//sortOrderRating(ratingList);
		Collections.sort(ratingList, Collections.reverseOrder());
		for (Rating curr : ratingList) {
			System.out.println(curr.getValue() + "\t" + mdb.getYear(curr.getItem()) + "\t" + mdb.getTitle(curr.getItem()) + "\n\t" + mdb.getGenres(curr.getItem()));
		}	
	}

	public void printSimilarRatingsByYearAfterAndMinutes() {
		//String moviefile = "data/ratedmovies_short.csv";
		String moviefile = "data/ratedmoviesfull.csv";
		//String raterfile = "data/ratings_short.csv";
		String raterfile = "data/ratings.csv";
		
		MovieDatabase mdb = new MovieDatabase();
		mdb.initialize(moviefile);
		System.out.println("# movies: " + mdb.size());
		RaterDatabase rdb = new RaterDatabase();
		rdb.initialize(raterfile);
		System.out.println("# raters: " + rdb.size());
		
		FourthRatings fr = new FourthRatings();
		
		int minimalRaters = 5;
		String raterID = "314";
		int numSimilarRaters = 10;
		int min = 70;
		int max = 200;
		int year = 1975;
		Filter yearFilter = new YearAfterFilter(year);
		Filter minutesFilter = new MinutesFilter(min, max);
		AllFilters filterCriteria = new AllFilters();
		filterCriteria.addFilter(yearFilter);
		filterCriteria.addFilter(minutesFilter);
		
		ArrayList<Rating> ratingList = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, filterCriteria);
		System.out.println("# valid movies: " + ratingList.size());
		//sortOrderRating(ratingList);
		Collections.sort(ratingList, Collections.reverseOrder());
		for (Rating curr : ratingList) {
			System.out.println(curr.getValue() + "\t" + mdb.getYear(curr.getItem()) + "\t" + mdb.getTitle(curr.getItem()) + "\n\t" + mdb.getGenres(curr.getItem()));
		}	
	}

	public static void main(String[] args) {
		MovieRunnerSimilarRatings result = new MovieRunnerSimilarRatings();
		result.printSimilarRatingsByYearAfterAndMinutes();
	}
}
