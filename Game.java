import java.util.Scanner;

/**
 * class for the game engine
 */
public class Game {
    // statics for the moment so you can globally use input & scanner
    static String userInput;
    static Scanner sc;

    /**
     * initializes scanner, creates actors and starts a fight
     * implemented for possible future content
     */
    public static void startGame() {
        sc = new Scanner(System.in);
        Player Derp = new Player();
        Monster[] Kultisten = createMonsters(11);

        if (startFight(Derp, Kultisten[(int) (Math.random() * 10)])) {
            System.out.println("Congratulations! You win!");
        } else {
            System.out.println("You lost!");
        }
    }

    public static Monster[] createMonsters(int number) {
        Monster[] Monsters = new Monster[number];
        for (int i = 0; i < number; i++) {
            Monsters[i] = new Monster(150 - 2 * i, 30 + i * 2 , 70 - i);
        }
        return Monsters;
    }

    /**
     * runs a fight between two actors
     * player will get to attack first and attacks at least once
     * actors' status will be printed after each action
     * fight is over if an actors hp is below or equal 0
     * @param Derp player being attacked
     * @param Cthulhu monster attacking
     * @return true if the monster is dead
     */
    public static boolean startFight(Player Derp, Monster Cthulhu) {
        int round = 1;
        System.out.println(Derp);
        System.out.println(Cthulhu);

        do {
            System.out.printf("~~~~~~~~~~~~~~~~ Round %d ~~~~~~~~~~~~~~~~\n", round);
            printPossibleActions(Derp);
            if (!processPlayerAction(Derp, Cthulhu)) {
                continue; // the player gets to act again if he chose an invalid action
            }
            System.out.println(Derp);
            System.out.println(Cthulhu);
            System.out.println("------------------------------------------");

            if (!Cthulhu.isDefeated()) {
                System.out.println("The monster attacks!");
                Cthulhu.attack(Derp);
                System.out.println(Derp);
                System.out.println(Cthulhu);
            }
            System.out.printf("Your powers are refreshing, regenerating %d mana.\n", Derp.regenerateAp());
            round++;
        } while (!Derp.isDefeated() && !Cthulhu.isDefeated());

        return Cthulhu.isDefeated();
    }

    /**
     * prints the player's possible actions
     * implemented to make adding actions easier
     * Using a hashMap would be neater but is unknown to the students yet
     * @param Derp player
     */
    public static void printPossibleActions(Player Derp) {
        System.out.printf("Possible actions:\n"
                        + "0 --> attack\n"
                        + "1 --> use potion (%d remaining)\n", Derp.getPotions());
        System.out.printf("What will you do?\n"
                        + "------------------------------------------\n");
    }

    /**
     * switches the userInput for the possible actions
     * again, hashMap would be neater
     * @param Derp player who's acting
     * @param Cthulhu monster in fight, in case of an attack
     * @return true if the player did sth, false if the action was invalid
     */
    public static boolean processPlayerAction(Player Derp, Monster Cthulhu) {
        userInput = sc.nextLine();
        try {
            int in = Integer.parseInt(userInput);
        switch (in) {
            case 0:
                Derp.attack(Cthulhu);
                break;
            case 1:
                Derp.usePotion();
                break;
            default:
                System.out.printf("Invalid action!\n");
                return false;
        }

        } catch (NumberFormatException e) {
            System.out.printf("Invalid action!\n");
            return false;
        }
        return true;
    }
}
