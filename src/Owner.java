import java.util.ArrayList;
import java.util.Scanner;

public class Owner {
    //an owner is either the player or an npc in the game, owner means "car owner"
    private String name;
    private double cash;
    private ArrayList<Car> cars;
    private Car selectedCar;
    private Location currentLocation;
    private int charisma;
    private Job job;

    public Owner(String name, double cash, Location currentLocation) {
        this.name = name;
        this.cash = cash;
        this.currentLocation = currentLocation;
        cars = new ArrayList<>();
    }
    public Owner(String name, double cash) {
        this.name = name;
        this.cash = cash;
        cars = new ArrayList<>();
    }

    public void printStatistics() {
        String toString =
                "Cars Owned: " + cars.size()
                + "Cash: " + cash;
        System.out.println(toString);
    }

    public void sellCar(Car car) {
        int counter = 1;
        for(Car selectedCar : cars) {
            if(selectedCar.getPlateNumber().equals(car.getPlateNumber())) {
                cash += car.getPrice();
                cars.remove(counter);
            } else { counter+=1;}
        }
    }
    public Car selectCar(Scanner sc) {
        System.out.println("Which vehicle would you like to select?");
        printCars();
        int in = sc.nextInt();
        return cars.get(in-1);
    }
    public void printCars() {
        int counter = 1;
        for(Car car : cars) {
            System.out.println(counter + ": " + car.getName());
            counter++;
        }
    }
    public void addCar(Car car) {
        cars.add(car);
        cash -= car.getPrice();
        //System.out.println(car.getYear() + " " + car.getMake() + " " + car.getModel());
    }

    public Job getJob() {return job;}

    public void setJob(Job job) {this.job = job;}

    public Car getSelectedCar() {return selectedCar;}

    public void setSelectedCar(Car selectedCar) {this.selectedCar = selectedCar;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {this.cash = cash;}

    public ArrayList<Car> getCars() {
        return cars;
    }

    public void setCars(ArrayList<Car> cars) {
        this.cars = cars;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public int getCharisma() {return charisma;}

    public void setCharisma(int charisma) {this.charisma = charisma;}
}
