public class Map {
    private Room room1 = null;
    private Room room2 = null;
    private Room room3 = null;
    private Room room4 = null;
    private Room room5 = null;
    private Room room6 = null;
    private Room room7 = null;
    private Room room8 = null;
    private Room room9 = null;
    private Room startRoom;

    public void createMap(){
        room1 = new Room("starting area","Objective: Get in the club");
        room2 = new Room("room of the bouncer", "Objective: take your jacket off");
        room3 = new Room("Dance floor", "Objective: sneak into VIP");
        room4 = new Room("The alley of bar", "Objective: go in the club. Side objective: sneak in from the back");
        room5 = new Room("VIP section","Objective: You win the game");
        room6 = new Room("Bar","Objective: VIP section and dont forget to grab a drink");
        room7 = new Room("Random door", "Objective: VIP section");
        room8 = new Room("Outside the VIP room", "Bouncer stopping you from entering VIP");
        room9 = new Room("Hall way of club", "Objective: VIP section");

        room1.setEast(room2);
        room1.setSouth(room4);
        room2.setWest(room1);
        room2.setEast(room3);
        room3.setWest(room2);
        room3.setSouth(room6);
        room4.setSouth(room7);
        room4.setNorth(room1);
        room5.setNorth(room8);
        room6.setNorth(room3);
        room6.setSouth(room9);
        room7.setEast(room8);
        room7.setNorth(room4);
        room8.setEast(room9);
        room8.setWest(room7);
        room8.setNorth(room5);
        room9.setNorth(room6);
        room9.setWest(room8);

        addItemToRoom(room1, "id", "your id");
        addItemToRoom(room1, "jacket","jacket");

        addItemToRoom(room3, "dance moves", "gives you the ability to throw it down");

        addItemToRoom(room4, "random key", "random key that leads to a random door");

        addFoodToRoom(room2, "sucuk", "tasty food from turkey", +100 );

        addFoodToRoom(room1, "vodka", "vodka", -20);
        addFoodToRoom(room6,  "Beer", "Cold nice drink", 20);
        addFoodToRoom(room7, "pepsi", "Cold nice drink", 20);
        addFoodToRoom(room8, "pepsi", "Cold nice drink", 20);


        addRangedWeapon(room1, "Sniper", "360 sniper", 100, 2);
        addMeleeWeapon(room6, "bottle", "dirty bottle", 10);


        Enemy bouncer = addEnemy(room1, "bouncer", " big ugly bouncer", 200, null);
        addMeleeWeapon(bouncer, "Arms", "Strong arms", 50 );

       /* Enemy cowboy = addEnemy(room1, "Cowboy", "An evil looking cowboy in black", 100, null);
        addRangedWeapon(cowboy, "Revolver", "A black revolver", 25, 6);*/

        startRoom = room1;
    }

    public Room getRoom1() {
        return room1;
    }

    //Add methods
    public void addItemToRoom(Room room, String itemName, String description){
        room.addItem(new Item(itemName, description));
    }
    public void addFoodToRoom(Room room, String itemName, String description, int healthPoint){
        room.addItem(new Food(itemName, description, healthPoint));
    }

    public void addRangedWeapon(Room room, String name, String description, int damage, int uses){
        room.addItem(new RangedWeapon(name, description, damage, uses));
    }
    public void addMeleeWeapon(Room room, String name, String description, int damage){
        room.addItem(new MeleeWeapon(name, description, damage));
    }

    public void addRangedWeapon(Enemy enemy, String name, String description, int damage, int uses){
        enemy.setWeapon(new RangedWeapon(name, description, damage, uses));
    }
    public void addMeleeWeapon(Enemy enemy, String name, String description, int damage){
        enemy.setWeapon(new MeleeWeapon(name, description, damage));
    }

    public Enemy addEnemy(Room room, String name, String description, int health, Weapon weapon){
        Enemy enemy = new Enemy(name, description, health, weapon);
        room.addEnemy(enemy);
        return enemy;
    }


    public void setDirection(Room room, Room north, Room south, Room east, Room west){
        room.setNorth(north);
        room.setSouth(south);
        room.setEast(east);
        room.setWest(west);
    }
}
