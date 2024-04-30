package io.myzticbean.finditemaddon.ScheduledTasks;

import io.myzticbean.finditemaddon.Utils.JsonStorageUtils.ShopSearchActivityStorageUtil;

public class Task15MinInterval implements Runnable {
    @Override
    public void run() {
//        HiddenShopStorageUtil.saveHiddenShopsToFile();
        ShopSearchActivityStorageUtil.syncShops();
    }
}
