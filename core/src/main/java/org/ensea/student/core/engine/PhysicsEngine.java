package org.ensea.student.core.engine;

import com.badlogic.gdx.math.Rectangle;
import org.ensea.student.core.displayable.SolidSprite;

public class PhysicsEngine implements Engine{

    public SolidSprite[][] tilesWithCollision;

    public PhysicsEngine(SolidSprite[][] sp){
        this.tilesWithCollision = sp;
    }

    @Override
    public void update() {
        /// ?
    }

    public boolean isColliding(Rectangle check){
        for (SolidSprite[] solidSprites : tilesWithCollision) {
            for (int j = 0; j < tilesWithCollision[0].length; j++) {
                if (solidSprites[j] != null && solidSprites[j].isOverlap(check)) {
                    return true;
                }
            }
        }
        return false;
    }
}
