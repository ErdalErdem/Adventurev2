import java.util.Scanner;

public class UserInterFace {
    //Laver en switchcase som fortæller brugeren valgmulighederne
    private Adventure adventure;
    private Player player = new Player();

    public UserInterFace(Adventure controller) {
        this.adventure = controller;
    }

    public void startProgram() {
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        System.out.println("""
                Welcome to our text adventure!""");
        System.out.println("""
                -----------------------------------------------------------------------------------------
                You are at the front of the club entrance. Your mission is to get in and get a VIP table.
                The problem the is goons of bouncers wont let you in. So therefore your adventure begin now.
                Get in and get that VIP table.
                ------------------------------
                Ingame controls: 
                If you want to go the direction of north, east west and south, simply write go (any of the four directions)
                Examples: go north, go south, go east go west
                ---------------------------------------------
                If need for awareness of surroundings write look
                If you forget any of these actions simply write help""");


        while (running) {

            // Vi opretter en switch case, som fortæller om det muligt gå i den retning, ellers vil den returnere at vejen ikke er gyldig.
            System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "Enter an command");
            String userInput = sc.nextLine().toLowerCase();

            // Vi laver et arrway og opretter en split

            String[] userInputs = userInput.split(" ");
            String command = userInputs[0];
            String userChoise = "";

            if (userInputs.length > 1) {
                userChoise = userInputs[1];
            }


            switch (command) {
                case "go":
                    boolean succes = adventure.go(userChoise);
                    if (succes) {
                        System.out.println("Going in dircetion of " + userChoise);

                    } else {
                        System.out.println("You can´t go that way");

                    }
                    break;


                case "take":
                    System.out.println("Please enter the name of the item you want to take");
                    String itemTake = sc.nextLine().toLowerCase();
                    if (adventure.getPlayer().takeItem(itemTake) == true) {
                        System.out.println("Item added to inventory");
                    } else {
                        System.out.println("Item was not found in this room");
                    }
                    break;
                case "drop":
                    System.out.println("Please enter the name of the item you want to drop");
                    //  String itemDrop = sc.nextLine().toLowerCase();
                    if (adventure.getPlayer().dropItem(userChoise) == true) {
                        System.out.println("Item removed from inventory");
                    } else {
                        System.out.println("Item was not found in your inventory");
                    }
                    break;

                case "equip": {
                    if (command.length() > 1) {
                        ReturnMessage message = adventure.equipWeapon(userChoise);
                        switch (message) {
                            case COULD_NOT_BE_FOUND -> System.out.println(userChoise + " is not in your inventory.");
                            case THIS_IS_NOT_A_WEAPON -> System.out.println(userChoise + " is not a weapon");
                            case EQUIPPED -> System.out.println(userChoise + " equipped");
                        }
                    } else {
                        System.out.println("You did not indicate the weapon to " + userChoise);
                    }
                }

                break;
                case "attack": {
                    if (command.length() > 1) {
                        ReturnMessage message = adventure.attack(userChoise);
                        switch (message) {
                            case NO_WEAPON_EQUIPPED ->
                                    System.out.println("You can not attack without a weapon equipped");
                            case NO_ENEMY_FOUND_BY_THAT_NAME ->
                                    System.out.println("No enemy like " + (userChoise) + " in the room");
                            case PLAYER_WEAPON_OUT_OF_AMMO -> System.out.println("You are out of ammo.");
                            case ENEMY_WEAPON_OUT_OF_AMMO -> System.out.println("Enemy is out of ammo");
                            case ENEMY_KILLED -> System.out.println("Enemy killed");
                            case PLAYER_ATTACKED -> {
                                System.out.println("You survived, but so did the " + (userChoise));
                                System.out.println("Player health: " + adventure.getPlayerHealth());
                            }
                            case PLAYER_DIED -> {
                                System.out.println("Game over, you died");
                            }
                        }
                    }
                }
                break;
                case "Help":
                    System.out.println("""
                                             
                                                                                 
                                                
                                              Game information:
                                       You are at the front of the club entrance. Your mission is to get in and get a VIP table.
                                       The problem the is goons of bouncers wont let you in. So therefore your adventure begin now.
                                       Get in and get that VIP table.
                                       ------------------------------
                                       Ingame controls: 
                                       If you want to go the direction of north, east west and south, simply write go (any of the four directions)
                                       Examples: go north, go south, go east go west
                                       ---------------------------------------------
                                       If need for awareness of surroundings write look
                                       If you forget any of these actions simply write help
                                                                           
                            """);
                    break;

                case "Look", "look":
                    System.out.println(adventure.getPlayer().getCurrentRoom());
                    break;

                case "Inventory", "inv":
                    boolean weaponEquipped = adventure.weaponEquipped();
                    if (weaponEquipped) {
                        System.out.println(adventure.printInventory());
                        System.out.println("\nEquipped weapon: " + adventure.getEquippedWeapon());
                    } else {
                        System.out.println(adventure.printInventory());
                        System.out.println("\nNo weapon equipped.");
                    }

                case "health":
                    System.out.println(adventure.printPlayerHealth());
                    break;

                case "eat", "drink":
                    System.out.println(adventure.eatItem(userChoise));
                    break;

                case "Exit", "exit":
                    System.out.println("Closing adventure.");
                    running = false;
                    break;
            }
        }
    }
}


