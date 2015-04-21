package com.markzhai.library.framework.core.controller;

import com.loopj.android.http.AsyncHttpClient;
import com.markzhai.library.exception.AccountParseException;
import com.markzhai.library.exception.JsonParseException;

/**
 * Created by marktlzhai on 2015/3/18.
 */
public class MZController {
    public static final AccountParseException ACCOUNT_PARSE_EXCEPTION = new AccountParseException();
    public static final JsonParseException JSON_PARSE_EXCEPTION = new JsonParseException();

    protected static final AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
}
