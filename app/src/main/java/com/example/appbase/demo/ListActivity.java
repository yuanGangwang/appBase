package com.example.appbase.demo;

import android.graphics.Canvas;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbase.R;
import com.recycler.adapter.base.BaseMultiItemQuickAdapter;
import com.recycler.adapter.base.BaseQuickAdapter;
import com.recycler.adapter.base.entity.MultiItemEntity;
import com.recycler.adapter.base.listener.OnItemSwipeListener;
import com.recycler.adapter.base.module.DraggableModule;
import com.recycler.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private static final String TAG = "ListActivity";
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        findViewById(R.id.adapterNormal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNormalAdapter();
            }
        });

        findViewById(R.id.adapterEmpty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEmptyAdapter();
            }
        });

        findViewById(R.id.adapterMoreItem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMoreItemAdapter();
            }
        });
        findViewById(R.id.dragAdapter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDragAdapter();
            }
        });
        findViewById(R.id.header).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setHeader();
            }
        });

        recyclerView = ((RecyclerView) findViewById(R.id.listView));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setHeader() {
        EmptyViewAdapter emptyViewAdapter = new EmptyViewAdapter();
        for (int i = 0; i < 30; i++) {
            emptyViewAdapter.addData("");
        }
        emptyViewAdapter.addHeaderView(LayoutInflater.from(this).inflate(R.layout.header, recyclerView, false));
        emptyViewAdapter.addFooterView(LayoutInflater.from(this).inflate(R.layout.footer, recyclerView, false));
        recyclerView.setAdapter(emptyViewAdapter);
        emptyViewAdapter.notifyDataSetChanged();
    }

    private void setDragAdapter() {
        ArrayList<String> data = new ArrayList<>();
        data.add("1");
        data.add("1");
        data.add("1");
        data.add("1");
        data.add("1");
        data.add("1");
        data.add("1");
        data.add("1");
        data.add("1");
        DragAdapter dragAdapter = new DragAdapter(data);
        dragAdapter.getDraggableModule().setSwipeEnabled(true);
        dragAdapter.getDraggableModule().setDragEnabled(false);
        // 监听
        OnItemSwipeListener onItemSwipeListener = new OnItemSwipeListener() {
            @Override
            public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {
                Log.d(TAG, "view swiped start: " + pos);
                BaseViewHolder holder = ((BaseViewHolder) viewHolder);
            }

            @Override
            public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {
                Log.d(TAG, "View reset: " + pos);
                BaseViewHolder holder = ((BaseViewHolder) viewHolder);
            }

            @Override
            public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) {
                Log.d(TAG, "View Swiped: " + pos);
            }

            @Override
            public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float dX, float dY, boolean isCurrentlyActive) {
                canvas.drawColor(ContextCompat.getColor(ListActivity.this, R.color.colorAccent));
            }
        };
        dragAdapter.getDraggableModule().setOnItemSwipeListener(onItemSwipeListener);
//        dragAdapter.getDraggableModule().setOnItemDragListener(this);
        dragAdapter.getDraggableModule().getItemTouchHelperCallback().setSwipeMoveFlags(ItemTouchHelper.START | ItemTouchHelper.END);
        recyclerView.setAdapter(dragAdapter);
        dragAdapter.notifyDataSetChanged();
    }

    private void setNormalAdapter() {
        final EmptyViewAdapter adapter = new EmptyViewAdapter();
        adapter.getData().add("1");
        adapter.getData().add("1");
        adapter.getData().add("1");
        adapter.getData().add("1");
        adapter.getData().add("1");
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void setMoreItemAdapter() {
        MoreItemAdapter moreItemAdapter = new MoreItemAdapter();
        List<MoreItem> data = moreItemAdapter.getData();
        data.add(new MoreItem(MoreItem.IMG));
        data.add(new MoreItem(MoreItem.Text));
        data.add(new MoreItem(MoreItem.Text));
        data.add(new MoreItem(MoreItem.Text));
        data.add(new MoreItem(MoreItem.IMG));
        data.add(new MoreItem(MoreItem.Text));
        recyclerView.setAdapter(moreItemAdapter);
        moreItemAdapter.notifyDataSetChanged();
    }

    private void setEmptyAdapter() {
        final EmptyViewAdapter adapter = new EmptyViewAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setEmptyView(R.layout.loading_layout);
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(1000);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.getData().add("1");
                        adapter.getData().add("1");
                        adapter.getData().add("1");
                        adapter.getData().add("1");
                        adapter.getData().add("1");
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }

    static class MoreItem implements MultiItemEntity {
        static int Text = 1;
        static int IMG = 2;

        public int Type;

        public MoreItem(final int type) {
            Type = type;
        }


        @Override
        public int getItemType() {
            return Type;
        }
    }


    public class EmptyViewAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public EmptyViewAdapter() {
            super(R.layout.naviga_layout);
        }

        @Override
        protected void convert(@NotNull BaseViewHolder helper, @NotNull String item) {
            helper.setText(R.id.navTitle, "position = " + helper.getLayoutPosition());

//            int layoutPosition = helper.getLayoutPosition();
//            View view = helper.getView("id");
//
//            helper.setTextColor("id","color");
//            helper.setVisible("id","isVisiable");
//            helper.setText("id", "内容");
//            helper.setImageResource("id", "图片url(本地)");
        }
    }

    public class MoreItemAdapter extends BaseMultiItemQuickAdapter<MoreItem, BaseViewHolder> {

        public MoreItemAdapter() {
            addItemType(MoreItem.Text, R.layout.item_txt);
            addItemType(MoreItem.IMG, R.layout.item_img);
        }

        @Override
        protected void convert(@NotNull BaseViewHolder helper, @NotNull MoreItem item) {
            if (item.getItemType() == MoreItem.Text) {
                ((Button) helper.getView(R.id.txt)).setText(helper.getLayoutPosition() + "");
            }
        }
    }

    public class DragAdapter extends BaseQuickAdapter<String, BaseViewHolder> implements DraggableModule {


        public DragAdapter(@Nullable List<String> data) {
            super(R.layout.setting_item_layout, data);
        }

        @Override
        protected void convert(@NotNull BaseViewHolder holder, String item) {
//            ((Button) holder.getView(R.id.txt)).setText(holder.getLayoutPosition() + "");
        }
    }

}
