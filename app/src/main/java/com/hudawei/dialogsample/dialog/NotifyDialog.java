package com.hudawei.dialogsample.dialog;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hudawei.dialogsample.R;

/**
 * Created by hudawei on 2017/2/10.
 */

public class NotifyDialog extends SimpleDialog implements View.OnClickListener{
    private TextView titleTextView;
    private TextView positiveText;
    private TextView negativeText;
    private TextView messageView;
    private RelativeLayout main;

    public NotifyDialog(Context context, int layoutId) {
        super(context, layoutId);
    }


    @Override
    protected void initView() {
        main=(RelativeLayout)findViewById(R.id.main);
        titleTextView=(TextView)findViewById(R.id.title);
        messageView=(TextView)findViewById(R.id.message);
        positiveText=(TextView)findViewById(R.id.positiveText);
        negativeText=(TextView)findViewById(R.id.negativeText);
    }

    @Override
    protected void initData() {
        titleTextView.setText("提示");
        messageView.setText("确定付款吗?");
        positiveText.setText("确定");
        negativeText.setText("放弃");
    }

    @Override
    protected void initEvent() {
        positiveText.setOnClickListener(this);
        negativeText.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        TextView textView=(TextView) v;
        Toast.makeText(getContext(),"选择："+textView.getText(),Toast.LENGTH_SHORT).show();
        dismiss();
    }
}
