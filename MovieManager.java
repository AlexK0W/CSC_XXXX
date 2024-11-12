package Homework3;


import java.io.Serializable;
import java.sql.*;
import java.time.LocalDate;
import java.util.Date;


public class MovieManager implements Serializable {

    Connection connection = null;

    public MovieManager(String userName, String password, String url) {
        connection=getConnection( userName, password, url);
    }

    public Connection getConnection(String userName, String password, String url) {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //jdbc:mysql://<dbhost>:<dbport>/dbname

            //root Tnight2Sky@1stEarth
            connection = DriverManager.getConnection(url, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }


    public void CloseConnection(){
        try {
            connection.close();
        }catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public Movie consultMovie(String movieName){
        Movie movie = null;
        try{
            String sql = "select * from movie m where m.title='" + movieName + "'";
            Statement stmt = connection.createStatement();
            ResultSet rset = stmt.executeQuery(sql);
            while(rset.next()){
                movie = new Movie(rset.getInt("movieID"), rset.getNString("title"),rset.getInt("year"),rset.getNString("director"));
            }
            rset.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movie;
    }

    public Movie addMovie(int movieID, String title, int year, String director) {
        Movie movie = null;
        try {
            System.out.println("inserting into movie table..");
            String sql = "insert into movie values(" + movieID + ",'" + title + "'," + year + ",'" + director + "')";
            Statement stmt = connection.createStatement();
            int nrows = stmt.executeUpdate(sql);
            if (nrows != -1) {
                movie = new Movie(movieID, title, year, director);
            }
            stmt.close();
            System.out.println("Record inserted successfully..");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movie;
    }


    public void updateMovie(int movieID, String title, int year, String director){
        PreparedStatement pstmt = null;
        try {
            connection.setAutoCommit(false);
            System.out.println("Opened database successfully");
            String query = "Update Movie set title = ?, year=?, director=? where movieID ='" + movieID + "'";
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, title);
            pstmt.setInt(2, year);
            pstmt.setString(3, director);
            pstmt.executeUpdate();
            connection.commit();
            pstmt.close();
            connection.setAutoCommit(true);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Update Completed successfully");
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
            System.out.println("inserting into reviewer table..");
            String sql = "insert into reviewer values(" + reviewerID + ",'" + name + "')";
            Statement stmt = connection.createStatement();
            int nrows = stmt.executeUpdate(sql);
            if (nrows !=-1){
                reviewer = new Reviewer(reviewerID,name);
            }
            stmt.close();
            System.out.println("Record inserted successfully..");
        }catch (Exception e){
            e.printStackTrace();
        }

        return reviewer;
    }

    public  void updateReviewer(int reviewerID, String name){
        PreparedStatement pstmt = null;
        try{
            connection.setAutoCommit(false);
            System.out.println("Opened database successfully");
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
        System.out.println("Update Completed successfully");
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
            System.out.println("inserting into reviewer table..");
            String sql = "insert into rating values(" + reviewerID + "," + movieID + "," + stars + ",'" + ratingDate + "')";
            Statement stmt = connection.createStatement();
            int nrows = stmt.executeUpdate(sql);
            if (nrows !=-1){
                rating = new Rating(reviewerID,movieID,stars, ratingDate);
            }
            stmt.close();
            System.out.println("Record inserted successfully..");
        }catch (Exception e){
            e.printStackTrace();
        }
        return rating;
    }

    public void updateRating(int reviewerID, int movieID, int stars, LocalDate ratingDate){
        PreparedStatement pstmt = null;
        try{
            connection.setAutoCommit(false);
            System.out.println("Opened database successfully");
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
        System.out.println("Update Completed successfully");
    }
}
