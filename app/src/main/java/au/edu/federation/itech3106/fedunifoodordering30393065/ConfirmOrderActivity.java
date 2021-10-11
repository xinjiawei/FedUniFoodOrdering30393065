package au.edu.federation.itech3106.fedunifoodordering30393065;

import android.app.Instrumentation;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.widget.RadioGroup.OnCheckedChangeListener;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Timer;
import java.util.TimerTask;

public class ConfirmOrderActivity<price> extends AppCompatActivity {
    private static final int request = 100;
    private RadioButton Meal_Big, Meal_Small, No_Meal;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmorder);
        //-----------------------------------------------
        SharedPreferences sharedPreferences = getSharedPreferences("data2", Context.MODE_PRIVATE);
        String msg2 = sharedPreferences.getString("1214-2.1", "");
        float price = sharedPreferences.getFloat("1214-2.2", 0);
        //price = price_float;
        int count = sharedPreferences.getInt("1214-2.3", 1);
        String ca = sharedPreferences.getString("ca", "");
        this.setTitle("Confirm Order");
        //-----------------------------------------------
        String msg = "Extras:" + msg2 + " (" + count + " total)";
        TextView food = this.findViewById(R.id.textView3);
        food.setTextSize(20);
        if (count >= 4 && count < 7) {
            food.setTextSize(15);
            food.setText(msg);
        } else if (count >= 7) {
            food.setTextSize(12);
            food.setText(msg);
        } else {
            food.setText(msg);
        }
        Log.e("1212-2.1", msg2);
        Log.e("1212-2.2", String.valueOf(price));
        Log.e("1214-2", String.valueOf(count));
        //-----------------------------------------------
        Button button = (Button) findViewById(R.id.place_order);
        button.setText("PLACE ORDER($" + price + ")");
        //-----------------------------------------------
        if(!ca.equals("sundae")) {
            RadioGroup rg = (RadioGroup) findViewById(R.id.extra_meal);
            Meal_Big = (RadioButton) findViewById(R.id.meal_big);
            Meal_Small = (RadioButton) findViewById(R.id.meal_small);
            No_Meal = (RadioButton) findViewById(R.id.no_meal);
            //注意是给RadioGroup绑定监视器
            rg.setOnCheckedChangeListener(new MyRadioButtonListener());
        }else {
            RadioGroup myRadioGroup = (RadioGroup) findViewById(R.id.extra_meal);
            myRadioGroup.setVisibility(View.GONE);

            //RadioButton bigMeal = (RadioButton) findViewById(R.id.meal_big);
            //bigMeal.setVisibility(View.GONE);
        }

    }

    class MyRadioButtonListener implements OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            //-----------------------------------------------
            SharedPreferences sharedPreferences = getSharedPreferences("data2", Context.MODE_PRIVATE);
            float price = sharedPreferences.getFloat("1214-2.2", 0);
            //-------------------------------------------------------
            //步骤1：创建一个SharedPreferences对象
            SharedPreferences sharedPreferences_setpri = getSharedPreferences("data2", Context.MODE_PRIVATE);
            //步骤2： 实例化SharedPreferences.Editor对象
            SharedPreferences.Editor editor = sharedPreferences_setpri.edit();
            // 选中状态改变时被触发
            NumberFormat nf = NumberFormat.getNumberInstance();
            nf.setMaximumFractionDigits(2);
            double extra_price;
                switch (checkedId) {
                    case R.id.meal_big:
                        extra_price = Double.parseDouble(nf.format(price + 4.99));
                        Log.e("1216", "当前用户选择" + Meal_Big.getText().toString() + ":" + extra_price);
                        break;
                    case R.id.meal_small:
                        extra_price = Double.parseDouble(nf.format(price + 2.99));
                        Log.e("1216", "当前用户选择" + Meal_Small.getText().toString() + ":" + extra_price);
                        break;
                    case R.id.no_meal:
                        extra_price = Double.parseDouble(nf.format(price + 0));
                        Log.e("1216", "当前用户选择" + No_Meal.getText().toString() + ":" + extra_price);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + checkedId);
                }
                //步骤4：提交
                Button button = (Button) findViewById(R.id.place_order);
                button.setText("PLACE ORDER($" + extra_price + ")");
                editor.putFloat("1214-2.2.0", (float) extra_price);
                editor.commit();

        }
    }

    //TODO can not exec, but it worked???
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("1211-3", "1211-3");
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == request) {
            if (data != null) {
                String sData = data.getStringExtra("data").toString();
                //sData: A，我是B,已经收到
                Log.e("1211-3", sData);
            }
        }
    }

    public void onClickCancel(View view) {
        Log.e("1215", "1215");
        finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onClickNotice(View view) {
        Log.e("0000", String.valueOf(System.currentTimeMillis()));
        SharedPreferences sharedPreferences = getSharedPreferences("data2", Context.MODE_PRIVATE);
        String ca = sharedPreferences.getString("ca", "");
        String msg2 = sharedPreferences.getString("1214-2.1", "");
        float price = sharedPreferences.getFloat("1214-2.2", 0);
        int his_order = sharedPreferences.getInt("history_order",0);
        Log.e("1224", String.valueOf(his_order));
        if(his_order < 0){
            his_order =0;
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("history_order",his_order);
            editor.commit();

        }else {
            his_order = his_order + 1;
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("history_order",his_order);
            editor.commit();
        }
        //String.valueOf(his_order);

        String cas = "";
        switch (ca) {
            case "burger":
                cas = "Burger";
                break;
            case "pizza":
                cas = "Pizza";
                break;
            case "sundae":
                cas = "Sundae";
                break;
            default:
                cas = "**";
                break;
        }
        Log.e("0000", msg2);
        Log.e("0000", String.valueOf(price));
        //
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent back_to_main = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        //----------------------1、NotificationManager----------------------------
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        /** 2、Builder->Notification
         *  必要属性有三项
         *  小图标，通过 setSmallIcon() 方法设置
         *  标题，通过 setContentTitle() 方法设置
         *  内容，通过 setContentText() 方法设置*/
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,ca);
        builder
                .setContentText(msg2)//设置通知内容
                .setContentTitle(cas + " Ready!")//设置通知标题
                .setSmallIcon(R.mipmap.ic_launcher_round)//不能缺少的一个属性
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(msg2))
                .setContentIntent(back_to_main)
                .setAutoCancel(true);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(ca, "Notice_ohhhh", NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableLights(false); //是否在桌面icon右上角展示小红点
            //channel.setLightColor(Color.GREEN); //小红点颜色
            //channel.setShowBadge(true); //是否在久按桌面图标时显示此渠道的通知
            manager.createNotificationChannel(channel);
        }

        //-------------------------------------------------------
        int finalHis_order = his_order;
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                    //do soming
                //Log.e("TimerTask", msg2);
                Notification n = builder.build();
                //3、manager.notify()
                manager.notify(finalHis_order, n);
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 3000);//3秒后执行TimeTask的run方法
        //-------------------------------------------------------

    }

}