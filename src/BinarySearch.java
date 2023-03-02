import java.util.ArrayList;

public class BinarySearch {
    public static int insertActorNoDuplicates(ArrayList<Actor> actors, Actor targetActor) {

//        System.out.println("\n" + actors + " inserting: " + targetActor);
        if (actors.size()==0) {
            actors.add(targetActor);
            return 0;
        }

        int left=0;
        int right = actors.size()-1;
        int i=0;
        while (true) {

//            numChecks++;
            i = (left + right) / 2;

            Actor checkedActor = actors.get(i);
            int comp = targetActor.getName().compareTo(checkedActor.getName());

//            System.out.println(left + "| " + i + " |" + right);
            if (comp == 0) {
//                System.out.println("Checks: " + numChecks);
                return -1;
            } else if (comp < 0) { // If the word checked is AFTER the target
//                System.out.println(actors.get(i) + ": after target!");
                right = i - 1;
            } else { // If the word checked is BEFORE the target
//                System.out.println(actors.get(i) + ": comes before target!");
                left = i + 1;
            }

            if (left > right) {
//                System.out.println(actors.get(i) + ": can't be before or after target!");
                if (comp < 0) { // If the word checked is AFTER the target
                    actors.add(i, targetActor);
                    return i;
                } else { // If the word checked is BEFORE the target
                    actors.add(i + 1, targetActor);
                    return i+1;
                }
//                System.out.println("Checks: " + numChecks);
            }
        }
    }

    public static int findActor(ArrayList<Actor> actors, Actor targetActor) {

        if (actors.size()==0) {
            actors.add(targetActor);
            return 0;
        }

        int left=0;
        int right = actors.size()-1;
        int i=0;
        while (true) {

            i = (left + right) / 2;

            Actor checkedActor = actors.get(i);
            int comp = targetActor.getName().compareTo(checkedActor.getName());

            if (comp == 0) {
                return i;
            } else if (comp < 0) { // If the word checked is AFTER the target
                right = i - 1;
            } else { // If the word checked is BEFORE the target
                left = i + 1;
            }

            if (left > right) {
                System.out.println("ERROR: Actor not found??");
                System.out.println("Looking for: " + targetActor.getName());
                return -1;
            }
        }
    }

    public static Actor findOrCreateActor(ArrayList<Actor> actors, String name) {
//        int numChecks=0;
        if (actors.size()==0) {
            Actor actor = new Actor(name);
            actors.add(actor);
            return actor;
        }

        int left=0;
        int right = actors.size()-1;
        int i=0;
        while (true) {

//            numChecks++;
            i = (left + right) / 2;

            Actor checkedActor = actors.get(i);
            int comp = name.compareTo(checkedActor.getName());

//            System.out.println(left + "| " + i + " |" + right);
            if (comp == 0) {
//                System.out.println("Checks: " + numChecks);
                return checkedActor;
            } else if (comp < 0) { // If the word checked is AFTER the target
//                System.out.println(words.get(i) + ": after target!");
                right = i - 1;
            } else { // If the word checked is BEFORE the target
//                System.out.println(words.get(i) + ": comes before target!");
                left = i + 1;
            }

            if (left > right) {
//                System.out.println(words.get(i) + ": can't be before or after target!");
                Actor actor = new Actor(name);
                if (comp < 0) { // If the word checked is AFTER the target
                    actors.add(i, actor);
                } else { // If the word checked is BEFORE the target
                    actors.add(i + 1, actor);
                }
//                System.out.println("Checks: " + numChecks);
                return actor;
            }
        }
    }

}
