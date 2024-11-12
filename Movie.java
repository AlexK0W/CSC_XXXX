package Homework3;

public class Movie {

    private int movieID;
    private  String title;
    private int Year;
    private String director;

    public Movie(){

    }

    public Movie(int movieID, String title, int year, String director) {
        this.movieID = movieID;
        this.title = title;
        this.Year = year;
        this.director = director;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        this.Year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieID=" + movieID +
                ", title='" + title + '\'' +
                ", Year=" + Year +
                ", director='" + director + '\'' +
                '}';
    }
}
