// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        RPGCharacter.Mage fireMage = new RPGCharacter.Mage("Mage");
        RPGCharacter.enemy Boss = new RPGCharacter.enemy("BaaBoo");
        RPGCharacter.Warrior Warrior = new RPGCharacter.Warrior("WarHam");

        fireMage.showName();
        fireMage.levelUp();
        fireMage.showStatus();

        Warrior.showName();
        Warrior.levelUp();
        Warrior.showStatus();

        fireMage.fireball(Boss);
        Boss.Showhp();
        // Create Warrior character


        // Level up Warrior and show status

        // Create Helmet accessory
        RPGAccessory.Helmet steelHelmets = new RPGAccessory.Helmet();

        Warrior.equiphelmet(steelHelmets);

        steelHelmets.isEquipped();

        Warrior.StoneWall(Boss);

        Boss.Showhp();
    }
}

