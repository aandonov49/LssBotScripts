package com.lssbot.scripts.gatherbuild;

import com.lssbot.core.api.device.Device;
import com.lssbot.core.api.game.Game;
import com.lssbot.core.api.game.roe.menus.build.ROEBuildMenuCategory;
import com.lssbot.core.api.script.AbstractScript;
import com.lssbot.core.api.script.ScriptManifest;
import com.lssbot.core.api.script.config.Config;

@ScriptManifest(
        game= Game.RISE_OF_EMPIRES,
        name= "Gather Build",
        version = 0.0,
        timeoutMinutes = 10,
        description = "Build rss rss tile with specific coordinates and send Legion to gather on it.",
        author= "Atanas Andonov"
)
public class GatherBuildScript extends AbstractScript {
    private final GatherBuildConfig CONFIG = new GatherBuildConfig();
    private boolean scrollRight=false;

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
        if (CONFIG.gather.getButtons().contains(ROEBuildMenuCategory.IRON.name())){
            scrollRight=true;
        }
        super.onStart();
    }

    @Override
    public String getTask() {
        return "Gather Build";
    }

    @Override
    public int loop() {
        if (!roe().getViewport().isOnMap()){
            roe().getViewport().openMap();
        }
        return 0;
    }

    @Override
    public Config getConfig() {
        return CONFIG;
    }
}
