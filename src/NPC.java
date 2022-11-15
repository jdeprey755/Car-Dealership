import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class NPC extends Owner {
    private String name;
    private double cash;
    private int amountOfCars;
    private ArrayList<Car> cars;
    private Car selectedCar;
    private ArrayList<String> dialogue;

    public NPC(String name, double cash, Location currentLocation) {
        super(name, cash, currentLocation);
        this.name = name;
        this.cash = cash;
        cars = new ArrayList<>();
        dialogue = new ArrayList<>();
    }

    public NPC(String name, double cash) {
        super(name, cash);
        this.name = name;
        this.cash = cash;
        cars = new ArrayList<>();
        dialogue = new ArrayList<>();
    }

    public void talk() {
        Random r = new Random();
        int dialogueIndex = r.nextInt(0, dialogue.size());
        System.out.println(dialogue.get(dialogueIndex));
    }

    public void addDialogue(String[] lines) {
        for(String line : lines) {
            dialogue.add(line);
        }
    }

    public void printCarsForSale() {
        for(Car car : cars) {
            if(car.isForSale()) {
                car.carInfo();
            }
        }
    }
}
