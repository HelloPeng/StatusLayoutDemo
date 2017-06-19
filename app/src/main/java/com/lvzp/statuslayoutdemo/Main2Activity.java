package com.lvzp.statuslayoutdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.lvzp.statuslayoutdemo.state.StateHelper;

public class Main2Activity extends AppCompatActivity {

    private StateHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //设置返回键可用
        helper = new StateHelper(this);
        helper.bindTitleView(toolbar);
        //处理点击
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, Main3Activity.class);
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
        }
        return true;
    }


}
