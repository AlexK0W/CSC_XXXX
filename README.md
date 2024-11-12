## MVBE: Movie Database Management

This package provides a Java class, `MovieManager`, for interacting with a movie database. It allows you to connect to a MySQL database, perform CRUD (Create, Read, Update) operations on movies, reviewers, and ratings.

### Features

  * Connect to a MySQL database using credentials.
  * Search for movies by title.
  * Add new movies to the database.
  * Update existing movie information.
  * Search for reviewers by name.
  * Add new reviewers to the database.
  * Update existing reviewer information.
  * Consult a rating for a specific movie by a specific reviewer.
  * Add a new rating for a movie by a reviewer.
  * Update existing rating information.

### Usage

**Prerequisites:**

  * Java installed
  * MySQL database server with relevant tables (movie, reviewer, rating)
  * Java connector for MySQL 8.0.40

**Using the `MovieManager` class:**

1.  Import the `MovieManager` class in your Java project.
2.  Create an instance of `MovieManager` by providing your database credentials and connection URL:

<!-- end list -->

```java
MovieManager manager = new MovieManager("username", "password", "jdbc:mysql://localhost:3306/your_database");
```

3.  Use the provided methods to interact with your movie database.

Here are some examples:

  * Search for a movie by title:

<!-- end list -->

```java
Movie movie = manager.consultMovie("The Shawshank Redemption");
if (movie != null) {
  System.out.println("Movie Found: " + movie.getTitle());
} else {
  System.out.println("Movie not found.");
}
```

  * Add a new movie:

<!-- end list -->

```java
Movie newMovie = manager.addMovie(123, "The Godfather", 1972, "Francis Ford Coppola");
if (newMovie != null) {
  System.out.println("Movie added successfully!");
} else {
  System.out.println("Failed to add movie.");
}
```

  * Update an existing movie's director:

<!-- end list -->

```java
manager.updateMovie(123, "The Godfather", 1972, "Francis Ford Coppola (Updated)");
System.out.println("Movie director updated!");
```

**Note:** This is a basic example. Refer to the actual method implementations in the `MovieManager` class for detailed parameter information and return values.

### License

This package is distributed under the [MIT License](https://www.google.com/url?sa=E&source=gmail&q=https://opensource.org/licenses/MIT).

Please refer to the license file for details.
