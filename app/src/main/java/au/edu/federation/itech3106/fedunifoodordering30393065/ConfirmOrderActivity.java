package au.edu.federation.itech3106.fedunifoodordering30393065;

import android.app.Instrumentation;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.widget.RadioGroup.OnCheckedChangeListener;

import java.text.NumberFormat;
import java.util.Timer;
import java.util.TimerTask;

public class ConfirmOrderActivity<price> extends AppCompatActivity {
    private static final int request = 1;
    private RadioButton Meal_Big, Meal_Small, No_Meal;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmorder);
        //-----------------------------------------------
        SharedPreferences sharedPreferences = getSharedPreferences("data2", Context.MODE_PRIVATE);
        String msg2 = sharedPreferences.getString("1214-2.1", "");
        float price = sharedPreferences.getFloat("1214-2.2", 0);
        //price = price_float;
        int extra_count = sharedPreferences.getInt("1214-2.3", 1);
        String ca = sharedPreferences.getString("ca", "");
        this.setTitle("Confirm Order");
        //-----------------------------------------------
        String cas = "";
        TextView foodcas = this.findViewById(R.id.textView2);
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
        foodcas.setText("You are order a " + cas);
        //
        String msg = "Extras:" + msg2 + " (" + extra_count + " total)";
        TextView food = this.findViewById(R.id.textView3);
        food.setTextSize(20);
        if (extra_count >= 4 && extra_count < 7) {
            food.setTextSize(15);
            food.setText(msg);
        } else if (extra_count >= 7) {
            food.setTextSize(12);
            food.setText(msg);
        } else {
            food.setText(msg);
        }
        Log.e("1212-2.1", msg2);
        Log.e("1212-2.2", String.valueOf(price));
        Log.e("1214-2", String.valueOf(extra_count));
        //-----------------------------------------------
        Button button = (Button) findViewById(R.id.place_order);
        button.setText("PLACE ORDER($" + price + ")");
        //-----------------------------------------------
        if(!ca.equals("sundae")) {
            RadioGroup rg = (RadioGroup) findViewById(R.id.extra_meal);
            Meal_Big = (RadioButton) findViewById(R.id.meal_big);
            Meal_Small = (RadioButton) findViewById(R.id.meal_small);
            No_Meal = (RadioButton) findViewById(R.id.no_meal);
            rg.setOnCheckedChangeListener(new MyRadioButtonListener());
        }else {
            RadioGroup myRadioGroup = (RadioGroup) findViewById(R.id.extra_meal);
            myRadioGroup.setVisibility(View.GONE);
        }

    }

    class MyRadioButtonListener implements OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            //-----------------------------------------------
            SharedPreferences sharedPreferences = getSharedPreferences("data2", Context.MODE_PRIVATE);
            float price = sharedPreferences.getFloat("1214-2.2", 0);
            //-------------------------------------------------------
            SharedPreferences sharedPreferences_setpri = getSharedPreferences("data2", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences_setpri.edit();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("1211-3", "1211-3");
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == request) {
            if (data != null) {
                String sData = data.getStringExtra("data").toString();
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
        Log.e("currentTimeMillis", String.valueOf(System.currentTimeMillis()));
        SharedPreferences sharedPreferences = getSharedPreferences("data2", Context.MODE_PRIVATE);
        String ca = sharedPreferences.getString("ca", "");
        String msg2 = sharedPreferences.getString("1214-2.1", "");
        float price = sharedPreferences.getFloat("1214-2.2", 0);

        int his_order = sharedPreferences.getInt("history_order",0);
        Log.e("1224", String.valueOf(his_order));
        if(his_order < 0 || his_order > 8){
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
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,ca);
        builder
                .setContentText(msg2)
                .setContentTitle(cas + " Ready!")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(msg2))
                .setContentIntent(back_to_main)
                .setAutoCancel(true);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(ca, "Notice_ohhhh", NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableLights(false);
            manager.createNotificationChannel(channel);
        }

        int finalHis_order = his_order;
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Notification n = builder.build();
                manager.notify(finalHis_order, n);
                //0TODO Need to initile his_order_count to "0"++不再需要了
                int his_order_count;
                his_order_count = sharedPreferences.getInt("his_order_count", 0);
                Log.e("1226-1", "his_order_count-old :" + his_order_count);
                his_order_count += 1;
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("his_order_count",his_order_count);
                editor.commit();
                Log.e("1226-2", "his_order_count-new :" + his_order_count);
                //===========================================================
                String order20 = sharedPreferences.getString("1214-2.4","");
                String order_all = sharedPreferences.getString("order_all","");
                order_all = order_all + "*" + ca + "#" + order20 + "@";

                Log.e("1226-3", "order-now :" + order20);
                Log.e("1226-3", "order-all :" + order_all);

                editor.putString("order_all", String.valueOf(order_all));
                editor.commit();
                //===========================================================



            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 2000);//3秒后执行TimeTask的run方法
        //-------------------------------------------------------

    }

}