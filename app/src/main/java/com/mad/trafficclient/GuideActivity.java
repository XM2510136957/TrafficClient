package com.mad.trafficclient;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.mad.trafficclient.login.LoginActivity;

public class GuideActivity extends Activity {

	RelativeLayout guide_RL;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_guide);
		guide_RL = (RelativeLayout) findViewById(R.id.guide_RL);
		//改错一：判断是否为第一次进入app，第一次进入后保存状态
		panduan();
	}

	//将状态改为true
	private void status() {
		SharedPreferences sharedPreferences = getSharedPreferences("GuideLog",MODE_PRIVATE);
		SharedPreferences.Editor edit = sharedPreferences.edit();

		edit.putBoolean("isFrist",true);

//		edit.commit();
		edit.apply();
	}

	private void panduan() {
		SharedPreferences sharedPreferences = getSharedPreferences("GuideLog",MODE_PRIVATE);
		boolean isFrist = sharedPreferences.getBoolean("isFrist", false);
		if (isFrist){
			Intent intent = new Intent(GuideActivity.this,
					LoginActivity.class);
			startActivity(intent);
			finish();
		}else {
			guide_RL.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(GuideActivity.this,
							LoginActivity.class);
					status();
					startActivity(intent);
					finish();
				}
			});
		}
	}

}
