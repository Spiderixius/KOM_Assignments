
package dk.sdu.kom16.spider.enemy;

import dk.sdu.kom16.spider.common.data.Entity;
import static dk.sdu.kom16.spider.common.data.EntityType.ENEMY;
import dk.sdu.kom16.spider.common.data.GameData;
import dk.sdu.kom16.spider.common.services.IGamePluginService;
import java.util.Map;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author spider
 */
@ServiceProvider(service = IGamePluginService.class)
public class EntityPlugin implements IGamePluginService{
    private Map<String, Entity> world;
    private Entity enemy;
    
    public EntityPlugin() {
    }
    
    @Override
    public void start(GameData gameData, Map<String, Entity> world) {
        this.world = world;
        // Add enemy to the world
        enemy = createEnemyShip(gameData);
        world.put(enemy.getID(), enemy);
    }

    @Override
    public void stop(GameData gameData) {
        world.remove(enemy.getID());
    }

    private Entity createEnemyShip(GameData gameData) {
        Entity enemyShip = new Entity();
        enemyShip.setType(ENEMY);

        enemyShip.setPosition(gameData.getDisplayWidth() / 2.5f, gameData.getDisplayHeight() / 2);

        enemyShip.setMaxSpeed(300);
        enemyShip.setAcceleration(200);
        enemyShip.setDeacceleration(20);
        enemyShip.setRadians(3.1415f / 2);
        enemyShip.setRotationSpeed(3);
        enemyShip.setRadius(3);

        return enemyShip;
    }
    
}
