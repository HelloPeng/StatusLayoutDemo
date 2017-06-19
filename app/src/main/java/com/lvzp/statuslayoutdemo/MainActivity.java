package com.lvzp.statuslayoutdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.lvzp.statuslayoutdemo.state.StateHelper;

public class MainActivity extends AppCompatActivity {
    StateHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new StateHelper(this);
        helper.showProgressView();
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.error:
                helper.showErrorView();
                break;
            case R.id.empty:
                helper.showEmptyView();
                break;
            case R.id.progress:
                helper.showProgressView();
                break;
            case R.id.net_error:
                helper.showNetErrorView();
                break;
            case R.id.hide:
                helper.hideStateLayout();
                break;
            default:
                break;
        }
        return true;
    }

}
