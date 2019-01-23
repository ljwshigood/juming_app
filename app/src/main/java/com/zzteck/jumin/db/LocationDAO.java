package com.zzteck.jumin.db;

import android.content.Context;

public class LocationDAO {

	private static LocationDAO mInstance ;

	private Context mContext ;

	private DatabaseManager mDatabaseManager ;

	public LocationDAO(Context context){
		this.mContext = context ;
		mDatabaseManager = DatabaseManager.getInstance(context);
	}
	
	public static LocationDAO getInstance(Context context){
		if(mInstance == null){
			mInstance = new LocationDAO(context);
		}
		return mInstance ;
	}
	
}
