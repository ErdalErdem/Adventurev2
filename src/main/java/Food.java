public class Food extends Item {
    private int healthPoint;

    public Food(String name, String description, int healthPoint) {
        super(name, description);
        this.healthPoint = healthPoint;
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    public String toString(){
        return super.toString() + " " + healthPoint;
    }
}
