package io.myzticbean.finditemaddon.Commands.SAPICommands;

import io.myzticbean.finditemaddon.FindItemAddOn;
import io.myzticbean.finditemaddon.Handlers.CommandHandler.CmdExecutorHandler;
import me.kodysimpson.simpapi.command.SubCommand;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Sub Command Handler for /finditemadmin revealshop
 * @author myzticbean
 */
public class RevealShopSubCmd extends SubCommand {

    private final CmdExecutorHandler cmdExecutor;

    public RevealShopSubCmd() {
        cmdExecutor = new CmdExecutorHandler();
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Run this command while looking at a hidden shop to make it public again";
    }

    @Override
    public String getSyntax() {
        return "";
    }


    @Override
    public void perform(CommandSender commandSender, String[] args) {
        cmdExecutor.handleRevealShop(commandSender);
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] strings) {
        return null;
    }
}

