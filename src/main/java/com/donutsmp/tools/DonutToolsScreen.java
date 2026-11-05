
package com.donutsmp.tools;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class DonutToolsScreen extends Screen {

    public DonutToolsScreen() {
        super(Component.literal("DonutSMP Tools"));
    }

    @Override
    protected void init() {
        int x = width / 2 - 100;
        int y = height / 2 - 65;

        addRenderableWidget(Button.builder(
            Component.literal("Coordinates: " + (DonutToolsClient.showCoords ? "ON" : "OFF")),
            b -> {
                DonutToolsClient.showCoords = !DonutToolsClient.showCoords;
                b.setMessage(Component.literal("Coordinates: " + (DonutToolsClient.showCoords ? "ON" : "OFF")));
            }).bounds(x, y, 200, 20).build());

        addRenderableWidget(Button.builder(
            Component.literal("Server IP: " + (DonutToolsClient.showIP ? "ON" : "OFF")),
            b -> {
                DonutToolsClient.showIP = !DonutToolsClient.showIP;
                b.setMessage(Component.literal("Server IP: " + (DonutToolsClient.showIP ? "ON" : "OFF")));
            }).bounds(x, y + 25, 200, 20).build());

        addRenderableWidget(Button.builder(
            Component.literal("Player Name: " + (DonutToolsClient.showName ? "ON" : "OFF")),
            b -> {
                DonutToolsClient.showName = !DonutToolsClient.showName;
                b.setMessage(Component.literal("Player Name: " + (DonutToolsClient.showName ? "ON" : "OFF")));
            }).bounds(x, y + 50, 200, 20).build());

        addRenderableWidget(Button.builder(
            Component.literal("Nether Coords: " + (DonutToolsClient.showNether ? "ON" : "OFF")),
            b -> {
                DonutToolsClient.showNether = !DonutToolsClient.showNether;
                b.setMessage(Component.literal("Nether Coords: " + (DonutToolsClient.showNether ? "ON" : "OFF")));
            }).bounds(x, y + 75, 200, 20).build());

        addRenderableWidget(Button.builder(
            Component.literal("Close"),
            b -> onClose()
        ).bounds(x, y + 105, 200, 20).build());
    }

    @Override
    public void extractRenderState(
        GuiGraphicsExtractor graphics,
        int mouseX,
        int mouseY,
        float delta
    ) {
        super.extractRenderState(graphics, mouseX, mouseY, delta);

        graphics.text(
            font,
            "DonutSMP Tools",
            width / 2 - 40,
            height / 2 - 95,
            0xFFFFFFFF,
            true
        );
    }
}
