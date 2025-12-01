public class BuildingFactory {
    public static Building createNGE() {
        Building nge = new Building("NGE Building (Tech Sector)");
        nge.addEnemy(EnemyFactory.createNullBug());
        nge.addEnemy(EnemyFactory.createSyntaxGremlin());
        nge.addEnemy(EnemyFactory.createStackPhantom());
        nge.setBoss(EnemyFactory.createSirArbs());
        nge.setKey('C');
        return nge;
    }
    public static Building createGLE() {
        Building gle = new Building("GLE Building (Network Lab)");
        gle.addEnemy(EnemyFactory.createPacketGhoul());
        gle.addEnemy(EnemyFactory.createPingWraith());
        gle.addEnemy(EnemyFactory.createRecursionShade());
        gle.setBoss(EnemyFactory.createMaamTulin());
        gle.setKey('I');
        return gle;
    }
    public static Building createRTL() {
        Building rtl = new Building("RTL Building (Old Grounds)");
        rtl.addEnemy(EnemyFactory.createLostNode());
        rtl.addEnemy(EnemyFactory.createMemoryLeech());
        rtl.addEnemy(EnemyFactory.createObjectGhost());
        rtl.setBoss(EnemyFactory.createSirKhai());
        rtl.setKey('T');
        return rtl;
    }
}