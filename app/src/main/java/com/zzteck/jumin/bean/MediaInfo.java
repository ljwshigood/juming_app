package com.zzteck.jumin.bean; /**
 * 项目名称：PublcNumber
 * 文件名：MediaInfo.java 
 * 2015-3-2-下午4:35:46
 * 2015 万家恒通公司-版权所有
 * @version 1.0.0
 */
import java.io.Serializable;

/**
 * 
 * @description:
 *
 * author : liujw
 * modify : 
 * 2015-3-2 下午4:35:46
 *
 * 
 */
public class MediaInfo implements Serializable {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	private int netWorkPicFlag ;
	
	public int getNetWorkPicFlag() {
		return netWorkPicFlag;
	}

	public void setNetWorkPicFlag(int netWorkPicFlag) {
		this.netWorkPicFlag = netWorkPicFlag;
	}

	private String thumbnailPath ;
	
	public String getThumbnailPath() {
		return thumbnailPath;
	}

	public void setThumbnailPath(String thumbnailPath) {
		this.thumbnailPath = thumbnailPath;
	}

	private String compressFile ;
	
	private int isCompressFile  = 0 ;
	
	public int getIsCompressFile() {
		return isCompressFile;
	}

	public void setIsCompressFile(int isCompressFile) {
		this.isCompressFile = isCompressFile;
	}

	private String uploadUrl ;
	
	public String getUploadUrl() {
		return uploadUrl;
	}

	public void setUploadUrl(String uploadUrl) {
		this.uploadUrl = uploadUrl;
	}

	private int loadFlag = 0 ;// 0 : 正在上传  1: 上传成功  2:上传失败
	
	public int getLoadFlag() {
		return loadFlag;
	}

	public void setLoadFlag(int loadFlag) {
		this.loadFlag = loadFlag;
	}

	public String getCompressFile() {
		return compressFile;
	}

	public void setCompressFile(String compressFile) {
		this.compressFile = compressFile;
	}

	private String filePath ;
	
	private String fileName ;
	
	private String fileThumb ;
	
	private int type ; // 0:picture 1:video
	
	private boolean isSelect ;
	
	
	public String getFileThumb() {
		return fileThumb;
	}

	public void setFileThumb(String fileThumb) {
		this.fileThumb = fileThumb;
	}
	
	public boolean isSelect() {
		return isSelect;
	}

	public void setSelect(boolean isSelect) {
		this.isSelect = isSelect;
	}

	/**
	 * type
	 *
	 * @return  the type
	 * @since   1.0.0
	 */
	
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * filePath
	 *
	 * @return  the filePath
	 * @since   1.0.0
	 */
	
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * fileName
	 *
	 * @return  the fileName
	 * @since   1.0.0
	 */
	
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
