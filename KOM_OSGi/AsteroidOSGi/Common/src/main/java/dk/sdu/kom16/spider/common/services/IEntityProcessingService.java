package dk.sdu.kom16.spider.common.services;

import dk.sdu.kom16.spider.common.data.Entity;
import dk.sdu.kom16.spider.common.data.GameData;
import java.util.Map;

/**
 *
 * @author spider
 */
public interface IEntityProcessingService {
    
    
    void process(GameData gameData, Map<String, Entity> world, Entity entity);

}