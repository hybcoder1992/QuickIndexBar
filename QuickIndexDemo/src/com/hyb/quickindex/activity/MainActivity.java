package com.hyb.quickindex.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.hyb.quickindex.R;
import com.hyb.quickindex.dapter.FriendAdapter;
import com.hyb.quickindex.model.Friend;
import com.hyb.quickindex.view.QuickIndexBar;
import com.hyb.quickindex.view.QuickIndexBar.OnQuickIndexBarTouchListener;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private ListView listView;
	private QuickIndexBar quickIndexBar;
	List<Friend> friends;
	private TextView tv_bg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		fillList();
		listView = (ListView)findViewById(R.id.listView);
		listView.setAdapter(new FriendAdapter(this, friends));
		tv_bg = (TextView)findViewById(R.id.tv_bg);
		quickIndexBar = (QuickIndexBar)findViewById(R.id.quickIndexBar);
		quickIndexBar.setQuickIndexBarTouchListener(new OnQuickIndexBarTouchListener() {
			
			@Override
			public void onTouchListener(String letter) {
				// TODO Auto-generated method stub
				//根据触摸的字母,去集合中找哪个item的首字母和letter一样,然后将对应的item防盗屏幕顶端
				for(int i=0;i<friends.size();i++){
					String first_letter=friends.get(i).getPinyin().charAt(0)+"";
					if(letter.equals(first_letter)){
						listView.setSelection(i);
						break;
					}
				}
				//显示当前触摸字母
				tv_bg.setText(letter);
				tv_bg.setVisibility(View.VISIBLE);
				//先移除之前的任务
				handler.removeCallbacksAndMessages(null);
				//延时隐藏
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						tv_bg.setVisibility(View.GONE);
					}
				}, 500);
			}
		});
	}
	private Handler handler=new Handler();
	private void fillList() {
		friends=new ArrayList<Friend>();
		// 虚拟数据
		friends.add(new Friend("李伟"));
		friends.add(new Friend("张三"));
		friends.add(new Friend("阿三"));
		friends.add(new Friend("阿四"));
		friends.add(new Friend("段誉"));
		friends.add(new Friend("段正淳"));
		friends.add(new Friend("张三丰"));
		friends.add(new Friend("陈坤"));
		friends.add(new Friend("林俊杰1"));
		friends.add(new Friend("陈坤2"));
		friends.add(new Friend("王二a"));
		friends.add(new Friend("林俊杰a"));
		friends.add(new Friend("张四"));
		friends.add(new Friend("林俊杰"));
		friends.add(new Friend("王二"));
		friends.add(new Friend("王二b"));
		friends.add(new Friend("赵四"));
		friends.add(new Friend("杨坤"));
		friends.add(new Friend("赵子龙"));
		friends.add(new Friend("杨坤1"));
		friends.add(new Friend("李伟1"));
		friends.add(new Friend("宋江"));
		friends.add(new Friend("宋江1"));
		friends.add(new Friend("李伟3"));
		Collections.sort(friends);//对friends集合根据拼音排序
	}
}
