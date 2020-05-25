package com.example.appbase.demo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbase.R;
import com.recycler.adapter.base.BaseQuickAdapter;
import com.recycler.adapter.base.viewholder.BaseViewHolder;
import com.example.appbase.dialog.BaseDialog;
import com.example.appbase.dialog.BaseDialogController;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class DialogActivity extends AppCompatActivity {

    BaseDialog picDialog;
    private BaseDialog commonDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_iialog);

        findViewById(R.id.common)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showCommon();
                    }
                });

        findViewById(R.id.sysDialog)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showSys();
                    }
                });
        findViewById(R.id.picDialog)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showPic();
                    }
                });
        findViewById(R.id.divDialog)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDiv();
                    }
                });
    }

    public void showCommon() {
        commonDialog = new BaseDialog.Builder(this, R.style.transDialogTheme)
                .setCanCancel(true)
                .setTitle("基础对话框")
                .setMessage("基础信息展示")
                .setLeftButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showToast("cancel");
                        commonDialog.dismiss();
                    }
                })
                .setRightButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showToast("confirm");
                        commonDialog.dismiss();
                    }
                }).create();
        commonDialog.show();
    }

    public void showDiv() {
        RecyclerView recyclerView = new RecyclerView(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<String> data = new ArrayList<>();
        data.add("1");

        recyclerView.setAdapter(new DivAdapter(data));

        commonDialog = new BaseDialog.Builder(this, R.style.transDialogTheme)
                .setCanCancel(true)
                .setTitle("自定义内容布局对话框(列表)")
                .setCustomerView(recyclerView)
                .setLeftButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showToast("cancel");
                        commonDialog.dismiss();
                    }
                })
                .setRightButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showToast("confirm");
                        commonDialog.dismiss();
                    }
                }).create();
        commonDialog.show();
    }

    public void showPic() {
        picDialog = new BaseDialog.ImageBuilder(this, R.style.transDialogTheme)
                .setDealImg(new BaseDialogController.DealDialogImg() {
                    @Override
                    public void onImgDeal(@Nullable ImageView img) {
                        img.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                picDialog.dismiss();
                            }
                        });
                    }
                }).create();
        picDialog.show();
    }

    public void showSys() {
        new AlertDialog.Builder(this)
                .setTitle("系统")
                .setMessage("信息展示")
                .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        showToast("confirm");
                    }
                })
                .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showToast("cancel");
                        dialog.dismiss();
                    }
                })
                .show();
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    class DivAdapter extends BaseQuickAdapter<String, DivAdapter.ViewHolder> {


        public DivAdapter(ArrayList<String> data) {
            super(R.layout.setting_item_layout, data);
        }

        @Override
        protected void convert(@NotNull ViewHolder holder, String item) {
            holder.text.setText(holder.getLayoutPosition()+"");
        }

        public class ViewHolder extends BaseViewHolder {
            private TextView text;

            public ViewHolder(@NotNull View view) {
                super(view);
                text = findView(R.id.settTitle);
            }
        }
    }
}
