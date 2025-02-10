package org.ensea.student.core.displayable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SimpleTilemap extends SpriteWrapper{
    //This custom class implements a way to track the tiles are placed on the map
    //you define it with a size, a backdrop, and can dynamically overlay solid sprites

    public static int[][] coordinates;

    @Override
    public void draw() {

        int xLen = coordinates.length;
        int yLen = coordinates[1].length;
        texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        TextureRegion tr = new TextureRegion(texture);
        tr.setRegion(0,0,texture.getWidth()*xLen, texture.getHeight()*yLen);
        drawTextureRegion(tr, 0, 0);
    }
    
    public SimpleTilemap(int x, int y, String backdropFileName){
        coordinates = new int[x][y];
        texture = new Texture(Gdx.files.internal(backdropFileName));
    }

    public void drawTextureRegion(TextureRegion tr, float x, float y) {
        batch.begin();
        batch.draw(tr, x, y);
        batch.end();
    }
}
