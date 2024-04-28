package com.lssbot.scripts.gatherbuild;

import com.lssbot.core.api.game.roe.menus.build.ROEBuildMenuCategory;
import com.lssbot.core.api.script.config.Config;
import com.lssbot.core.api.script.config.ConfigItem;
import com.lssbot.core.ui.components.configelements.StringPickPanel;

public class GatherBuildConfig implements Config {
@ConfigItem(
        keyName="gatherBuff",
        displayName = "Use gather buff",
        groupName = "General settings"
)
    boolean gatherBuff=false;
    @ConfigItem(
            keyName="gather",
            displayName = "Gather",
            groupName = "General settings",
            defaultStringModel = {"MARBLE","IRON","LUMBER","FOOD"}
    )
    StringPickPanel gather=new StringPickPanel(ROEBuildMenuCategory.MARBLE.name(),ROEBuildMenuCategory.IRON.name(),ROEBuildMenuCategory.LUMBER.name(),ROEBuildMenuCategory.FOOD.name());
}
