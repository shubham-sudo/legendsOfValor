package PubSub;

import creature.Creature;
import creature.Hero;
import creature.Monster;
import game.LegendsOfValor;
import map.Position;
import map.lane.Lane;
import map.space.Space;


/**
 * One round effects observer
 */
public class OneRoundObserver implements GameObserver{

    /**
     * clean the space and revive hero if fainted
     * @param attacker attacker creature
     * @param opponent opponent creature
     * @param game game
     */
    private void spaceCleanWithRevive(Creature attacker, Creature opponent, LegendsOfValor game){
        if (!opponent.isAlive()) {
            Position position = opponent.getCurrentPosition();
            game.getMap().getLane(position.laneNumber).vacantSpace(opponent, position.rowNumber, position.colNumber);
            if (opponent instanceof Hero) {
                ((Hero) opponent).revive();
                int homeLane = opponent.getHomeLane();
                Lane lane = game.getMap().getLane(homeLane);
                for (int i = lane.getLength()-1; i > -1; i--){
                    try {
                        lane.occupySpace(opponent, lane.getLength()-1, i);
                        break;
                    } catch (IllegalAccessException ie) {
                        // ie.printStackTrace();
                    }
                }
            } else if (opponent instanceof Monster && attacker instanceof Hero) {
                Hero hero = (Hero) attacker;
                hero.gainExperience(2);
                hero.gainGold(attacker.getLevel() * 100);
                game.increaseRound();
            }
        } else {
            if (attacker instanceof Hero) {
                Hero hero = (Hero) attacker;
                hero.regain();
            }
        }
    }

    /**
     * apply effected of one round
     * @param creature attacker creature
     * @param opponent opponent creature
     * @param space space would be null
     */
    private void applyOneRoundEffects(Creature creature, Creature opponent, Space space){
        LegendsOfValor game = LegendsOfValor.getGameInstance();
        spaceCleanWithRevive(creature, opponent, game);
    }

    /**
     * Check if it is time to spawn new monster in the game
     */
    private void checkForNewMonsterSpawn() {
        LegendsOfValor game = LegendsOfValor.getGameInstance();
        if (game.rounds() % 8 == 0) {
            try {
                game.spawnMonsters();
                game.resetRound();
            } catch (IllegalAccessException ie) {
                // ie.printStackTrace();
            }
        }
    }

    /**
     *
     * @param creature hero object itself
     * @param opponent opponent in this round
     * @param space may be null but can be space object
     */
    @Override
    public void notifying(Creature creature, Creature opponent, Space space) {
        if (creature != null && opponent != null){
            applyOneRoundEffects(creature, opponent, space);
        }
        checkForNewMonsterSpawn();
    }
}
