package com.lssbot.scripts.gatherbuild;

import com.lssbot.core.api.device.Device;
import com.lssbot.core.api.game.Game;
import com.lssbot.core.api.game.roe.menus.build.ROEBuildMenuCategory;
import com.lssbot.core.api.geometry.BRectangle;
import com.lssbot.core.api.script.AbstractScript;
import com.lssbot.core.api.script.ScriptManifest;
import com.lssbot.core.api.script.config.Config;

@ScriptManifest(
        game = Game.RISE_OF_EMPIRES,
        name = "Gather Build",
        version = 0.0,
        timeoutMinutes = 10,
        description = "Build rss rss tile with specific coordinates and send Legion to gather on it.",
        author = "Atanas Andonov"
)
public class GatherBuildScript extends AbstractScript {
    private final GatherBuildConfig CONFIG = new GatherBuildConfig();
    private boolean scrollRight = false;

    public GatherBuildScript(Device device) {
        super(device);
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onStart() {
        if (CONFIG.gather.getButtons().contains(ROEBuildMenuCategory.IRON.name())) {
            scrollRight = true;
        }
        super.onStart();
    }

    @Override
    public String getTask() {
        return "Gather Build";
    }

    @Override
    public int loop() {
        if (!isOnMap()) {
            return -1;
        }
        openMapBuildMenu();
        return 0;
    }

    @Override
    public Config getConfig() {
        return CONFIG;
    }

    private boolean isOnMap() {
        if (!roe().getViewport().isOnMap()) {
            roe().getViewport().openMap();
            return roe().getViewport().isOnMap();
        }
        return false;
    }

    private void openMapBuildMenu() {
        if (!isBuidlMenuOpen()) {
            device.log("Open Build Menu on the map ");
            roe().getViewport().openBuildMenu();
            isBuidlMenuOpen();
        }
    }

    private boolean isBuidlMenuOpen() {
        BRectangle buildMenuText = getOpenCV().getAreaMatch(Images.BUILD_MENU_TEXT, 0.75);
        device.log("Is Build Menu on the map Open? " + (buildMenuText == null ? "False" : "True"));
        return buildMenuText != null;
    }
}
