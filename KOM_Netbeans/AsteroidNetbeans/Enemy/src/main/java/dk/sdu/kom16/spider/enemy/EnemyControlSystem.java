package dk.sdu.kom16.spider.enemy;

import dk.sdu.kom16.spider.common.data.Entity;
import static dk.sdu.kom16.spider.common.data.EntityType.ENEMY;
import dk.sdu.kom16.spider.common.data.GameData;
import dk.sdu.kom16.spider.common.services.IEntityProcessingService;
import java.util.Map;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author spider
 */
@ServiceProvider(service = IEntityProcessingService.class)
public class EnemyControlSystem implements IEntityProcessingService {

    private int turnDirection = 1;

    @Override
    public void process(GameData gameData, Map<String, Entity> world, Entity entity) {
        if (entity.getType().equals(ENEMY)) {
            setShape(entity);
            movement(gameData, entity);
        }
    }

    private void setShape(Entity entity) {
        entity.setShapeX(new float[6]);
        entity.setShapeY(new float[6]);

        entity.getShapeX()[0] = (entity.getX() - 10);
        entity.getShapeY()[0] = (entity.getY());
        
        entity.getShapeX()[1] = (entity.getX() - 3);
        entity.getShapeY()[1] = (entity.getY() - 5);
        
        entity.getShapeX()[2] = (entity.getX() + 3);
        entity.getShapeY()[2] = (entity.getY() - 5);
        
        entity.getShapeX()[3] = (entity.getX() + 10);
        entity.getShapeY()[3] = (entity.getY());
        
        entity.getShapeX()[4] = (entity.getX() + 3);
        entity.getShapeY()[4] = (entity.getY() + 5);
        
        entity.getShapeX()[5] = (entity.getX() - 3);
        entity.getShapeY()[5] = (entity.getY() + 5);
        
    }

    private void movement(GameData gameData, Entity entity) {
        if (Math.random() < 0.2) {
            turnDirection = -turnDirection;
        }
        entity.setRadians((float) (entity.getRadians() + (turnDirection * 0.1)));
        
        entity.setX(entity.getX() + (float) Math.cos(entity.getRadians() * 2) );
        entity.setY(entity.getY() + (float) Math.sin(entity.getRadians() * 2) ); 

    }

}
