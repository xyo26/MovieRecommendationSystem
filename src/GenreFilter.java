
public class GenreFilter implements Filter {
	private String myGenre;
	
	public GenreFilter(String genre) {
		// TODO Auto-generated constructor stub
		myGenre = genre;
	}
	
	@Override
	public boolean satisfies(String id) {
		// TODO Auto-generated method stub
		return MovieDatabase.getGenres(id).contains(myGenre);
	}

}
