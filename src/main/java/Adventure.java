import java.util.ArrayList;

public class Adventure {
    private Map map;
    private Player player;


    public Adventure() {
        map = new Map();
        map.createMap();
        player = new Player();
        player.setCurrentRoom(map.getRoom1());

    }
    public Player getPlayer() {
        return player;
    }
    public void setPlayer(){
        this.player = player;
    }

    public Room getCurrentRoom() {
        return player.getCurrentRoom;
    }


    public int getPlayerHealth() {
        return player.getPlayerHealth();
    }

    public int getCurrentEnemyHealth() {
        return player.getCurrentEnemyHealth();
    }

    public String printInventory() {
        return player.printInventory();
    }

    public String printPlayerHealth() {
        return player.printPlayerHealth();
    }

    public boolean go(String direction) {
        return player.go(direction);
    }

    public boolean takeItem(String itemName) {
        return player.takeItem(itemName);
    }

    public boolean dropItem(String itemName) {
        return player.dropItem(itemName);
    }

    public ReturnMessage eatItem(String itemName) {
        return player.eatItem(itemName);
    }

    public ReturnMessage equipWeapon(String weaponName) {
        return player.equipWeapon(weaponName);
    }

    public boolean weaponEquipped() {
        return player.weaponEquipped();
    }

    public Weapon getEquippedWeapon() {
        return player.getEquippedWeapon();
    }

    public ReturnMessage attack(String enemyName){
        return player.attack(enemyName);
    }
}
