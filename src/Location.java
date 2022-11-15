import javax.swing.text.NumberFormatter;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Location {
    private String name;
    private Owner owner;
    private NPC npc;
    private String intro;
    private int mapIndex;
    private ArrayList<String> options;
    private Job job;

    public Location(String name, NPC npc, int mapIndex, String intro, ArrayList<String> options) {
        this.name = name;
        this.npc = npc;
        this.mapIndex = mapIndex;
        this.intro = intro;
        this.options = options;
    }
    public Location(String name, int mapIndex, String intro, ArrayList<String> options) {
        this.name = name;
        this.mapIndex = mapIndex;
        this.intro = intro;
        this.options = options;
    }

    public void addWorkOption() {
        for(String option : options) {
            if(option.equals("Apply for Job")) {
                options.remove(option);
                options.add(options.size()-1, "Work a shift");
            }
        }
    }

    public HashMap<Integer, Car> getCarsForSale() {
        System.out.println("Enter the respective number to buy a car. Enter 0 to cancel");
        HashMap<Integer, Car> cars = new HashMap<>();
        int counter = 1;
        for(Car car : owner.getCars()) {
            if(car.isForSale()) {
                cars.put(counter, car);
                System.out.println(counter + ": " + car.getName());
                counter+=1;
            }
        }
        return cars;
    }

    public int options(Scanner sc) {
        System.out.print("[");
        for(String option : options) {
            System.out.print(option + " ");
        }
        System.out.println("\b]");
        int in = sc.nextInt();
        return in;
    }

    public void printString(int max) {
        String output = "";
        for(int i = 0;i<max+1;i++) {
            output+="\b";
        }
        output+=".";
        for(int i = 0;i<max-1;i++) {
            output += " ";
        }
        output+="]";
        System.out.print(output);
    }
    public String drive(Owner owner, Car car) {
        double distance = 0.0;
        distance = Math.abs((mapIndex - owner.getCurrentLocation().getMapIndex()) * 1.0);
        int max = (int) Math.ceil(distance / 3);
        car.setMiles(car.getMiles() + (int) distance);
        double gasCost = (car.getTankSize() - car.getGasInTank()) * 3.80;
        DecimalFormat f = new DecimalFormat("##.00");
        if(distance / car.getMpg() > car.getGasInTank()) {
            System.out.println("You need to buy more gas to reach the location. Gas is $3.80 a gallon, so to fill up your " + car.getTankSize() + " gallon tank it costs $" + gasCost);
            if(owner.getCash() > gasCost) {
                car.setGasInTank(car.getTankSize());
                System.out.println("$" + owner.getCash() + " - $" + gasCost + " = $" + (owner.getCash() - gasCost) + " cash left");
                owner.setCash(owner.getCash() - gasCost);
            } else {
                return "Not enough cash for gas";
            }

        }
        car.setGasInTank(Double.parseDouble(f.format(car.getGasInTank() - distance / car.getMpg())));
        System.out.print("DRIVING[");
        for(int i=0;i<max;i++) {
            System.out.print(" ");
        }
        System.out.print("]");
        while (distance > 0) {
            distance-=3;
            try {Thread.sleep(250);} catch (InterruptedException e) {System.err.print("THREAD ERROR");}
            printString(max);
            max-=1;
        }
        owner.setCurrentLocation(this);
        System.out.println("");
        System.out.println("Your " + car.getName() + " now has " + car.getMiles() + " miles and " + car.getGasInTank() + " gallons of gas left in your tank size of " + car.getTankSize() + " gallons.");
        return intro;

    }
    public static ArrayList<Location> makeLocations() {
        NPC o1 = new NPC("Malachai", 300);
        NPC o2 = new NPC("Steve", 300);
        NPC o3 = new NPC("Ralph", 300);
        NPC o4 = new NPC("Nessin", 2000);
        NPC o5 = new NPC("Welfin", 1000300);

        o1.addCar(new Car("Hyundai", "Accent", 2017, 25000, 12000, "HSJAKW", 24.5, 12.6));
        o1.addCar(new Car("Ford", "Jeep", 2008, 115000, 6500, true, "7DHSE", 18.1, 14.4));
        o2.addCar(new Car("Ford", "Expedition", 2022, 1250, 22000, "8YS421", 20.2, 15.8));

        String[] malachaiDialogue = {"I've been in the car business for about 20 years", "I love to go to car meets with my son", "I'm hoping to one day get a Camaro, i've always liked tho"};
        String[] steveLines = {"I've been in the car business for about 20 years", "I love to go to car meets with my son", "I'm hoping to one day get a Camaro, i've always liked tho"};
        String[] nessinLines = {"I've been in the food business for about 7 years", "I like making food for good people, and hate making it for bad people", "I own a 2008 Honda Civic, it's pretty fast if you ask me"};

        o1.addDialogue(malachaiDialogue);
        o2.addDialogue(steveLines);
        o4.addDialogue(nessinLines);

        ArrayList<Location> locations = new ArrayList<>();
        String[] locationDefault = {"Talk to Owner", "Open Shop", "Drive to a Location", "Save and Quit"};
        Location Home = new Location("Home", 20, "Welcome Home!", new ArrayList<>(Arrays.asList("Inspect your Vehicles", "Drive to a Location", "List cars to sell", "View Statistics", "Save and Quit")));
        Location Scrapyard = new Location("Scrapyard", 4, "Welcome to the Scrapyard", new ArrayList<>(Arrays.asList(locationDefault)));
        Location MM = new Location("Malachai's Motors", o1, 50, "Welcome to Malachai's Motors", new ArrayList<>(Arrays.asList(locationDefault)));
        Location Mechanic = new Location("Quick Fix", 16, "Welcome to the Mechanic", new ArrayList<>(Arrays.asList(locationDefault)));
        Location Sakana = new Location("Sakana", o4, 30, "Welcome to Sakana, a Sushi Bar", new ArrayList<>(Arrays.asList("Talk to Owner", "Open Shop", "Apply for Job", "Save and Quit")));
        Location LamboDealer = new Location("Herb Chambers", o5, 340, "Welcome to Herb Chambers Lamborghini", new ArrayList<>(Arrays.asList(locationDefault)));

        Job sushiChef = new Job("Sushi Chef", 19.79, 8, 5);
        Sakana.setJob(sushiChef);

        locations.add(Home);
        locations.add(Scrapyard);
        locations.add(MM);
        locations.add(Mechanic);
        locations.add(Sakana);
        locations.add(LamboDealer);

        return locations;
    }

    public Job getJob() {return job;}

    public void setJob(Job job) {this.job = job;}

    public ArrayList<String> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public NPC getNpc() {return npc;}

    public void setNpc(NPC npc) {this.npc = npc;}

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public int getMapIndex() {
        return mapIndex;
    }

    public void setMapIndex(int mapIndex) {
        this.mapIndex = mapIndex;
    }
}
