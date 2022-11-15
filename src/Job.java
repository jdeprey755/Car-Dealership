public class Job {
    private String name;
    private double payRate;
    private double shiftLength;
    private int charismaRequirement;

    public Job(String name, double payRate, double shiftLength, int charismaRequirement) {
        this.name = name;
        this.payRate = payRate;
        this.shiftLength = shiftLength;
        this.charismaRequirement = charismaRequirement;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPayRate() {
        return payRate;
    }

    public void setPayRate(double payRate) {
        this.payRate = payRate;
    }

    public int getCharismaRequirement() {
        return charismaRequirement;
    }

    public void setCharismaRequirement(int charismaRequirement) {
        this.charismaRequirement = charismaRequirement;
    }
}
