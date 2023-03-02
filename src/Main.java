import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        ArrayList<Actor> actors;

        MovieDatabaseBuilder.getMovieDB("src/movie_data");

        actors = MovieDatabaseBuilder.getActors();

        while (true) {
            System.out.println("-------------------------------");
            System.out.print("Enter an actor's name or (q) to quit: ");
            String generalName = scan.nextLine().toLowerCase();
            if (generalName.equals("q")) {break;}

            ArrayList<Actor> matchingActors = new ArrayList<>();

            int count = 1;
            for (Actor actor : actors) {
                if (actor.getName().toLowerCase().contains(generalName)) {
                    matchingActors.add(actor);
                    System.out.println(count + ": " + actor.getName());
                    count++;
                }
            }

            if (matchingActors.size()==0) {
                System.out.println("No matching actors found.");
                continue;
            }

            System.out.print("\nEnter the number correlating to an actor: ");
            Actor specificActor = matchingActors.get(scan.nextInt() - 1);
            scan.nextLine();

            System.out.println("1. See actor's Bacon Degree");
            System.out.println("2. See all of the actor's coworkers");
            System.out.print("\nEnter of the number correlating to an action: ");
            String response = scan.nextLine(); // Don't actually need a number

            if (response.equals("1")) {
                System.out.println("-------------------------------");
                System.out.println("Calculating the connection to Kevin Bacon of " + specificActor.getName() + ":\n");
                Actor kevinBacon = specificActor.connectToBacon();

                if (kevinBacon==null) {
                    System.out.println( specificActor.getName() + " is NOT connected to Kevin Bacon!" );
                } else {
                    Actor currentActor = kevinBacon;

                    int degree=0;
                    while (true) {
                        Actor previousActor = currentActor.getConnectedTo();
                        if (previousActor==null) {break;}
                        degree++;
                        System.out.println(currentActor.getConnectionInfo());

                        currentActor=previousActor;
                    }

                    System.out.println("\n" + specificActor.getName() + " has a Bacon degree of: " + degree);
                }
            } else if (response.equals("2")) {
                System.out.println("-------------------------------");
                System.out.println(specificActor.info());
            }


        }
    }
}