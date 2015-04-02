package com.markzhai.adultvideo.core.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.markzhai.adultvideo.R;
import com.markzhai.adultvideo.core.model.empflix.EmpflixCategory;

/**
 * Created by marktlzhai on 2015/4/1.
 */
public class PickCategoryDialog extends Dialog {

    public interface CategorySelectedCallback {
        void onSelected(EmpflixCategory category);
    }

    private RelativeLayout rootView;

    private GridView categoryView;

    private CategorySelectedCallback callback;

    public PickCategoryDialog(Context context) {
        super(context, com.markzhai.library.R.style.BaseDialog);
    }

    public PickCategoryDialog(Context context, int theme) {
        super(context, com.markzhai.library.R.style.BaseDialog);
    }

    protected PickCategoryDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = (RelativeLayout) LayoutInflater.from(getContext()).inflate(R.layout.dialog_pick_category, null, false);

        categoryView = (GridView) rootView.findViewById(R.id.category_grid);
        CategoryAdapter adapter = new CategoryAdapter();
        categoryView.setAdapter(adapter);

        setContentView(rootView);
    }

    public void setCategoryCallback(CategorySelectedCallback callback) {
        this.callback = callback;
    }

    class CategoryAdapter extends BaseAdapter {

        private EmpflixCategory[] categorys;

        public CategoryAdapter() {
            categorys = EmpflixCategory.values();
        }

        @Override
        public int getCount() {
            return categorys.length;
        }

        @Override
        public EmpflixCategory getItem(int position) {
            return categorys[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_category, parent, false);

            final EmpflixCategory category = getItem(position);

            Button button = (Button) convertView.findViewById(R.id.category_btn);
            button.setText(category.getNameRes());
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();

                    if (callback != null) {
                        callback.onSelected(category);
                    }
                }
            });

            return convertView;
        }
    }
}
