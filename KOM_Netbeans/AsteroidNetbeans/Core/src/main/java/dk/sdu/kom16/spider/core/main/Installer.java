package dk.sdu.kom16.spider.core.main;

/**
 *
 * @author spider
 */
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.openide.modules.ModuleInstall;

public class Installer extends ModuleInstall {

    @Override
    public void restored() {

        LwjglApplicationConfiguration cfg
                = new LwjglApplicationConfiguration();
        cfg.title = "Asteroids";
        cfg.width = 500;
        cfg.height = 400;
        cfg.useGL30 = false;
        cfg.resizable = true;

        new LwjglApplication(new Game2(), cfg);

    }

}
