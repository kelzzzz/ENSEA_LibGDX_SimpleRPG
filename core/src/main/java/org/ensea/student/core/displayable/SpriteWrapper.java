package org.ensea.student.core.displayable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class SpriteWrapper implements Displayable{
    protected double height;
    protected double width;
    protected float xPos;
    protected float yPos;
    protected Texture texture;
    protected final SpriteBatch batch;

    public SpriteWrapper(String textureFileName, float x, float y){
        this.texture = new Texture(Gdx.files.internal(textureFileName));
        this.batch = new SpriteBatch();
        this.xPos = x;
        this.yPos = y;
        this.height = texture.getHeight();
        this.width = texture.getWidth();
    }

    public SpriteWrapper(Texture texture, float x, float y){
        this.texture = texture;
        this.batch = new SpriteBatch();
        this.xPos = x;
        this.yPos = y;
        this.height = texture.getHeight();
        this.width = texture.getWidth();
    }

    public SpriteWrapper(){
        this.batch = new SpriteBatch();
    }

    @Override
    public void draw() {
        this.batch.begin();
        this.batch.draw(texture, xPos,yPos);
        this.batch.end();
    }

    public double getHeight() {return height;}
    public double getWidth() {return width;}
    public float getX(){
        return xPos;
    }
    public void setX(float x){
        xPos = x;
    }
    public Texture getTexture(){
        return texture;
    }
    public void setY(float y ){
        yPos = y;
    }
    public float getY(){
        return yPos;
    }

}
