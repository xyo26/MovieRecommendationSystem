import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerWithFilters {
	
	public void printAverageRatings() {
		//String moviefile = "data/ratedmovies_short.csv";
		String moviefile = "data/ratedmoviesfull.csv";
		//String raterfile = "data/ratings_short.csv";
		String raterfile = "data/ratings.csv";
		
		ThirdRatings tr = new ThirdRatings(raterfile);
		System.out.println("# raters: " + tr.getRaterSize());
		MovieDatabase mdb = new MovieDatabase();
		mdb.initialize(moviefile);
		System.out.println("# movies: " + mdb.size());
		
		int minimalRaters = 35;
		ArrayList<Rating> ratingList = tr.getAverageRatings(minimalRaters);
		System.out.println("# valid movies: " + ratingList.size());
		Collections.sort(ratingList);
		for (Rating curr : ratingList) {
			System.out.println(curr.getValue() + "\t" + mdb.getTitle(curr.getItem()));
		}	
	}
	
	public void printAverageRatingsByYear() {
		//String moviefile = "data/ratedmovies_short.csv";
		String moviefile = "data/ratedmoviesfull.csv";
		//String raterfile = "data/ratings_short.csv";
		String raterfile = "data/ratings.csv";
		
		ThirdRatings tr = new ThirdRatings(raterfile);
		System.out.println("# raters: " + tr.getRaterSize());
		MovieDatabase mdb = new MovieDatabase();
		mdb.initialize(moviefile);
		System.out.println("# movies: " + mdb.size());
		
		int minimalRaters = 20;
		int year = 2000;
		Filter filterCriteria = new YearAfterFilter(year);
		ArrayList<Rating> ratingList = tr.getAverageRatingsByFilter(minimalRaters, filterCriteria);
		System.out.println("# valid movies: " + ratingList.size());
		//sortOrderRating(ratingList);
		Collections.sort(ratingList);
		for (Rating curr : ratingList) {
			System.out.println(curr.getValue() + "\t" + mdb.getYear(curr.getItem()) + "\t" + mdb.getTitle(curr.getItem()));
		}	
	}
	
	public void printAverageRatingsByGenre() {
		//String moviefile = "data/ratedmovies_short.csv";
		String moviefile = "data/ratedmoviesfull.csv";
		//String raterfile = "data/ratings_short.csv";
		String raterfile = "data/ratings.csv";
		
		ThirdRatings tr = new ThirdRatings(raterfile);
		System.out.println("# raters: " + tr.getRaterSize());
		MovieDatabase mdb = new MovieDatabase();
		mdb.initialize(moviefile);
		System.out.println("# movies: " + mdb.size());
		
		int minimalRaters = 20;
		String genre = "Comedy";
		Filter filterCriteria = new GenreFilter(genre);
		ArrayList<Rating> ratingList = tr.getAverageRatingsByFilter(minimalRaters, filterCriteria);
		System.out.println("# valid movies: " + ratingList.size());
		//sortOrderRating(ratingList);
		Collections.sort(ratingList);
		for (Rating curr : ratingList) {
			System.out.println(curr.getValue() + "\t" + mdb.getTitle(curr.getItem()) + "\n" + mdb.getGenres(curr.getItem()));
		}	
	}
	
	public void printAverageRatingsByMinutes() {
		//String moviefile = "data/ratedmovies_short.csv";
		String moviefile = "data/ratedmoviesfull.csv";
		//String raterfile = "data/ratings_short.csv";
		String raterfile = "data/ratings.csv";
		
		ThirdRatings tr = new ThirdRatings(raterfile);
		System.out.println("# raters: " + tr.getRaterSize());
		MovieDatabase mdb = new MovieDatabase();
		mdb.initialize(moviefile);
		System.out.println("# movies: " + mdb.size());
		
		int minimalRaters = 5;
		int min = 105;
		int max = 135;
		Filter filterCriteria = new MinutesFilter(min, max);
		ArrayList<Rating> ratingList = tr.getAverageRatingsByFilter(minimalRaters, filterCriteria);
		System.out.println("# valid movies: " + ratingList.size());
		//sortOrderRating(ratingList);
		Collections.sort(ratingList);
		for (Rating curr : ratingList) {
			System.out.println(curr.getValue() + "\t" + mdb.getMinutes(curr.getItem()) + "\t" + mdb.getTitle(curr.getItem()));
		}	
	}
	
	public void printAverageRatingsByDirectors() {
		//String moviefile = "data/ratedmovies_short.csv";
		String moviefile = "data/ratedmoviesfull.csv";
		//String raterfile = "data/ratings_short.csv";
		String raterfile = "data/ratings.csv";
		
		ThirdRatings tr = new ThirdRatings(raterfile);
		System.out.println("# raters: " + tr.getRaterSize());
		MovieDatabase mdb = new MovieDatabase();
		mdb.initialize(moviefile);
		System.out.println("# movies: " + mdb.size());
		
		int minimalRaters = 4;
		String directors = "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack";
		Filter filterCriteria = new DirectorsFilter(directors);
		ArrayList<Rating> ratingList = tr.getAverageRatingsByFilter(minimalRaters, filterCriteria);
		System.out.println("# valid movies: " + ratingList.size());
		//sortOrderRating(ratingList);
		Collections.sort(ratingList);
		for (Rating curr : ratingList) {
			System.out.println(curr.getValue() + "\t" + mdb.getTitle(curr.getItem()) + "\n\t" + mdb.getDirector(curr.getItem()));
		}	
	}
	
	public void printAverageRatingsByYearAfterAndGenre() {
		//String moviefile = "data/ratedmovies_short.csv";
		String moviefile = "data/ratedmoviesfull.csv";
		//String raterfile = "data/ratings_short.csv";
		String raterfile = "data/ratings.csv";
		
		ThirdRatings tr = new ThirdRatings(raterfile);
		System.out.println("# raters: " + tr.getRaterSize());
		MovieDatabase mdb = new MovieDatabase();
		mdb.initialize(moviefile);
		System.out.println("# movies: " + mdb.size());
		
		int minimalRaters = 8;
		int year = 1990;
		String genre = "Drama";
		Filter yearAfterFilter = new YearAfterFilter(year);
		Filter genreFilter = new GenreFilter(genre);
		AllFilters filterCriteria = new AllFilters();
		filterCriteria.addFilter(genreFilter);
		filterCriteria.addFilter(yearAfterFilter);
		
		ArrayList<Rating> ratingList = tr.getAverageRatingsByFilter(minimalRaters, filterCriteria);
		System.out.println("# valid movies: " + ratingList.size());
		//sortOrderRating(ratingList);
		Collections.sort(ratingList);
		for (Rating curr : ratingList) {
			System.out.println(curr.getValue() + "\t" + mdb.getYear(curr.getItem()) + "\t" + mdb.getTitle(curr.getItem()) + "\n\t" + mdb.getGenres(curr.getItem()));
		}	
	}
	
	public void printAverageRatingsByDirectorsAndMinutes() {
		//String moviefile = "data/ratedmovies_short.csv";
		String moviefile = "data/ratedmoviesfull.csv";
		//String raterfile = "data/ratings_short.csv";
		String raterfile = "data/ratings.csv";
		
		ThirdRatings tr = new ThirdRatings(raterfile);
		System.out.println("# raters: " + tr.getRaterSize());
		MovieDatabase mdb = new MovieDatabase();
		mdb.initialize(moviefile);
		System.out.println("# movies: " + mdb.size());
		
		int minimalRaters = 3;
		int min = 90;
		int max = 180;
		String directors = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
		Filter DirectorsFilter = new DirectorsFilter(directors);
		Filter MinutesFilter = new MinutesFilter(min, max);
		AllFilters filterCriteria = new AllFilters();
		filterCriteria.addFilter(DirectorsFilter);
		filterCriteria.addFilter(MinutesFilter);
		
		ArrayList<Rating> ratingList = tr.getAverageRatingsByFilter(minimalRaters, filterCriteria);
		System.out.println("# valid movies: " + ratingList.size());
		//sortOrderRating(ratingList);
		Collections.sort(ratingList);
		for (Rating curr : ratingList) {
			System.out.println(curr.getValue() + "\t Time:" + mdb.getMinutes(curr.getItem()) + "\t" + mdb.getTitle(curr.getItem()) + "\n\t" + mdb.getDirector(curr.getItem()));
		}	
	}
	
	public static void main(String[] args) {
		MovieRunnerWithFilters result =  new MovieRunnerWithFilters();
		result.printAverageRatingsByYearAfterAndGenre();
	}
}
