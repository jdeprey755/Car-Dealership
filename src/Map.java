import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Map {
    private ArrayList<Location> locations;
    private double tileSize;

    public Map(double ts) {
        tileSize = ts;
        locations = Location.makeLocations();
    }

    public Location printLocations(Owner user, Scanner sc) {
        int currentIndex = user.getCurrentLocation().getMapIndex();
        int counter = 1;
        HashMap<Integer, Location> list = new HashMap();
        for(Location location : locations) {
            if(location.getMapIndex() > currentIndex) {
                list.put(counter, location);
                System.out.println(counter + ": " + location.getName() + " - " + ((location.getMapIndex()-currentIndex)*tileSize) + " miles");
                counter++;
            } else if(location.getMapIndex() < currentIndex) {
                list.put(counter, location);
                System.out.println(counter + ": " + location.getName() + " - " + ((currentIndex-location.getMapIndex()) * tileSize) + " miles");
                counter++;
            }

        }
        System.out.println(counter + ": Cancel");
        int in = sc.nextInt();
        if(counter == in) {
            return null;
        }
        return list.get(in);
    }
    public ArrayList<Location> getLocations() {
        return locations;
    }

}
