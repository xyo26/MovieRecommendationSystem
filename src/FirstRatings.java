import edu.duke.*;

import java.lang.reflect.Array;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
	
	public ArrayList<Movie> loadMovies(String filename) {
		ArrayList<Movie> list = new ArrayList<Movie>();	
		FileResource input = new FileResource(filename);
		CSVParser parser = input.getCSVParser(true);
		for (CSVRecord currMovie : parser) {
			String currID = currMovie.get("id");
			String currTitle = currMovie.get("title");
			String currYear = currMovie.get("year");
			String currGenres = currMovie.get("genre");
			String currDirector = currMovie.get("director");
			String currCountry = currMovie.get("country");
			String currPoster = currMovie.get("poster");
			int currMinutes = Integer.parseInt(currMovie.get("minutes"));
			//int currMinutes = 1;
			
			Movie movie = new Movie(currID, currTitle, currYear, currGenres, currDirector, currCountry, currPoster, currMinutes);
			list.add(movie);
		}
		return list;
	}
	
	public ArrayList<Rater> loadRaters(String filename) {
		ArrayList<Rater> raterList = new ArrayList<Rater>();
		ArrayList<String> raterIDList = new ArrayList<String>();
		FileResource input = new FileResource(filename);
		CSVParser parser = input.getCSVParser(true);
		for (CSVRecord curr : parser) {
			String currRater = curr.get("rater_id");		
			String currMovie = curr.get("movie_id");
			double currRating = Double.parseDouble(curr.get("rating"));
			String currTime = curr.get("time");
			
			
			Rating rating = new Rating(currMovie, currRating);
			
			if (!raterIDList.contains(currRater)) {
				Rater rater = new EfficientRater(currRater);
				rater.addRating(currMovie, currRating);
				raterList.add(rater);
				raterIDList.add(currRater);
			}
			else {
				int index = raterIDList.indexOf(currRater);
				raterList.get(index).addRating(currMovie, currRating);
			}
		}
		return raterList;	
	}
	
	public HashMap<String, ArrayList<Rating>> buildRaterMap(String filename) {
		HashMap<String, ArrayList<Rating>> raterMap = new HashMap<String, ArrayList<Rating>>();
		FileResource input = new FileResource(filename);
		CSVParser parser = input.getCSVParser(true);
		for (CSVRecord curr : parser) {
			String currRater = curr.get("rater_id");		
			String currMovie = curr.get("movie_id");
			double currRating = Double.parseDouble(curr.get("rating"));
			String currTime = curr.get("time");
			
			Rater rater = new EfficientRater(currRater);
			Rating rating = new Rating(currMovie, currRating);
			
			if (!raterMap.containsKey(currRater)) {				
				ArrayList<Rating> ratingList = new ArrayList<Rating>();
				ratingList.add(rating);
				raterMap.put(currRater, ratingList);
			}
			else {				
				ArrayList<Rating> ratingList = raterMap.get(currRater);
				ratingList.add(rating);
				raterMap.put(currRater, ratingList);
			}
		}
		return raterMap;	
	}
	
	public int numRatingsForRater(String raterID, String filename) {
		HashMap<String, ArrayList<Rating>> raterMap = buildRaterMap(filename);
		return raterMap.get(raterID).size();
	}
	
	public int maxNumRatings(String filename) {
		HashMap<String, ArrayList<Rating>> raterMap = buildRaterMap(filename);
		ArrayList<String> maxRaterList = new ArrayList<String>();
		int maxNumRatings = 0;
		String maxRater = "";
		for (String curr : raterMap.keySet()) {
			if (raterMap.get(curr).size() > maxNumRatings) {
				maxNumRatings = raterMap.get(curr).size();
				maxRater = curr;
			}
		}
		for (String curr : raterMap.keySet()) {
			if (raterMap.get(curr).size() == maxNumRatings) {
				maxRaterList.add(curr);
				System.out.println("max rater ID: " + curr);
			}
		}
		System.out.println("# raters with max ratings: " + maxRaterList.size());
		return maxNumRatings;
	}
	
	public int numRatingsForMovie (String movieID, String filename) {
		HashMap<String, ArrayList<Rating>> raterMap = buildRaterMap(filename);
		ArrayList<String> raterList = new ArrayList<String>();
		for (String curr : raterMap.keySet()) {
			int numRating = raterMap.get(curr).size();
			ArrayList<Rating> ratingList = raterMap.get(curr);
			for (int i = 0; i < numRating; i++) {
				if (ratingList.get(i).getItem().equals(movieID)) {
					raterList.add(curr);
				}
			}
		}
		return raterList.size();
	}
	
	public int numMovies (String filename) {
		HashMap<String, ArrayList<Rating>> raterMap = buildRaterMap(filename);
		ArrayList<String> movieList = new ArrayList<String>();
		for (String curr : raterMap.keySet()) {
			int numRating = raterMap.get(curr).size();
			ArrayList<Rating> ratingList = raterMap.get(curr);
			for (int i = 0; i < numRating; i++) {
				if (!movieList.contains(ratingList.get(i).getItem())) {
					movieList.add(ratingList.get(i).getItem());
				}
			}
		}
		return movieList.size();
	}
	
	public int countComedy(ArrayList<Movie> list) {
		int comedy =  0;
		for (Movie curr : list) {
			if (curr.getGenres().indexOf("Comedy") != -1) {
				comedy +=1;
			}
		}
		return comedy;
	}
	
	public int countLargeMinutes(ArrayList<Movie> list) {
		int minutes =  0;
		for (Movie curr : list) {
			if (curr.getMinutes() > 150) {
				minutes +=1;
			}
		}
		return minutes;
	}
	
	public HashMap<String, ArrayList<String>> countDirectorWork(ArrayList<Movie> list) {
		HashMap<String, ArrayList<String>> dirMap =  new HashMap<String, ArrayList<String>>();
		for (Movie curr : list) {
			String[] dirList = curr.getDirector().split(", ");
			for (int i = 0; i < dirList.length; i++) {
				if (!dirMap.containsKey(dirList[i].trim())) {
					ArrayList<String> movies = new ArrayList<String>();
					movies.add(curr.getID());
					dirMap.put(dirList[i].trim(), movies);
				}
				else {
					ArrayList<String> mov = dirMap.get(dirList[i].trim());
					mov.add(curr.getID());
					dirMap.put(dirList[i].trim(), mov);
				}
			}
		}
		return dirMap;
	}
	
	public void testLoadMovies() {
		ArrayList<Movie> list = new ArrayList<Movie>();
		String sourceShort = "data/ratedmovies_short.csv";
		String sourceLong = "data/ratedmoviesfull.csv";
		list = loadMovies(sourceLong);
//		for (Movie curr : list) {
//			System.out.println(curr.toString());
//		}
		System.out.println("# movies in the file: " + list.size());
	}
	
	public void testLoadRaters() {
		HashMap<String, ArrayList<Rating>> raterMap = new HashMap<String, ArrayList<Rating>>();
		String sourceShort = "data/ratings_short.csv";
		String sourceLong = "data/ratings.csv";
		raterMap = buildRaterMap(sourceLong);
		System.out.println("# raters in the file: " + raterMap.size());
		for (String curr : raterMap.keySet()) {
			//System.out.print(curr + "\t" + raterMap.get(curr).size() + "\n");
			for (int i = 0; i < raterMap.get(curr).size(); i++) {
				//System.out.println(raterMap.get(curr).get(i).toString());
			}
		}
	}
	
	public void testNumRatingsForRater() {
		ArrayList<Movie> list = new ArrayList<Movie>();
		String sourceShort = "data/ratings_short.csv";
		String sourceLong = "data/ratings.csv";
		String raterID = "193";
		int ratingNum = numRatingsForRater(raterID, sourceLong);
		System.out.println("Rater-" + raterID + "\t commented on " + ratingNum + " movies");
	}
	
	public void testMaxNumRatings() {
		ArrayList<Movie> list = new ArrayList<Movie>();
		String sourceShort = "data/ratings_short.csv";
		String sourceLong = "data/ratings.csv";
		int max = maxNumRatings(sourceLong);
		System.out.println("Max rating number: " + max);
	}
	
	
	public void testNumRatingsForMovie() {
		ArrayList<Movie> list = new ArrayList<Movie>();
		String sourceShort = "data/ratings_short.csv";
		String sourceLong = "data/ratings.csv";
		String movieID = "1798709";
		int numRatings = numRatingsForMovie(movieID, sourceLong);
		System.out.println("Movie-" + movieID + " is commented by " + numRatings + " people");
	}
	
	
	public void testNumMovies() {
		ArrayList<Movie> list = new ArrayList<Movie>();
		String sourceShort = "data/ratings_short.csv";
		String sourceLong = "data/ratings.csv";
		int num = numMovies(sourceLong);
		System.out.println("Number of movies: " + num);
	}
	
	public void testCountComedy() {
		ArrayList<Movie> list = new ArrayList<Movie>();
		String sourceShort = "data/ratedmovies_short.csv";
		String sourceLong = "data/ratedmoviesfull.csv";
		list = loadMovies(sourceLong);
//		for (Movie curr : list) {
//			System.out.println(curr.toString());
//		}
		System.out.println("# movies in the file: " + list.size());
		int comedy = countComedy(list);
		System.out.println("# comedy movies in the file: " + comedy);
	}
	
	public void testCountLargeMinutes() {
		ArrayList<Movie> list = new ArrayList<Movie>();
		String sourceShort = "data/ratedmovies_short.csv";
		String sourceLong = "data/ratedmoviesfull.csv";
		list = loadMovies(sourceLong);
//		for (Movie curr : list) {
//			System.out.println(curr.toString());
//		}
		System.out.println("# movies in the file: " + list.size());
		int minutes = countLargeMinutes(list);
		System.out.println("# comedy movies in the file: " + minutes);
	}
	
	public void testCountDirectorWork() {
		ArrayList<Movie> list = new ArrayList<Movie>();
		String sourceShort = "data/ratedmovies_short.csv";
		String sourceLong = "data/ratedmoviesfull.csv";
		list = loadMovies(sourceLong);
//		for (Movie curr : list) {
//			System.out.println(curr.toString());
//		}
		System.out.println("# movies in the file: " + list.size());
		HashMap<String, ArrayList<String>> dirMap = countDirectorWork(list);
		int max = 0;
		ArrayList<String> maxMovies = new ArrayList<>();
		for (ArrayList<String> curr : dirMap.values()) {
			if (curr.size() > max) {
				max = curr.size();
				maxMovies = curr;
			}
		}
		System.out.println("# movies from one director: " + max);
		for (String curr : dirMap.keySet()) {
			if (dirMap.get(curr).size()  == max) {
				System.out.println("director with max number of movies: " + curr);
			}
		}
		
	}
	
	public void testLoadRaters2() {
		String sourceShort = "data/ratings_short.csv";
		String sourceLong = "data/ratings.csv";
		ArrayList<Rater> raterList = loadRaters(sourceShort);
		System.out.println("# raters in the file: " + raterList.size());
		for (Rater curr : raterList) {
			System.out.println(curr.toString());
		}
	}
	
	public static void main(String[] args) {
		FirstRatings result = new FirstRatings();
		result.testLoadRaters2();
	}
	
}
