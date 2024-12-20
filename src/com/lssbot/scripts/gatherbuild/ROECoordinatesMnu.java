package com.lssbot.scripts.gatherbuild;

import com.lssbot.core.api.device.Device;
import com.lssbot.core.api.device.input.keyboard.Keyboard;
import com.lssbot.core.api.device.input.mouse.Mouse;
import com.lssbot.core.api.geometry.BPoint;
import com.lssbot.core.api.geometry.BRectangle;
import com.lssbot.core.api.image.ImageUtil;
import com.lssbot.core.api.image.OCR;
import com.lssbot.core.api.image.OpenCV;
import com.lssbot.core.api.random.RNG;
import com.lssbot.core.api.script.methodprovider.ROEMethodProvider;
import com.lssbot.core.framework.device.DeviceEngine;
import net.sourceforge.tess4j.Word;

import java.awt.image.BufferedImage;
import java.util.List;

public class ROECoordinatesMnu {
    private Device device;
    private ROEMethodProvider roeMethodProvider;
    private static final BRectangle PROVINCE_INPUT_FIELD = new BRectangle(96, 720, 88, 31);
    private static final BRectangle COORDINATES_LABEL = new BRectangle(21, 650, 496, 35);
    private static final BRectangle SEARCH_BUTTON = new BRectangle(447, 773, 32, 29);
    private static final BRectangle X_INPUT_FIELD = new BRectangle(96, 769, 58, 32);
    private static final BRectangle Y_INPUT_FIELD = new BRectangle(294, 769, 64, 33);
    private static final BRectangle COORDINATES_MENU = new BRectangle(294, 769, 64, 33);
    private static final int MOUSE_CLICK_DELAY=600;
//    private static final String MENU_LABEL = "Enter coordinates";

    public ROECoordinatesMnu(Device device, ROEMethodProvider roeMethodProvider) {
        if (device != null && roeMethodProvider != null) {
            this.device = device;
            this.roeMethodProvider = roeMethodProvider;
        } else {
            throw new RuntimeException("Cannot initialize Coordinates menu");
        }
    }

    public boolean openSearchCoordinates() {
        if (!isCoordinatesMenuOpen()) {
            mouseClick(COORDINATES_MENU, 2000, 50);
            return isCoordinatesMenuOpen();
        }
        return false;
    }
    public boolean clickSearchCoordinates(){
        device.log("Pressing search");
        mouseClick(SEARCH_BUTTON, 2000, 50);
        return roeMethodProvider.getViewport().isOnMap();
    }
    public void setCoordinates(int province, int xValue, int yValue) {
        device.log("Entering coordinates " + xValue + ", " + yValue + " (s" + province + ")");
        if (province > 0) {
            device.log("Entering province");
            mouseClick(PROVINCE_INPUT_FIELD, MOUSE_CLICK_DELAY, 50);
            getKeyboard().type(Integer.toString(province));
            mouseClick(COORDINATES_LABEL, MOUSE_CLICK_DELAY, 50);
        }
        if (xValue > 0) {
            device.log("Entering x");
            mouseClick(X_INPUT_FIELD, MOUSE_CLICK_DELAY, 50);
            getKeyboard().type(Integer.toString(xValue));
            mouseClick(COORDINATES_LABEL, MOUSE_CLICK_DELAY, 50);
        }
        if (yValue > 0) {
            device.log("Entering y");
            mouseClick(Y_INPUT_FIELD, MOUSE_CLICK_DELAY, 50);
            getKeyboard().type(Integer.toString(yValue));
            mouseClick(COORDINATES_LABEL, MOUSE_CLICK_DELAY, 50);
        }

    }

    public boolean isCoordinatesMenuOpen() {
        device.c.b();
        boolean coordinatesMenu = getOpenCV().isTemplateOnScreen(Images.MENU_LABEL, 0.75);
        device.log("Is Build Menu on the map Open? " + coordinatesMenu);
        return coordinatesMenu;
    }

    private Mouse getMouse() {
        return this.device.e;
    }

    private OCR getOCR() {
        return device.c.f;
    }

    private Keyboard getKeyboard() {
        return this.device.f;
    }

    private OpenCV getOpenCV() {
        return device.c.g;
    }

    public void mouseClick(BRectangle clickPont, int delay, int delayDeviation) {
        getMouse().click(clickPont.getCentralPoint());
        DeviceEngine.a(RNG.plusMinus(delay, delayDeviation));
    }
    public void mouseClick(BPoint clickPont, int delay, int delayDeviation) {
        getMouse().click(clickPont);
        DeviceEngine.a(RNG.plusMinus(delay, delayDeviation));
    }

    @Override
    public String toString() {
        return "ROECoordinatesMnu{" +
                "device=" + device +
                ", roeMethodProvider=" + roeMethodProvider +
                '}';
    }
}
