package model.mvmodel;

public class Date {

	public Date(int start) {
		start = year;
	}
	
	int year;
    // season == 0:Spring, 1:Summer, 2:Fall, 3:Winter
    int season;
    int seasonTotal;
    
    public void incrementSeason() {
        if (season < 3) {
            season++;
            seasonTotal++;
        }
        else if (season == 3) {
            // If it's winter set season to spring and add one to the year.
            season = 0;
            seasonTotal++;
            year++;
        }
        else {
            System.out.println("incrementSeason() says: Problem with season setting!");
        }
    }
}