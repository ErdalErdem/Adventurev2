public class Enemy {
    private String name;
    private String description;
    private int health;
    private Weapon weapon;


    public Enemy (String name, String description, int health, Weapon weapon){
        this.name = name;
        this.description = description;
        this.health = health;
        this.weapon = weapon;
    }

    public String getName() {
        return name;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public int getHealth() {
        return health;
    }

    public ReturnMessage attack(){
        return weapon.use();
    }

    public void hit(int damage){
        health -= damage;
    }

    public boolean isAlive(){
        if(health > 0)
            return true;
        else
            return false;

    }
    @Override
    public String toString(){
        return description + " - Enemy health: " + health;
    }
}
