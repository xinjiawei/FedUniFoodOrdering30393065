package au.edu.federation.itech3106.fedunifoodordering30393065;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public class OrderHistoryActivity extends AppCompatActivity {
    private static final int request = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orderhistoryactivity);
        //
        int[] radio_id = {R.id.history1, R.id.history2, R.id.history3, R.id.history4,
                R.id.history5, R.id.history6, R.id.history7, R.id.history8, R.id.history9, R.id.history10};
        SharedPreferences sharedPreferences = getSharedPreferences("data2", Context.MODE_PRIVATE);
        String msg3 = sharedPreferences.getString("1214-2.1","");
        int i = sharedPreferences.getInt("history_order",0);
        //
        String order_his = sharedPreferences.getString("order_all","");
        Log.e("1226-1",order_his);
        //
        RadioButton his1 = (RadioButton) findViewById(radio_id[i]);
        his1.setText("Extras:" + msg3);

        //TODO is apk started just now?
        Intent intent_backcode = new Intent();
        intent_backcode.putExtra("back_code_string","back_code_string:3");
        setResult(2,intent_backcode);
        //
        Intent intent_get_request_code = getIntent();
        String values = intent_get_request_code.getStringExtra("data");
        Log.e("intent2",values);
        //
    }

    public void onClickHistoryConfirm (View view) {
        Log.e("1206-1.4", "1206");
        //-----------------------------------------------------
        Intent intent = new Intent(OrderHistoryActivity.this,MakeOrderActivity.class);
        intent.putExtra("data","this is OrderHistoryActivity");
        startActivityForResult(intent,request);
        //-----------------------------------------------------
        SharedPreferences sharedPreferences= getSharedPreferences("data2", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("reorder",1);
        editor.commit();
    }
}
