import java.util.ArrayList;

public class SimpleMovie {
    private String title;
    private String actorsData;
    private ArrayList<String> actors;

    public SimpleMovie(String t, String a) {
        title = t;
        actorsData = a;
        actors = new ArrayList<String>();
        String[] tempActors = actorsData.split(":");
        for (int i = 0; i < tempActors.length; i++) {
            String actor = tempActors[i];
            if (actor.indexOf("|") != -1) {
                tempActors[i] = actor.substring(actor.indexOf("|") + 1);
            }
            actors.add(tempActors[i]);
        }

    }

    public String getTitle() {
        return title;
    }

    public ArrayList<String> getActors() {
        return actors;
    }

    public String toString() {
        return "Title: " + title + "\n" + "Actors: " + actors + "\n";
    }
}