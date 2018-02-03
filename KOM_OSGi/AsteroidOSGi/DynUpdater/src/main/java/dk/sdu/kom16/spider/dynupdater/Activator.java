package dk.sdu.kom16.spider.dynupdater;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

public class Activator implements BundleActivator {

    private BundleContext context;
    List<File> files = new ArrayList();

    @Override
    public void start(BundleContext context) throws Exception {

        System.out.println("about 10 seconds!");
        this.context = context;

        // Used to install and unistall components after a time
        ScheduledExecutorService s = Executors.newSingleThreadScheduledExecutor();
        s.scheduleAtFixedRate(dynInstaller, 1, 3, TimeUnit.SECONDS);

        ScheduledExecutorService s2 = Executors.newSingleThreadScheduledExecutor();
        s2.scheduleAtFixedRate(dynUninstaller, 1, 3, TimeUnit.SECONDS);

    }

    @Override
    public void stop(BundleContext context) throws Exception {

    }

    private Runnable dynInstaller = new Runnable() {

        @Override
        public void run() {
            try {
                File folder = new File(".//plugins");

                if (!folder.exists()) {
                    folder.mkdir();
                }

                for (File file : folder.listFiles()) {
                    if (file.getName().endsWith(".jar")) {
                        Bundle b = context.installBundle(file.toURI().toString());
                        b.start();
                        files.add(file);
                    }
                }
            } catch (BundleException e) {
                System.out.println("Exception caught...:" + e);
            }
        }
    };

    private Runnable dynUninstaller = new Runnable() {
        @Override
        public void run() {
            try {
                File folder = new File(".//plugins");

                if (!folder.exists()) {
                    folder.mkdir();
                }

                for (File file : files) {
                    if (!file.exists()) {
                        Bundle b = context.installBundle(file.toURI().toString());
                        b.stop();
                        files.remove(file);
                        b.uninstall();
                    }
                }
            } catch (BundleException e) {
                System.out.println("Exception caught..:" + e);
            }
        }
    };

}