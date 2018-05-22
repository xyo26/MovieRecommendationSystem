
public class DirectorsFilter implements Filter {
	private String myDirector;
	
	public DirectorsFilter(String directors) {
		myDirector = directors;
	}
	@Override
	public boolean satisfies(String id) {
		String[] list = myDirector.split("\\s*,\\s*");
		boolean ans = false;
		for (int i = 0; i < list.length; i++) {
			if (MovieDatabase.getDirector(id).contains(list[i])) {
				ans = true;
			}
		}
		return ans;
	}

}
