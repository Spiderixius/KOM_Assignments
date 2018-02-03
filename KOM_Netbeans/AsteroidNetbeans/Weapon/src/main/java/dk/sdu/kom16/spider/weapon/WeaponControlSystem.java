/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.kom16.spider.weapon;

import dk.sdu.kom16.spider.common.data.Entity;
import static dk.sdu.kom16.spider.common.data.EntityType.BULLET;
import static dk.sdu.kom16.spider.common.data.EntityType.PLAYER;
import static dk.sdu.kom16.spider.common.data.EntityType.WEAPON;
import dk.sdu.kom16.spider.common.data.GameData;
import dk.sdu.kom16.spider.common.data.GameKeys;
import dk.sdu.kom16.spider.common.events.Event;
import dk.sdu.kom16.spider.common.events.EventType;
import dk.sdu.kom16.spider.common.services.IEntityProcessingService;
import java.util.Map;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author spider
 */
@ServiceProvider(service = IEntityProcessingService.class)
public class WeaponControlSystem implements IEntityProcessingService {
  
    
    
    @Override
    public void process(GameData gameData, Map<String, Entity> world, Entity entity) {

        for (Event e : gameData.getEvents()) {
            Entity bullet = EntityPlugin.createBullet(gameData.player);
            world.put(bullet.getID(), bullet);
            gameData.removeEvent(e);
        }
        
        if (entity.getType().equals(BULLET)) {
            movement(gameData, entity);
            //expiration(entity, world, gameData);
        }
    }
    
    private void movement(GameData gameData, Entity entity) {
        entity.setX(entity.getX() + (entity.getDx() * gameData.getDelta()));
        entity.setY(entity.getY() + (entity.getDy() * gameData.getDelta())); 
    }
    
    private void expiration(Entity entity, Map<String, Entity> world, GameData gameData) {
//        int lifeTime = 1;
//        int lifeTimer = 0;
//        
//        lifeTime += gameData.getDelta();
//        if (lifeTimer > lifeTime) {
//            world.remove(entity.getID());
//        }
        
    }
}
 
