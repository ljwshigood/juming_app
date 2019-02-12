package com.baijiahulian.common.utils;

import java.util.List;

import com.baijiahulian.common.crop.model.PhotoInfo;

public class DataHolder {	
	
	private static DataHolder mInstance ;
	
	private List<PhotoInfo> mPhotoList ;
	
	private DataHolder(){
		
	}
	
	public static DataHolder getInstance(){
		if(mInstance == null){
			mInstance = new DataHolder() ;
		}
		return mInstance ;
	}
	
	public void setData(List<PhotoInfo> photoList){
		this.mPhotoList = photoList ;
	}
	
	public List<PhotoInfo> getData(){
		return mPhotoList ;
	}
	
	
}
