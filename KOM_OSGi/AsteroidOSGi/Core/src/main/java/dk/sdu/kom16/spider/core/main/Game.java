package dk.sdu.kom16.spider.core.main;

/**
 *
 * @author spider
 */

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dk.sdu.kom16.spider.common.data.Entity;
import static dk.sdu.kom16.spider.common.data.EntityType.ENEMY;
import dk.sdu.kom16.spider.common.data.GameData;
import dk.sdu.kom16.spider.common.services.IEntityProcessingService;
import dk.sdu.kom16.spider.common.services.IGamePluginService;
import dk.sdu.kom16.spider.core.managers.GameInputProcessor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;

public class Game implements ApplicationListener {

    private static OrthographicCamera cam;
    private ShapeRenderer sr;

    private final GameData gameData = new GameData();
    private Map<String, Entity> world = new ConcurrentHashMap<>();
    private final Lookup lookup = Lookup.getDefault();
    private List<IGamePluginService> gamePlugins;

    @Override
    public void create() {
       
        // Add Lookup listener
        Lookup.Result<IGamePluginService> result = lookup.lookupResult(IGamePluginService.class);
        result.addLookupListener(lookupListener);
        gamePlugins = new ArrayList<IGamePluginService>(result.allInstances());
        result.allItems();
        
        // Game
        gameData.setDisplayWidth(Gdx.graphics.getWidth());
        gameData.setDisplayHeight(Gdx.graphics.getHeight());

        cam = new OrthographicCamera(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        cam.translate(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 2);
        cam.update();

        sr = new ShapeRenderer();

        Gdx.input.setInputProcessor(
                new GameInputProcessor(gameData)
        );

        // Lookup all Game Plugins using ServiceLoader
        for (IGamePluginService iGamePlugin : gamePlugins) {
            iGamePlugin.start(gameData, world);
        }
    }

    @Override
    public void render() {

        // clear screen to black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameData.setDelta(Gdx.graphics.getDeltaTime());

        update();

        draw();

        gameData.getKeys().update();
    }

    private void update() {
        // Update
        for (IEntityProcessingService entityProcessorService : getEntityProcessingServices()) {
            for (Entity e : world.values()) {
                entityProcessorService.process(gameData, world, e);
                e.wrapping(e, gameData);
            }
        }
    }
    
//    private void updateGamePlugins(IGamePluginService iGamePlugin) {
//    // Lookup all Game Plugins using ServiceLoader
//        
//    }

    private void draw() {
        for (Entity entity : world.values()) {

            sr.setColor(1, 1, 1, 1);
            if (entity.getType().equals(ENEMY)) {
                sr.setColor(Color.RED);
            }
            sr.begin(ShapeRenderer.ShapeType.Line);

            float[] shapex = entity.getShapeX();
            float[] shapey = entity.getShapeY();

            for (int i = 0, j = shapex.length - 1;
                    i < shapex.length;
                    j = i++) {

                sr.line(shapex[i], shapey[i], shapex[j], shapey[j]);
            }

            sr.end();
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        sr.dispose();
    }
    
    private Collection<? extends IEntityProcessingService> getEntityProcessingServices() {
        return lookup.lookupAll(IEntityProcessingService.class);
    }
    
    private final LookupListener lookupListener = new LookupListener() {
        @Override
        public void resultChanged(LookupEvent le) {
            for (IGamePluginService updatedGamePlugin : lookup.lookupAll(IGamePluginService.class)) {
                if (!gamePlugins.contains(updatedGamePlugin)) {
                    updatedGamePlugin.start(gameData, world);
                    gamePlugins.add(updatedGamePlugin);
                }
            }
        }
    };
}