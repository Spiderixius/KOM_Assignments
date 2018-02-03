package dk.sdu.kom16.spider.player;

import dk.sdu.kom16.spider.common.data.GameData;
import dk.sdu.kom16.spider.common.services.IEntityProcessingService;
import dk.sdu.kom16.spider.common.services.IGamePluginService;
import java.util.Arrays;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {    
@Override
public void start(BundleContext context) throws Exception {
    context.registerService(IEntityProcessingService.class, new PlayerControlSystem(), null);
    context.registerService(IGamePluginService.class, new EntityPlugin(), null); 
}

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Player bundle stopped!");
    }

}
