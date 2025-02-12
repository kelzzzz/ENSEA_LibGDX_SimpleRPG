package org.ensea.student.core.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import org.ensea.student.core.displayable.DynamicSprite;
import org.ensea.student.core.displayable.SimpleTilemap;
import org.ensea.student.core.displayable.SolidSprite;

import static org.ensea.student.core.util.Constants.*;

public class RenderEngine implements Engine {
    SimpleTilemap tm;
    SolidSprite ss;
    DynamicSprite ds;

    public static PhysicsEngine pe;
    float elapsed;

    public RenderEngine(){
        tm = new SimpleTilemap(6,9,TEXTURE_GRASS_PATH);
        Texture ssTexture = new Texture(Gdx.files.internal(TEXTURE_ROCK_PATH));
        ss = new SolidSprite(ssTexture,4*ssTexture.getWidth(),1*ssTexture.getHeight());
        SolidSprite ss2 = new SolidSprite(ssTexture,4*ssTexture.getWidth(),5*ssTexture.getHeight());
        SolidSprite ss3 = new SolidSprite(ssTexture,2*ssTexture.getWidth(),6*ssTexture.getHeight());
        tm.insertTile(ss,ss.getX(),ss.getY());
        tm.insertTile(ss2,ss2.getX(),ss2.getY());
        tm.insertTile(ss3,ss3.getX(),ss3.getY());
        drawTrees();
        ds = new DynamicSprite(TEXTURE_HERO_PATH,100,100);
        pe = new PhysicsEngine(tm.coordinates);
    }
    @Override
    public void update() {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        tm.draw();
        ds.draw();
    }

    public void drawTrees(){
        Texture treeTexture = new Texture(Gdx.files.internal(TEXTURE_TREE_PATH));
        for(int i = 0; i < tm.coordinates.length; i++){
            for(int j = 0; j < tm.coordinates[0].length; j++){
                if(i == 0 || i == tm.coordinates.length-1){
                    SolidSprite tree = new SolidSprite(treeTexture,i*treeTexture.getWidth(),j* treeTexture.getHeight());
                    tm.insertTile(tree,i,j);
                }
                else if(j == 0 || j == tm.coordinates[0].length-1){
                    SolidSprite tree = new SolidSprite(treeTexture,i* treeTexture.getWidth(),j* treeTexture.getHeight());
                    tm.insertTile(tree,i,j);
                }
            }
        }
        tm.coordinates[5][4].releaseSprite();
        tm.coordinates[5][4] = null;
    }
}
