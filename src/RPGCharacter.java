import java.text.DecimalFormat;

public class RPGCharacter {
    protected String name;
    protected double atk;
    protected int def;
    protected int level;
    protected int maxHP;
    protected int currentHP;
    protected int maxMana;
    protected int currentMana;
    protected double baseRunSpeed;
    protected double maxRunSpeed;
    protected boolean equippedBoot = false;
    protected boolean equippedbracelet = false;
    protected boolean equippedHelmet = false;
    protected boolean equippedGauntlet = false;
    protected boolean CritOn = false;
    protected double Critdmg;
    protected boolean DoubleAttack = false;
    protected int countAtk;
    protected int beAttackedCount;
    protected boolean stackAttackedOn = false;
    protected boolean protectlife = false;
    protected int maxHpEnemy;
    protected int currentEnemyHp;
    public void levelUp() {
        level++;
        updateStat();
        
    }

    public void takeDamage(double dmg) {
        if(protectlife){
            dmg = 0;
            currentHP -= dmg;
        }else{
            currentHP -= (int) dmg;
            if (currentHP < 0) {
                currentHP = 0;
            }

        }
        beAttackedCount++;

    }

    public void showName() {

    }

    public void showStatus(){
        System.out.println("Attack : " + atk);
        System.out.println("Defend : " + def);
    };
    public void ShowHMR(){
        System.out.println("HP Gauge = " + maxHP);
        System.out.println("Mana Gauge = " + maxMana);
        System.out.println("RunSpeed = " + maxRunSpeed);
        System.out.println();
    };
    public void showCurrentHP(){
        System.out.println("Current HP: " + currentHP + "/" + maxHP);
    }
    public void showCurrentMana(){
        System.out.println("Current Mana: " + currentMana + "/" + maxMana);
    };
    public void updateRunSpeed(int n){
        maxRunSpeed -= n;
        System.out.println("Run speed updated.");
    };
    protected void updateStat(){

    };
    public double calculateMaxSpeed(){
        return Double.parseDouble(new DecimalFormat("##.##").format(baseRunSpeed * (0.1 + 0.02 * level)));
    };
    public int getLevel(){
        return level;
    };

    public void attack(enemy a,int dmg){
        if(CritOn){
            dmg *= Critdmg;
        }
        if(stackAttackedOn){
            dmg *= 1.20;
        }
        a.currentHP -= dmg;
        if(DoubleAttack){
            a.currentHP -= (int)(dmg*0.8);
        }
        currentEnemyHp = a.currentHP;
        countAtk++;
    }

    public void equipBoot(RPGAccessory.SpeedyBoots x){
        x.equipped = true;
        equippedBoot = true;

        DoubleAttack = x.DoubleAttack((int)maxRunSpeed);
        if(x.boostSpeed(countAtk)){
            maxRunSpeed *= 1.40;
        }

    }
    public void equiphelmet(RPGAccessory.Helmet x){
        x.equipped = true;
        equippedHelmet = true;

        CritOn = x.RandomCritDamage();
        Critdmg = x.CalculatedCritDamage(CritOn);
    }
    public void equipBracelet(RPGAccessory.Bracelets x){
        x.equipped = true;
        equippedbracelet = true;

        protectlife = x.Protectedlife(maxHP,currentHP);
        if(x.ManaBoost(maxMana,currentMana)){
            if(maxMana == currentMana){
                maxMana *= 1.50;
                currentMana = maxMana;
            }else{
                maxMana *= 1.50;
                currentMana *= 1.50;


            }
        }
    }
    public void equipGauntlet(RPGAccessory.Gauntlets x){
        x.equipped = true;
        equippedGauntlet = true;

        atk *= x.BoostAttack(maxHpEnemy,currentEnemyHp);
        if(x.StackAttacked(beAttackedCount)){
            stackAttackedOn = true;
        }
    }



    public static class Mage extends RPGCharacter {
        public Mage(String name) {
            this.name = name;
            this.level = 1;
            baseRunSpeed = 10;
            updateStat();
        }
        @Override
        public void showName() {
            System.out.println("Mage: " + name);
        }


        @Override
        protected void updateStat(){
            maxHP = 100 + 10 * level;
            maxMana = 50 + 5 * level;
            atk = 10 + 2 * level;
            def = 6 + 2 * level;
            maxRunSpeed = calculateMaxSpeed();
            currentHP = maxHP;
            currentMana = maxMana;
        };

        public void fireball(enemy a){
            if(currentMana < 15){
                System.out.println("require mana at least 15. your mana not enough to use this");
            }else{
                System.out.println("Fireball incant activates");
                System.out.println("take 12 damage");
                attack(a,12);
                currentMana -= 15;
            }
        }

        public void CallingTides(enemy a){
            if(currentMana < 22){
                System.out.println("require mana at least 22. your mana not enough to use this");
            }else{
                System.out.println("CallingTides incant activates");
                System.out.println("take 25 damage");
                attack(a,25);
                currentMana -= 22;
            }
        }

        public void PathofTheScholar(){
            if(currentMana < 10){
                System.out.println("require mana at least 10. your mana not enough to use this");
            }else{
                System.out.println("PathofTheScholar incant activates");
                if(maxHP == currentHP){
                    maxHP *= (int) (120.0 /100);
                    currentHP = maxHP;
                }else{
                    maxHP *= (int) (120.0 /100);
                    currentMana *= 1.20;


                }

                if(maxMana == currentMana){
                    maxMana *= (int) (120.0 /100);
                    currentMana = maxMana;
                }else{
                    maxMana *= (int) (120.0 /100);
                }
                atk *= (int) (120.0 /100);
                def *= (int) (120.0 /100);

                currentMana -= 10;
            }
        }
    }


    public static class Warrior extends RPGCharacter {
        private boolean GuardianOn = false;
        public Warrior(String name) {
            this.name = name;
            this.level = 1;
            this.baseRunSpeed = 7;
            updateStat();
        }

        @Override
        public void showName() {
            System.out.println("Warrior: " + name);
        }

        @Override
        public void updateStat() {
            maxHP = 150 + 15 * level;
            maxMana = 40 + 2 * level;
            atk = 6 + 2 * level;
            def = 13 + 3 * level;
            maxRunSpeed = calculateMaxSpeed();
            currentHP = maxHP;
            currentMana = maxMana;
        }
        @Override
        public void takeDamage(double dmg) {

            if(protectlife){
                dmg = 0;
                currentHP -= dmg;
            }else{

                if(!GuardianOn){
                    currentHP -= (int) dmg;
                }else{
                    currentHP -= (int)(dmg * 0.60);
                }
                if (currentHP < 0) {
                    currentHP = 0;
                }
                beAttackedCount++;

            }
        }

        public void StoneWall(enemy a){
            if(currentMana < 15){
                System.out.println("require mana at least 15. your mana not enough to use this");
            }else{
                System.out.println("StoneWall incant activates");
                System.out.println("take 12 damage");
                attack(a,12);
                currentMana -= 15;
            }
        }

        public void TheDefender(enemy a){
            if(currentMana < 8){
                System.out.println("require mana at least 8. your mana not enough to use this");
            }else{
                System.out.println("TheDefender incant activates");
                System.out.println("increase def 50%");
                def *= (150/100);
                currentMana -= 8;
            }
        }

        public void guardian(enemy a){
            if(currentMana < 10){
                System.out.println("require mana at least 10. your mana not enough to use this");
            }else{
                System.out.println("guardian incant activates");
                System.out.println("prevent attack for one hit");
                GuardianOn = true;
                currentMana -= 10;
            }
        }
    }

    public static class enemy extends RPGCharacter{
        private final String name;
        private double atk = 5;
        private int def = 6;
        private int maxHP = 200;
        private int currentHP= maxHP;

        public enemy(String name){
            this.name = name;
            maxHpEnemy = maxHP;
        }

        public void Showhp(){
            System.out.println(name + " : " + currentHP);
        }
    }
}
