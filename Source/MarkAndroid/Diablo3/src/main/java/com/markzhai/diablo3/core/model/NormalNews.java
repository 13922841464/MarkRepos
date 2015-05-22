package com.markzhai.diablo3.core.model;

import com.markzhai.jsouplib.JSoupUtils;
import com.markzhai.library.framework.core.model.MZModel;

import org.jsoup.nodes.Element;

/**
 * Created by marktlzhai on 2015/5/20.
 */
public class NormalNews extends MZModel {
    public String title;
    public String content;
    public String imageUrl;
    public String newsUrl;
    public String date;

    public NormalNews(Element imgElement, Element contentElement) throws Exception {
        title = JSoupUtils.getSubElementByTagFirst(contentElement, "a").text();
        newsUrl = JSoupUtils.getSubElementByTagFirst(contentElement, "a").attr("href");
        content = JSoupUtils.getSubElementByTagFirst(contentElement, "p").text();
        imageUrl = JSoupUtils.getSubElementByTagFirst(imgElement, "img").attr("src");
        date = JSoupUtils.getSubElementByTagFirst(contentElement, "span").text();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
