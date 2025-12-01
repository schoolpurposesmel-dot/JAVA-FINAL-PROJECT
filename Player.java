import java.util.*;
import java.io.Serializable;

public abstract class Player implements Serializable {
    private static final long serialVersionUID = 1L;
    
    protected String name;
    protected int level = 1;
    protected int xp = 0;
    protected int coins = 0;
    protected List<Brainrot> party = new ArrayList<>(); 
    protected Set<Character> keys = new HashSet<>();
    private boolean instantText = false;
    private int activeSlot = 0;

    // === NEW: ABILITY COOLDOWN ===
    protected int abilityCooldown = 0;
    protected final int MAX_COOLDOWN = 3;

    public Player(String name) { this.name = name; this.coins = 50; }

    // === NEW: ABILITY METHODS ===
    public boolean isAbilityReady() { return abilityCooldown <= 0; }
    
    public void decreaseCooldown() { 
        if (abilityCooldown > 0) abilityCooldown--; 
    }

    public void resetAbilityCooldown() {
        abilityCooldown = MAX_COOLDOWN;
    }

    public int getCooldown() { return abilityCooldown; }

    // Abstract methods for Roles to implement
    public abstract String getAbilityName();
    public abstract boolean useAbility(Brainrot active); 
    // ============================

    public String getName() { return name; }
    public int getLevel() { return level; }
    public int getXp() { return xp; }
    public int getCoins() { return coins; }
    public void setInstantText(boolean instant) { this.instantText = instant; }
    public boolean isInstantText() { return instantText; }

    public void addXp(int amount) {
        xp += amount;
        while (xp >= level * 100) { xp -= level * 100; level++; System.out.println("Level up! Now level " + level); }
    }
    public void addCoins(int c) { coins += c; }
    public void spendCoins(int c) { coins -= c; if (coins < 0) coins = 0; }

    public void addToParty(Brainrot b) {
        if (party.size() >= 3) { System.out.println("Party full."); return; }
        applyPassives(b); 
        party.add(b);
    }
    
    public abstract void applyPassives(Brainrot b);

    public void clearParty() { party.clear(); activeSlot = 0; }
    public int getPartyCount() { return party.size(); }
    
    public Brainrot getActiveBrainrot() {
        if (party.isEmpty()) return null;
        if (activeSlot >= party.size()) activeSlot = 0;
        return party.get(activeSlot);
    }

    public void setActiveBrainrot(Brainrot b) {
        int idx = party.indexOf(b);
        if (idx != -1) activeSlot = idx;
    }

    public Brainrot getPartyFirst() { return party.isEmpty() ? null : party.get(0); }
    public boolean anyAliveInParty() { for (Brainrot b : party) if (!b.isFainted()) return true; return false; }
    
    public Brainrot firstAlive() {
        for (Brainrot b : party) {
            if (!b.isFainted()) return b;
        }
        return null;
    }

    public void healAll() { for (Brainrot b : party) b.healFull(); }
    public void restoreManaPartial(int amount) {
        for (Brainrot b : party) { if (!b.isFainted()) b.restoreMana(amount); }
    }
    public void restoreManaAll() { for (Brainrot b : party) b.restoreManaFull(); }
    public void reviveAll() { for (Brainrot b : party) b.reviveFull(); }
    
    public void printParty() {
        System.out.println("\n=== Party ===");
        if (party.isEmpty()) System.out.println("No brainrots equipped.");
        for (Brainrot b : party) System.out.println("- " + b.getName() + " HP:" + b.getCurrentHp() + "/" + b.getMaxHp() + " Mana:" + b.getCurrentMana() + "/" + b.getMaxMana() + " [" + b.getRarity() + "]");
    }
    
    public Brainrot getPartyByName(String n) { for (Brainrot b : party) if (b.getName().equalsIgnoreCase(n)) return b; return null; }

    public void obtainKey(char k) { keys.add(k); }
    public boolean hasKey(char k) { return keys.contains(k); }
    public boolean hasAllKeys() { return keys.contains('C') && keys.contains('I') && keys.contains('T'); }
    public String keysString() {
        if (keys.isEmpty()) return "None";
        StringBuilder sb = new StringBuilder();
        for (char c : keys) sb.append(c).append(' ');
        return sb.toString().trim();
    }

    public double getDamageMultiplier() { return 1.0; }
    public double getIncomingDamageMultiplier() { return 1.0; }
    public void onTurnStart(Brainrot active) { }
    public double getRewardMultiplier() { return 1.0; }
}