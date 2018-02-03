/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.collision;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import java.util.Map;

/**
 *
 * @author spider
 */
public class CollisionSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, Map<String, Entity> world, Entity source) {
        for (Entity target : world.values()) {
            if(!(source.equals(target)) && testCollision(source, target)) {
                System.out.println("We got contact!");
            }
        }
    }
    
    private boolean testCollision(Entity source, Entity target) {
        
        float dx = source.getX() - target.getX();
        float dy = source.getY() - target.getY();
        
        double dist = Math.sqrt((dx * dx) + (dy * dy));

        boolean isCollision = dist <= source.getRadius()+ target.getRadius();
        int count = 0;
        if (isCollision) {
            count++;
            System.out.println("COLLISION DETECTED!" + count);   
        }
  
        return isCollision;
    }
    


}
