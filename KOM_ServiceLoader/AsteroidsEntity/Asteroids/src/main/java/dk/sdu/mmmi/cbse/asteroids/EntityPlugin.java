/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.asteroids;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import java.util.Map;
import java.util.Random;


/**
 *
 * @author spider
 */
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
        asteroids.setRadius(3f);
//        asteroids.setDx(80-rn.nextInt(160));
//        asteroids.setDy(80-rn.nextInt(160));
    
        return asteroids;
    }

    @Override
    public void stop(GameData gameData) {
        // TODO: Remove the entity from the world
        world.remove(asteroid.getID());
    }
    
}
