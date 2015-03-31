package com.markzhai.adultvideo.core.model.empflix;

import com.markzhai.library.framework.core.model.MZModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marktlzhai on 2015/3/23.
 */
public class EmpflixVideoModel extends MZModel {

    public String videoURL = "";

    public String videoTitle = "";

    public String videoDuration = "";

    public String videoThumb = "";

    @Override
    public String toString() {
        return videoTitle + "\n" + videoURL + "\n" + videoDuration + "\n" + videoThumb;
    }

    public int getDurationMilliSecond() {
        int duration = 100;

        String[] durationArry = videoDuration.split(":");
        if (durationArry != null && durationArry.length == 2) {
            Integer min = Integer.parseInt(durationArry[0]);
            Integer sec = Integer.parseInt(durationArry[1]);
            return (min * 60 + sec) * 1000;
        }

        return duration;
    }

    public static List<EmpflixVideoModel> parseList(String httpResponse) {
        List<EmpflixVideoModel> result = new ArrayList<EmpflixVideoModel>();
        Document document = Jsoup.parse(httpResponse);

        Elements videoItems = document.getElementsByClass("thumb");
        if (videoItems == null || videoItems.size() == 0) {
            return result;
        }

        EmpflixVideoModel videoModel = null;
        for (Element element : videoItems) {
            videoModel = new EmpflixVideoModel();

            videoModel.videoURL = "http:" + element.attr("href");

            Elements imgElemens = element.getElementsByTag("img");
            if (imgElemens != null && imgElemens.size() >= 1) {
                videoModel.videoTitle = imgElemens.get(0).attr("alt");
                videoModel.videoThumb = imgElemens.get(0).attr("src");
            }

            Elements durationElements = element.getElementsByClass("videoDuration");
            if (durationElements != null && durationElements.size() >= 1) {
                videoModel.videoDuration = durationElements.get(0).text();
            }

            result.add(videoModel);
        }

        return result;
    }
}
