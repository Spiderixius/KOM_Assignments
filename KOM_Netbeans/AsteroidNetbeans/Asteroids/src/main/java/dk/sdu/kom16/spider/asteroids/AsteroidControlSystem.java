package dk.sdu.kom16.spider.asteroids;

import dk.sdu.kom16.spider.common.data.Entity;
import static dk.sdu.kom16.spider.common.data.EntityType.ASTEROIDS;
import dk.sdu.kom16.spider.common.data.GameData;
import dk.sdu.kom16.spider.common.services.IEntityProcessingService;
import java.util.Map;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author spider
 */
@ServiceProvider(service = IEntityProcessingService.class)
public class AsteroidControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, Map<String, Entity> world, Entity entity) {
        // TODO: Implement process
        if (entity.getType().equals(ASTEROIDS)) {
            setShape(entity);
            movement(gameData, entity);
        }
    }

    private void setShape(Entity entity) {
        entity.setShapeX(new float[8]);
        entity.setShapeY(new float[8]);

        entity.getShapeX()[0] = (float) (entity.getX() + Math.cos(entity.getRadians()) * 8);
        entity.getShapeY()[0] = (float) (entity.getY() + Math.sin(entity.getRadians()) * 8);

        entity.getShapeX()[1] = (float) (entity.getX() + Math.cos(entity.getRadians() - 4 * Math.PI / 8) * 8);
        entity.getShapeY()[1] = (float) (entity.getY() + Math.sin(entity.getRadians() - 4 * Math.PI / 8) * 8);

        entity.getShapeX()[2] = (float) (entity.getX() + Math.cos(entity.getRadians() + Math.PI) * 8);
        entity.getShapeY()[2] = (float) (entity.getY() + Math.sin(entity.getRadians() + Math.PI) * 8);

        entity.getShapeX()[3] = (float) (entity.getX() + Math.cos(entity.getRadians() + 4 * Math.PI / 8) * 8);
        entity.getShapeY()[3] = (float) (entity.getY() + Math.sin(entity.getRadians() + 4 * Math.PI / 8) * 8);

        entity.getShapeX()[4] = (float) (entity.getX() + Math.cos(entity.getRadians() + 2 * Math.PI / 8) * 8);
        entity.getShapeY()[4] = (float) (entity.getY() + Math.sin(entity.getRadians() + 2 * Math.PI / 8) * 8);

        entity.getShapeX()[5] = (float) (entity.getX() + Math.cos(entity.getRadians() + 2 * Math.PI / 8) * 8);
        entity.getShapeY()[5] = (float) (entity.getY() + Math.sin(entity.getRadians() + 2 * Math.PI / 8) * 8);

        entity.getShapeX()[6] = (float) (entity.getX() + Math.cos(entity.getRadians() - 5 * Math.PI / 8) * 8);
        entity.getShapeY()[6] = (float) (entity.getY() + Math.sin(entity.getRadians() - 5 * Math.PI / 8) * 8);

        entity.getShapeX()[7] = (float) (entity.getX() + Math.cos(entity.getRadians() + 2 * Math.PI / 8) * 8);
        entity.getShapeY()[7] = (float) (entity.getY() + Math.sin(entity.getRadians() + 2 * Math.PI / 8) * 8);
    }

    private void movement(GameData gameData, Entity entity) {

        entity.setX(entity.getX() + (entity.getDx() * gameData.getDelta()));
        entity.setY(entity.getY() + (entity.getDy() * gameData.getDelta()));

        entity.setRadians(entity.getRadians() + entity.getRotationSpeed() * gameData.getDelta());
        

    }

}
