package com.aven.demo.testdemo.card;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.aven.demo.testdemo.R;

import java.util.ArrayList;
import java.util.List;


public class TestCdAdapter extends RecyclerView.Adapter<TestCdAdapter.ViewHolder> {
    private List<Integer> mList = new ArrayList<>();

    public TestCdAdapter(List<Integer> list) {
        mList.clear();
        mList.addAll(list);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_test_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mImageView.setImageResource(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void repalceAll(List<Integer> list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void removeItem(int layoutPosition) {
        Object remove = mList.remove(layoutPosition);
        mList.add(0, (Integer) remove);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView mImageView;

        public ViewHolder(final View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.imageView);
        }

    }

}
