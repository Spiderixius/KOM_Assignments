package dk.sdu.kom16.spider.common.data;

import dk.sdu.kom16.spider.common.events.Event;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author spider
 */
public class GameData {
    private float delta;
    private int displayWidth;
    private int displayHeight;
    private List<Event> events = new CopyOnWriteArrayList<>();

    private final GameKeys keys = new GameKeys();
    public Entity player;
    
    public void addEvent(Event e){
        events.add(e);
    }
    
    public void removeEvent(Event e){
        events.remove(e);
    }
    
    public List<Event> getEvents(){
        return events;
    }
    
    public GameKeys getKeys() {
        return keys;
    }

    public void setDelta(float delta) {
        this.delta = delta;
    }

    public float getDelta() {
        return delta;
    }

    public void setDisplayWidth(int width) {
        this.displayWidth = width;
    }

    public int getDisplayWidth() {
        return displayWidth;
    }

    public void setDisplayHeight(int height) {
        this.displayHeight = height;
    }

    public int getDisplayHeight() {
        return displayHeight;
    }

}
