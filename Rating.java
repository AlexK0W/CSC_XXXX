package Homework3;

import java.time.LocalDate;

public class Rating {

    private int reviewerID;
    private int movieID;
    private int stars;
    private LocalDate ratingDate;

    public Rating(){

    }

    public Rating(int reviewerID, int movieID, int stars, LocalDate ratingDate) {
        this.reviewerID = reviewerID;
        this.movieID = movieID;
        this.stars = stars;
        this.ratingDate = ratingDate;
    }

    public int getReviewerID() {
        return reviewerID;
    }

    public void setReviewerID(int reviewerID) {
        this.reviewerID = reviewerID;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public LocalDate getRatingDate() {
        return ratingDate;
    }

    public void setRatingDate(LocalDate ratingDate) {
        this.ratingDate = ratingDate;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "reviewerID=" + reviewerID +
                ", movieID=" + movieID +
                ", stars=" + stars +
                ", ratingDate=" + ratingDate +
                '}';
    }
}
