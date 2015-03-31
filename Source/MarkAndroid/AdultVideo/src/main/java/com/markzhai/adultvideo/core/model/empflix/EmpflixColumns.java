package com.markzhai.adultvideo.core.model.empflix;

import android.net.Uri;

import com.markzhai.library.framework.core.model.MZColumn;

/**
 * Created by marktlzhai on 2015/3/31.
 */
public class EmpflixColumns extends MZColumn {
    public static final String AUTHORITY = "com.markzhai.adultvideo.empflix";
    public static final String CONTENT_URI_ITEM = "empflix";

    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + CONTENT_URI_ITEM);

    public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google." + CONTENT_URI_ITEM;

    public static final String videoURL = "v_url";
    public static final String videoTitle = "v_title";
    public static final String videoDuration = "v_duration";
    public static final String videoThumb = "v_thumb";
}
