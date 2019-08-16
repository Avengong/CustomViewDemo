package com.aven.demo.testdemo;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aven.demo.testdemo.carousellayoutmanager.CarouselLayoutManager;
import com.aven.demo.testdemo.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.aven.demo.testdemo.carousellayoutmanager.CenterScrollListener;
import com.aven.demo.testdemo.carousellayoutmanager.DefaultChildSelectionListener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class CarouselPreviewActivity extends AppCompatActivity {

    private static Context mContext;
    private TestAdapter mAdapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carousel_preview);

        RecyclerView listHorizontal= findViewById(R.id.list_horizontal);
        RecyclerView listVertical= findViewById(R.id.list_vertical);

        mContext=getApplicationContext();
        // create layout manager with needed params: vertical, cycle
        initRecyclerView(listHorizontal, new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL, false));
//        initRecyclerView(listVertical, new CarouselLayoutManager(CarouselLayoutManager.VERTICAL, true), adapter);

        // fab button will add element to the end of the list
//        binding.fabScroll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(final View v) {
///*
//                final int itemToRemove = adapter.mItemsCount;
//                if (10 != itemToRemove) {
//                    adapter.mItemsCount++;
//                    adapter.notifyItemInserted(itemToRemove);
//                }
//*/
//                binding.listHorizontal.smoothScrollToPosition(adapter.getItemCount() - 2);
//                binding.listVertical.smoothScrollToPosition(adapter.getItemCount() - 2);
//            }
//        });

        // fab button will remove element from the end of the list
//        binding.fabChangeData.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(final View v) {
///*
//                final int itemToRemove = adapter.mItemsCount - 1;
//                if (0 <= itemToRemove) {
//                    adapter.mItemsCount--;
//                    adapter.notifyItemRemoved(itemToRemove);
//                }
//*/
//                binding.listHorizontal.smoothScrollToPosition(1);
//                binding.listVertical.smoothScrollToPosition(1);
//            }
//        });
    }

    private void initRecyclerView(final RecyclerView recyclerView, final CarouselLayoutManager layoutManager) {
        mAdapter = new TestAdapter();
        // enable zoom effect. this line can be customized
        layoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());
        layoutManager.setMaxVisibleItems(1);

        recyclerView.setLayoutManager(layoutManager);
        // we expect only fixed sized item for now
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);//禁止滑动
        // sample adapter with random data
        recyclerView.setAdapter(mAdapter);
        // enable center post scrolling
        recyclerView.addOnScrollListener(new CenterScrollListener());
        // enable center post touching on item and item click listener
        DefaultChildSelectionListener.initCenterItemListener(new DefaultChildSelectionListener.OnCenterItemClickListener() {
            @Override
            public void onCenterItemClicked(@NonNull final RecyclerView recyclerView, @NonNull final CarouselLayoutManager carouselLayoutManager, @NonNull final View v) {
                final int position = recyclerView.getChildLayoutPosition(v);
                final String msg = String.format(Locale.US, "Item %1$d was clicked", position);
                AccountBean item = mAdapter.getItem(position);
                System.out.println("点击了 position "+position+" name "+item.getName());
                Toast.makeText(CarouselPreviewActivity.this, msg, Toast.LENGTH_SHORT).show();
            }



        }, recyclerView, layoutManager);

        layoutManager.addOnItemSelectionListener(new CarouselLayoutManager.OnCenterItemSelectionListener() {

            @Override
            public void onCenterItemChanged(final int adapterPosition) {
                if (CarouselLayoutManager.INVALID_POSITION != adapterPosition) {
//                    final int value = mAdapter.mPosition[adapterPosition];
                    AccountBean item = mAdapter.getItem(adapterPosition);

                    System.out.println("onCenterItemChanged position "+adapterPosition+" name "+item.getName());

/*
                    adapter.mPosition[adapterPosition] = (value % 10) + (value / 10 + 1) * 10;
                    adapter.notifyItemChanged(adapterPosition);
*/
                }
            }
        });


    }

    public void setflingfact(RecyclerView recyclerView,int velocity){
        try{
            Field mMaxFlingVelocity = recyclerView.getClass().getDeclaredField("mMaxFlingVelocity");
            mMaxFlingVelocity.setAccessible(true);
            mMaxFlingVelocity.set(recyclerView,velocity);
        }catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public void  update(View view){
        List<AccountBean> accountBeans = new ArrayList<>();
        for (int i = 0; 3 > i; ++i) {
            AccountBean accountBean = new AccountBean();
            accountBean.setId(i);
            accountBean.setName("update "+i);
            accountBeans.add(accountBean);
        }
        mAdapter.replaceAll(accountBeans);
    }

    private static final class TestAdapter extends RecyclerView.Adapter<TestViewHolder> {

        @SuppressWarnings("UnsecureRandomNumberGeneration")
        private final Random mRandom = new Random();
        private final int[] mColors;
        private List<AccountBean> mAccountLst = new ArrayList<>();
        private int mItemsCount = 5;

        TestAdapter() {
            mAccountLst.clear();
            mColors = new int[mItemsCount];
            for (int i = 0; mItemsCount > i; ++i) {
                //noinspection MagicNumber
                mColors[i] = Color.argb(255, mRandom.nextInt(256), mRandom.nextInt(256), mRandom.nextInt(256));

                AccountBean accountBean = new AccountBean();
                accountBean.setId(100+i);
                accountBean.setName("number "+i);
                mAccountLst.add(accountBean);
            }
        }

        @Override
        public TestViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
            Log.e("!!!!!!!!!", "onCreateViewHolder");
            return new TestViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view,parent,false));
        }

        @Override
        public void onBindViewHolder(final TestViewHolder holder, final int position) {
            Log.e("!!!!!!!!!", "onBindViewHolder: " + position);
//            holder.mItemViewBinding.cItem1.setText(String.valueOf(mPosition[position]));
//            holder.mItemViewBinding.cItem2.setText(String.valueOf(mPosition[position]));
            AccountBean accountBean = mAccountLst.get(position);
            holder.itemView.setBackgroundColor(mColors[position]);
            holder.mTextView.setText(accountBean.getName());
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Toast.makeText(mContext, "长按事件", Toast.LENGTH_SHORT).show();
                    return false;
                }
            });
        }

        @Override
        public int getItemCount() {
            return mAccountLst.size();
        }

        @Override
        public long getItemId(final int position) {
            return position;
        }

        public AccountBean getItem(int adapterPosition) {
            return mAccountLst.get(adapterPosition);
        }

        public void replaceAll(List<AccountBean> ccountLst) {
            mAccountLst.clear();
            mAccountLst.addAll(ccountLst);
            notifyDataSetChanged();
        }
    }

    private static class TestViewHolder extends RecyclerView.ViewHolder {

        private final View itemView;
        private final TextView mTextView;

        TestViewHolder(final View itemViewBinding) {
            super(itemViewBinding);

            itemView = itemViewBinding.findViewById(R.id.lltsdf);
            mTextView = itemViewBinding.findViewById(R.id.c_item_1);
        }
    }
}