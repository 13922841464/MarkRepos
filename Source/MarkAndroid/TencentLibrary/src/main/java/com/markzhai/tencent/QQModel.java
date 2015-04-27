package com.markzhai.tencent;

import com.markzhai.library.framework.core.model.MZModel;

/**
 * Created by marktlzhai on 2015/4/24.
 */
public class QQModel extends MZModel {
    public String accessToken;
    public String expiresIn;
    public String openID;

    public QQModel(String accessToken, String expiresIn, String openID) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.openID = openID;
    }
}
