package com.terroir.caisse;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class FavorisActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textview = new TextView(this);
        textview.setText("This is Apple tab");
        setContentView(textview);
    }
}