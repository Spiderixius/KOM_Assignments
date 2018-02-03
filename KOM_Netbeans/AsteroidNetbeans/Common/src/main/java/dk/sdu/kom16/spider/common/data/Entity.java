package dk.sdu.kom16.spider.common.data;

/**
 *
 * @author spider
 */

import static dk.sdu.kom16.spider.common.data.EntityType.BULLET;
import java.io.Serializable;
import java.util.UUID;

public final class Entity implements Serializable {

    private final UUID ID = UUID.randomUUID();
    private EntityType type;
    private float x;
    private float y;
    private float dx;
    private float dy;
    private float radians;
    private float maxSpeed;
    private float acceleration;
    private float deacceleration;
    private float[] shapeX = new float[4];
    private float[] shapeY = new float[4];
    private int rotationSpeed;
    private float radius;
    private float speed;

    public Entity(){
        
    }
    public Entity(float x, float y) {
        this.x = x;
        this.y = y;
        
    }
    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public String getID() {
        return ID.toString();
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
    
    public void setType(EntityType type) {
        this.type = type;
    }

    public EntityType getType() {
        return type;
    }

    public float getDx() {
        return dx;
    }

    public void setDx(float dx) {
        this.dx = dx;
    }

    public float getDy() {
        return dy;
    }

    public void setDy(float dy) {
        this.dy = dy;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getRadians() {
        return radians;
    }

    public void setRadians(float radians) {
        this.radians = radians;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public float getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }

    public float getDeacceleration() {
        return deacceleration;
    }

    public void setDeacceleration(float deacceleration) {
        this.deacceleration = deacceleration;
    }

    public float[] getShapeX() {
        return shapeX;
    }

    public void setShapeX(float[] shapeX) {
        this.shapeX = shapeX;
    }

    public float[] getShapeY() {
        return shapeY;
    }

    public void setShapeY(float[] shapeY) {
        this.shapeY = shapeY;
    }

    public int getRotationSpeed() {
        return rotationSpeed;
    }

    public void setRotationSpeed(int rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }
    
    // Wrap entities if they reach the border
    public void wrapping(Entity e, GameData gameData) {
        if (!e.getType().equals(BULLET)) {
            if (e.getX() < 0) e.setX(gameData.getDisplayWidth());
            if (e.getX() > gameData.getDisplayWidth()) e.setX(0);
            if (e.getY() < 0) e.setY(gameData.getDisplayHeight());
            if (e.getY() > gameData.getDisplayHeight()) e.setY(0);
        }
        
    }

}
