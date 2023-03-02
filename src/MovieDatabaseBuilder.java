import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.ArrayList;

public class MovieDatabaseBuilder {

    private static int loadingCount = 0;
    private static final int TOTAL_LOADING_COUNT = 135828; // Number of movies - would need to be determined beforehand

    private static ArrayList<SimpleMovie> completeMovies;
    private static ArrayList<Actor> completeActors;

    public static void getMovieDB(String fileName) {
        ArrayList<SimpleMovie> movies = new ArrayList<>();
        ArrayList<Actor> actors = new ArrayList<>();
        try {
            File movieData = new File(fileName);
            Scanner reader = new Scanner(movieData);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] data = line.split("---");
                if (data.length > 1) {
                    SimpleMovie s = new SimpleMovie(data[0], data[1]);
                    movies.add(s);

                    loadingCount+=1;
                    System.out.println("Processing data... " + loadingCount + "/" + TOTAL_LOADING_COUNT + " (" + (100.0*loadingCount/TOTAL_LOADING_COUNT) + "%)" );

                    // PROCESS ACTORS:
                    ArrayList<String> movieNames = s.getActors();
                    ArrayList<Actor> movieActors = new ArrayList<>();
                    for (String name : movieNames) {
                        Actor actor = BinarySearch.findOrCreateActor(actors, name);
                        movieActors.add(actor);
                    }
//                    System.out.println("CAST: " + movieNames);
                    for (Actor movieActor : movieActors) {
//                        System.out.println("-----NEW ACTOR-----");
                        for (Actor otherActor : movieActors) {
                            if (movieActor!=otherActor) { // Since the goal is to have only one Actor object per actor, we can use ==
                                movieActor.addCoworker(otherActor,s.getTitle());
                            }
                        }
//                        System.out.println("COMPLETED ACTOR INFO:\n" + movieActor.info());
                    }
                }

            }
        }
        catch (FileNotFoundException noFile) {
            System.out.println("File not found!");
            return;
        }
        completeMovies=movies;
        completeActors=actors;
    }

    public static ArrayList<SimpleMovie> getMovies() {
        return completeMovies;
    }

    public static ArrayList<Actor> getActors() {
        return completeActors;
    }
}
