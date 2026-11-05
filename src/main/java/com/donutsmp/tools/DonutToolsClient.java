
package com.donutsmp.tools;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.Level;

public class DonutToolsClient implements ClientModInitializer {

    public static boolean showCoords = true;
    public static boolean showIP = true;
    public static boolean showName = true;
    public static boolean showNether = true;

    private KeyMapping openMenu;

    @Override
    public void onInitializeClient() {

        var category = KeyMapping.Category.register(
            Identifier.fromNamespaceAndPath("donutsmp", "tools")
        );

        openMenu = KeyMappingHelper.registerKeyMapping(
            new KeyMapping(
                "key.donutsmp.open_menu",
                InputConstants.Type.KEYSYM,
                InputConstants.KEY_RIGHT_SHIFT,
                category
            )
        );

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openMenu.consumeClick()) {
                client.setScreen(new DonutToolsScreen());
            }
        });

        HudElementRegistry.attachElementBefore(
            VanillaHudElements.CHAT,
            Identifier.fromNamespaceAndPath("donutsmp", "info"),
            (graphics, delta) -> {

                Minecraft mc = Minecraft.getInstance();

                if (mc.player == null || mc.level == null || mc.screen != null) return;

                int y = 8;

                if (showCoords) {
                    graphics.text(mc.font,
                        "XYZ: " + mc.player.blockPosition(),
                        8, y, 0xFFFFFF, true);
                    y += 12;
                }

                if (showName) {
                    graphics.text(mc.font,
                        "Name: " + mc.player.getName().getString(),
                        8, y, 0xFFFFFF, true);
                    y += 12;
                }

                if (showIP) {
                    var server = mc.getCurrentServer();
                    String ip = server == null ? "Singleplayer" : server.ip;

                    graphics.text(mc.font,
                        "Server: " + ip,
                        8, y, 0xFFFFFF, true);
                    y += 12;
                }

                if (showNether) {
                    double x = mc.player.getX();
                    double z = mc.player.getZ();

                    if (mc.level.dimension() == Level.NETHER) {
                        x *= 8;
                        z *= 8;
                    } else {
                        x /= 8;
                        z /= 8;
                    }

                    graphics.text(mc.font,
                        "Nether: " + (int)x + ", " + (int)z,
                        8, y, 0xFFFFFF, true);
                }
            }
        );
    }
}
