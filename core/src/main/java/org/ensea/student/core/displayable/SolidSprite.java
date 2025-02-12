package org.ensea.student.core.displayable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class SolidSprite extends SpriteWrapper{
    Rectangle spriteRectangle;

    public SolidSprite(String textureFileName, float x, float y){
        texture = new Texture(Gdx.files.internal(textureFileName));
        batch = new SpriteBatch();
        xPos = x;
        yPos = y;
        spriteRectangle = new Rectangle(xPos, yPos, texture.getWidth(), texture.getHeight());
    }
    public SolidSprite(Texture tex, float x, float y){
        texture = tex;
        batch = new SpriteBatch();
        xPos = x;
        yPos = y;
        spriteRectangle = new Rectangle(xPos, yPos, texture.getWidth(), texture.getHeight());
    }

    public int getTextureWidth(){
        return this.texture.getWidth();
    }

    public int getTextureHeight(){
        return this.texture.getHeight();
    }

    public boolean isOverlap(Rectangle check){
        return this.spriteRectangle.overlaps(check);
    }

    public void releaseSprite(){
        spriteRectangle = null;
    }
}
