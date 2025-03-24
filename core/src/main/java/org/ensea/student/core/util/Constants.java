package org.ensea.student.core.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class Constants {
    public static final String TEXTURE_GRASS_PATH = "grass.png";
    public static final String TEXTURE_HERO_PATH = "heroTileSheetLowRes.png";
    public static final String TEXTURE_ROCK_PATH = "rock.png";
    public static final String TEXTURE_TRAP_PATH = "trap.png";
    public static final String TEXTURE_TREE_PATH = "tree.png";
    public static enum ITEM_TYPES{
        HEALING,DAMAGING
    }
    public static final String ITEM_ICONS_PATH = "Item-icons.png";
    public static String ITEMS_JSON_STRING = "";
}
