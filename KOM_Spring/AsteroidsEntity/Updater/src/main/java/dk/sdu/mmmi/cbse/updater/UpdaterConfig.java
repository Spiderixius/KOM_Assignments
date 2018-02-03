package dk.sdu.mmmi.cbse.updater;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author spider
 */
@Configuration
public class UpdaterConfig {
    @Bean
    public UpdaterImpl createUpdater() {
        UpdaterImpl u = new UpdaterImpl();
        u.start();
        return u;
    }
}
