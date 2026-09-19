public class ArenaBattleSimulator {

    interface Attackable {
        String attack();
        String attack(String weaponName);
    }

    interface Defendable {
        String defend();
    }

    static abstract class GameCharacter {
        private static int counter = 0;
        private final int characterId;
        protected final String name;

        GameCharacter(String name) {
            this.name = name;
            this.characterId = ++counter;
        }

        public final int getCharacterId() {
            return characterId;
        }

        public abstract String getSpecialMove();
    }

    static class Warrior extends GameCharacter implements Attackable, Defendable {

        Warrior(String name) {
            super(name);
        }

        @Override
        public String attack() {
            return name + " strikes with a blade";
        }

        @Override
        public String attack(String weaponName) {
            return name + " strikes with an " + weaponName;
        }

        @Override
        public String defend() {
            return name + " raises a shield";
        }

        @Override
        public String getSpecialMove() {
            return name + " unleashes Whirlwind Slash";
        }
    }

    static class Trap implements Defendable {
        private final String trapType;

        Trap(String trapType) {
            this.trapType = trapType;
        }

        @Override
        public String defend() {
            return trapType + " triggers automatically";
        }
    }

    static void resolveDefense(Defendable[] combatants) {
        for (Defendable combatant : combatants) {
            System.out.println(combatant.defend());
        }
    }

    public static void main(String[] args) {
        Warrior w = new Warrior("Kael");

        System.out.println(w.attack());
        System.out.println(w.attack("Iron Sword"));
        System.out.println(w.defend());
        System.out.println(w.getSpecialMove());

        Trap t = new Trap("Spike Pit");
        System.out.println(t.defend());

        resolveDefense(new Defendable[]{w, t});
    }
}
