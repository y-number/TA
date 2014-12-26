/**
 * class to represent the player
 */
public class Player {
    private int hp;
    private int atk;
    private double hit;
    private int maxHp;
    private int potions;
    private int healingPower;
    private int maxAp;
    private int ap;
    private int apRegen;

    public Player(int sMaxHp, int sAtk, int sHealingPower, int remainingItemUses, double sHitChance, int sMaxAp, int sApRegen) {
        maxHp = sMaxHp;
        hp = maxHp;
        atk = sAtk;
        healingPower = sHealingPower;
        potions = remainingItemUses;
        hit = sHitChance; // Input-format 0.xx %
        maxAp = sMaxAp;
        ap = maxAp;
        apRegen = sApRegen;
    }

    public Player(int sMaxHp, int sAtk, int sHealingPower, int remainingItemUses, double sHitChance) {
        maxHp = sMaxHp;
        hp = maxHp;
        atk = sAtk;
        healingPower = sHealingPower;
        potions = remainingItemUses;
        hit = sHitChance; // Input-format 0.xx
    }

    public Player() {
        maxHp = 120;
        hp = maxHp;
        atk = 20;
        healingPower = 70;
        potions = 3;
        hit = 0.7; // Input-format 0.xx
        maxAp = 40;
        ap = maxAp;
        apRegen = 8;
    }

    public int getHp() {
        return hp;
    }

    public double getHit() {
        return hit;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getPotions() {
        return potions;
    }

    public int getAtk() {
        return atk;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int regenerateAp () {
        int oldAp = ap;
        ap += (ap + apRegen > maxAp) ? (maxAp - ap) : apRegen;
        return ap - oldAp;
    }

    public boolean isDefeated() {
        return this.hp == 0;
    }

    public void takeDamage(int damage) {
        hp -= damage < hp ? damage : hp;
    }

    /**
     * heals player for healingPower, but no further than maxHp
     */
    public boolean usePotion() {
        if (getPotions() > 0) {
            hp += (hp + healingPower > maxHp) ? (maxHp - hp) : healingPower;
            this.potions--;
            System.out.println("You feel new strength rush through your veins!");
            return true;
        } else {
            System.out.printf("You're out of potions - bad luck eh?\n");
            return false;
        }
    }

    /**
     * compares random double with player's hit chance, successful if random <= hit
     * small chance for critical fail implemented
     * @param m monster being attacked
     */
    public int attack(Monster m) {
        double random = Math.random();
        if (random < 0.01) {
            System.out.println("Your attack backfired and you killed yourself");
            System.out.println("You died - bad luck, eh?");
            System.exit(0);
            return -2;
        } else if (random > getHit()) {
            System.out.println("Sadly, you missed");
            return -1;
        } else {
            int damage = (int) Math.floor(getAtk() * (Math.random() + 1));
            m.takeDamage(damage);
            System.out.printf("Hit! - You dealt %d damage\n", damage);
            return damage;
        }
    }

    public String toString() {
        return String.format("Player -- HP %d -- ATK %d \n"
                           + "       -- Potions %d -- AP %d ", hp, atk, potions, ap);
    }
}
