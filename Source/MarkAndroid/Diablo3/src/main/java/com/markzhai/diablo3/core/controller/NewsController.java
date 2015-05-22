package com.markzhai.diablo3.core.controller;

import com.loopj.android.http.TextHttpResponseHandler;
import com.markzhai.diablo3.core.model.NormalNews;
import com.markzhai.diablo3.core.model.SliderNews;
import com.markzhai.jsouplib.JSoupUtils;
import com.markzhai.library.framework.BaseApplication;
import com.markzhai.library.framework.core.controller.MZController;
import com.markzhai.talkingdata.TalkingData;

import org.apache.http.Header;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marktlzhai on 2015/5/19.
 */
public class NewsController extends MZController {

    private static final String HOME_URL = "http://d.163.com";

    public interface NewsLoadCallback {
        void loadSliderNewsSuccess(List<SliderNews> sliderNewses);

        void loadNewsSuccess(List<NormalNews> normalNewses);

        void loadNewsFailure(String errorMessage);
    }

    public static void loadNews(final NewsLoadCallback newsLoadCallback) {
        asyncHttpClient.get(HOME_URL, new TextHttpResponseHandler() {
            @Override
            public String getCharset() {
                return "GB2312";
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                if (newsLoadCallback != null) {
                    newsLoadCallback.loadNewsFailure("status code : " + statusCode);
                }

                TalkingData.onError(BaseApplication.getApplication(), throwable);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Document htmlDoc = Jsoup.parse(responseString);

                Elements slideElements = JSoupUtils.getSubElementByAttrValueFirst(htmlDoc, "class", "slideshow-image click-image").getElementsByTag("a");

                if (JSoupUtils.isNotEmpty(slideElements)) {
                    List<SliderNews> sliderNewsResult = new ArrayList<SliderNews>();

                    for (int i = 0; i < slideElements.size(); i++) {
                        try {
                            sliderNewsResult.add(new SliderNews(slideElements.get(i)));
                        } catch (Exception e) {
                            TalkingData.onError(BaseApplication.getApplication(), e);
                        }
                    }

                    if (newsLoadCallback != null) {
                        newsLoadCallback.loadSliderNewsSuccess(sliderNewsResult);
                    }
                }

                Elements newsImgElements = JSoupUtils.getSubElementByAttrValueFirst(htmlDoc, "class", "blog-articles").getElementsByAttributeValue("class", "article-image");
                Elements newsContentElements = JSoupUtils.getSubElementByAttrValueFirst(htmlDoc, "class", "blog-articles").getElementsByAttributeValue("class", "article-content");

                if (JSoupUtils.isNotEmpty(newsImgElements) && JSoupUtils.isNotEmpty(newsContentElements)) {
                    List<NormalNews> newsResult = new ArrayList<NormalNews>();
                    if (newsImgElements.size() == newsContentElements.size()) {
                        for (int i = 0; i < newsImgElements.size(); i++) {
                            try {
                                newsResult.add(new NormalNews(newsImgElements.get(i), newsContentElements.get(i)));
                            } catch (Exception e) {
                                TalkingData.onError(BaseApplication.getApplication(), e);
                            }
                        }
                    }

                    if (newsLoadCallback != null) {
                        newsLoadCallback.loadNewsSuccess(newsResult);
                    }
                }
            }
        });
    }
}
