package org.ensea.student.core.displayable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class SpriteWrapper implements Displayable{
    double height;
    double width;
    float xPos;
    float yPos;
    Texture texture;
    SpriteBatch batch;

    public SpriteWrapper(String textureFileName, float x, float y){
        texture = new Texture(Gdx.files.internal(textureFileName));
        batch = new SpriteBatch();
        xPos = x;
        yPos = y;
    }

    public SpriteWrapper(){
        batch = new SpriteBatch();
    }

    @Override
    public void draw() {
        batch.begin();
        batch.draw(texture, xPos,yPos);
        batch.end();
    }
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
