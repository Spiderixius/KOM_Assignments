/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.kom16.spider.collision;

import dk.sdu.kom16.spider.common.data.Entity;
import dk.sdu.kom16.spider.common.data.GameData;
import java.util.Map;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author spider
 */
public class CollisionSystemTest {
    
    public CollisionSystemTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of process method, of class CollisionSystem.
     */
    @Test
    public void testProcess() {
        Entity player, enemy;
        player = new Entity();
        player.setPosition(10, 10);
        enemy = new Entity();
        enemy.setPosition(10, 10);
        
        
        System.out.println("\nProcess 1");
        CollisionSystem instance = new CollisionSystem();
        System.out.println("Player position x: " + player.getX() + " y: " + player.getY());
        System.out.println("Enemy position x: " + enemy.getX() + " y: " + enemy.getY());
        boolean result = instance.testCollision(enemy, player);
        if (result) {
           assertEquals(true, result);
            System.out.println("Player has collided with Enemy.");
        }
        
        System.out.println("\n");
        System.out.println("Process 2");
        player.setPosition(50, 50);
        enemy.setPosition(30, 30);
        System.out.println("Player position x: " + player.getX() + " y: " + player.getY());
        System.out.println("Enemy position x: " + enemy.getX() + " y: " + enemy.getY());

        result = instance.testCollision(enemy, player);
        if (result == false) {
            assertEquals(false, result);
            System.out.println("Player has not collided with Enemy");
            
        }
        
    }
    
}
