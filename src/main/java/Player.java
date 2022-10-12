import java.util.ArrayList;

public class Player {

    public Room getCurrentRoom;
    private Room currentRoom;
        private ArrayList<Item> inventory;
        private int health;
        private String healthDescription;
        private Weapon equippedWeapon;
        private Enemy currentEnemy;

        public Player() {
            currentRoom = new Room(null, null);
            inventory = new ArrayList<>();
            this.health = 100;
            this.healthDescription = printHealthDescription();
            currentEnemy = new Enemy(null, null, 0, null);
        }

        //CurrentRoom
        public Room getCurrentRoom() {
            return currentRoom;
        }

        public void setCurrentRoom(Room newCurrentRoom) {
            this.currentRoom = newCurrentRoom;
        }

        //Enemy
        public int getCurrentEnemyHealth() {
            return currentEnemy.getHealth();
        }

        public void setCurrentEnemy(String enemyName) {
            Enemy requiredEnemy = currentRoom.searchEnemy(enemyName);
            if (requiredEnemy != null) {
                this.currentEnemy = requiredEnemy;
            }
        }

        //Go
        public boolean go(String direction) {
            boolean goingInDirection = false;
            Room newCurrentRoom = null;
            switch (direction) {
                case "north", "n" -> {
                    if (currentRoom.getNorth() != null) {
                        newCurrentRoom = currentRoom.getNorth();
                    }
                }
                case "south", "s" -> {
                    if (currentRoom.getSouth() != null) {
                        newCurrentRoom = currentRoom.getSouth();
                    }
                }
                case "east", "e" -> {
                    if (currentRoom.getEast() != null) {
                        newCurrentRoom = currentRoom.getEast();
                    }
                }
                case "west", "w" -> {
                    if (currentRoom.getWest() != null) {
                        newCurrentRoom = currentRoom.getWest();
                    }
                }
            }
            if (newCurrentRoom != null) {
                setCurrentRoom(newCurrentRoom);
                goingInDirection = true;
            }
            return goingInDirection;
        }


        //Health
        public String printHealthDescription() {
            String str = "";
            if (health > 0 && health <= 25) {
                str = "Your health is low. Eat food or medicine to improove it.";
            } else if (health <= 50) {
                str = "You healt is okay, but avoid fighting right now.";
            } else if (health <= 75) {
                str = "You are in good health, but be carefull when you fight.";
            } else if (health > 75) {
                str = "You are in great health";
            }
            return str;
        }

        public String printPlayerHealth() {
            return "Player health:\n" + health + " - " + healthDescription;
        }

        public int getPlayerHealth() {
            return health;
        }

        public void setHealth(Item item) {
            if (item instanceof Food food) {
                this.health = health += ((Food) item).getHealthPoint();
            }
            if (health > 100) {
                health = 100;
            }
            healthDescription = printHealthDescription();
        }

        //Inventory
        public void addItemToInventory(Item item) {
            inventory.add(item);
        }

        public Item searchItemInInventory(String itemName) {
            for (Item s : inventory) {
                if (s.getName().toLowerCase().equals(itemName))
                    return s;
            }
            return null;
        }

        public String printInventory() {
            String str = "";
            if (inventory.isEmpty()) {
                str += "Inventory is empty.";
            } else {
                str += "Inventory:";
                for (Item i : inventory) {
                    str += '\n' + i.toString();
                }
            }
            return str;
        }

        public void removeItemFromInventory(Item item) {
            int startIndex = inventory.size() - 1;
            for (int i = startIndex; i >= 0; i--) {
                if (inventory.get(i) == item) {
                    inventory.remove(i);
                    break;
                }
            }
        }

        public boolean takeItem(String itemName) {
            boolean takeItem = false;
            Item requiredItem = currentRoom.searchItem(itemName);
            if (requiredItem != null) {
                addItemToInventory(requiredItem);
                currentRoom.removeItemFromRoom(requiredItem);
                takeItem = true;
            }
            return takeItem;
        }

        public boolean dropItem(String itemName) {
            boolean dropItem = false;
            Item requiredItem = searchItemInInventory(itemName);
            if (requiredItem != null) {
                getCurrentRoom().addItem(requiredItem);
                removeItemFromInventory(requiredItem);
                dropItem = true;
            }
            if (requiredItem == equippedWeapon) {
                equippedWeapon = null;
            }
            return dropItem;
        }

        public ReturnMessage eatItem(String itemName) {
            Item itemRequired = searchItemInInventory(itemName);
            if (itemRequired == null) {
                return ReturnMessage.COULD_NOT_BE_FOUND;
            } else if (itemRequired instanceof Food) {
                setHealth(itemRequired);
                removeItemFromInventory(itemRequired);
                return ReturnMessage.EATEN;
            } else {
                return ReturnMessage.CAN_NOT_BE_EATEN;
            }
        }

        //Weapon methods
        public ReturnMessage equipWeapon(String weaponName) {
            Item weaponRequired = searchItemInInventory(weaponName);
            if (weaponRequired == null) {
                return ReturnMessage.COULD_NOT_BE_FOUND;
            } else if (weaponRequired instanceof Weapon) {
                equippedWeapon = (Weapon) weaponRequired;
                return ReturnMessage.EQUIPPED;
            } else {
                return ReturnMessage.THIS_IS_NOT_A_WEAPON;
            }
        }

        public boolean weaponEquipped() {
            if (equippedWeapon != null)
                return true;
            else
                return false;
        }

        public Weapon getEquippedWeapon() {
            return equippedWeapon;
        }

        //Combat
        public ReturnMessage attack(String enemyName) {
            setCurrentEnemy(enemyName);
            if (!weaponEquipped()) {
                return ReturnMessage.NO_WEAPON_EQUIPPED;
            }
            if (currentRoom.searchEnemy(enemyName) == null) {
                return ReturnMessage.NO_ENEMY_FOUND_BY_THAT_NAME;
            }
            if (equippedWeapon.use() == ReturnMessage.WEAPON_OUT_OF_AMMO) {
                return ReturnMessage.PLAYER_WEAPON_OUT_OF_AMMO;
            } else
                return attack();
        }

        public void hit() {
            health -= currentEnemy.getWeapon().getDamage();
            healthDescription = printHealthDescription();
        }

        public boolean playerIsAlive() {
            if (health > 0) {
                return true;
            } else {
                return false;
            }
        }

        private ReturnMessage attack() {
            currentEnemy.hit(equippedWeapon.getDamage());
            if (currentEnemy.isAlive()) {
                if (currentEnemy.attack() == ReturnMessage.WEAPON_OUT_OF_AMMO) {
                    return ReturnMessage.ENEMY_WEAPON_OUT_OF_AMMO;
                } else {
                    hit();
                    if (playerIsAlive()) {
                        return ReturnMessage.PLAYER_ATTACKED;
                    } else {
                        return ReturnMessage.PLAYER_DIED;
                    }
                }
            } else {
                currentRoom.addItem(currentEnemy.getWeapon());
                currentRoom.removeEnemyFromRoom(currentEnemy);
                return ReturnMessage.ENEMY_KILLED;
            }
        }

    }



