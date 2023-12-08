import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public interface Characters {
    void displayStat();
    void displayAllInventory();
    void levelUp();
    void takeDamage(double damage);
    boolean hasNoFirsthitBoots();
    boolean hasArmorBoots();
    boolean hasWarRing();
    boolean hasGodRing();
    void equip(Accessories accessory);
    void unequip(Accessories accessory);
    void attack(RPGCharacter target);
    String getJobClass();
    String getName();
    void setDamage(double d);
    void setHp(double h);
    void setMoveSpeed(double s);
    double getDamage();
    double getHp();
    double getMoveSpeed();
    void setLevel(int l);
    int getLevel();
    void setMaxHp(double h);
    double getMaxHp();
    void setMaxMana(double m);
    double getMaxMana();
    void setMana(double m);
    double getMana();
    double getBasedamage();
    void setJob(String s);

}

interface Magician extends Characters{
    void fireball(RPGCharacter target);
}

interface Warriror extends Characters{
    void sword_dance();
}

interface Necromancer_0 extends Magician{
    void equipNoOnehitkill();
    void ghostRush(RPGCharacter target);
}

interface Witcher_0 extends Magician,Warriror {
    void MagicBlade();
}

interface Samurai_0 extends Warriror{
    void highDamage_attack(RPGCharacter target);
}

class RPGCharacter implements Characters {
    private String name;
    private int level;
    private double hp;
    private double mana;
    private double max_hp;
    private double max_mana;
    private double moveSpeed;
    private String Job;
    private List<Accessories> inventory;
    private double damage;
    private int tempNUM;
    private double base_damage;

    public RPGCharacter(String name ,int level ,double r) {
        this.name = name;
        this.level = level;
        this.moveSpeed = r;
        this.hp = 100 + (10 * level);
        this.mana = 50 + (2 * level);
        this.max_hp = 100 + (10 * level);
        this.max_mana = 50 + (2 * level);
        this.inventory = new ArrayList<>();
        this.base_damage = 20;
        this.damage = base_damage * (1+0.1*level);
        this.Job = "Jobless";
        this.tempNUM = 0;
    }


    public void displayStat() {
        System.out.println(name + " - Level: " + level + ", Job: " + this.getJobClass() + ", HP: " + hp + "/" + max_hp +
                ", Mana: " + mana + "/" + max_mana + ", moveSpeed: " + moveSpeed + ", Damage: " + damage);
    }
    /** To show stat character on terminal
     * effect: All stat Character printed
     */

    public void displayAllInventory() {
        System.out.println("Inventory for " + name + ":");
        for (Accessories item : inventory) {
            System.out.println("- " + item.getName() + ": " + item.getDescription());
        }
        System.out.println("End of inventory.");
    }

    /** To show Inventories equipped
     * effect: All Accessories this Character has
     *         accessories name and description printed
     */

    public void levelUp() {
        double diff_maxHp = (100 + (10 * level)) - (100 + (10 * (level - 1)));
        double diff_maxMana = (50 + (2 * level)) - (50 + (2 * (level - 1)));
        this.hp = Math.min(this.hp + diff_maxHp, this.max_hp);//heal hp when level up
        this.mana = Math.min(this.mana + diff_maxMana, this.max_mana);//heal mana when level up
        calculateStatAccessories();


        System.out.println("---------------------------");
        System.out.println(name + " Level up to " + level + "!!");
        System.out.println("maxHp up to " + max_hp);
        System.out.println("maxMana up to " + max_mana);
        System.out.println("damage up to " + damage);
        System.out.println("---------------------------");
    }

    /** When character level up
     * effect: update heal mana and hp
     * effect: call function calculate stat update
     *         has changed by accessory equipped
     * effect: information character printed
     */

    public void takeDamage(double damage) {
        if (hasNoFirsthitBoots()) {
            for (Accessories item : inventory) {
                if (item instanceof noFirsthitBoots) {
                    noFirsthitBoots nofirsthitBoots = (noFirsthitBoots) item;
                    if (nofirsthitBoots.noOnehit_kill()) {
                        damage = 0;
                    }
                }
            }
        }

        if (hasArmorBoots()) {
            for (Accessories item : inventory) {
                if (item instanceof armorBoots) {
                    armorBoots ArmorBoots = (armorBoots) item;
                    damage = damage - (damage * ArmorBoots.DecreaseDamagePercent()/100);
                }
            }
        }

        hp -= damage;
        if (hp <= 0) {
            System.out.println(name + " took " + damage + " and has been defeated!");
        } else {
            System.out.println(name + " took " + damage + " damage. Remaining health: " + hp);
        }

        /** When this character is token damage
         * effect: damage is zero one time
         *         if character wear noFirstHitBoots;
         * effect: damage decrease by ArmorBoots
         *         if character wear ArmorBoots;
         * effect: hp this character decrease = damage
         *  continuing effect: "this character is token damage and has been defeated!" printed
         *                     if hp character is zero;
         *             effect: "this character is token damage. Remaining health: hp
         *                     if hp character is not zero;
         * @param damage that this character is taken
         */
    }


    public boolean hasNoFirsthitBoots() {
        for (Accessories item : inventory) {
            if (item instanceof noFirsthitBoots) {
                return true;
            }
        }
        return false;
    }

    /** Find FirstHitBoots in inventory of this character;
     * @return ture if it is found
     * @return false if it is not found
     */

    public boolean hasArmorBoots() {
        for (Accessories item : inventory) {
            if (item instanceof armorBoots) {
                return true;
            }
        }
        return false;
    }
    /** Find ArmorBoots in inventory of this character;
     * @return ture if it is found
     * @return false if it is not found
     */

    public boolean hasWarRing() {
        for (Accessories item : inventory) {
            if (item instanceof warRing) {
                return true;
            }
        }
        return false;
    }
    /** Find WarRing in inventory of this character;
     * @return ture if it is found
     * @return false if it is not found
     */

    public boolean hasGodRing() {
        for (Accessories item : inventory) {
            if (item instanceof godRing) {
                return true;
            }
        }
        return false;
    }
    /** Find GodRing in inventory of this character;
     * @return ture if it is found
     * @return false if it is not found
     */

    public void equip(Accessories accessory) {
        inventory.add(accessory);
        System.out.println(name + " equipped " + accessory.getName());
        calculateStatAccessories();

        /** equip accessory for this character
         * effect: add accessory to inventory of character
         * effect: "new accessory equipped" printed
         * effect: call function calculate stat update
         *         when equipped it
         * @param accessory to equip for character
         */
    }


    public void calculateStatAccessories(){
        for (Accessories acType : inventory) {
            if (acType instanceof Ringclass) {
                Ringclass ring = (Ringclass) acType;
                this.mana = mana + ring.manaIncrease();

            }
            if (acType instanceof Bootsclass) {
                Bootsclass boots = (Bootsclass) acType;
                this.moveSpeed = moveSpeed + boots.moveSpeedIncrease();

            }
        }
        if (hasGodRing()) {
            for (Accessories item : inventory) {
                if (item instanceof godRing) {
                    godRing GodRing = (godRing) item;
                    switch (GodRing.RandomNumber()) {
                        case 1:
                            damage *= GodRing.multipleOneStat();
                            System.out.println(GodRing.getName() + " Bless Damage!!");
                            this.tempNUM = 1;
                            break;
                        case 2:
                            hp *= GodRing.multipleOneStat();
                            System.out.println(GodRing.getName() + " Bless HP!!");
                            this.tempNUM = 2;
                            break;
                        default:
                            mana *= GodRing.multipleOneStat();
                            System.out.println(GodRing.getName() + " Bless Mana!!");
                            this.tempNUM = 3;
                    }
                }

            }
        }
    }
    /** calculate stat has changed by accessories
     * effect: calculate increase stat by accessorise
     *         if character has accessories that follow it type
     *         rings mana+ and boots moveSpeed+
     * effect: calculate 3 cases increase (damage, hp and mana) 2X
     *         if character has GodRing;
     */

    // Operation 5: Attack
    public void attack(RPGCharacter target) {
        System.out.println(name + " attacks " + target.name + " !!!");

        if (hasWarRing()) {
            for (Accessories item : inventory) {
                if (item instanceof warRing) {
                    warRing WarRing = (warRing) item;
                    damage = damage + (damage * WarRing.DamageIncreasePercent()/100);
                }
            }
        }

        target.takeDamage(this.damage);
    }

    /** attack to target
     * effect: this character attack target !!! printed
     * effect: damage increase by warRing
     *         if character has warRing
     * effect: target is taken damage
     * @param target is taken attacking by this character
     */

    public void attack_skill(RPGCharacter target, double skillDamage){
        System.out.println(name + " attacks " + target.name + " !!!");

        if (hasWarRing()) {
            for (Accessories item : inventory) {
                if (item instanceof warRing) {
                    warRing WarRing = (warRing) item;
                    skillDamage = skillDamage + (skillDamage * WarRing.DamageIncreasePercent()/100);
                }
            }
        }

        target.takeDamage(skillDamage);

        /** attack to target by skill
         * effect: this character attack target !!! printed
         * effect: skillDamage increase by warRing
         *         if character has warRing
         * effect: target is taken skillDamage
         * @param target is taken attacking by this character
         * @param skillDamage this character use skill
         */

    }

    public String getJobClass() {
        return Job;
    }

    /** to access private string job of character
     * @return  jod of character
     */

    public void unequip(Accessories accessory) {
        if (inventory.contains(accessory)) {
            inventory.remove(accessory);
            if (accessory instanceof godRing) {
                godRing GodRing = (godRing) accessory;
                switch (tempNUM) {
                    case 1:
                        damage /= GodRing.multipleOneStat();
                        break;
                    case 2:
                        hp /= GodRing.multipleOneStat();
                        break;
                    default:
                        mana /= GodRing.multipleOneStat();
                }
            }
            if (accessory instanceof Ringclass) {
                Ringclass ring = (Ringclass) accessory;
                this.mana = mana - ring.manaIncrease();

            }
            if (accessory instanceof Bootsclass) {
                Bootsclass boots = (Bootsclass) accessory;
                this.moveSpeed = moveSpeed - boots.moveSpeedIncrease();

            }

            System.out.println(name + " unequipped " + accessory.getName());
        } else {
            System.out.println(name + " does not have " + accessory.getName() + " in the inventory.");
        }
    }

    /** Unequipped accessory of character
     * effect: remove accessory form inventory of character
     *         effect: calculate new stat when unequipped it
     *                 if character has unequipped accessories that follow it type
     *                 rings mana+ and boots moveSpeed+
     *         effect: calculate 3 cases decrease (damage, hp and mana) 2X
     *                 if character has unequipped GodRing;
     *         effect: "name accessory unequipped" printed
     *         if inventory of character has this accessory
     * effect: "name does not have accessory inventory" printed
     *         if not found accessory in inventory of character
     */

    @Override
    public String getName() {
        return name;
    }
    /** to access private string Name of character
     * @return  value string name of character
     */

    public void setDamage(double d) {
        this.damage = d;

        /** to set new damage
         * effect: damage this character new set up
         * @param damage to new set up of character
         */
    }


    public void setHp(double h) {
        this.hp = h;

        /** to set new hp
         * effect: hp of this character new set up
         * @param HP to new set up of character
         */

    }

    public void setMoveSpeed(double s) {
        this.moveSpeed = s;

        /** to set new moveSpeed
         * effect: moveSpeed of this character new set up
         * @param moveSpeed to new set up of character
         */
    }


    @Override
    public void setLevel(int level) {
        this.level = level;

        /** to set new level
         * effect: level of this character new set up
         * @param level to new set up of character
         */

    }

    @Override
    public double getDamage() {
        return damage;
    }
    /** to access private double damage character
     * @return  value double damage character
     */

    @Override
    public double getHp() {
        return hp;
    }
    /** to access private double hp of character
     * @return  value double hp of character
     */

    @Override
    public double getMoveSpeed() {
        return moveSpeed;
    }
    /** to access private double moveSpeed character
     * @return  value double moveSpeed of character
     */

    @Override
    public int getLevel() {
        return level;
    }
    /** to access private int level character
     * @return  value int level of character
     */

    @Override
    public double getMaxHp() {
        return max_hp;
    }
    /** to access private double maxHp character
     * @return  value double maxHP of character
     */

    @Override
    public double getMaxMana() {
        return max_mana;
    }
    /** to access private double maxMana of character
     * @return  value double maxMana of character
     */

    @Override
    public void setMaxHp(double h) {
        this.max_hp = h;
        /** to set new hp
         * effect: hp of this character new set up
         * @param maxhp to new set up of character
         */
    }


    @Override
    public void setMaxMana(double m) {
        this.max_mana = m;
    }

    public double getMana() {
        return mana;
    }
    /** to access private double mana of character
     * @return  value double mana of character
     */

    @Override
    public void setMana(double mana) {
        this.mana = mana;
        /** to set new mana
         * effect: mana of this character new set up
         * @param Mana to new set up of character
         */
    }

    @Override
    public double getBasedamage() {
        return base_damage;
    }
    /** to access private double BaseDamage of character
     * @return  value double BaseDamage of character
     */

    @Override
    public void setJob(String s) {
        this.Job = s;
    }
    /** to set Job
     * effect: job of this character new set up
     * @param job to new set up of character
     */
}

class Mage extends RPGCharacter implements Magician {
    public Mage(String name,int level,double movespeed) {
        super(name,level,movespeed);
        this.setHp(80 + (2 * this.getLevel()));
        this.setMana(100 + (2 * getLevel()));
        this.setMaxHp(getHp());
        this.setMaxMana(getMana());
        this.setDamage(getBasedamage() * (1+0.3*getLevel()));
        this.setJob("Magician");

    }
    @Override
    public void fireball(RPGCharacter target) {
        if (this.getMana() - 50 >= 0) {
            this.setDamage(50 + this.getDamage());
            System.out.println(this.getName() + " use fireball \uD83D\uDD25 !!");
            attack_skill(target, getDamage());
            this.setDamage(this.getDamage() - 50);
            this.setMana(getMana() - 50);
        } else {
            System.out.println(this.getName() + " can't use skill");
        }
        /** skill fireball of mage
         * effect: set skill_damage += 50;
         * effect: "this character use skill attack" printed
         * effect: attack to target character;
         * effect: damage to normal;
         * effect: use mana -= 50;
         *   if mana has more 50;
         * effect: can not use skill
         *  if mana has than less 50;
         * @param target RPGCharacter to attack
         */
    }



    @Override
    public void levelUp() {
        this.setLevel(this.getLevel() + 1);
        this.setMaxHp(80 + (2 * this.getLevel()));
        this.setMaxMana(100 + (2 * getLevel()));
        double diff_maxHp = (80 + (2 * getLevel())) - (80 + (2 * (getLevel() - 1)));
        double diff_maxMana = (100 + (2 * getLevel())) - (100 + (2 * (getLevel() - 1)));
        this.setHp(Math.min(this.getHp() + diff_maxHp, this.getMaxHp()));
        this.setMana(Math.min(this.getMana() + diff_maxMana, this.getMaxMana()));
        this.setDamage(getBasedamage() * (1+0.3*getLevel()));
        super.levelUp();
    }
    /** when Character level up
     * effect: set new level
     * effect: set new maxHP,maxMana and Damage
     * effect: call super levelUp to heal current mana and hp
     */
}

class Soldier extends RPGCharacter implements Warriror {
    public Soldier(String name,int level,double movespeed) {
        super(name,level,movespeed);
        this.setHp(100 + (3 * this.getLevel()));
        this.setMana(50 + (2 * getLevel()));
        this.setMaxHp(getHp());
        this.setMaxMana(getMana());
        this.setDamage(getBasedamage() * (1 + 0.05*getLevel()));
        this.setJob("Magician");

    }

    @Override
    public void sword_dance() {
        if(this.getMana() - 25 >= 0) {
            System.out.println(this.getName() + " use sword dance ⚔");
            System.out.println("HP increase 75%");
            System.out.println("Damage X1.5 !!");
            setDamage(getDamage() * 1.75);
            setHp(getHp() + 50*getMaxHp()/100);
            setMana(getMana() - 25);
        }else System.out.println(this.getName() + " can't use skill");

    }

    /** skill sword_dance of Solider
     * effect: Character use skill printed;
     * effect: Character hp increase 75% and damage x1.5 printed
     * effect: set up HP and Mana
     * effect: mana -= 25;
     *      if character has mana more 25 that can use it;
     * effect: character can not use skill;
     *      if character has mana than less 25
     */

    @Override
    public void levelUp() {
        this.setLevel(this.getLevel() + 1);
        this.setMaxHp(100 + (3 * this.getLevel()));
        this.setMaxMana(50 + (2 * getLevel()));
        double diff_maxHp = (100 + (3 * this.getLevel())) - (100 + (3 * (getLevel() - 1)));
        double diff_maxMana = (50 + (2 * getLevel())) - (50 + (2 * (getLevel() - 1)));
        this.setHp(Math.min(this.getHp() + diff_maxHp, this.getMaxHp()));
        this.setMana(Math.min(this.getMana() + diff_maxMana, this.getMaxMana()));
        this.setDamage(getBasedamage() * (1+0.05*getLevel()));
        super.levelUp();
    }
}

class Ghost {
    private String name;
    private double damage;
    private double hp;

    public Ghost() {
        this.name = "Ghost";
        this.damage = 30;
        this.hp = 1;
        System.out.println("\uD83D\uDC80");
    }

    public double getDamage() {
        Random rand = new Random();
        int n1 = rand.nextInt(3) + 1;
        switch (n1) {
            case 1:
                System.out.println("แกไม่รอดแน่!!");
                break;
            case 2:
                System.out.println("ผีหลอกวิญญาณหลอน~~");
                break;
            default:
                System.out.println("ตามติดไปทุกที่!!");
        }
        return damage;
    }
    /** ghost summon has 3 type ghosts
     * effect: random ghost to printed text
     *         "แกไม่รอดแน่!!", "ผีหลอกวิญญาณหลอน~~", and "ตามติดไปทุกที่!!"
     * @return value double damaged of ghost
     */

    public String getName() {
        return name;
    }

    /** To access private name ghost
     * @return value string name of ghost
     */

    public double getHp() {
        return hp;
    }
    /** To access private hp ghost
     * @return value double hp of ghost
     */
}



class Necromancer extends Mage implements Necromancer_0 {
    public Necromancer(String name,int level,double movespeed) {
        super(name,level,movespeed);
    }

    @Override
    public void equipNoOnehitkill() {
        this.equip(new noFirsthitBoots("Sorcerer Boots","avoid next damage"));
    }

    /** equip NoOneHitKill();
     * effect: call equip accessory name "Sorcerer Boots" and  description "avoid next damage"
     */

    public void ghostRush(RPGCharacter target) {
        if(this.getMana() - 50 >= 0 && this.getHp() - 10 >= 1) {
            System.out.println(this.getName() + " summon 3 ghost.");
            Ghost g1 = new Ghost();
            Ghost g2 = new Ghost();
            Ghost g3 = new Ghost();
            double skillDamage1 = (g1.getDamage() + 10 * getDamage()/100);
            double skillDamage2 = (g2.getDamage() + 10 * getDamage()/100);
            double skillDamage3 = (g3.getDamage() + 10 * getDamage()/100);
            attack_skill(target, skillDamage1);
            attack_skill(target, skillDamage2);
            attack_skill(target, skillDamage3);
            this.setMana(getMana() - 50);
            this.setHp(getHp() - 10);
        }else{
            System.out.println(this.getName() + " can't use skill");
        }

    }
    /** skill ghostRush to attack target character
     * effect: summon 3 ghost printed;
     * effect: attacked target by ghost 3 hit
     * effect: mana -= 50 and hp -= 10;
     *      if this character has mana more 50 and hp more 10
     * effect: character can not use skill printed
     *      if this character has mana and hp than less 50, 10
     * @param target RPGCharacter to attack
     */
}

class Witcher extends Soldier implements Witcher_0 {
    public Witcher(String name,int level,double movespeed) {
        super(name,level,movespeed);
    }

    @Override
    public void MagicBlade() {
        if(this.getMana() - 50 >= 0) {
            this.sword_dance();
            System.out.println(this.getName() + " Enchance to MagicBlade \uD83C\uDF87 !!");
            this.setDamage(getDamage() + getMaxMana()*50/100);
            System.out.println(this.getName() + " obtains bonus damage");
            this.setMana(getMana() - 25);
        }else{
            System.out.println(this.getName() + " can't use skill");
        }
        /** skill MagicBlade of witcher
         *  effect: use skill sword_dance
         *  effect: this character " Enchance to MagicBlade \uD83C\uDF87 !!" printed
         *  effect: set new damage character
         *  effect: this character " obtains bonus damage" printed
         *      if character has mana more than 50;
         *  effect: this character can not use skill printed
         *      if character has mana tha less 50;
         */
    }

    @Override
    public void fireball(RPGCharacter target) {

        if(this.getMana() - 50 >= 0) {
            this.setDamage(50 + this.getDamage());
            System.out.println(this.getName() + " use fireball \uD83D\uDD25 !!");
            attack_skill(target, getDamage());
            this.setDamage(this.getDamage() - 50);
            this.setMana(getMana() - 50);
        }else{
            System.out.println(this.getName() + " can't use skill");
        }
    }
    /** skill fireball of mage
     * effect: set skill_damage += 50;
     * effect: "this character use skill attack" printed
     * effect: attack to target character;
     * effect: damage to normal;
     * effect: use mana -= 50;
     *   if mana has more 50;
     * effect: can not use skill
     *  if mana has than less 50;
     * @param target RPGCharacter to attack
     */
}

class Samurai extends Soldier implements Samurai_0 {
    public Samurai(String name,int level,double movespeed) {
        super(name,level,movespeed);
    }

    @Override
    public void highDamage_attack(RPGCharacter target) {
        if(this.getHp() - 50*getMaxHp()/100 > 0) {
            System.out.println(this.getName() + " use Getsuga Tenshō 1ﾒ");
            target.takeDamage(25*getMaxHp()/100 + this.getDamage() + 50*getMaxMana()/100 + this.getLevel() * 1.25 + 5);

            this.setHp(this.getHp() - 50*getMaxHp()/100);
        }else{
            System.out.println(this.getName() + " can't use skill");
        }
    }
    /** skill highDamage_attack
     * effect: this character use Getsuga Tenshō 1ﾒ printed
     * effect: boot skillDamage form damaged attack
     * effect: attack to target character;
     *      if  this character hp enough to use skill
     * effect: this character can't use skill printed
     *      if  this character hp is not enough to use skill
     */
}
