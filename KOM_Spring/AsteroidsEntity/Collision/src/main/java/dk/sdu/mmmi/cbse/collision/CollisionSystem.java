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
        
        boolean isRemoved;
        for (Entity target : world.values()) {
            isRemoved = false;
            if(!(source.equals(target)) && testCollision(source, target)) {
                world.remove(source.getID());
                isRemoved = true;

                System.out.println(source.getType() + " collided with " + target.getType());
            }
            if (isRemoved) {
                world.remove(target.getID());
                continue;
            }
            
        }
    }
    
    private boolean testCollision(Entity source, Entity target) {
        
        float dx = source.getX() - target.getX();
        float dy = source.getY() - target.getY();
        
        double dist = Math.sqrt((dx * dx) + (dy * dy));

        boolean isCollision = dist <= source.getRadius()+ target.getRadius();
        if (isCollision) {
            System.out.println("COLLISION DETECTED!");   
        }
  
        return isCollision;
    }
    


}
