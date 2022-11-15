import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Controller {
    //class is for controlling the game state, if player is at home, print home options and take input
    public static void introduction(Owner user, Scanner sc) {
        ArrayList<Car> cars = Car.getCars();
        System.out.println("Welcome to the Car Dealership Program.\nWhat kind of car would you like to start with?\n");

        //pick users starter car
        /*int counter = 1;
        for(Car vehicle : cars) {System.out.print(counter + ": ");vehicle.carInfo();counter+=1;}
        int in = sc.nextInt();
        user.addCar(cars.get(in-1));*/
        user.addCar(cars.get(0));
        user.setSelectedCar(user.getCars().get(0));
    }
    public static void runGame(Owner user, Scanner sc, Map map) {
        boolean run = true;
        while(run) {
            HashMap<Integer, String> actions = new HashMap<>();
            Car selectedCar;
            int counter = 1;
            for(String action : user.getCurrentLocation().getOptions()) {
                actions.put(counter, action);
                System.out.println(counter + ": " + actions.get(counter));
                counter+=1;
            }
            int in = sc.nextInt();

            //CHANGING ACTION SYSTEM
            //create hashmap with location's available actions, print actions, access actions using key
            if(actions.get(in).equals("Inspect your Vehicles")) {
                user.setSelectedCar(user.selectCar(sc));

            } else if(actions.get(in).equals("Drive to a Location")) {
                Location selectedLocation = map.printLocations(user, sc);

                if(selectedLocation != null) {
                    System.out.println(selectedLocation.drive(user, user.getSelectedCar()));
                } else {
                    System.out.println("Driving Cancelled");
                }

            } else if(actions.get(in).equals("List cars to sell")) {
                selectedCar = user.selectCar(sc);
                user.sellCar(selectedCar);

            } else if(actions.get(in).equals("View Statistics")) {
                user.printStatistics();

            } else if(actions.get(in).equals("Save and Quit")) {
                System.out.println("Saving and then Exiting Program");
                run = false;

            } else if(actions.get(in).equals("Talk to Owner")) {
                user.getCurrentLocation().getNpc().talk();
                user.setCharisma(user.getCharisma() + 1);

            } else if(actions.get(in).equals("Open Shop")) {
                HashMap<Integer, Car> carsForSale = user.getCurrentLocation().getCarsForSale();
                in = sc.nextInt();
                if(in != 0) {
                    user.addCar(carsForSale.get(in));
                    user.setCash(user.getCash() - carsForSale.get(in).getPrice());
                }

            } else if(actions.get(in).equals("Apply for Job")) {
                if(user.getCharisma() > user.getCurrentLocation().getJob().getCharismaRequirement()) {
                    user.setJob(user.getCurrentLocation().getJob());
                    user.getCurrentLocation().addWorkOption();
                }

            } else if(actions.get(in).equals("Work a Shift")) {
                System.out.println();
            }
        }
    }
    public static void watchTV() {

    }
}

