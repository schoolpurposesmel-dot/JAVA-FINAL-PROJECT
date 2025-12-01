public class Fighter extends Player {
    public Fighter(String name) { super(name); }

    @Override public double getDamageMultiplier() { return 1.25; }
    @Override public void applyPassives(Brainrot b) { System.out.println("⚔️ Fighter Passive: Ready."); }

    @Override public String getAbilityName() { return "ADRENALINE RUSH"; }

    @Override public boolean useAbility(Brainrot active) {
        System.out.println("FIGHTER SHOUT: 'LET'S DO THIS!'");
        System.out.println(">> Next attack deals DOUBLE damage!");
        active.applyTempBuff(2.0); // 2x Damage for next hit
        return true;
    }
}