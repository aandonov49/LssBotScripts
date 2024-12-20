package com.lssbot.scripts.gatherbuild;


import com.lssbot.core.api.game.roe.menus.build.ROEBuildMenuCategory;
import com.lssbot.core.api.script.config.Config;
import com.lssbot.core.api.script.config.ConfigItem;
import com.lssbot.core.ui.components.configelements.StringOrderPickPanel;
import com.lssbot.core.ui.components.configelements.StringPickPanel;

import java.util.*;

public class GatherBuildConfig implements Config {

    @ConfigItem(
            keyName = "gatherBuff",
            displayName = "Use gather buff",
            groupName = "General settings"
    )
    boolean gatherBuff = false;
    @ConfigItem(
            keyName = "gather",
            displayName = "Gather",
            groupName = "General settings",
            defaultStringModel = {"Marble", "Iron", "Lumber", "Food"}
    )
    StringPickPanel gather = new StringPickPanel(ROEBuildMenuCategory.MARBLE.name(), ROEBuildMenuCategory.IRON.name(), ROEBuildMenuCategory.LUMBER.name(), ROEBuildMenuCategory.FOOD.name());
    @ConfigItem(
            keyName = "state",
            displayName = "State",
            groupName = "Tile location"
    )
    int state;
    @ConfigItem(
            keyName = "xConfig",
            displayName = "X coordinate",
            groupName = "Tile location"
    )
    int xConfig;
    @ConfigItem(
            keyName = "yConfig",
            displayName = "Y coordinate",
            groupName = "Tile location"
    )
    int yConfig;
}
