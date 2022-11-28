package PubSub;

import creature.Creature;
import creature.Hero;
import creature.Monster;
import factory.CreaturesFactory;
import game.LegendsOfValor;
import map.Position;
import map.lane.Lane;
import map.space.Space;


public class BattleOneRoundObserver implements GameObserver{
    private static final CreaturesFactory creaturesFactory = new CreaturesFactory();

    private void spaceCleanWithRevive(Creature creature, Creature opponent, LegendsOfValor game){
        if (!creature.isAlive()) {
            Position position = creature.getCurrentPosition();
            game.getMap().getLane(position.laneNumber).vacantSpace(creature, position.rowNumber, position.colNumber);
            if (creature instanceof Hero) {
                ((Hero) creature).revive();
                int homeLane = creature.getHomeLane();
                Lane lane = game.getMap().getLane(homeLane);
                for (int i = 0; i < lane.getWidth(); i++){
                    try {
                        lane.occupySpace(creature, lane.getLength()-1, i);
                        break;
                    } catch (IllegalAccessException ie) {
                        // ie.printStackTrace();
                    }
                }
            } else if (creature instanceof Monster && opponent instanceof Hero) {
                Hero hero = (Hero) opponent;
                hero.gainExperience(2);
                hero.gainGold(creature.getLevel() * 100);
            }
        } else {
            if (creature instanceof Hero) {
                Hero hero = (Hero) creature;
                hero.regain();
            }
        }
    }

    private void applyOneRoundEffects(Creature creature, Creature opponent, Space space){
        LegendsOfValor game = LegendsOfValor.getGameInstance();
        spaceCleanWithRevive(creature, opponent, game);  // TODO If monster died increase the experience and gold of creature
        spaceCleanWithRevive(opponent, creature, game);
    }

    private void checkForNewMonsterSpawn() {
        LegendsOfValor game = LegendsOfValor.getGameInstance();
        if (game.rounds() % 8 == 0) {
            try {
                game.spawnMonsters();
            } catch (IllegalAccessException ie) {
                // ie.printStackTrace();
            }
        }
    }

    @Override
    public void notifying(Creature creature, Creature opponent, Space space) {
        applyOneRoundEffects(creature, opponent, space);
    }
}
