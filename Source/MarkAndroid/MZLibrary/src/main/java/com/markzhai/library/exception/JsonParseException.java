package com.markzhai.library.exception;

/**
 * Created by marktlzhai on 2015/4/21.
 */
public class JsonParseException extends Exception {
    public JsonParseException() {
        super("解析服务器数据失败");
    }
}
