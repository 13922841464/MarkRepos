package com.markzhai.healthkeeper.core.model.medicalcenter;

import com.markzhai.library.framework.core.model.MZModel;
import com.markzhai.library.utils.StringUtils;

/**
 * Created by marktlzhai on 2015/4/21.
 */
public class PlaceModel extends MZModel {
    public int id;
    public String name;

    public PlaceModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public PlaceModel(String placeSTR) {
        if (StringUtils.isEmpty(placeSTR)) {
            return;
        }
        String[] array = placeSTR.split(PARSE_SEPARATOR);
        this.id = Integer.valueOf(array[0]);
        this.name = String.valueOf(array[1]);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(64);
        builder.append(id);
        builder.append(SEPARATOR);
        builder.append(name);
        return builder.toString();
    }
}
