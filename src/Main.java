import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

//Car pontiac = new Car("GM", "G6 Pontiac", 2006,  92000, 3000, false);

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map map = new Map(1.0);
        ArrayList<Location> locations = map.getLocations();
        Owner user = new Owner("Jerid", 25000, locations.get(0));
        locations.get(1).setOwner(user);

        Controller.introduction(user, sc);
        Controller.runGame(user, sc, map);
    }
}
