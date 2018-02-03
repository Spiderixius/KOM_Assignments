package dk.sdu.mmmi.cbse.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.managers.GameInputProcessor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Game implements ApplicationListener {

    private static OrthographicCamera cam;
    private ShapeRenderer sr;

    private final GameData gameData = new GameData();
    private List<IEntityProcessingService> entityProcessors = new ArrayList<>();
    private Map<String, Entity> world = new ConcurrentHashMap<>();
    private AnnotationConfigApplicationContext ctx;
    

    @Override
    public void create() {
        ctx = new AnnotationConfigApplicationContext();
        ctx.scan("dk.sdu");
        ctx.refresh();
        
        gameData.setDisplayWidth(Gdx.graphics.getWidth());
        gameData.setDisplayHeight(Gdx.graphics.getHeight());

        cam = new OrthographicCamera(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        cam.translate(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 2);
        cam.update();

        sr = new ShapeRenderer();

        Gdx.input.setInputProcessor(
                new GameInputProcessor(gameData)
        );

        // Lookup all Game Plugins
        // check the IGamePluginService collection
        // The foreach loop simply takes game plugin services from the collection
        // and adds them to the world once (we are in the create method (duh!))
        for (IGamePluginService gamePluginService : getGamePluginService()) {
           gamePluginService.start(gameData, world);
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
    
    // Found something to plugin? 
    // Put it in the collection, later we will iterate over it in a foreach loop
    private Collection<? extends IGamePluginService> getGamePluginService() {
        return ctx.getBeansOfType(IGamePluginService.class).values();
    }

    // Found entity processing services? 
    //Put them in the collection, later we can iterate over this collection
    private Collection<? extends IEntityProcessingService> getEntityProcessingServices() {
        return ctx.getBeansOfType(IEntityProcessingService.class).values();
    }

    private void update() {
        
        // Update all entities using ServiceLoader
        // simply iterate over content of the collection with foreach loops
        // First foreach loop:
        //      Iterates over entities in world
        // Second foreach loop:
        //      Iterates over EntityProcesses in the collection
        for (Entity e : world.values()) {
            for (IEntityProcessingService entityProcessorService : getEntityProcessingServices()) {
                entityProcessorService.process(gameData, world, e);
            }
        }

    }

    private void draw() {
        for (Entity entity : world.values()) {
            float[] shapex = entity.getShapeX();
            float[] shapey = entity.getShapeY();
            if (shapex != null && shapey != null) {

                sr.setColor(1, 1, 1, 1);

                sr.begin(ShapeRenderer.ShapeType.Line);

                for (int i = 0, j = shapex.length - 1;
                        i < shapex.length;
                        j = i++) {

                    sr.line(shapex[i], shapey[i], shapex[j], shapey[j]);
                }
                sr.end();
            }
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
    }
}
