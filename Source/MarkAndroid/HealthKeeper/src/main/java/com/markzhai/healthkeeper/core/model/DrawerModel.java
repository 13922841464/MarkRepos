package com.markzhai.healthkeeper.core.model;

import com.markzhai.library.framework.core.model.MZModel;

/**
 * Created by marktlzhai on 15/4/20.
 */
public class DrawerModel extends MZModel {

    public int drawerIcon = 0;
    public int drawerNameRes = 0;
    public boolean hasArrow = false;

    public DrawerModel(int drawerIcon, int drawerNameRes, boolean hasArrow) {
        this.drawerIcon = drawerIcon;
        this.drawerNameRes = drawerNameRes;
        this.hasArrow = hasArrow;
    }
}
