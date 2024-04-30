package io.myzticbean.finditemaddon.Handlers.GUIHandler;

import com.ghostchu.quickshop.api.QuickShopAPI;
import com.ghostchu.quickshop.api.shop.Shop;
import com.ghostchu.quickshop.api.shop.ShopType;
import io.myzticbean.finditemaddon.FindItemAddOn;
import io.myzticbean.finditemaddon.Models.FoundShopItemModel;
import io.myzticbean.finditemaddon.Utils.LoggerUtils;
import io.myzticbean.finditemaddon.Utils.Utils;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.UUID;

public abstract class CategoryPaginated extends PaginatedMenu {

    public CategoryPaginated(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
        initMaterialsForBottomBar();
    }

    public CategoryPaginated(PlayerMenuUtility playerMenuUtility, List<FoundShopItemModel> searchResult) {
        super(playerMenuUtility);
        initMaterialsForBottomBar();
        super.playerMenuUtility.setPlayerShopSearchResult(searchResult);
    }

    public int getTotalShopsForItemType(Material material) {
        int i = 0;
        List<Shop> allShops = QuickShopAPI.getInstance().getShopManager().getAllShops();
        for (Shop shop : allShops) {
            if (shop.getItem().getType() == material && shop.getShopType() == ShopType.SELLING) {
                if (FindItemAddOn.isExcellentCratesInstalled) {
                    if (FindItemAddOn.getInstance().excellentCrates.getKeyManager().isKey(shop.getItem())) {
                        continue;
                    }
                }
                i++;
            }
        }

        return i;
    }

}
