package au.edu.federation.itech3106.fedunifoodordering30393065;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmOrderActivity extends AppCompatActivity{
    private static final int request = 100;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmorder);
        //-----------------------------------------------
        SharedPreferences sharedPreferences= getSharedPreferences("data2", Context.MODE_PRIVATE);
        String msg2=sharedPreferences.getString("1214-2.1","");
        String price=sharedPreferences.getString("1214-2.2","");
        int count = sharedPreferences.getInt("1214-2.3",1);
        Log.e("1212-2",msg2);
        Log.e("1212-2",price);
        Log.e("1214-2", String.valueOf(count));
        //-----------------------------------------------
        this.setTitle("Confirm Order");
        //-----------------------------------------------
        String msg = "Extras:" + msg2;
        TextView food = this.findViewById(R.id.textView3);
        food.setTextSize(20);
        food.setText(msg);
        //-----------------------------------------------
        Button button = (Button) findViewById(R.id.place_order);
        button.setText("PLACE ORDER($" + price + ")");
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
}

