/**
 * 项目名称：PublcNumber
 * 文件名：DatabaseManager.java 
 * 2015-1-6-上午10:26:00
 * 2015 万家恒通公司-版权所有
 * @version 1.0.0
 */
package com.zzteck.jumin.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * 
 * @description: 数据库管理类，sqlite采用单例模式进行数据管理
 *
 * author : liujw
 * modify : 
 */


public class DatabaseManager {

	private Context mContext;

	private static DatabaseManager mInstance;

	private DBHelper mDBHelp = null;

	private SQLiteDatabase mSQLiteDatabase;

	public synchronized SQLiteDatabase getmSQLiteDatabase() {
		return mSQLiteDatabase;
	}

	public void setmSQLiteDatabase(SQLiteDatabase mSQLiteDatabase) {
		this.mSQLiteDatabase = mSQLiteDatabase;
	}

	private DatabaseManager(Context context) {
		this.mContext = context;
		mDBHelp = DBHelper.getInstance(context);
		mSQLiteDatabase = mDBHelp.getSQLiteDatabaseObject();
	}

	public synchronized static DatabaseManager getInstance(Context context){
		if(mInstance == null){
			mInstance = new DatabaseManager(context);
		}
		return mInstance ;
	}
	
	public synchronized void recyleCursor(Cursor cursor){
		if (cursor != null) {
			cursor.close();
			cursor = null;
		}
	}
	
	
}
