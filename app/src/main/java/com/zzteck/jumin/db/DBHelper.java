/**
 * 项目名称：PublcNumber
 * 文件名：DBHelper.java 
 * 2015-1-6-上午10:26:00
 * 2015 万家恒通公司-版权所有
 * @version 1.0.0
 */
package com.zzteck.jumin.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	private Context mContext;

	private static DBHelper dbHelper = null;

	private static String tag = "DBHelper";
	
	public static final String DATABASE_NAME = "live.db";

	public static final int DATABASE_VERSION = 23;// 默认15作为版本号,作为最开始的版本号

	private static final String DROP_TABLE_IM = "drop table if exists im_record" ;

	private static final String DROP_TABLE_IM_RECORD = "drop table if exists im_record_count" ;

	private static final String CREATE_TABLE_IM = "create table if not exists im_record ("
			+ "id integer primary key ,"
			+ "content text, "
			+ "type integer)" ;

	private static final String CREATE_TABLE_IM_COUNT = "create table if not exists im_record_count("
			+ "id integer primary key AUTOINCREMENT ,"
			+ "user_id text, "
			+ "im_count integer)" ;

	private static final String CREATE_TABLE_COINS = "create table if not exists coins("
			+ "id integer primary key AUTOINCREMENT ,"
			+ "uid text, "
			+ "targetId text, "
			+ "method text, "
			+ "type integer)" ;
	
	public SQLiteDatabase getSQLiteDatabaseObject(){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        return database ;
	}

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.mContext = context;
	}

	public static synchronized DBHelper getInstance(Context context) {
		
		if (null == dbHelper) {
			dbHelper = new DBHelper(context);
		}
		
		return dbHelper;
	}

	/**
	 * 初始化数据库的表
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DROP_TABLE_IM);
		db.execSQL(DROP_TABLE_IM_RECORD);
		db.execSQL(CREATE_TABLE_IM);
		db.execSQL(CREATE_TABLE_IM_COUNT);
		db.execSQL(CREATE_TABLE_COINS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		onCreate(db);
	}

}
