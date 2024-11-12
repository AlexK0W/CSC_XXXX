package MVBE;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;

public class Test {
    public static void main(String[] args) throws ParseException, SQLException, ClassNotFoundException {
        MovieManagerTester();
    }

    public static void MovieManagerTester() throws SQLException, ClassNotFoundException {
        MovieManager m = new MovieManager("root","Tnight2Sky@1stEarth","jdbc:mysql://localhost:3306/moviedb");
        m.addMovie(226,"The Thing",1989,"Me");
        System.out.println(m.consultMovie("The Thing").toString());
        m.updateMovie(226,"The Thing 34",2003,"me and u");
        m.closeConnection();
    }
    public static void ReviewerTester() throws SQLException, ClassNotFoundException {
        MovieManager m = new MovieManager("root","Tnight2Sky@1stEarth","jdbc:mysql://localhost:3306/moviedb");
        System.out.println(m.consultReviewer("James Cameron").toString());
        m.addReviewer(999,"Alexandre Wottor");
        System.out.println(m.consultReviewer("Alexandre Wottor").toString());
        m.updateReviewer(999,"Alexandre Komi Wottor");
        System.out.println(m.consultReviewer("Alexandre Komi Wottor").toString());
        m.closeConnection();
    }

    public static void RatingTester() throws ParseException, SQLException, ClassNotFoundException {
        MovieManager m = new MovieManager("root","Tnight2Sky@1stEarth","jdbc:mysql://localhost:3306/moviedb");
        System.out.println(m.consultRating(201,101).toString());
        LocalDate d1 = LocalDate.now();
        System.out.println(d1);
        m.addRating(206,101,5, d1);
        System.out.println(m.consultRating(206,101).toString());
        m.updateRating(206,101,0,d1);
        m.closeConnection();
    }
}
