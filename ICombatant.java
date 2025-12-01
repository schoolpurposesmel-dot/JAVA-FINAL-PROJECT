public interface ICombatant {
    String getName();
    int getCurrentHp();
    int getMaxHp();
    
    void takeDamage(int dmg);
    void heal(int amt);
    boolean isFainted();
}