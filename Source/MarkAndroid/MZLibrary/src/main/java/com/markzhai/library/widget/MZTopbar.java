package com.markzhai.library.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.PaintDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.markzhai.library.R;

import java.util.List;

/**
 * Created by marktlzhai on 2015/3/18.
 */
public class MZTopbar extends RelativeLayout {

    /**
     * 下拉列表点击的回调接口 如果该topbar需要实现弹出下拉列表，则应该调用 #setDragListClickCallback
     */
    public interface DragListClickCallback {
        void itemClicked(int position, Object item);
    }

    public interface DragListTitle {
        String getTitle();

        int getIcon();
    }

    private TextView titleView;

    private ImageView iconView;

    private ImageView menuView;

    private PopupWindow dragWindow;
    private DragListClickCallback dragListClickCallback;

    private View rootView;

    public MZTopbar(Context context) {
        super(context);
        initViews();
    }

    public MZTopbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public MZTopbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    private void initViews() {
        rootView = LayoutInflater.from(getContext()).inflate(R.layout.widget_topbar, null, false);

        titleView = (TextView) rootView.findViewById(R.id.topbar_title);
        iconView = (ImageView) rootView.findViewById(R.id.topbar_icon);
        iconView.setImageResource(0);

        menuView = (ImageView) rootView.findViewById(R.id.topbar_menu);
        menuView.setImageResource(0);

        addView(rootView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    public void setTitle(int stringRes) {
        titleView.setText(stringRes);
    }

    public void setTitle(String title) {
        titleView.setText(title);
    }

    public void setIcon(int iconRes, OnClickListener listener) {
        if (iconRes <= 0) {
            iconView.setImageDrawable(new ColorDrawable(Color.TRANSPARENT));
        } else {
            iconView.setImageResource(iconRes);
        }

        if (listener == null) {
            iconView.setClickable(false);
        } else {
            iconView.setClickable(true);
            iconView.setOnClickListener(listener);
        }
    }

    public void setMenu(int menuRes, OnClickListener listener) {
        if (menuRes <= 0) {
            menuView.setImageDrawable(new ColorDrawable(Color.TRANSPARENT));
        } else {
            menuView.setImageResource(menuRes);
        }
        if (listener == null) {
            menuView.setClickable(false);
        } else {
            menuView.setClickable(true);
            menuView.setOnClickListener(listener);
        }
    }

    public void setMenuList(int menuRes, List<? extends DragListTitle> itemList, DragListClickCallback callback) {
        if (menuRes <= 0) {
            menuView.setImageDrawable(new ColorDrawable(Color.TRANSPARENT));
        } else {
            dragListClickCallback = callback;

            menuView.setImageResource(menuRes);
            ListView dragList = new ListView(getContext());
            dragList.setDivider(new ColorDrawable(Color.TRANSPARENT));
            dragList.setDividerHeight(0);
            dragList.setCacheColorHint(android.R.color.transparent);
            dragList.setSelector(new ColorDrawable(Color.TRANSPARENT));
            dragList.setAdapter(new DragListAdapter(itemList));

            dragWindow = new PopupWindow(dragList, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            dragWindow.setAnimationStyle(R.style.PopupAnimation);
            PaintDrawable bgdDrawable = new PaintDrawable(android.R.color.transparent);
            dragWindow.setBackgroundDrawable(bgdDrawable);
            dragWindow.setFocusable(true);
            dragWindow.setOutsideTouchable(true);
            menuView.setClickable(true);
            menuView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!dragWindow.isShowing()) {
                        dragWindow.showAsDropDown(rootView);
                    }
                }
            });
        }
    }

    class DragListAdapter extends BaseAdapter {

        private List<? extends DragListTitle> dragListItems;

        public DragListAdapter(List<? extends DragListTitle> dragListItems) {
            this.dragListItems = dragListItems;
        }

        @Override
        public int getCount() {
            return dragListItems.size();
        }

        @Override
        public DragListTitle getItem(int position) {
            return dragListItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View rootView = LayoutInflater.from(getContext()).inflate(R.layout.topbar_drag_list_item, parent, false);

            TextView adapterTitleView = (TextView) rootView.findViewById(R.id.drag_item_text);
            adapterTitleView.setText(getItem(position).getTitle());

            ImageView iconView = (ImageView) rootView.findViewById(R.id.drag_item_icon);
            if (getItem(position).getIcon() > 0) {
                iconView.setVisibility(View.VISIBLE);
                iconView.setImageResource(getItem(position).getIcon());
            } else {
                iconView.setVisibility(View.GONE);
            }

            rootView.findViewById(R.id.clicked_layout).setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    if (dragWindow.isShowing()) {
                        dragWindow.dismiss();
                    }

                    if (dragListClickCallback != null) {
                        dragListClickCallback.itemClicked(position, getItem(position));
                    }
                }
            });

            return rootView;
        }

        public void update(List<? extends DragListTitle> dragListItems) {
            this.dragListItems = dragListItems;
            notifyDataSetChanged();
        }
    }
}
