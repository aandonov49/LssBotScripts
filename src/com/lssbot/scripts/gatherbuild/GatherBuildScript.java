package com.lssbot.scripts.gatherbuild;

import com.lssbot.core.api.device.Device;
import com.lssbot.core.api.game.Game;
import com.lssbot.core.api.game.roe.menus.build.ROEBuildMenuCategory;
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
    protected final GatherBuildConfig CONFIG = new GatherBuildConfig();
    protected boolean scrollRight = false;
    protected ROECoordinatesMnu roeCoordinatesMenu;

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
        if (CONFIG.gather.getButtons().size()>1){
            throw new RuntimeException("Only one value must be selected");
        }
        if (CONFIG.gather.getButtons().contains(ROEBuildMenuCategory.IRON.name())) {
            scrollRight = true;
        }
        super.onStart();
    }

    @Override
    public int loop() {
        this.roeCoordinatesMenu = new ROECoordinatesMnu(getDevice(), roe());
        if (!isOnMap()) {
            return -1;
        }
        if (roeCoordinatesMenu.openSearchCoordinates()){
            getDevice().log("Coordinates menu is open");
            roeCoordinatesMenu.setCoordinates(117,666,546);
            roeCoordinatesMenu.clickSearchCoordinates();
        }

//        openMapBuildMenu();
        roe().getViewport().enterBase();
        sleepUntil(() -> roe().getViewport().isInBase(), 4000);
        return 0;
    }

    @Override
    public Config getConfig() {
        return CONFIG;
    }

    private boolean isOnMap() {
        if (!roe().getViewport().isOnMap()) {
            roe().getViewport().openMap();
            sleepUntil(() -> roe().getViewport().isOnMap(), 4000);
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
        device.c.b();
        boolean buildMenuText = getOpenCV().isTemplateOnScreen(Images.BUILD_MENU_TEXT, 0.75);
        device.log("Is Build Menu on the map Open? " + buildMenuText);
        return buildMenuText;
    }
}
