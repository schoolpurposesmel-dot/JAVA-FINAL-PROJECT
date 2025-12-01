public class Collector extends Player {
    public Collector(String name) { super(name); }

    @Override public double getRewardMultiplier() { return 1.5; }
    @Override public void applyPassives(Brainrot b) {}

    @Override public String getAbilityName() { return "POCKET CHANGE"; }

    @Override public boolean useAbility(Brainrot active) {
        System.out.println("COLLECTOR: 'Ooh, what's this?'");
        System.out.println(">> found 50 Coins on the floor!");
        addCoins(50);
        return true;
    }
}