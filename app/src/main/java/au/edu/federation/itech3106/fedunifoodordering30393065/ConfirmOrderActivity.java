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
    private RadioGroup rg;
    private RadioButton Meal_Big, Meal_Small, No_Meal;
    private static final int NOTIFICATION_ID = 1001;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmorder);
        //-----------------------------------------------
        SharedPreferences sharedPreferences = getSharedPreferences("data2", Context.MODE_PRIVATE);
        String msg2 = sharedPreferences.getString("1214-2.1", "");
        float price = sharedPreferences.getFloat("1214-2.2", 0);
        //price = price_float;
        int count = sharedPreferences.getInt("1214-2.3", 1);
        this.setTitle("Confirm Order");
        //-----------------------------------------------
        String msg = "Extras:" + msg2 + " (" + count + " total)";
        TextView food = this.findViewById(R.id.textView3);
        food.setTextSize(20);
        if (count > 4 && count < 7) {
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
        //-----------------------------------------------
        Button button = (Button) findViewById(R.id.place_order);
        button.setText("PLACE ORDER($" + price + ")");
        //-----------------------------------------------
        rg = (RadioGroup) findViewById(R.id.extra_meal);
        Meal_Big = (RadioButton) findViewById(R.id.meal_big);
        Meal_Small = (RadioButton) findViewById(R.id.meal_small);
        No_Meal = (RadioButton) findViewById(R.id.no_meal);
        //注意是给RadioGroup绑定监视器
        rg.setOnCheckedChangeListener(new MyRadioButtonListener());
    }

    class MyRadioButtonListener implements OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            /*
            //-------------------------------------------------------
            //步骤1：创建一个SharedPreferences对象
            SharedPreferences sharedPreferences= getSharedPreferences("data2",Context.MODE_PRIVATE);
            //步骤2： 实例化SharedPreferences.Editor对象
            SharedPreferences.Editor editor = sharedPreferences.edit();
            //步骤3：将获取过来的值放入文件

            //editor.putString("1214-2.1",msg20);
            //editor.putFloat("1214-2.2", (float) price);
            //editor.putInt("1214-2.3",count_num);


            //步骤4：提交
            editor.commit();
            //Log.e("1214-2.1",msg);
            //Log.e("1214-2.2",msg20);
            */
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
        String cas = "";
        if (ca == "burger") {
            cas = "Burger";
        } else if (ca == "pizza") {
            cas = "Pizza";
        } else if (ca == "sundae") {
            cas = "Sundae";
        } else {
            cas = "**";
        }
        Log.e("0000", msg2);
        Log.e("0000", String.valueOf(price));
        //1、NotificationManager
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        /** 2、Builder->Notification
         *  必要属性有三项
         *  小图标，通过 setSmallIcon() 方法设置
         *  标题，通过 setContentTitle() 方法设置
         *  内容，通过 setContentText() 方法设置*/
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentInfo("Content info")
                .setContentText(msg2)//设置通知内容
                .setContentTitle(cas + " Ready!")//设置通知标题
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setSmallIcon(R.mipmap.ic_launcher_round)//不能缺少的一个属性
                .setSubText("Now")//显示的那玩意。
                .setTicker("滚动消息" + msg2)
                .setWhen(System.currentTimeMillis());//设置通知时间，默认为系统发出通知的时间，通常不用设置

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(ca, "Notice_ohhhh", NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableLights(true); //是否在桌面icon右上角展示小红点
            channel.setLightColor(Color.GREEN); //小红点颜色
            channel.setShowBadge(true); //是否在久按桌面图标时显示此渠道的通知
            manager.createNotificationChannel(channel);
            builder.setChannelId(ca);
        }

        //-------------------------------------------------------
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                /**
                 *要执行的操作
                 */
                //Log.e("TimerTask", msg2);
                Notification n = builder.build();
                //3、manager.notify()
                manager.notify(NOTIFICATION_ID, n);
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 3000);//3秒后执行TimeTask的run方法
        //-------------------------------------------------------

    }

}