package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import java.util.Map;

public interface IGamePluginService {

    /**
     * TODO: Describe the contract using pre- and post-conditions.
     *  SPI:
     *      IGamePluginService
     *  Operation:
     *      start(GameData gameData, Map<String, Entity> world)
     *  Description: 
     *      Initiates the world and the entities belonging to it.
     *  Parameters:
     *      gameData - Contains different get and set for different data types 
     *                  such as getKey, get and set Delta (difference between 
     *                  two times), display settings. 
     *      world - Dictionary that stores the entities. 
     *                  String: The id
     *                  Entity: the entity
     *  Pre-condition:
     *      World has to be present
     *  Post-condition:
     *      Entities have been added to the map dictionary.
     * 
     * @param gameData
     * @param world
     */
    void start(GameData gameData, Map<String, Entity> world);

    /**
     * TODO: Describe the contract using pre- and post-conditions.
     *  
     *  SPI: 
     *      IGamePluginService
     *  Operation:
     *      stop()
     *  Description:
     *      Removes the entity from the map dictionary.
     *  Parameters:
     *      GameData - Contains different get and set for different data types 
     *                  such as getKey, get and set Delta (difference between 
     *                  two times), display settings.
     *      world - Dictionary that stores the entities.
     *  Pre-condition:
     *      An entity must have been added to the map, which can be stopped.
     *  Post-condition:
     *      Entity has been removed from the map.
     * 
     * @param gameData
     */
    void stop(GameData gameData);
}
