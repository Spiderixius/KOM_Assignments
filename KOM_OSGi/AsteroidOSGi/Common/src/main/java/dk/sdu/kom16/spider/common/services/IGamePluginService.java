package dk.sdu.kom16.spider.common.services;


import dk.sdu.kom16.spider.common.data.Entity;
import dk.sdu.kom16.spider.common.data.GameData;
import java.util.Map;

/**
 *
 * @author spider
 */
public interface IGamePluginService {
    
    void start(GameData gameData, Map<String, Entity> world);

    void stop(GameData gameData);
}