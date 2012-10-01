package com.uphyca.testing.robolectric;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.uphyca.testing.robolectric.test.R;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
