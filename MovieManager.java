package MVBE;


import java.io.Serializable;
import java.sql.*;
import java.time.LocalDate;

import java.util.logging.Logger;
import java.util.logging.Level;

public class MovieManager implements Serializable {

    private static final Logger logger = Logger.getLogger(MovieManager.class.getName());
    private final Connection connection;

    public MovieManager(String userName, String password, String url) throws SQLException, ClassNotFoundException {
        this.connection = getConnection(userName, password, url);
    }

    private Connection getConnection(String userName, String password, String url) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, userName, password);
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error closing connection", e);
        }
    }
    public Movie consultMovie(String movieName) {
        String sql = "SELECT * FROM movie WHERE title = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, movieName);
            try (ResultSet rset = stmt.executeQuery()) {
                if (rset.next()) {
                    return new Movie(rset.getInt("movieID"), rset.getString("title"),
                            rset.getInt("year"), rset.getString("director"));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error consulting movie", e);
        }
        return null;
    }

    public Movie addMovie(int movieID, String title, int year, String director) {
        String sql = "INSERT INTO movie (movieID, title, year, director) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, movieID);
            stmt.setString(2, title);
            stmt.setInt(3, year);
            stmt.setString(4, director);
            if (stmt.executeUpdate() > 0) {
                logger.info("Movie inserted successfully");
                return new Movie(movieID, title, year, director);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error adding movie", e);
        }
        return null;
    }
    public void updateMovie(int movieID, String title, int year, String director) {
        String sql = "UPDATE movie SET title = ?, year = ?, director = ? WHERE movieID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            pstmt.setString(1, title);
            pstmt.setInt(2, year);
            pstmt.setString(3, director);
            pstmt.setInt(4, movieID);
            pstmt.executeUpdate();
            connection.commit();
            logger.info("Movie updated successfully");
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException rollbackEx) {
                logger.log(Level.SEVERE, "Error rolling back transaction", rollbackEx);
            }
            logger.log(Level.SEVERE, "Error updating movie", e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Error setting auto-commit", e);
            }
        }
    }

    public Reviewer consultReviewer(String name){
        Reviewer reviewer = null;
        try {
            String sql = "select * from reviewer r where r.name='" + name + "'";
            Statement stmt = connection.createStatement();
            ResultSet rset = stmt.executeQuery(sql);
            while (rset.next()){
                reviewer = new Reviewer(rset.getInt("reviewerID"),rset.getNString("name"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return reviewer;
    }

    public Reviewer addReviewer(int reviewerID, String name){
        Reviewer reviewer = null;
        try {
            String sql = "insert into reviewer values(" + reviewerID + ",'" + name + "')";
            Statement stmt = connection.createStatement();
            int nrows = stmt.executeUpdate(sql);
            if (nrows !=-1){
                reviewer = new Reviewer(reviewerID,name);
            }
            stmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return reviewer;
    }

    public  void updateReviewer(int reviewerID, String name){
        PreparedStatement pstmt = null;
        try{
            connection.setAutoCommit(false);
            String query = "Update Reviewer set name = ? where reviewerID ='" + reviewerID + "'";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1,name);
            pstmt.executeUpdate();
            connection.commit();
            pstmt.close();
            connection.setAutoCommit(true);
        }catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public Rating consultRating(int reviewerID,int movieID){
        Rating rating = null;
        try {
            String sql = "select * from rating r where r.movieID = " + movieID + " and r.reviewerID = " + reviewerID;
            Statement stmt = connection.createStatement();
            ResultSet rset = stmt.executeQuery(sql);
            while (rset.next()){
               rating = new Rating(rset.getInt("reviewerID"), rset.getInt("movieID"), rset.getInt("stars"), rset.getDate("ratingDate").toLocalDate());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return rating;
    }

    public Rating addRating(int reviewerID, int movieID, int stars, LocalDate ratingDate){
        Rating rating = null;
        try {
            String sql = "insert into rating values(" + reviewerID + "," + movieID + "," + stars + ",'" + ratingDate + "')";
            Statement stmt = connection.createStatement();
            int nrows = stmt.executeUpdate(sql);
            if (nrows !=-1){
                rating = new Rating(reviewerID,movieID,stars, ratingDate);
            }
            stmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rating;
    }

    public void updateRating(int reviewerID, int movieID, int stars, LocalDate ratingDate){
        PreparedStatement pstmt = null;
        try{
            connection.setAutoCommit(false);
            String query = "Update rating set stars = ? where reviewerID ='" + reviewerID + "' and movieID = '" + movieID + "'";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, String.valueOf(stars));
            pstmt.executeUpdate();
            connection.commit();
            pstmt.close();
            connection.setAutoCommit(true);
        }catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}
