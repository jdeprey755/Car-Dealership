import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Car {
    private String make; //refers to specific car model
    private String model; //refers to brand of car
    private int year;
    private int miles; 
    private int price;
    private boolean forSale;
    private String plateNumber;
    private double mpg;
    private final double tankSize;
    private double gasInTank;

    public Car(String make, String model, int year, int miles, int price, boolean forSale, String plateNumber, double mpg, double tankSize) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.miles = miles;
        this.price = price;
        this.forSale = forSale;
        this.plateNumber = plateNumber;
        this.mpg = mpg;
        this.tankSize = tankSize;
        gasInTank = tankSize;
    }

    public Car(String make, String model, int year, int miles, int price, String plateNumber, double mpg, double tankSize) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.miles = miles;
        this.price = price;
        this.plateNumber = plateNumber;
        this.mpg = mpg;
        this.tankSize = tankSize;
        gasInTank = tankSize;
    }

    public String getName() {
        return year + " " + make + " " + model;
    }
    public static ArrayList<Car> getCars() {
        String line = "";
        String path = "res/Cars.csv";
        ArrayList<Car> cars = new ArrayList<Car>();
        try {
            BufferedReader r = new BufferedReader(new FileReader(path));
            r.readLine();
            while((line=r.readLine()) != null) {
                String[] a = line.split(",");
                cars.add(new Car(a[0], a[1], Integer.parseInt(a[2]), Integer.parseInt(a[3]), Integer.parseInt(a[4]), Boolean.parseBoolean(a[5]), a[6], Double.parseDouble(a[7]), Double.parseDouble(a[8])));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cars;
    }
    public void carInfo() {
        String saleStr = "";
        if(forSale) {
            saleStr = "for sale";
        } else {
            saleStr = "not for sale";
        }
        System.out.println(year + " " + make + " " + model + " with " + miles + " miles priced at $" + NumberFormat.getNumberInstance(Locale.US).format(price) + " currently " + saleStr);
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMiles() {
        return miles;
    }

    public void setMiles(int miles) {
        this.miles = miles;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isForSale() {
        return forSale;
    }

    public void setForSale(boolean forSale) {
        this.forSale = forSale;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public double getMpg() {
        return mpg;
    }

    public void setMpg(double mpg) {
        this.mpg = mpg;
    }

    public double getTankSize() {
        return tankSize;
    }

    public double getGasInTank() {
        return gasInTank;
    }

    public void setGasInTank(double gasInTank) {
        this.gasInTank = gasInTank;
    }
}
