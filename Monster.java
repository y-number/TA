/**
 * class to represent the monster
 */
public class Monster {
    private int hp;
    private int atk;
    private double hit;

    public Monster(int sMaxHp, int sAtk, double sHitChance) {
        hp = sMaxHp;
        atk = sAtk;
        hit = sHitChance;
    }

    public Monster() {
        hp = 150;
        atk = 30;
        hit = 0.7;
    }

    public int getHp() {
        return hp;
    }

    public int getAtk() {
        return atk;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public double getHit() {
        return hit;
    }

    public boolean isDefeated() {
        return this.hp == 0;
    }

    public void takeDamage(int damage) {
        hp -= damage < hp ? damage : hp;
    }

    /**
     * compares random double with monster's hit chance, successful if random <= hit
     * small chance for critical fail implemented
     * @param p player being attacked
     */
    public int attack(Player p) {
        double random = Math.random();
        if (random < 0.01) {
            System.out.println("The monster's attack backfired and it killed itself");
            System.out.println("You win, you lucky bastard!");
            System.exit(0);
            return -2;
        } else if (random > getHit()) {
            System.out.println("Lucky for you - the monster missed");
            return -1;
        } else {
            int damage = (int) Math.floor(getAtk() * (Math.random() + 1));
            p.takeDamage(damage);
            System.out.printf("The monster hits for %d damage\n", damage);
            return damage;
        }
    }

    public String toString() {
        return String.format("Monster -- HP %d -- ATK %d", getHp(), getAtk());
    }
}
