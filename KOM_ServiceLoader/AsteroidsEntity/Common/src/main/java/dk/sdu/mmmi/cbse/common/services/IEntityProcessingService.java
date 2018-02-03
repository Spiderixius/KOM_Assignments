package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import java.util.Map;

public interface IEntityProcessingService {

    /**
     * TODO: Describe the contract using pre- and post-conditions.
     *  SPI: 
     *      IEntityProcessingService
     *  Operation: 
     *      process(GameData gameData, Map<String, Entity> world, Entity entity)
     *  Description:
     *      Processing of the Entity into the world.
     *  Parameters:
     *      gameData - Contains different get and set for different data types 
     *                  such as getKey, get and set Delta (difference between 
     *                  two times), display settings.
     *      world - Dictionary that stores the entities.
     *      entity - collection of data representing an Entity which will be
     *                  presented in the world.
     *  Pre-condition:
     *      Entity must be present in the world dictionary.
     *  Post-condition:
     *      
     * @param gameData
     * @param world
     * @param entity
     */
    void process(GameData gameData, Map<String, Entity> world, Entity entity);
}
