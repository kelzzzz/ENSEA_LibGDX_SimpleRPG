package org.ensea.student.core.displayable;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class SolidSprite extends SpriteWrapper{
    private Rectangle spriteRectangle;

    public SolidSprite(String textureFileName, float x, float y){
        super(textureFileName, x, y);
        spriteRectangle = new Rectangle(xPos, yPos, texture.getWidth(), texture.getHeight());
    }

    public SolidSprite(Texture texture, float x, float y){
        super(texture,x,y);
        spriteRectangle = new Rectangle(xPos, yPos, this.texture.getWidth(), this.texture.getHeight());
    }

    public boolean isOverlap(Rectangle check){
        return this.spriteRectangle.overlaps(check);
    }

    public void releaseSprite(){
        spriteRectangle = null;
    }
}
