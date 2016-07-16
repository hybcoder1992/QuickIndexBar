package com.hyb.quickindex.dapter;

import java.util.List;

import com.hyb.quickindex.R;
import com.hyb.quickindex.model.Friend;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class FriendAdapter extends BaseAdapter {
	List<Friend> friends;
	Context mContext;
	public FriendAdapter() {
		// TODO Auto-generated constructor stub
	}
	
	public FriendAdapter(Context context,List<Friend> friends) {
		super();
		this.friends = friends;
		this.mContext=context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return friends.size();
	}

	@Override
	public Friend getItem(int position) {
		// TODO Auto-generated method stub
		return friends.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder=null;
		if(convertView==null){
			convertView=View.inflate(mContext, R.layout.layout_friend, null);
			holder=new ViewHolder();
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder)convertView.getTag();
		}
		Friend friend=friends.get(position);
		holder.tv_head=(TextView)convertView.findViewById(R.id.tv_head);
		holder.tv_name=(TextView)convertView.findViewById(R.id.tv_name);
		holder.tv_name.setText(friend.getName());
		holder.tv_head.setText(friend.getPinyin().charAt(0)+"");
		String currentPinyin=null;
		String lastPinyin=null;
		holder.tv_head.setVisibility(View.VISIBLE);
		if(position>0)
		{
			lastPinyin=friends.get(position-1).getPinyin().charAt(0)+"";
			currentPinyin=friend.getPinyin().charAt(0)+"";
			if(currentPinyin.equals(lastPinyin))
			{
				holder.tv_head.setVisibility(View.GONE);
			}
			else 
			{
				holder.tv_head.setVisibility(View.VISIBLE);
			}
		}
		
		return convertView;
	}
	class ViewHolder{
		public TextView tv_head,tv_name;
	}
}
