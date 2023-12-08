import java.util.Random;

public interface Accessories {
    String getName();
    String getDescription();
    }

    interface Boots extends Accessories {
        double moveSpeedIncrease();
    }

    interface Ring extends Accessories {

        double manaIncrease();
    }

    interface noOnehit_Boots extends Boots{
        boolean noOnehit_kill();
    }
    interface DecreaseDamage_Boots extends Boots{
        double DecreaseDamagePercent();
    }
    interface DamageIncrease_Ring extends Ring{
        double DamageIncreasePercent();
    }
    interface multipleStat_Ring extends Ring{
        double multipleOneStat();
        int RandomNumber();
    }

    class Accessory implements Accessories {
        protected String name;
        protected String description;

        public Accessory(String name, String description) {
            this.name = name;
            this.description = description;
        }

        @Override
        public String getName() {
            return name;
        }

        /** to access private string name accessory
         * @return name accessory
         */

        @Override
        public String getDescription() {
            return description;
        }

        /** to access private string description accessory
         * @return description accessory
         */

    }

    class Ringclass extends Accessory implements Ring{
        public Ringclass(String name, String description) {
            super(name,description);
        }

        @Override
        public double manaIncrease() {
            System.out.println("Mana increased by wearing " + name);
            return 50;
        }
        /** Type ring accessory ManaIncrease
         * effect: "Mana increased by wearing " + name accessory
         *         printed when Character wearing Ring
         * @return Mana value 50;
         */
    }

    class Bootsclass extends Accessory implements Boots{
        public Bootsclass(String name,String description) {
            super(name,description);
        }

        @Override
        public double moveSpeedIncrease() {
            System.out.println("Move speed increased by wearing " + name);
            return 50;
        }
        /** Type boot accessory MoveSpeedIncrease
         * effect: "move speed increased by wearing " + name accessory
         *         printed when Character wearing Boot
         * @return moveSpeed value 50;
         */
    }

    class noFirsthitBoots extends Bootsclass implements noOnehit_Boots{
        private boolean isProtected;

        public noFirsthitBoots(String name, String description) {
            super(name,description);
            this.isProtected = false;
        }

        @Override
        public boolean noOnehit_kill() {
            if (!isProtected) {
                System.out.println(super.getName() + " prevents a first-hit!");
                isProtected = true;
                return true;
            } else {
                System.out.println(super.getName() + " effect is already active.");
                return false;
            }
        }
        /** Skill avoid one hit damage first time;
         * effect: this accessory "prevents a first-hit!" printed and set isProtected is true;
         *         if isProtected is false;
         * @retrun true;
         * effect: this accessory "effect is already active." printed
         *         if isProtected is true;
         * @return false;
         */
    }

    class armorBoots extends Bootsclass implements DecreaseDamage_Boots {
        public armorBoots(String name,String description) {
            super(name,description);
        }
        @Override
        public double DecreaseDamagePercent() {
            System.out.println(name + "Decrease damage 15%!!");
            return 15.0;
        }
        /** Skill decrease damage
         * effect: this accessory "Decrease damage 15%!!" printed
         * return percent to decrease damage value 15%
         */
    }

    class warRing extends Ringclass implements DamageIncrease_Ring {
        public warRing(String name,String description) {
            super(name,description);
        }
        @Override
        public double DamageIncreasePercent() {
            System.out.println(name + "Increase damage 10%!!");
            return 10.0;
        }
        /** Skill increase damage
         * effect: this accessory "Increase damage 10%!!" printed
         * return percent to increase damage value 10%
         */
    }

    class godRing extends Ringclass implements multipleStat_Ring {

        public godRing(String name,String description) {
            super(name,description);
        }
        @Override
        public double multipleOneStat() {
            return 2;

        }

        /** God increase stat 2X
         * @return value 2 to calculate stat 2X
         */

        @Override
        public int RandomNumber() {
            Random rand = new Random();
            int n1 = rand.nextInt(3) + 1;
            return n1;
        }
        /** God ring random increase stat 3 cases
         *  return case value 1 or 2 or 3 cases;
         */
}
