package dk.sdu.kom16.spider.collision;

import dk.sdu.kom16.spider.common.data.Entity;
import dk.sdu.kom16.spider.common.data.GameData;
import dk.sdu.kom16.spider.common.services.IEntityProcessingService;
import java.util.Map;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author spider
 */
@ServiceProvider(service = IEntityProcessingService.class)
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
    
    public boolean testCollision(Entity source, Entity target) {
        
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

