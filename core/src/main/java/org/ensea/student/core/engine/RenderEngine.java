package org.ensea.student.core.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import org.ensea.student.core.displayable.DynamicSprite;
import org.ensea.student.core.displayable.SimpleTilemap;
import org.ensea.student.core.displayable.SolidSprite;
import org.ensea.student.core.displayable.level.Level;

import static org.ensea.student.core.util.Constants.*;

public class RenderEngine implements Engine {
    private SimpleTilemap tm;
    private final DynamicSprite ds;

    public static PhysicsEngine pe;

    public RenderEngine(){
        this.ds = new DynamicSprite(TEXTURE_HERO_PATH,100,100);
        pe = new PhysicsEngine(SimpleTilemap.coordinates);
    }

    public RenderEngine(int spriteStartX, int spriteStartY, Level lvl){
        loadTilemap(lvl);
        this.ds = new DynamicSprite(TEXTURE_HERO_PATH,spriteStartX,spriteStartY);
        pe = new PhysicsEngine(SimpleTilemap.coordinates);
    }

    @Override
    public void update() {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        this.tm.draw();
        this.ds.draw();
    }

    public void loadTilemap(Level lvl){
        this.tm = lvl.retrievePackedLevelTilemap();
    }

    public static SolidSprite createTile(Texture t, int x, int y){
        return new SolidSprite(t,x*t.getWidth(),y*t.getHeight());
    }
}
