public class Tank extends Player {
    public Tank(String name) { super(name); }

    @Override public double getIncomingDamageMultiplier() { return 0.8; }
    @Override public void applyPassives(Brainrot b) { b.buffMaxHp((int)(b.getMaxHp()*0.3)); }

    @Override public String getAbilityName() { return "IRON SHELL"; }

    @Override public boolean useAbility(Brainrot active) {
        System.out.println("TANK STANCE: 'COME AT ME!'");
        System.out.println(">> Next enemy attack reduced by 50%!");
        active.setDamageResist(0.5); 
        return true;
    }
}