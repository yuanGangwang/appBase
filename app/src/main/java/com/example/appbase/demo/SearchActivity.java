package com.example.appbase.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.appbase.R;
import com.recycler.adapter.base.BaseQuickAdapter;
import com.recycler.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

import dev.common.weight.search.SearchView;

public class SearchActivity extends AppCompatActivity {

    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchView = (SearchView) findViewById(R.id.search);
        searchView.setSearChKeyClickListener(new SearchView.OnSearChKeyClickListener() {
            @Override
            public void onSearch(String search) {
                Toast.makeText(SearchActivity.this, search+"", Toast.LENGTH_SHORT).show();
                setAdapter();
            }

            @Override
            public void onTextChange(String search) {

            }
        });

    }

    private void setAdapter() {
        EmptyViewAdapter emptyViewAdapter = new EmptyViewAdapter();
        emptyViewAdapter.getData().add("1");
        emptyViewAdapter.getData().add("1");
        emptyViewAdapter.getData().add("1");
        emptyViewAdapter.getData().add("1");
        emptyViewAdapter.getData().add("1");
        emptyViewAdapter.getData().add("1");
        emptyViewAdapter.getData().add("1");
        emptyViewAdapter.getData().add("1");
        searchView.setAdapter(emptyViewAdapter);
    }


    public class EmptyViewAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public EmptyViewAdapter() {
            super(R.layout.naviga_layout);
        }

        @Override
        protected void convert(@NotNull BaseViewHolder helper, @NotNull String item) {
            helper.setText(R.id.navTitle, "position = " + helper.getLayoutPosition());

        }
    }
}
