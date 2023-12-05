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

        RPGAccessory.Helmet steelHelmets = new RPGAccessory.Helmet();

        Warrior.equiphelmet(steelHelmets);

        steelHelmets.isEquipped();

        Warrior.StoneWall(Boss);

        Boss.Showhp();

        Warrior.showCurrentMana();
    }
}
