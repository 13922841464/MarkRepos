package com.markzhai.jsouplib;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JSoupUtils {
    public static boolean isNotEmpty(Elements e) {
        if (e == null || e.isEmpty()) {
            return false;
        }
        return true;
    }

    public static Elements getSubElementsByTag(Element parent, String tag) {
        return parent.getElementsByTag(tag);
    }

    public static Element getSubElementByTagFirst(Element parent, String tag) {
        Elements tmpArry = getSubElementsByTag(parent, tag);
        if (isNotEmpty(tmpArry)) {
            return tmpArry.get(0);
        } else {
            return null;
        }
    }

    public static Elements getSubElementsByClass(Element parent, String clazz) {
        return parent.getElementsByClass(clazz);
    }

    public static Element getSubElementByClassFirst(Element parent, String clazz) {
        Elements tmpArry = getSubElementsByClass(parent, clazz);
        if (isNotEmpty(tmpArry)) {
            return tmpArry.get(0);
        } else {
            return null;
        }
    }

    public static Elements getSubElementsByAttrValue(Element parent, String attr, String value) {
        return parent.getElementsByAttributeValue(attr, value);
    }

    public static Element getSubElementByAttrValueFirst(Element parent, String attr, String value) {
        Elements tmpArry = getSubElementsByAttrValue(parent, attr, value);
        if (isNotEmpty(tmpArry)) {
            return tmpArry.get(0);
        } else {
            return null;
        }
    }
}
