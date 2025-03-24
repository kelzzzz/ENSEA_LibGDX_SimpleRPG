package org.ensea.student.core.inventory;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.google.gson.*;

import java.util.ArrayList;

import static org.ensea.student.core.util.Constants.ITEMS_JSON_STRING;
import static org.ensea.student.core.util.Constants.ITEM_ICONS_PATH;

public class SerializedItemDB {
    private final String jsonData = ITEMS_JSON_STRING;
    private final Gson gson = new Gson();
    private JsonArray jsonArr;
    private ArrayList<SingleItem> itemDB = new ArrayList<SingleItem>();
    private static SerializedItemDB instance;
    private Texture iconSheet = new Texture(ITEM_ICONS_PATH);
    private TextureRegion[][] sprites;

    public static SerializedItemDB getInstance() {
        if(instance == null){
            instance = new SerializedItemDB();
        }
        return instance;
    }
    public ArrayList<SingleItem> getItemDB(){
        return this.itemDB;
    }
    private SerializedItemDB(){
        JsonParser jsonParser = new JsonParser();
        JsonObject jo = (JsonObject)jsonParser.parse(jsonData);
        jsonArr = jo.getAsJsonArray("items");
        ripTextures();
        initItemDB();
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
