public class Potion {
    private String name;
    private String effect;
    private String type;
    private String rarity;

    public Potion(String name, String effect, String type, String rarity) {
        this.name = name;
        this.effect = effect;
        this.type = type;
        this.rarity = rarity;
    }

    public void display() {
        System.out.println(name + " [" + rarity + " - " + type + "]");
        System.out.println("Effect: " + effect);
    }

    public String getName() {
        return name;
    }

    public String getEffect() {
        return effect;
    }

    public String getType() {
        return type;
    }

    public String getRarity() {
        return rarity;
    }
} 


