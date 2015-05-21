package com.markzhai.diablo3.core.model;

import com.markzhai.diablo3.utils.JSoupUtils;
import com.markzhai.library.framework.core.model.MZModel;
import com.markzhai.library.utils.StringUtils;

import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
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
        Elements imgEs = e.getElementsByTag("img");
        Elements titleEs = e.getElementsByTag("span");
        if (JSoupUtils.isNotEmpty(imgEs)) {
            imageUrl = imgEs.get(0).attr("src");
        }

        if (JSoupUtils.isNotEmpty(titleEs)) {
            title = titleEs.text();
        }

        newsUrl = e.attr("href");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
