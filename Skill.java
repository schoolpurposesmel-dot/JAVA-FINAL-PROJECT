import java.io.Serializable;

public class Skill implements Serializable {
    private static final long serialVersionUID = 1L; // Version ID for saving
    private final String name;
    private int dmgLow;
    private int dmgHigh;
    private final int manaCost;

    public Skill(String name, int dmgLow, int dmgHigh, int manaCost) {
        this.name = name;
        this.dmgLow = dmgLow;
        this.dmgHigh = dmgHigh;
        this.manaCost = manaCost;
    }

    public String getName() { return name; }
    public int getManaCost() { return manaCost; }
    public int getBaseDamageRangeLow() { return dmgLow; }
    public int getBaseDamageRangeHigh() { return dmgHigh; }

    public void increaseDamage(int amt) {
        dmgLow += amt;
        dmgHigh += amt;
    }
}