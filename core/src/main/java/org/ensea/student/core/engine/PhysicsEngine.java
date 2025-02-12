package org.ensea.student.core.engine;

import com.badlogic.gdx.math.Rectangle;
import org.ensea.student.core.displayable.DynamicSprite;
import org.ensea.student.core.displayable.SolidSprite;

public class PhysicsEngine implements Engine{
    public SolidSprite[][] collidable;

    public PhysicsEngine(SolidSprite[][] sp){
        this.collidable = sp;
    }
    @Override
    public void update() {
        /// ?
    }

    public boolean isColliding(Rectangle check){
        for(int i = 0; i < collidable.length; i++){
            for(int j = 0; j < collidable[0].length; j++){
                if(collidable[i][j] != null && collidable[i][j].isOverlap(check)){
                    return true;
                }
            }
        }
        return false;
    }
}
