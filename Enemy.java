import java.util.Random;

// REFRACTOR: Implements ICombatant
public class Enemy implements ICombatant {
    private String name;
    private int maxHp;
    private int hp;
    private int maxMana;
    private int mana;
    private Skill s1, s2, ult;
    private Random rnd = new Random();

    public Enemy(String name, int maxHp, int maxMana, Skill s1, Skill s2, Skill ult) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.maxMana = maxMana;
        this.mana = maxMana;
        this.s1 = s1;
        this.s2 = s2;
        this.ult = ult;
    }

    // ICombatant Implementation
    @Override public String getName() { return name; }
    @Override public int getCurrentHp() { return hp; }
    @Override public int getMaxHp() { return maxHp; }
    @Override public boolean isFainted() { return hp <= 0; }

    @Override
    public void takeDamage(int dmg) {
        hp -= dmg;
        if(hp < 0) hp = 0;
        System.out.println(name + " takes " + dmg + " damage! (HP: " + hp + "/" + maxHp + ")");
    }

    @Override
    public void heal(int amt) {
        hp += amt;
        if (hp > maxHp) hp = maxHp;
    }

    // Enemy Specifics
    public int getCurrentMana() { return mana; }
    public int getMaxMana() { return maxMana; }

    public void attack(Brainrot target, int turn) {
        Skill chosenSkill = null;
        
        if (ult != null && mana >= ult.getManaCost() && rnd.nextInt(100) < 20) {
            chosenSkill = ult;
        } 
        else if (s2 != null && mana >= s2.getManaCost() && rnd.nextInt(100) < 40) {
            chosenSkill = s2;
        } 
        else if (s1 != null && mana >= s1.getManaCost()) {
            chosenSkill = s1;
        }

        if (chosenSkill != null) {
            mana -= chosenSkill.getManaCost();
            int dmg = Utils.randomBetween(chosenSkill.getBaseDamageRangeLow(), chosenSkill.getBaseDamageRangeHigh());
            
            System.out.println(name + " casts " + chosenSkill.getName() + "!");
            target.takeDamage(dmg);
        } else {
            System.out.println(name + " is out of mana and Struggles!");
            int struggleDmg = 15;
            target.takeDamage(struggleDmg);
            mana = Math.min(maxMana, mana + 10);
        }
    }
}