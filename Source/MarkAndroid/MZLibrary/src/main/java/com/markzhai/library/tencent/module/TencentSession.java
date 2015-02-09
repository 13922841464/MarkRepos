package com.markzhai.library.tencent.module;

import java.io.Serializable;

/**
 * Created by marktlzhai on 2015/2/9.
 */
public class TencentSession implements Serializable {

    public String payToken = "";

    public String pf = "";

    public long expires = 0L;

    public String openID = "";

    public String pfkey = "";

    public String accessToken = "";

    public TencentSession() {
    }

    public TencentSession(String payToken, String pf, long expires, String openID, String pfkey, String accessToken) {
        this.payToken = payToken;
        this.pf = pf;
        this.expires = expires;
        this.openID = openID;
        this.pfkey = pfkey;
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("payToken = " + payToken + "\n");
        stringBuilder.append("pf = " + pf + "\n");
        stringBuilder.append("expires = " + expires + "\n");
        stringBuilder.append("openID = " + openID + "\n");
        stringBuilder.append("pfkey = " + pfkey + "\n");
        stringBuilder.append("accessToken = " + accessToken);
        return stringBuilder.toString();
    }
}
