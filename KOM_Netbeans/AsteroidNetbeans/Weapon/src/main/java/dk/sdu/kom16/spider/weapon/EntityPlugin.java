/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.kom16.spider.weapon;

import dk.sdu.kom16.spider.common.data.Entity;
import static dk.sdu.kom16.spider.common.data.EntityType.BULLET;
import dk.sdu.kom16.spider.common.data.GameData;

/**
 *
 * @author spider
 */

public class EntityPlugin  {

    
    public static Entity createBullet(Entity source) {
        
        // Put bullet right infront of the Player
        float c = 10f;
        float x = source.getX() + (float) (c * Math.cos(source.getRadians()));
        float y = source.getY() + (float) (c * Math.sin(source.getRadians()));        
        
        Entity bullet = new Entity();
        bullet.setType(BULLET);
        bullet.setPosition(x, y);
        bullet.setMaxSpeed(500);
        bullet.setSpeed(250);
        bullet.setAcceleration(400);
        bullet.setRadius(2);
        bullet.setRadians(source.getRadians());
        bullet.setDx((float) (Math.cos(bullet.getRadians()) * bullet.getSpeed()));
        bullet.setDy((float) (Math.sin(bullet.getRadians()) * bullet.getSpeed()));

        return bullet;
    }
    
    
    
}
