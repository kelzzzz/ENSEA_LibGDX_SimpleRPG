package org.ensea.student.core.inventory;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.google.gson.*;

import java.util.ArrayList;

import static org.ensea.student.core.util.Constants.*;

public class SerializedItemDB {
    private final Gson gson = new Gson();
    private final JsonArray jsonArr;

    /*TODO: Convert to a map eventually*/
    private final ArrayList<SingleItem> itemDB = new ArrayList<SingleItem>();

    /*Textures*/
    private final Texture iconSheet = new Texture(ITEM_ICONS_PATH);
    private TextureRegion[][] sprites;

    /*Singleton*/
    private static SerializedItemDB instance;

    public static SerializedItemDB getInstance() {
        if(instance == null){
            instance = new SerializedItemDB();
        }
        return instance;
    }

    private SerializedItemDB(){
        JsonParser jsonParser = new JsonParser();
        String jsonData = ITEMS_JSON_STRING;
        JsonObject jo = (JsonObject)jsonParser.parse(jsonData);
        jsonArr = jo.getAsJsonArray(JSON_ITEM_MEMBER_CLASS_NAME);
        ripTextures();
        initItemDB();
    }

    public ArrayList<SingleItem> getItemDB(){
        return this.itemDB;
    }

    private void ripTextures(){
        sprites = TextureRegion.split(this.iconSheet, 32,32);
    }

    public void initItemDB(){
        for(JsonElement je : jsonArr){
            this.itemDB.add(gson.fromJson(je, SingleItem.class));
        }
        for(SingleItem i : itemDB){
            i.setIcon(sprites[i.getIconRow()][i.getIconCol()]);
        }
    }
}
