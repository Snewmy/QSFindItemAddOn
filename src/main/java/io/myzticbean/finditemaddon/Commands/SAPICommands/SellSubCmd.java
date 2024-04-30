package io.myzticbean.finditemaddon.Commands.SAPICommands;

import io.myzticbean.finditemaddon.FindItemAddOn;
import io.myzticbean.finditemaddon.Handlers.CommandHandler.CmdExecutorHandler;
import io.myzticbean.finditemaddon.Utils.Utils;
import me.kodysimpson.simpapi.command.SubCommand;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Sub Command Handler for /finditem TO_SELL
 * @author myzticbean
 */
public class SellSubCmd extends SubCommand {

    private final List<String> itemsList = new ArrayList<>();
    private final CmdExecutorHandler cmdExecutor;

    public SellSubCmd() {
        if(itemsList.isEmpty()) {
            for(Material mat : Material.values()) {
                itemsList.add(mat.name());
            }
        }
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
        return "Find shops that sell a specific item";
    }

    @Override
    public String getSyntax() {
        return "";
    }


    @Override
    public void perform(CommandSender commandSender, String[] args) {
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] args) {
        List<String> result = new ArrayList<>();
        for(String a : itemsList) {
            if(a.toLowerCase().startsWith(args[1].toLowerCase())) {
                result.add(a);
            }
        }
        return result;
    }
}

