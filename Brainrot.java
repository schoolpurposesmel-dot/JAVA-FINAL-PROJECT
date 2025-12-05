import java.io.Serializable;

public class Brainrot implements ICombatant, Serializable {
    private static final long serialVersionUID = 1L;
    
    public enum Rarity { COMMON, RARE, LEGENDARY }

    private final String name;
    private final Rarity rarity;
    private int maxHp;
    private int hp;
    private int maxMana;
    private int mana;
    private final Skill s1, s2, ult;
    private double tempBuff = 1.0;
    private int upgradeLevel = 0; 
    private final int MAX_UPGRADE = 2;
    private int ultCooldown = 0; 
    private final int MAX_ULT_COOLDOWN = 3;
    public void resetUltCooldown() { this.ultCooldown = MAX_ULT_COOLDOWN; }
    public void decreaseUltCooldown() { if (ultCooldown > 0) ultCooldown--; }
    public boolean isUltReady() { return ultCooldown <= 0; }
    public int getUltCooldown() { return ultCooldown; }
    
    // NEW: For Tank Ability
    private double damageResist = 1.0; 
    
    public Brainrot(String name, Rarity rarity, int maxHp, int maxMana, Skill s1, Skill s2, Skill ult) {
        this.name = name;
        this.rarity = rarity;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.maxMana = maxMana;
        this.mana = maxMana;
        this.s1 = s1;
        this.s2 = s2;
        this.ult = ult;
    }

    @Override public String getName() { return name; }
    @Override public int getMaxHp() { return maxHp; }
    @Override public int getCurrentHp() { return hp; }
    @Override public boolean isFainted() { return hp <= 0; }

    // === UPDATED: TAKE DAMAGE WITH RESIST ===
    @Override
    public void takeDamage(int dmg) {
        if (damageResist < 1.0) {
            dmg = (int)(dmg * damageResist);
            System.out.print(" (Shielded!)");
            damageResist = 1.0; // Reset after one hit
        }
        
        hp -= dmg;
        if(hp < 0) hp = 0;
        System.out.println(name + " takes " + dmg + " dmg");
    }

    public void setDamageResist(double val) { this.damageResist = val; }
    // ========================================

    @Override
    public void heal(int amt) {
        hp += amt;
        if(hp > maxHp) hp = maxHp;
    }

    public Rarity getRarity() { return rarity; }
    public int getMaxMana() { return maxMana; }
    public int getCurrentMana() { return mana; }
    public Skill getSkill1() { return s1; }
    public Skill getSkill2() { return s2; }
    public Skill getUlt() { return ult; }

    public void buffMaxHp(int amount) {
        this.maxHp += amount;
        this.hp += amount; 
    }

    public void healFull() { hp = maxHp; }
    public void restoreManaFull() { mana = maxMana; }
    public void restoreMana(int amt) {
        mana += amt;
        if(mana > maxMana) mana = maxMana;
    }
    public void reviveFull() { hp = Math.max(1, maxHp/3); }
    public void applyTempBuff(double m) { tempBuff = m; }
    public void clearTempBuff() { tempBuff = 1.0; }

    
    public String describeShort() {
        return name + " (" + rarity + ") HP:" + maxHp + "/" + hp + " Mana:" + maxMana + "/" + mana
                + " Skills: " + s1.getName() + ", " + s2.getName() + ", " + ult.getName();
    }

   public void upgrade() {
        if (upgradeLevel >= MAX_UPGRADE) {
            System.out.println(name + " is already at max upgrade level (" + MAX_UPGRADE + ")!");
            return;
        }
        
        maxHp += 40;
        hp = maxHp;
        maxMana += 20;
        mana = maxMana;
        s1.increaseDamage(10);
        s2.increaseDamage(15);
        ult.increaseDamage(25);
        
        upgradeLevel++; // Increment the counter
        System.out.println("Upgraded! (" + upgradeLevel + "/" + MAX_UPGRADE + ")");
    }

    public boolean useSkill(int id, Enemy enemy, double playerMultiplier) {
    Skill sk = id == 1 ? s1 : id == 2 ? s2 : ult;
    if(sk == null) return false;
    
    // CHECK ULT COOLDOWN
    if (id == 3 && !isUltReady()) {
        System.out.println("Ult is on cooldown! (" + ultCooldown + " turns remaining)");
        return false;
    }
    
    if(mana < sk.getManaCost()) {
        System.out.println("Not enough mana!");
        return false;
    }

    mana -= sk.getManaCost();
    // SET ULT COOLDOWN
    if (id == 3) {
        resetUltCooldown();
    }

    int base = Utils.randomBetween(sk.getBaseDamageRangeLow(), sk.getBaseDamageRangeHigh());
    
    // === MISSING DAMAGE CALCULATION BLOCK STARTS HERE ===
    double rarityMult = switch(rarity) {
        case COMMON -> 1.0;
        case RARE -> 1.4;
        case LEGENDARY -> 2.0;
    };
    
    // DEFINE finalDmg (fixes the error)
    int finalDmg = (int)Math.round(base * rarityMult * tempBuff * playerMultiplier);
    // === MISSING DAMAGE CALCULATION BLOCK ENDS HERE ===
    
    String buffMsg = (playerMultiplier > 1.0 || tempBuff > 1.0) ? " (Boosted!)" : "";
    enemy.takeDamage(finalDmg); // This line now uses the defined finalDmg
    System.out.println(name + " uses " + sk.getName() + " for ~" + finalDmg + " dmg." + buffMsg);

    clearTempBuff();
    return true;
}
    

   
}