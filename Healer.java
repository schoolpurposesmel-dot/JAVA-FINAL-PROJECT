public class Healer extends Player {
    public Healer(String name) { super(name); }

    @Override public void onTurnStart(Brainrot active) {
        if(active.getCurrentHp()<active.getMaxHp()) active.heal((int)(active.getMaxHp()*0.05));
    }
    @Override public void applyPassives(Brainrot b) {}

    @Override public String getAbilityName() { return "QUICK FIX"; }

    @Override public boolean useAbility(Brainrot active) {
        int healAmt = active.getMaxHp() / 4; // 25% Heal
        active.heal(healAmt);
        System.out.println("HEALER MAGIC: 'Stay with me!'");
        System.out.println(">> Healed " + active.getName() + " for " + healAmt + " HP!");
        return true;
    }
}