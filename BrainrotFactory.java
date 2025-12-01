

public class BrainrotFactory {

    // ================== COMMON BRAINROTS ==================
    public static Brainrot liriliLarila() {
        Skill s1 = new Skill("Li-Ri Flick", 80, 80, 15);
        Skill s2 = new Skill("La-Ri Boom", 160, 160, 40);
        Skill ult = new Skill("Larila Echo Storm", 320, 320, 100);
        return new Brainrot("Lirili Larila", Brainrot.Rarity.COMMON, 500, 200, s1, s2, ult);
    }

    public static Brainrot bonecaAmbalabu() {
        Skill s1 = new Skill("Ambala Slap", 90, 90, 20);
        Skill s2 = new Skill("Boneca Smash", 170, 170, 45);
        Skill ult = new Skill("Ambalabu Kaboom", 340, 340, 110);
        return new Brainrot("Boneca Ambalabu", Brainrot.Rarity.COMMON, 520, 220, s1, s2, ult);
    }

    public static Brainrot bombiniGuzini() {
        Skill s1 = new Skill("Bzzz Pop", 85, 85, 15);
        Skill s2 = new Skill("Guzini Swarm", 175, 175, 50);
        Skill ult = new Skill("Bombini Buzzquake", 350, 350, 120);
        return new Brainrot("Bombini Guzini", Brainrot.Rarity.COMMON, 510, 210, s1, s2, ult);
    }

    public static Brainrot tripiTropi() {
        Skill s1 = new Skill("Trip-Tap Kick", 95, 95, 20);
        Skill s2 = new Skill("Tropi Slam", 180, 180, 50);
        Skill ult = new Skill("Tripi Tropicannon", 360, 360, 120);
        return new Brainrot("Tripi Tropi", Brainrot.Rarity.COMMON, 530, 220, s1, s2, ult);
    }

    public static Brainrot chimpanziniBananini() {
        Skill s1 = new Skill("Banana Toss", 100, 100, 20);
        Skill s2 = new Skill("Chimp Kick", 190, 190, 55);
        Skill ult = new Skill("Banana Bombardment", 380, 380, 130);
        return new Brainrot("Chimpanzini Bananini", Brainrot.Rarity.COMMON, 540, 230, s1, s2, ult);
    }

    public static Brainrot glorboFrutodrilo() {
        Skill s1 = new Skill("Fruit Slap", 110, 110, 25);
        Skill s2 = new Skill("Drilo Crunch", 200, 200, 55);
        Skill ult = new Skill("Frutodrilo Frenzy", 400, 400, 140);
        return new Brainrot("Glorbo Frutodrilo", Brainrot.Rarity.COMMON, 550, 240, s1, s2, ult);
    }

    // ================== RARE BRAINROTS ==================
    public static Brainrot cappuccinoAssassino() {
        Skill s1 = new Skill("Foam Flick", 140, 140, 30);
        Skill s2 = new Skill("Latte Lunge", 260, 260, 65);
        Skill ult = new Skill("Caffeine Meltdown", 500, 500, 150);
        return new Brainrot("Cappuccino Assassino", Brainrot.Rarity.RARE, 700, 300, s1, s2, ult);
    }

    public static Brainrot bombardinoCrocodilo() {
        Skill s1 = new Skill("Mini Bomb Bite", 150, 150, 30);
        Skill s2 = new Skill("Explosive Roll", 280, 280, 70);
        Skill ult = new Skill("Crocodilo Megaboom", 520, 520, 160);
        return new Brainrot("Bombardino Crocodilo", Brainrot.Rarity.RARE, 720, 320, s1, s2, ult);
    }

    public static Brainrot brrBrrPatapim() {
        Skill s1 = new Skill("Brr Jab", 135, 135, 25);
        Skill s2 = new Skill("Patapim Kick", 270, 270, 70);
        Skill ult = new Skill("Patapim Drumblast", 540, 540, 160);
        return new Brainrot("Brr Brr Patapim", Brainrot.Rarity.RARE, 710, 310, s1, s2, ult);
    }

    public static Brainrot ballerinaCappuccina() {
        Skill s1 = new Skill("Spin Kick", 145, 145, 30);
        Skill s2 = new Skill("Cup Smash", 290, 290, 70);
        Skill ult = new Skill("Ballerina Brewstorm", 560, 560, 170);
        return new Brainrot("Ballerina Cappuccina", Brainrot.Rarity.RARE, 730, 330, s1, s2, ult);
    }

    // ================== LEGENDARY BRAINROTS ==================
    public static Brainrot tralaleroTralala() {
        Skill s1 = new Skill("Trala Note", 200, 200, 35);
        Skill s2 = new Skill("Lero Blast", 350, 350, 80);
        Skill ult = new Skill("Opera of Oblivion", 700, 700, 180);
        return new Brainrot("Tralalero Tralala", Brainrot.Rarity.LEGENDARY, 1000, 400, s1, s2, ult);
    }

    public static Brainrot tungTungTungSahur() {
        Skill s1 = new Skill("Sahur Slap", 220, 220, 40);
        Skill s2 = new Skill("Dawn Strike", 380, 380, 90);
        Skill ult = new Skill("Eternal Sahur Chant", 800, 800, 190);
        return new Brainrot("Tung Tung Tung Sahur", Brainrot.Rarity.LEGENDARY, 1100, 450, s1, s2, ult);
    }

    // ================== RANDOM BRAINROT WITH PLAYER ROLE ==================
    public static Brainrot randomBrainrot(Player player) {
        int roll = (int)(Math.random() * 100);

        // Collector gets a 10% boost toward rarer Brainrots
        if(player instanceof Collector) {
            roll += 10;
            if(roll > 100) roll = 100;
        }

        if(roll < 60) {
            Brainrot[] commons = {liriliLarila(), bonecaAmbalabu(), bombiniGuzini(),
                                  tripiTropi(), chimpanziniBananini(), glorboFrutodrilo()};
            return commons[(int)(Math.random() * commons.length)];
        } else if(roll < 90) {
            Brainrot[] rares = {cappuccinoAssassino(), bombardinoCrocodilo(),
                                brrBrrPatapim(), ballerinaCappuccina()};
            return rares[(int)(Math.random() * rares.length)];
        } else {
            Brainrot[] legendaries = {tralaleroTralala(), tungTungTungSahur()};
            return legendaries[(int)(Math.random() * legendaries.length)];
        }
    }
}
