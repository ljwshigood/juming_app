/**
 * 项目名称：PublcNumber
 * 文件名：DBHelper.java 
 * 2015-1-6-上午10:26:00
 * 2015 万家恒通公司-版权所有
 * @version 1.0.0
 */
package com.zzteck.jumin.db;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.zzteck.jumin.R;

public class DBHelper extends SQLiteOpenHelper {

	private Context mContext;

	private static DBHelper dbHelper = null;

	private static String tag = "DBHelper";
	
	public static final String DATABASE_NAME = "juming.db";
	
	public SQLiteDatabase getSQLiteDatabaseObject(){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        return database ;
	}

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, 15);
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

		Log.e(tag, "start create table");
		InputStream in = null;
		in = mContext.getResources().openRawResource(R.raw.database_init);
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		StringBuilder sql = new StringBuilder();
		String line;
		try {
			db.beginTransaction();
			while ((line = reader.readLine()) != null) {
				line = line.trim();
				sql.append("\n").append(line);
				if (line.endsWith(";")) {
					db.execSQL(sql.toString());
					Log.e("liujw",sql.toString()+ "生成");
					sql = new StringBuilder();
				}
			}
			db.setTransactionSuccessful();
			reader.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.endTransaction();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.v(tag, "Upgrading airs database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
		onCreate(db);
	}

}
