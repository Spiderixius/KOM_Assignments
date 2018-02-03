package dk.sdu.kom16.spider.player;

import dk.sdu.kom16.spider.common.data.Entity;
import static dk.sdu.kom16.spider.common.data.EntityType.PLAYER;
import dk.sdu.kom16.spider.common.data.GameData;
import dk.sdu.kom16.spider.common.data.GameKeys;
import dk.sdu.kom16.spider.common.services.IEntityProcessingService;
import java.util.Map;

/**
 *
 * @author spider
 */

public class PlayerControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, Map<String, Entity> world, Entity entity) {
        
        // TODO: Implement entity processor
                
        if(entity.getType().equals(PLAYER)){
            setShape(entity);
            movement(gameData, entity);   
        }
    }
    
    private void setShape(Entity entity) {
        entity.setShapeX(new float[4]);
        entity.setShapeY(new float[4]);

        entity.getShapeX()[0] = (float) (entity.getX() + Math.cos(entity.getRadians()) * 8);
        entity.getShapeY()[0] = (float) (entity.getY() + Math.sin(entity.getRadians()) * 8);

        entity.getShapeX()[1] = (float) (entity.getX() + Math.cos(entity.getRadians() - 4 * Math.PI / 5) * 8);
        entity.getShapeY()[1] = (float) (entity.getY() + Math.sin(entity.getRadians() - 4 * Math.PI / 5) * 8);

        entity.getShapeX()[2] = (float) (entity.getX() + Math.cos(entity.getRadians() + Math.PI) * 5);
        entity.getShapeY()[2] = (float) (entity.getY() + Math.sin(entity.getRadians() + Math.PI) * 5);

        entity.getShapeX()[3] = (float) (entity.getX() + Math.cos(entity.getRadians() + 4 * Math.PI / 5) * 8);
        entity.getShapeY()[3] = (float) (entity.getY() + Math.sin(entity.getRadians() + 4 * Math.PI / 5) * 8);
    }
    
    private void movement(GameData gameData, Entity entity) {
        
        // Accelerate up!
        if (gameData.getKeys().isDown(GameKeys.UP)) {
            entity.setDx(entity.getDx() + (float)Math.cos(entity.getRadians()) * entity.getAcceleration() * gameData.getDelta());
            entity.setDy(entity.getDy() + (float)Math.sin(entity.getRadians()) * entity.getAcceleration() * gameData.getDelta());            
        }
        
        // deceleration
        float vec = (float) Math.sqrt(entity.getDx() * entity.getDx() + entity.getDy() * entity.getDy());
        if (vec > 0) {
            entity.setDx(entity.getDx() - (entity.getDx() / vec) * entity.getDeacceleration() * gameData.getDelta());
            entity.setDy(entity.getDy() - (entity.getDy() / vec) * entity.getDeacceleration() * gameData.getDelta());
        }
        
        if (vec > entity.getMaxSpeed()) {
            //entity.setDx(entity.getDx() + (entity.getDx() * gameData.getDelta()));
            //entity.setDy(entity.getDx() + (entity.getDy() * gameData.getDelta()));
            
            entity.setDx(entity.getDx() + ((entity.getDx() / vec)) * entity.getMaxSpeed() );
            entity.setDy(entity.getDy() + ((entity.getDy() / vec)) * entity.getMaxSpeed() );

        }
        // Turning Left/Right
        if (gameData.getKeys().isDown(GameKeys.LEFT)) {
            entity.setRadians(entity.getRadians() + entity.getRotationSpeed() * gameData.getDelta());
        } else if(gameData.getKeys().isDown(GameKeys.RIGHT)) {
            entity.setRadians(entity.getRadians() - entity.getRotationSpeed() * gameData.getDelta());
        }
        
        // Set Position
        entity.setPosition(entity.getX() + entity.getDx() * gameData.getDelta(), 
                    entity.getY() + entity.getDy() * gameData.getDelta());
        
    }

}