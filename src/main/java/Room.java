import java.util.ArrayList;

public class Room {
    private String name;
    private String description;
    private Room north;
    private Room south;
    private Room east;
    private Room west;
    private ArrayList<Item> items;
    private ArrayList<Enemy> enemies;


    public Room(String name, String description){
        this.name = name;
        this.description = description;
        this.north = null;
        this.south = null;
        this.east = null;
        this.west = null;
        items = new ArrayList<>();
        enemies = new ArrayList<>();
    }

    //Gettere
    public Room getNorth() {
        return north;
    }

    public Room getSouth() {
        return south;
    }

    public Room getEast() {
        return east;
    }

    public Room getWest() {
        return west;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    //Settere

    public void setNorth(Room northRoom) {
        this.north = northRoom;
    }

    public void setSouth(Room southRoom) {
        this.south = southRoom;
    }

    public void setEast(Room eastRoom) {
        this.east = eastRoom;
    }

    public void setWest(Room westRoom) {
        this.west = westRoom;
    }

    public void addItem(Item item){
        items.add(item);
    }

    public Item searchItem(String itemName){
        for (Item s: items) {
            if(s.getName().toLowerCase().equals(itemName))
                return s;
        }
        return null;
    }

    public void removeItemFromRoom(Item item){
        int startIndex = items.size() - 1;
        for (int i = startIndex; i >= 0 ; i--) {
            if (items.get(i) == item){
                items.remove(i);
                break;
            }
        }
    }

    public void addEnemy(Enemy enemy){
        enemies.add(enemy);
    }

    public Enemy searchEnemy(String enemyName){
        for (Enemy e: enemies) {
            if(e.getName().toLowerCase().equals(enemyName))
                return e;
        }
        return null;
    }

    public void removeEnemyFromRoom(Enemy enemy){
        int startIndex = enemies.size() - 1;
        for (int i = startIndex; i >= 0 ; i--) {
            if (enemies.get(i) == enemy){
                enemies.remove(i);
                break;
            }
        }
    }

    public String toString() {
        String str = "";
        str += "You are in the " + name + '\n';
        str += description;
        if(items.isEmpty()){
            str += "\nNothing else of interest in here.";
        } else {
            for (Item i: items) {
                str += "\nThere is " + i.getDescription();
            }
        }
        if(enemies.isEmpty()){
            str += "\nNothing else of interest in here.";
        } else {
            for (Enemy e: enemies) {
                str += "\nThere is " + e;
            }
        }
        return str;
    }
}
