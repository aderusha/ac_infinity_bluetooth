package com.google.android.play.core.assetpacks;

import android.app.Activity;
import com.google.android.play.core.tasks.Task;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.play:core@@1.10.3 */
public interface AssetPackManager {
    AssetPackStates cancel(List<String> list);

    void clearListeners();

    Task<AssetPackStates> fetch(List<String> list);

    AssetLocation getAssetLocation(String str, String str2);

    AssetPackLocation getPackLocation(String str);

    Map<String, AssetPackLocation> getPackLocations();

    Task<AssetPackStates> getPackStates(List<String> list);

    void registerListener(AssetPackStateUpdateListener assetPackStateUpdateListener);

    Task<Void> removePack(String str);

    Task<Integer> showCellularDataConfirmation(Activity activity);

    void unregisterListener(AssetPackStateUpdateListener assetPackStateUpdateListener);
}
