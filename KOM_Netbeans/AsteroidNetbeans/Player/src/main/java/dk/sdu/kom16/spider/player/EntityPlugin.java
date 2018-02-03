package dk.sdu.kom16.spider.player;

import dk.sdu.kom16.spider.common.data.Entity;
import static dk.sdu.kom16.spider.common.data.EntityType.PLAYER;
import dk.sdu.kom16.spider.common.data.GameData;
import dk.sdu.kom16.spider.common.services.IGamePluginService;
import java.util.Map;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author spider
 */
@ServiceProvider(service = IGamePluginService.class)
public class EntityPlugin implements IGamePluginService {

    private Map<String, Entity> world;
    private Entity player;

    public EntityPlugin() {
    }

    @Override
    public void start(GameData gameData, Map<String, Entity> world) {

        this.world = world;
        // Add entities to the world
        player = createPlayerShip(gameData);
        gameData.player = player;
        world.put(player.getID(), player);
    }

    private Entity createPlayerShip(GameData gameData) {

        Entity playerShip = new Entity();
        playerShip.setType(PLAYER);

        playerShip.setPosition(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 2);

        playerShip.setMaxSpeed(300);
        playerShip.setAcceleration(200);
        playerShip.setDeacceleration(20);
        playerShip.setRadians(3.1415f / 2);
        playerShip.setRotationSpeed(3);
        playerShip.setRadius(3);

        return playerShip;
    }

    @Override
    public void stop(GameData gameData) {
        // Remove entities
        world.remove(player.getID());
    }

}
