package org.ensea.student.core.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import org.ensea.student.core.displayable.DynamicSprite;

import static org.ensea.student.core.engine.RenderEngine.pe;

public class GameEngine implements Engine{
    private final DynamicSprite ds;

    public GameEngine(DynamicSprite primarySprite){
        ds = primarySprite;
    }

    @Override
    public void update() {
        handleMovement();
    }

    private void handleMovement() {
        boolean collide = pe.isColliding(ds.getSpriteRectangle());

        //TODO The idle animation always faces forward, better if it faces the direction it was
        idleAction();

        if(collide){
            ds.setX(ds.getPrevx());
            ds.setY(ds.getPrevy());
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            leftAction();
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            rightAction();
        }

        else if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            upAction();
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            downAction();
        }
    }

    private void downAction() {
        ds.setPrevy(ds.getY());
        ds.setCurrentAnimation(ds.getDownAnimation());
        ds.setCurrentDirection(DynamicSprite.direction.SOUTH);
        ds.setY(ds.getY()-Gdx.graphics.getDeltaTime() * ds.getSpeed());
    }

    private void upAction() {
        ds.setPrevy(ds.getY());
        ds.setCurrentAnimation(ds.getUpAnimation());
        ds.setCurrentDirection(DynamicSprite.direction.NORTH);
        ds.setY(ds.getY()+Gdx.graphics.getDeltaTime() * ds.getSpeed());
    }

    private void rightAction() {
        ds.setPrevx(ds.getX());
        ds.setCurrentAnimation(ds.getRightAnimation());
        ds.setCurrentDirection(DynamicSprite.direction.EAST);
        ds.setX(ds.getX()+Gdx.graphics.getDeltaTime() * ds.getSpeed());
    }

    private void leftAction() {
        ds.setPrevx(ds.getX());
        ds.setCurrentAnimation(ds.getLeftAnimation());
        ds.setCurrentDirection(DynamicSprite.direction.WEST);
        ds.setX(ds.getX()-Gdx.graphics.getDeltaTime() * ds.getSpeed());
    }

    private void idleAction() {
        if(!Gdx.input.isKeyPressed(Input.Keys.LEFT)
                && !Gdx.input.isKeyPressed(Input.Keys.RIGHT)
                && !Gdx.input.isKeyPressed(Input.Keys.UP)
                && !Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            ds.setCurrentAnimation(ds.getIdleAnimation());
        }
    }
}
