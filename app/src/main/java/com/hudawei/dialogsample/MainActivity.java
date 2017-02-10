package com.hudawei.dialogsample;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.hudawei.dialogsample.dialog.NotifyDialog;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private boolean[] checkResults=new boolean[]{false,false,true,true,false,false,false,true,true,false};
    private ProgressDialog progressDialog;
    final static int UPGRADE_PROGRESS=1;
    private int progress;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==UPGRADE_PROGRESS){
                int progress=(int)msg.obj;
                progressDialog.setProgress(progress);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void normalDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog dialog = builder
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("系统提示")
                .setMessage("这是一个普通的AlertDialog")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "选择是", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "选择否", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNeutralButton("中立", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "选择中立", Toast.LENGTH_SHORT).show();
                    }
                })
                .create();
        dialog.show();
    }

    public void listDialog(View view) {
        final String[] lists={"1","2","3","4","5","6","7","8","9","10"};
        new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("系统提示")
//                .setMessage("这是一个普通的AlertDialog")
                .setItems(lists, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position) {
                        Toast.makeText(MainActivity.this, "选择："+lists[position], Toast.LENGTH_SHORT).show();
                    }
                })
                .create()
                .show();
    }

    public void singleListDialog(View view) {
        final String[] lists={"1","2","3","4","5","6","7","8","9","10"};
        new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("系统提示")
//                .setMessage("这是一个普通的AlertDialog")
                .setSingleChoiceItems(lists, 5, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position) {
                        Toast.makeText(MainActivity.this, "选择："+lists[position], Toast.LENGTH_SHORT).show();
                    }
                })
                .create()
                .show();
    }

    public void multiListDialog(View view) {
        final CharSequence[] lists={"1","2","3","4","5","6","7","8","9","10"};
        new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("系统提示")
//                .setMessage("这是一个普通的AlertDialog")
                .setMultiChoiceItems(lists, checkResults, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                        checkResults[position]=isChecked;
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .create()
                .show();

    }

    public void circleProgress(View view){
        ProgressDialog dialog=new ProgressDialog(this);
        dialog.setCancelable(true);
        dialog.setTitle("提示");
        dialog.setMessage("数据加载中...");
        dialog.show();
//        ProgressDialog.show(this,"提示","数据加载中...");

    }
    public void hor1Progress(View view){
        ProgressDialog dialog=new ProgressDialog(this);
        dialog.setTitle("提示");
        dialog.setMessage("更新中...");
        dialog.setCancelable(true);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setIndeterminate(true);
        dialog.setProgressPercentFormat(null);
        dialog.setProgressNumberFormat(null);
        dialog.show();
    }
    public void hor2Progress(View view){


        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("提示");
        progressDialog.setMessage("更新中...");
        progressDialog.setCancelable(true);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setIndeterminate(false);
        progressDialog.setMax(100);
        progressDialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(progress<100) {
                    try {
                        Thread.sleep(100);
                        handler.obtainMessage(UPGRADE_PROGRESS, ++progress).sendToTarget();
                    } catch (InterruptedException e) {

                    }
                }
            }
        }).start();
    }

    public void datePicker(View view){
        Calendar calendar=Calendar.getInstance();
       DatePickerDialog datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayofMonth) {
                Toast.makeText(MainActivity.this, year+"/"+month+"/"+dayofMonth, Toast.LENGTH_SHORT).show();
            }
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }
    public void timePicker(View view){
        Calendar calendar=Calendar.getInstance();
        TimePickerDialog timePickerDialog=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                Toast.makeText(MainActivity.this, hour+":"+minute, Toast.LENGTH_SHORT).show();
            }
        },
        calendar.get(Calendar.HOUR),
        calendar.get(Calendar.MINUTE),
        true
        );
        timePickerDialog.show();
    }

    public void customDialog(View view){
        NotifyDialog dialog= (NotifyDialog) new NotifyDialog(this,R.layout.notify_dialog)
                .animation(R.style.dialogAnimation)
                .width(600)
                .height(600)
                .gravity(Gravity.BOTTOM)
//                .offsetX(100)
//                .offsetY(100)
                .alpha(1)
                ;

        dialog.show();
    }
}
