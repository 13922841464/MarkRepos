package com.markzhai.library.framework.core.model;

import com.markzhai.library.exception.AccountParseException;
import com.markzhai.library.utils.StringUtils;

/**
 * Created by marktlzhai on 2015/4/20.
 */
public class MZAccount extends MZModel {

    public String name = "";
    public int golden = 0;

    public MZAccount() {

    }

    public MZAccount(String spString) throws AccountParseException {
        if (StringUtils.isEmpty(spString)) {
            throw new AccountParseException();
        }
        String[] array = spString.split(PARSE_SEPARATOR);
        this.name = String.valueOf(array[0]);
        this.golden = Integer.valueOf(array[1]);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(128);

        builder.append(name);
        builder.append(SEPARATOR);
        builder.append(golden);

        return builder.toString();
    }
}
