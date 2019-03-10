package com.zzteck.jumin.db;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;

import com.zzteck.jumin.bean.LoginBean;
import com.zzteck.jumin.bean.User;

public class UserDAO {
	
	private static UserDAO mInstance ; 
	
	private Context mContext ;
	
	private DatabaseManager mDatabaseManager ;

	public UserDAO(Context context){
		this.mContext = context ;
		mDatabaseManager = DatabaseManager.getInstance(context);
	}
	
	public static UserDAO getInstance(Context context){
		if(mInstance == null){
			mInstance = new UserDAO(context);
		}
		return mInstance ;
	}
	
	public synchronized void updateUserByUserId(User user){
		String updateSQL = "update user set token = ?, wanjiaToken= ? where id = (select id from user where isLogin = 1)" ;
		Object[] mValue = new Object[]{"",""};
		mDatabaseManager.getmSQLiteDatabase().execSQL(updateSQL,mValue);
	}
	
	public synchronized void updateUserInfo(User user){
		String updateSQL = "update user set role = ? where id = ?" ;
		Object[] mValue = new Object[]{1,user.getUserid()};
		mDatabaseManager.getmSQLiteDatabase().execSQL(updateSQL,mValue);
	}
	
	public synchronized void insertUserTable(LoginBean.DataBean user, int isLogin){
		if(user == null){
			return ;
		}
		String insertSQL = "insert into user values (%s,\"%s\",\"%s\",\"%s\"," +
										"%d,%d,%d," +
										"%d,\"%s\",\"%s\"," +
										"%s,\"%s\",\"%s\",%d)" ;
		
		insertSQL = String.format(insertSQL, String.valueOf(user.getId()),user.getTname(),user.getTname(),user.getTname(),
					1,1,1,
					System.currentTimeMillis(),user.getMobile(),user.getEmail(),
					String.valueOf(user.getId()),String.valueOf(user.getId()),String.valueOf(user.getId()),isLogin);
		mDatabaseManager.getmSQLiteDatabase().execSQL(insertSQL);
	}
	
	public synchronized boolean isExistRecord(String id){
		String sqlSQL = "select * from user where id = %s";
		sqlSQL = String.format(sqlSQL, id);
		Cursor cursor  = mDatabaseManager.getmSQLiteDatabase().rawQuery(sqlSQL,null);
		while(cursor != null && cursor.moveToNext()){
			return true ;
		}
		mDatabaseManager.recyleCursor(cursor) ;
		return false ;
	}

	public synchronized boolean isExistRecordLogin(){
		String sqlSQL = "select id from user where isLogin = 1";
		Cursor cursor  = mDatabaseManager.getmSQLiteDatabase().rawQuery(sqlSQL,null);
		while(cursor != null && cursor.moveToNext()){
			return true ;
		}
		mDatabaseManager.recyleCursor(cursor) ;
		return false ;
	}
	
	public synchronized void updateLoginStatus(User user){
		String updateSQL = "update user set isLogin = 1 where id = %s" ;
		String updateSQL_1 = "update user set isLogin = 0 where id != %s" ;
		updateSQL = String.format(updateSQL, user.getUserid());
		updateSQL_1 = String.format(updateSQL_1, user.getUserid());
		mDatabaseManager.getmSQLiteDatabase().execSQL(updateSQL);
		mDatabaseManager.getmSQLiteDatabase().execSQL(updateSQL_1);
	}
	
	public synchronized void updateBindWeixinStatus(){
		String updateSQL = "update user set isBindWeixin = 1 where isLogin = 1";
		mDatabaseManager.getmSQLiteDatabase().execSQL(updateSQL);
	}
	
	public synchronized void editorUserTable(LoginBean.DataBean user){
		if(isExistRecord(String.valueOf(user.getId()))){
			String updateSQL = "update user set username = \"%s\",wanjiaToken = \"%s\",head = \"%s\" ,token = \"%s\"  where id = %s" ;
			updateSQL = String.format(updateSQL, user.getTname(),user.getTname(),"",user.getLogo(),user.getTname(),user.getId());
			mDatabaseManager.getmSQLiteDatabase().execSQL(updateSQL);
		}else{
			insertUserTable(user,1);
		}
	}
	
	public synchronized void editorUserTableInviteCode(User user){
		if(isExistRecord(user.getUserid())){
			String updateSQL = "update user set inviteCode = \"%s\"  where id = %s" ;
			updateSQL = String.format(updateSQL, "",user.getUserid());
			mDatabaseManager.getmSQLiteDatabase().execSQL(updateSQL);
		}
	}
	
	public synchronized void editorUserInfo(User user){
		if(isExistRecord(user.getUserid())){
			String updateSQL = "update user set head = \"%s\",nickname = \"%s\"  where id = %s" ;
			updateSQL = String.format(updateSQL, "","",user.getUserid());
			mDatabaseManager.getmSQLiteDatabase().execSQL(updateSQL);
		}
	}
	
	public synchronized void setUserLogout(int isLogin,long userId){
		String updateSQL = "update user set isLogin = %d where id = %d" ;
		updateSQL = String.format(updateSQL,isLogin,userId);
		mDatabaseManager.getmSQLiteDatabase().execSQL(updateSQL);
	}
	
	public synchronized List<User> selectUserList(){
		String selectSQL = "select * from user" ;
		Cursor cursor = mDatabaseManager.getmSQLiteDatabase().rawQuery(selectSQL,null);
		List<User> userList = new ArrayList<User>();
		while(cursor != null && cursor.moveToNext()){
			User user = new User();
			user.setUserid(cursor.getString(cursor.getColumnIndex("id")));
			userList.add(user);
		}
		mDatabaseManager.recyleCursor(cursor) ;
		return userList ;
	}
	

	public synchronized User selectUserByUserId(long userId){
		User user = null ;
		String selectSQL = "select * from user where id = %d" ;
		selectSQL = String.format(selectSQL, userId);
		Cursor cursor = mDatabaseManager.getmSQLiteDatabase().rawQuery(selectSQL,null);
		while(cursor != null && cursor.moveToNext()){
			user = new User();
			user.setUserid(cursor.getString(cursor.getColumnIndex("id")));
		}
		mDatabaseManager.recyleCursor(cursor) ;
		return user ;
	}
	
	
	public synchronized User selectUserByIsLogin(int isLogin){
		User user = null ;
		String selectSQL = "select * from user where isLogin = %d" ;
		selectSQL = String.format(selectSQL, isLogin);
		Cursor cursor = mDatabaseManager.getmSQLiteDatabase().rawQuery(selectSQL,null);
		while(cursor != null && cursor.moveToNext()){
			user = new User();
			user.setUserid(cursor.getString(cursor.getColumnIndex("id")));
			user.setUsername(cursor.getString(cursor.getColumnIndex("nickname"))) ;
			user.setTrueName(cursor.getString(cursor.getColumnIndex("username"))) ;
			user.setMobile(cursor.getString(cursor.getColumnIndex("phone"))) ;
			user.setToken(cursor.getString(cursor.getColumnIndex("token")));
			user.setWanjiaToken(cursor.getString(cursor.getColumnIndex("wanjiaToken"))) ;
		}
		mDatabaseManager.recyleCursor(cursor) ;
		return user ;
	}

	public synchronized User selectTouristUser(){
		User user = null ;
		String selectSQL = "select * from user where isLogin = 0" ;
		Cursor cursor = mDatabaseManager.getmSQLiteDatabase().rawQuery(selectSQL,null);
		while(cursor != null && cursor.moveToNext()){
			user = new User();
			user.setUserid(cursor.getString(cursor.getColumnIndex("id")));
		}
		mDatabaseManager.recyleCursor(cursor) ;
		return user ;
	}
	
	public synchronized User queryUserTable(){
		String sqlSQL = "select * from user" ;
		Cursor cursor = mDatabaseManager.getmSQLiteDatabase().rawQuery(sqlSQL,null);
		User user = null ;
		while(cursor != null && cursor.moveToNext()){
			user = new User();
			user.setUserid(cursor.getString(cursor.getColumnIndex("id")));
		}
		mDatabaseManager.recyleCursor(cursor) ;
		return user ;
	}
	
	public synchronized void deleteUser(){
		String sqlSQL = "delete from user" ;
		mDatabaseManager.getmSQLiteDatabase().execSQL(sqlSQL) ;
	}
	
}
