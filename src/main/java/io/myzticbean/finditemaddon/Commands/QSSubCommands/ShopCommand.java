package io.myzticbean.finditemaddon.Commands.QSSubCommands;

import com.ghostchu.quickshop.api.command.CommandHandler;
import io.myzticbean.finditemaddon.FindItemAddOn;
import io.myzticbean.finditemaddon.Handlers.CommandHandler.CmdExecutorHandler;
import io.myzticbean.finditemaddon.Handlers.GUIHandler.Menus.ShopMenu;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ShopCommand implements CommandHandler<Player> {

    private final CmdExecutorHandler cmdExecutor;

    public ShopCommand() {
        cmdExecutor = new CmdExecutorHandler();
    }

    @Override
    public void onCommand(Player commandSender, @NotNull String label, @NotNull String[] args) {
        ShopMenu shopMenu = new ShopMenu(FindItemAddOn.getPlayerMenuUtility(commandSender));
        shopMenu.open();
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull Player sender, @NotNull String commandLabel, @NotNull String[] args) {
        return null;
    }
}

