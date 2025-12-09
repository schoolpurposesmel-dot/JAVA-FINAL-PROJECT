

public class BrainrotFactory {

    // ================== COMMON BRAINROTS ==================
    public static Brainrot liriliLarila() {
        Skill s1 = new Skill("Li-Ri Flick", 70, 95, 15);
        Skill s2 = new Skill("La-Ri Boom", 150, 170, 40);
        Skill ult = new Skill("Larila Echo Storm", 300, 340, 100);
        return new Brainrot("Lirili Larila", Brainrot.Rarity.COMMON, 500, 200, s1, s2, ult);
    }

    public static Brainrot bonecaAmbalabu() {
        Skill s1 = new Skill("Ambala Slap", 85, 95, 20);
        Skill s2 = new Skill("Boneca Smash", 160, 180, 45);
        Skill ult = new Skill("Ambalabu Kaboom", 320, 360, 110);
        return new Brainrot("Boneca Ambalabu", Brainrot.Rarity.COMMON, 520, 220, s1, s2, ult);
    }

    public static Brainrot bombiniGuzini() {
        Skill s1 = new Skill("Bzzz Pop", 80, 90, 15);
        Skill s2 = new Skill("Guzini Swarm", 165, 185, 50);
        Skill ult = new Skill("Bombini Buzzquake", 330, 370, 120);
        return new Brainrot("Bombini Guzini", Brainrot.Rarity.COMMON, 510, 210, s1, s2, ult);
    }

    public static Brainrot tripiTropi() {
        Skill s1 = new Skill("Trip-Tap Kick", 90, 100, 20);
        Skill s2 = new Skill("Tropi Slam", 170, 190, 50);
        Skill ult = new Skill("Tripi Tropicannon", 340, 380, 120);
        return new Brainrot("Tripi Tropi", Brainrot.Rarity.COMMON, 530, 220, s1, s2, ult);
    }

    public static Brainrot chimpanziniBananini() {
        Skill s1 = new Skill("Banana Toss", 90, 110, 20);
        Skill s2 = new Skill("Chimp Kick", 180, 200, 55);
        Skill ult = new Skill("Banana Bombardment", 360, 400, 130);
        return new Brainrot("Chimpanzini Bananini", Brainrot.Rarity.COMMON, 540, 230, s1, s2, ult);
    }

    public static Brainrot glorboFrutodrilo() {
        Skill s1 = new Skill("Fruit Slap", 100, 120, 25);
        Skill s2 = new Skill("Drilo Crunch", 190, 210, 55);
        Skill ult = new Skill("Frutodrilo Frenzy", 380, 420, 140);
        return new Brainrot("Glorbo Frutodrilo", Brainrot.Rarity.COMMON, 550, 240, s1, s2, ult);
    }

    // ================== RARE BRAINROTS ==================
    public static Brainrot cappuccinoAssassino() {
        Skill s1 = new Skill("Foam Flick", 130, 150, 30);
        Skill s2 = new Skill("Latte Lunge", 240, 280, 65);
        Skill ult = new Skill("Caffeine Meltdown", 480, 520, 150);
        return new Brainrot("Cappuccino Assassino", Brainrot.Rarity.RARE, 700, 300, s1, s2, ult);
    }

    public static Brainrot bombardinoCrocodilo() {
        Skill s1 = new Skill("Mini Bomb Bite", 140, 160, 30);
        Skill s2 = new Skill("Explosive Roll", 260, 300, 70);
        Skill ult = new Skill("Crocodilo Megaboom", 500, 540, 160);
        return new Brainrot("Bombardino Crocodilo", Brainrot.Rarity.RARE, 720, 320, s1, s2, ult);
    }

    public static Brainrot brrBrrPatapim() {
        Skill s1 = new Skill("Brr Jab", 125, 145, 25);
        Skill s2 = new Skill("Patapim Kick", 250, 290, 70);
        Skill ult = new Skill("Patapim Drumblast", 520, 560, 160);
        return new Brainrot("Brr Brr Patapim", Brainrot.Rarity.RARE, 710, 310, s1, s2, ult);
    }

    public static Brainrot ballerinaCappuccina() {
        Skill s1 = new Skill("Spin Kick", 135, 155, 30);
        Skill s2 = new Skill("Cup Smash", 270, 310, 70);
        Skill ult = new Skill("Ballerina Brewstorm", 540, 580, 170);
        return new Brainrot("Ballerina Cappuccina", Brainrot.Rarity.RARE, 730, 330, s1, s2, ult);
    }

    // ================== LEGENDARY BRAINROTS ==================
    public static Brainrot tralaleroTralala() {
        Skill s1 = new Skill("Trala Note", 180, 220, 35);
        Skill s2 = new Skill("Lero Blast", 330, 370, 80);
        Skill ult = new Skill("Opera of Oblivion", 650, 750, 180);
        return new Brainrot("Tralalero Tralala", Brainrot.Rarity.LEGENDARY, 1000, 400, s1, s2, ult);
    }

    public static Brainrot tungTungTungSahur() {
        Skill s1 = new Skill("Sahur Slap", 200, 240, 40);
        Skill s2 = new Skill("Dawn Strike", 360, 400, 90);
        Skill ult = new Skill("Eternal Sahur Chant", 750, 850, 190);
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
