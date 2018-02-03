package dk.sdu.kom16.spider.asteroids;

import dk.sdu.kom16.spider.common.data.Entity;
import dk.sdu.kom16.spider.common.data.EntityType;
import dk.sdu.kom16.spider.common.data.GameData;
import dk.sdu.kom16.spider.common.services.IGamePluginService;
import java.util.Map;
import java.util.Random;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author spider
 */
@ServiceProvider(service = IGamePluginService.class)
public class EntityPlugin implements IGamePluginService{

    private Map<String, Entity> world;
    private Entity asteroid;
    
    public EntityPlugin () {
    }
    
    @Override
    public void start(GameData gameData, Map<String, Entity> world) {
        // TODO: Add entity to the world
        this.world = world;
        
        // Add asteroid entities to the world
        for(int i = 0; i < 10; i++){
            asteroid = createAsteroid(gameData);
            world.put(asteroid.getID(), asteroid);
        }
        
    }
    
    private Entity createAsteroid(GameData gameData) {
        Random rn = new Random();
 
        Entity asteroids = new Entity();
        asteroids.setType(EntityType.ASTEROIDS);
        
        //asteroids.setPosition(gameData.getDisplayWidth() / 3, gameData.getDisplayHeight() / 2);
        asteroids.setPosition((rn.nextInt(360) - 20), rn.nextInt(360) - 20);
        asteroids.setMaxSpeed(100);
        asteroids.setAcceleration(200);
        asteroids.setDeacceleration(20);
        asteroids.setRadians((float) ((Math.PI) / 2));
        asteroids.setSpeed(80);
        asteroids.setRadius(5f);
        asteroids.setDx(80-rn.nextInt(160));
        asteroids.setDy(80-rn.nextInt(160));
    
        return asteroids;
    }

    @Override
    public void stop(GameData gameData) {
        // TODO: Remove the entity from the world
        world.remove(asteroid.getID());
    }
    
}
