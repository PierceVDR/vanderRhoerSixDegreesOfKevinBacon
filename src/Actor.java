import java.util.ArrayList;

public class Actor { // Actor class used to create a differently formatted database
    private String name;
    private ArrayList<Actor> coworkers;
    private ArrayList<String> movies;

    private Actor connectedTo;

    public Actor(String name) {
        this.name=name;
        this.coworkers = new ArrayList<>();
        this.movies = new ArrayList<>();
        this.connectedTo=null;
    }


    public void setConnectedTo(Actor connectedTo) {
        this.connectedTo=connectedTo;
    }
    public String getName() {return name;}
    public ArrayList<Actor> getCoworkers() {return coworkers;}

    public Actor getConnectedTo() {return connectedTo;}
    public Actor addToLayer(ArrayList<Actor> layer, ArrayList<Actor> alreadyChecked) {
        for (Actor coworker : coworkers) {
            if (BinarySearch.insertActorNoDuplicates(alreadyChecked,coworker)!=-1) { // If the coworker isn't already checked...
                BinarySearch.insertActorNoDuplicates(layer,coworker);
                coworker.setConnectedTo(this);
            }
            if (coworker.getName().equals("Kevin Bacon")) {
                return coworker;
            }
        }
        return null;
    }

    public Actor connectToBacon() {
        this.connectedTo=null; // Reset connections -- other connections will be changed anyway, so we only need to worry about this one
        if (this.name.equals("Kevin Bacon")) {
            return this;
        }

        ArrayList<Actor> checked = new ArrayList<>();
        checked.add(this);

        ArrayList<Actor> layer = new ArrayList<>();
        layer.add(this);

        while (layer.size()>0) {
            ArrayList<Actor> nextLayer = new ArrayList<>();
            for (Actor v : layer) {
                Actor kevinBacon = v.addToLayer(nextLayer, checked);
                if (kevinBacon != null) {
                    return kevinBacon;
                }
            }
            layer=nextLayer;
        }
        return null; // Not connected to Kevin Bacon
    }

    public void addCoworker(Actor coworker, String movieTitle) {
        int i = BinarySearch.insertActorNoDuplicates(coworkers,coworker);
        if (i!=-1) {
            movies.add(i,movieTitle);
        }
    }

    public String info() {
        String returnTxt = name;
        for (int i=0; i<coworkers.size(); i++) {
            returnTxt += "\n  " + (i+1) + ". " + coworkers.get(i).getName() + " -IN- " + movies.get(i);
        }
        return returnTxt;
    }

    public String toString() {
        return name;
    }

    public String getConnectionInfo() {
        // First find the movie we were in with connectedTo
        int i=BinarySearch.findActor(coworkers,connectedTo); // Won't insert anything since it's there.

        return this.name + " WAS IN " + movies.get(i) + " WITH " + connectedTo.getName();
    }
}
