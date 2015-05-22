package com.markzhai.diablo3.core.model;

import com.markzhai.jsouplib.JSoupUtils;
import com.markzhai.library.framework.core.model.MZModel;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by marktlzhai on 2015/5/20.
 */
public class SliderNews extends MZModel {

    public String title;
    public String imageUrl;
    public String newsUrl;

    public SliderNews(Element e) throws Exception {
        imageUrl = JSoupUtils.getSubElementByTagFirst(e, "img").attr("src");
        title = JSoupUtils.getSubElementByTagFirst(e, "span").text();
        newsUrl = e.attr("href");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
