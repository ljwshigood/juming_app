package com.zzteck.jumin.db;

import android.content.Context;
import android.database.Cursor;

import com.zzteck.jumin.bean.PushBean;

import java.util.ArrayList;
import java.util.List;

public class PushDAO {

	private static PushDAO mInstance ;

	private Context mContext ;

	private DatabaseManager mDatabaseManager ;

	public PushDAO(Context context){
		this.mContext = context ;
		mDatabaseManager = DatabaseManager.getInstance(context);
	}
	
	public static PushDAO getInstance(Context context){
		if(mInstance == null){
			mInstance = new PushDAO(context);
		}
		return mInstance ;
	}

	public void insertPush(PushBean bean){
		if(!isExistPush(bean)){
			String insertSQL = "insert into push(title,content,url) values (%s,%s,%s)" ;
			//insertSQL = String.format(insertSQL,bean.getTitle(),bean.getContent(),bean.getUrl());
			mDatabaseManager.getmSQLiteDatabase().execSQL(insertSQL);
		}
	}

	public boolean isExistPush(PushBean bean){
		String sql = "select * from push where id = %d" ;
		//sql = String.format(sql, bean.getId());
		Cursor cursor  = mDatabaseManager.getmSQLiteDatabase().rawQuery(sql,null);
		while(cursor != null && cursor.moveToNext()){
			return true ;
		}
		mDatabaseManager.recyleCursor(cursor) ;
		return false ;
	}

	public List<PushBean> selectPushList(){
		//String updateString = "update im_record_count set im_count = %d ,createdTime = datetime('now','localtime') where user_id = %s" ;
		String selectSQL = "select * from push order by createdTime desc" ;
		List<PushBean> list = new ArrayList<>() ;
		Cursor cursor = mDatabaseManager.getmSQLiteDatabase().rawQuery(selectSQL,null);
		while(cursor != null && cursor.moveToNext()){
			PushBean pushBean = new PushBean();
		/*	pushBean.setId(cursor.getInt(cursor.getColumnIndex("id")));
			pushBean.setTitle(cursor.getString(cursor.getColumnIndex("title")));
			pushBean.setContent(cursor.getString(cursor.getColumnIndex("content")));
			pushBean.setUrl(cursor.getString(cursor.getColumnIndex("url")));*/
			list.add(pushBean) ;
		}
		mDatabaseManager.recyleCursor(cursor) ;
		return null ;
	}

	public void deletePush(int id){
		String deleteSQL = "delete from push where id = %d" ;
		deleteSQL = String.format(deleteSQL, id);
		mDatabaseManager.getmSQLiteDatabase().execSQL(deleteSQL);
	}

	
}
