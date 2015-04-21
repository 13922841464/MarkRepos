package com.markzhai.healthkeeper.core.model;

import com.markzhai.library.framework.core.model.MZModel;
import com.markzhai.library.framework.page.FragmentRequest;

/**
 * Created by marktlzhai on 2015/4/21.
 */
public class HomeItemModel extends MZModel {
    public int iconRes;
    public int nameRes;
    public int descRes;
    public FragmentRequest actionRequest;

    public HomeItemModel(int iconRes, int nameRes, int descRes, FragmentRequest actionRequest) {
        this.iconRes = iconRes;
        this.nameRes = nameRes;
        this.descRes = descRes;
        this.actionRequest = actionRequest;
    }
}
