package com.zzteck.jumin.ui.location;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.zzteck.jumin.R;
import com.zzteck.jumin.db.DatabaseManager;
import com.zzteck.jumin.ui.mainui.BaseActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class ActivityLocation extends BaseActivity implements OnScrollListener {
	private BaseAdapter adapter;
	private ResultListAdapter resultListAdapter;
	private ListView personList;
	private ListView resultList;
	private TextView overlay; 
	private MyLetterListView letterListView; 
	private HashMap<String, Integer> alphaIndexer;
	private String[] sections;//
	private Handler handler;
	private OverlayThread overlayThread; //
	private ArrayList<City> allCity_lists; //
	private ArrayList<City> city_lists;//
	private ArrayList<City> city_hot;
	private ArrayList<City> city_result;
	private ArrayList<String> city_history;
	private EditText sh;
	private TextView tv_noresult;

	private String currentCity;
	private int locateProcess = 1; 
	private boolean isNeedFresh;

	private DatabaseManager helper;


	private AMapLocationClient locationClient = null;

	private AMapLocationClientOption locationOption = new AMapLocationClientOption();


	/**
	 * 初始化定位
	 *
	 * @since 2.8.0
	 * @author hongming.wang
	 *
	 */
	private void initLocation(){
		//初始化client
		locationClient = new AMapLocationClient(this.getApplicationContext());
		//设置定位参数
		locationClient.setLocationOption(getDefaultOption());
		// 设置定位监听
		locationClient.setLocationListener(locationListener);
	}

	/**
	 * 默认的定位参数
	 * @since 2.8.0
	 * @author hongming.wang
	 *
	 */
	private AMapLocationClientOption getDefaultOption(){
		AMapLocationClientOption mOption = new AMapLocationClientOption();
		mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
		mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
		mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
		mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
		mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
		mOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
		mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
		AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
		mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
		mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
		mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
		return mOption;
	}

	/**
	 * 定位监听
	 */
	AMapLocationListener locationListener = new AMapLocationListener() {
		@Override
		public void onLocationChanged(AMapLocation loc) {
			if (null != loc) {
				//解析定位结果
				//String result = UtilsTools.getLocationStr(loc);
				//tvResult.setText(result);
			} else {
				//tvResult.setText("定位失败，loc is null");
			}
		}
	};

	// 根据控件的选择，重新设置定位参数
	private void resetOption() {
		// 设置是否需要显示地址信息
		locationOption.setNeedAddress(true);
		/**
		 * 设置是否优先返回GPS定位结果，如果30秒内GPS没有返回定位结果则进行网络定位
		 * 注意：只有在高精度模式下的单次定位有效，其他方式无效
		 */
		locationOption.setGpsFirst(false);
		// 设置是否开启缓存
		locationOption.setLocationCacheEnable(false);
		// 设置是否单次定位
		locationOption.setOnceLocation(false);
		//设置是否等待设备wifi刷新，如果设置为true,会自动变为单次定位，持续定位时不要使用
		locationOption.setOnceLocationLatest(false);
		//设置是否使用传感器
		locationOption.setSensorEnable(false);
		//设置是否开启wifi扫描，如果设置为false时同时会停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
		String strInterval = 2000+"";
		if (!TextUtils.isEmpty(strInterval)) {
			try{
				// 设置发送定位请求的时间间隔,最小值为1000，如果小于1000，按照1000算
				locationOption.setInterval(Long.valueOf(strInterval));
			}catch(Throwable e){
				e.printStackTrace();
			}
		}

		String strTimeout = 30000+"";
		if(!TextUtils.isEmpty(strTimeout)){
			try{
				// 设置网络请求超时时间
				locationOption.setHttpTimeOut(Long.valueOf(strTimeout));
			}catch(Throwable e){
				e.printStackTrace();
			}
		}
	}

	/**
	 * 开始定位
	 *
	 * @since 2.8.0
	 * @author hongming.wang
	 *
	 */
	private void startLocation(){
		//根据控件的选择，重新设置定位参数
		resetOption();
		// 设置定位参数
		locationClient.setLocationOption(locationOption);
		// 启动定位
		locationClient.startLocation();
	}

	/**
	 * 停止定位
	 *
	 * @since 2.8.0
	 * @author hongming.wang
	 *
	 */
	private void stopLocation(){
		// 停止定位
		locationClient.stopLocation();
	}

	/**
	 * 销毁定位
	 *
	 * @since 2.8.0
	 * @author hongming.wang
	 *
	 */
	private void destroyLocation(){
		if (null != locationClient) {
			/**
			 * 如果AMapLocationClient是在当前Activity实例化的，
			 * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
			 */
			locationClient.onDestroy();
			locationClient = null;
			locationOption = null;
		}
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.location_main);
		personList =  findViewById(R.id.list_view);
		allCity_lists = new ArrayList<City>();
		city_hot = new ArrayList<City>();
		city_result = new ArrayList<City>();
		city_history = new ArrayList<String>();
		resultList =  findViewById(R.id.search_result);
		sh =  findViewById(R.id.tv_search);
		tv_noresult =  findViewById(R.id.tv_noresult);
		helper = DatabaseManager.getInstance(this);
		sh.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (s.toString() == null || "".equals(s.toString())) {
					letterListView.setVisibility(View.VISIBLE);
					personList.setVisibility(View.VISIBLE);
					resultList.setVisibility(View.GONE);
					tv_noresult.setVisibility(View.GONE);
				} else {
					city_result.clear();
					letterListView.setVisibility(View.GONE);
					personList.setVisibility(View.GONE);
					//getResultCityList(s.toString());
					if (city_result.size() <= 0) {
						tv_noresult.setVisibility(View.VISIBLE);
						resultList.setVisibility(View.GONE);
					} else {
						tv_noresult.setVisibility(View.GONE);
						resultList.setVisibility(View.VISIBLE);
						resultListAdapter.notifyDataSetChanged();
					}
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
		letterListView = findViewById(R.id.MyLetterListView01);
		letterListView.setOnTouchingLetterChangedListener(new LetterListViewListener());
		alphaIndexer = new HashMap<String, Integer>();
		handler = new Handler();
		overlayThread = new OverlayThread();
		isNeedFresh = true;
		personList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (position >= 4) {

					Toast.makeText(getApplicationContext(),
							allCity_lists.get(position).getName(),
							Toast.LENGTH_SHORT).show();
				}
			}
		});
		locateProcess = 1;
		personList.setAdapter(adapter);
		personList.setOnScrollListener(this);
		resultListAdapter = new ResultListAdapter(this, city_result);
		resultList.setAdapter(resultListAdapter);
		resultList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getApplicationContext(),
						city_result.get(position).getName(), Toast.LENGTH_SHORT)
						.show();
			}
		});
		initOverlay();
		cityInit();
		hotCityInit();
		setAdapter(allCity_lists, city_hot, city_history);

		initLocation();
		locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);

		startLocation();

	}
	

	/*public void InsertCity(String name) {
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from recentcity where name = '"
				+ name + "'", null);
		if (cursor.getCount() > 0) { //
			db.delete("recentcity", "name = ?", new String[] { name });
		}
		db.execSQL("insert into recentcity(name, date) values('" + name + "', "
				+ System.currentTimeMillis() + ")");
		db.close();
	}*/

//	private void InitLocation() {
//		// öλ
//		LocationClientOption option = new LocationClientOption();
//		option.setCoorType("bd09ll"); // 
//		option.setScanSpan(10000); // 10ɨ1
//		// ҪַϢΪκֵstringͣҲΪnullʱʾ޵ַϢ
//		option.setAddrType("all");
//		// Ƿ񷵻POIĵ绰͵ַϸϢĬֵΪfalsePOIĵ绰͵ַϢ
//		option.setPoiExtraInfo(true);
//		// òƷơǿҽʹԶĲƷƣԺΪṩЧ׼ȷĶλ
//		option.setProdName("ͨGPSλҵǰλ");
//		// û涨λ
//		option.disableCache(true);
//		// ɷصPOIĬֵΪ3POIѯȽϺķ෵صPOIԱʡ
//		option.setPoiNumber(3);
//		// öλʽȼ
//		// gpsãһȡ˶λʱٷֱӷظûꡣѡʺϣõ׼ȷλõûgpsãٷ󣬽жλ
//		option.setPriority(LocationClientOption.GpsFirst);
//		mLocationClient.setLocOption(option);
//	}

	private void cityInit() {
		City city = new City("λ", "0"); // ǰλ
		allCity_lists.add(city);
		city = new City("", "1"); // ʵĳ
		allCity_lists.add(city);
		city = new City("", "2"); // ų
		allCity_lists.add(city);
		city = new City("ȫ", "3"); // ȫ
		allCity_lists.add(city);
		city_lists = getCityList();
		allCity_lists.addAll(city_lists);
	}

	/**
	 * ų
	 */
	public void hotCityInit() {
		City city = new City("上海", "2");
		city_hot.add(city);
		city = new City("北京", "2");
		city_hot.add(city);
		city = new City("广州", "2");
		city_hot.add(city);
		city = new City("深圳", "2");
		city_hot.add(city);
		city = new City("武汉", "2");
		city_hot.add(city);
		city = new City("天津", "2");
		city_hot.add(city);
		city = new City("西安", "2");
		city_hot.add(city);
		city = new City("南京", "2");
		city_hot.add(city);
		city = new City("杭州", "2");
		city_hot.add(city);
		city = new City("成都", "2");
		city_hot.add(city);
		city = new City("重庆", "2");
		city_hot.add(city);
	}

	/*private void hisCityInit() {
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery(
				"select * from recentcity order by date desc limit 0, 3", null);
		while (cursor.moveToNext()) {
			city_history.add(cursor.getString(1));
		}
		cursor.close();
		db.close();
	}*/

	@SuppressWarnings("unchecked")
	private ArrayList<City> getCityList() {
		ArrayList<City> list = new ArrayList<City>();

		Collections.sort(list, comparator);
		return list;
	}

	/*@SuppressWarnings("unchecked")
	private void getResultCityList(String keyword) {
		DBHelper dbHelper = new DBHelper(this);
		try {
			dbHelper.createDataBase();
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			Cursor cursor = db.rawQuery(
					"select * from city where name like \"%" + keyword
							+ "%\" or pinyin like \"%" + keyword + "%\"", null);
			City city;
			Log.e("info", "length = " + cursor.getCount());
			while (cursor.moveToNext()) {
				city = new City(cursor.getString(1), cursor.getString(2));
				city_result.add(city);
			}
			cursor.close();
			db.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Collections.sort(city_result, comparator);
	}*/

	/**
	 * a-z
	 */
	@SuppressWarnings("rawtypes")
	Comparator comparator = new Comparator<City>() {
		@Override
		public int compare(City lhs, City rhs) {
			String a = lhs.getPinyi().substring(0, 1);
			String b = rhs.getPinyi().substring(0, 1);
			int flag = a.compareTo(b);
			if (flag == 0) {
				return a.compareTo(b);
			} else {
				return flag;
			}
		}
	};

	private void setAdapter(List<City> list, List<City> hotList,
			List<String> hisCity) {
		adapter = new ListAdapter(this, list, hotList, hisCity);
		personList.setAdapter(adapter);
	}

	/**
	 * ʵʵλص
	 */
	/*public class MyLocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation arg0) {
			Log.e("info", "city = " + arg0.getCity());
			if (!isNeedFresh) {
				return;
			}
			isNeedFresh = false;
			if (arg0.getCity() == null) {
				locateProcess = 3; // λʧ
				personList.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				return;
			}
			currentCity = arg0.getCity().substring(0,
					arg0.getCity().length() - 1);
			locateProcess = 2; // λɹ
			personList.setAdapter(adapter);
			adapter.notifyDataSetChanged();
		}

		@Override
		public void onReceivePoi(BDLocation arg0) {

		}
	}*/

	private class ResultListAdapter extends BaseAdapter {
		private LayoutInflater inflater;
		private ArrayList<City> results = new ArrayList<City>();

		public ResultListAdapter(Context context, ArrayList<City> results) {
			inflater = LayoutInflater.from(context);
			this.results = results;
		}

		@Override
		public int getCount() {
			return results.size();
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder = null;
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.list_item, null);
				viewHolder = new ViewHolder();
				viewHolder.name = convertView.findViewById(R.id.name);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			viewHolder.name.setText(results.get(position).getName());
			return convertView;
		}

		class ViewHolder {
			TextView name;
		}
	}

	public class ListAdapter extends BaseAdapter {
		private Context context;
		private LayoutInflater inflater;
		private List<City> list;
		private List<City> hotList;
		private List<String> hisCity;
		final int VIEW_TYPE = 5;

		public ListAdapter(Context context, List<City> list,
				List<City> hotList, List<String> hisCity) {
			this.inflater = LayoutInflater.from(context);
			this.list = list;
			this.context = context;
			this.hotList = hotList;
			this.hisCity = hisCity;
			alphaIndexer = new HashMap<String, Integer>();
			sections = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				String currentStr = getAlpha(list.get(i).getPinyi());
				String previewStr = (i - 1) >= 0 ? getAlpha(list.get(i - 1)
						.getPinyi()) : " ";
				if (!previewStr.equals(currentStr)) {
					String name = getAlpha(list.get(i).getPinyi());
					alphaIndexer.put(name, i);
					sections[i] = name;
				}
			}
		}

		@Override
		public int getViewTypeCount() {
			return VIEW_TYPE;
		}

		@Override
		public int getItemViewType(int position) {
			return position < 4 ? position : 4;
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		ViewHolder holder;

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			final TextView city;
			int viewType = getItemViewType(position);
			if (viewType == 0) { // 定位
				convertView = inflater.inflate(R.layout.frist_list_item, null);
				TextView locateHint =  convertView.findViewById(R.id.locateHint);
				city =  convertView.findViewById(R.id.lng_city);
				city.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						if (locateProcess == 2) {
							Toast.makeText(getApplicationContext(),
									"选中城市:"+city.getText().toString(),
									Toast.LENGTH_SHORT).show();
						} else if (locateProcess == 3) {
							locateProcess = 1;
							personList.setAdapter(adapter);
							adapter.notifyDataSetChanged();
							isNeedFresh = true;
							currentCity = "";
						}
					}
				});
				ProgressBar pbLocate = (ProgressBar) convertView
						.findViewById(R.id.pbLocate);
				if (locateProcess == 1) { // 正在定位
					locateHint.setText("正在定位");
					city.setVisibility(View.GONE);
					pbLocate.setVisibility(View.VISIBLE);
				} else if (locateProcess == 2) { // 定位成功
					locateHint.setText("当前定位城市");
					city.setVisibility(View.VISIBLE);
					city.setText(currentCity);
					pbLocate.setVisibility(View.GONE);
				} else if (locateProcess == 3) {
					locateHint.setText("未定位到城市,请选择");
					city.setVisibility(View.VISIBLE);
					city.setText("重新选择");
					pbLocate.setVisibility(View.GONE);
				}
			} else if (viewType == 1) {
				convertView = inflater.inflate(R.layout.recent_city, null);
				GridView hotCity = (GridView) convertView
						.findViewById(R.id.recent_city);
				hotCity.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
											int position, long id) {

						Toast.makeText(getApplicationContext(),
								city_hot.get(position).getName(),
								Toast.LENGTH_SHORT).show();

					}
				});
				hotCity.setAdapter(new HotCityAdapter(context, this.hotList));
				TextView hotHint = (TextView) convertView
						.findViewById(R.id.recentHint);
				hotHint.setText("热门城市");
			} else {
				if (convertView == null) {
					convertView = inflater.inflate(R.layout.list_item, null);
					holder = new ViewHolder();
					holder.alpha = convertView.findViewById(R.id.alpha);
					holder.name = convertView.findViewById(R.id.name);
					convertView.setTag(holder);
				} else {
					holder = (ViewHolder) convertView.getTag();
				}
				if (position >= 1) {
					holder.name.setText(list.get(position).getName());
					String currentStr = getAlpha(list.get(position).getPinyi());
					String previewStr = (position - 1) >= 0 ? getAlpha(list
							.get(position - 1).getPinyi()) : " ";
					if (!previewStr.equals(currentStr)) {
						holder.alpha.setVisibility(View.VISIBLE);
						holder.alpha.setText(currentStr);
					} else {
						holder.alpha.setVisibility(View.GONE);
					}
				}
			}
			return convertView;
		}

		private class ViewHolder {
			TextView alpha; //
			TextView name; // 
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	class HotCityAdapter extends BaseAdapter {
		private Context context;
		private LayoutInflater inflater;
		private List<City> hotCitys;

		public HotCityAdapter(Context context, List<City> hotCitys) {
			this.context = context;
			inflater = LayoutInflater.from(this.context);
			this.hotCitys = hotCitys;
		}

		@Override
		public int getCount() {
			return hotCitys.size();
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			convertView = inflater.inflate(R.layout.item_city, null);
			TextView city = convertView.findViewById(R.id.city);
			city.setText(hotCitys.get(position).getName());
			return convertView;
		}
	}

	class HitCityAdapter extends BaseAdapter {
		private Context context;
		private LayoutInflater inflater;
		private List<String> hotCitys;

		public HitCityAdapter(Context context, List<String> hotCitys) {
			this.context = context;
			inflater = LayoutInflater.from(this.context);
			this.hotCitys = hotCitys;
		}

		@Override
		public int getCount() {
			return hotCitys.size();
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			convertView = inflater.inflate(R.layout.item_city, null);
			TextView city = (TextView) convertView.findViewById(R.id.city);
			city.setText(hotCitys.get(position));
			return convertView;
		}
	}

	private boolean mReady;

	private void initOverlay() {
		mReady = true;
		LayoutInflater inflater = LayoutInflater.from(this);
		overlay = (TextView) inflater.inflate(R.layout.overlay, null);
		overlay.setVisibility(View.INVISIBLE);
		WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.TYPE_APPLICATION,
				WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
						| WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
				PixelFormat.TRANSLUCENT);
		WindowManager windowManager = (WindowManager) this
				.getSystemService(Context.WINDOW_SERVICE);
		windowManager.addView(overlay, lp);
	}

	private boolean isScroll = false;

	private class LetterListViewListener implements MyLetterListView.OnTouchingLetterChangedListener {

		@Override
		public void onTouchingLetterChanged(final String s) {
			isScroll = false;
			if (alphaIndexer.get(s) != null) {
				int position = alphaIndexer.get(s);
				personList.setSelection(position);
				overlay.setText(s);
				overlay.setVisibility(View.VISIBLE);
				handler.removeCallbacks(overlayThread);
				handler.postDelayed(overlayThread, 1000);
			}
		}
	}

	private class OverlayThread implements Runnable {
		@Override
		public void run() {
			overlay.setVisibility(View.GONE);
		}
	}

	private String getAlpha(String str) {
		if (str == null) {
			return "#";
		}
		if (str.trim().length() == 0) {
			return "#";
		}
		char c = str.trim().substring(0, 1).charAt(0);
		// ʽжĸǷӢĸ
		Pattern pattern = Pattern.compile("^[A-Za-z]+$");
		if (pattern.matcher(c + "").matches()) {
			return (c + "").toUpperCase();
		} else if (str.equals("0")) {
			return "λ";
		} else if (str.equals("1")) {
			return "";
		} else if (str.equals("2")) {
			return "";
		} else if (str.equals("3")) {
			return "ȫ";
		} else {
			return "#";
		}
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if (scrollState == SCROLL_STATE_TOUCH_SCROLL
				|| scrollState == SCROLL_STATE_FLING) {
			isScroll = true;
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		if (!isScroll) {
			return;
		}

		if (mReady) {
			String text;
			String name = allCity_lists.get(firstVisibleItem).getName();
			String pinyin = allCity_lists.get(firstVisibleItem).getPinyi();
			if (firstVisibleItem < 4) {
				text = name;
			} else {
				text = PingYinUtil.converterToFirstSpell(pinyin)
						.substring(0, 1).toUpperCase();
			}
			overlay.setText(text);
			overlay.setVisibility(View.VISIBLE);
			handler.removeCallbacks(overlayThread);
			handler.postDelayed(overlayThread, 1000);
		}
	}
	
	
	/*
	 */
	/*@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLocationChanged(AMapLocation amapLocation) {
		
		if (!isNeedFresh) {
			return;
		}
		isNeedFresh = false;
		if (amapLocation != null
				&& amapLocation.getAMapException().getErrorCode() == 0) {
			// λɹصϢϢ
			// tv_location.setText(amapLocation.getCity()
			// + amapLocation.getDistrict() + amapLocation.getRoad());
			// tv_location.setVisibility(View.VISIBLE);
//			tv_location.setText(amapLocation.getCity());
//			Constant.lat = amapLocation.getLatitude();
//			Constant.lon = amapLocation.getLongitude();
//			Constant.city = amapLocation.getCity();
			
			currentCity = amapLocation.getCity();
			locateProcess = 2; //
			personList.setAdapter(adapter);
			adapter.notifyDataSetChanged();
		} else {
			Toast.makeText(
					getApplicationContext(),
					"Location ERR:"
							+ amapLocation.getAMapException().getErrorCode(),
					Toast.LENGTH_LONG).show();
			locateProcess = 3; //
			personList.setAdapter(adapter);
			adapter.notifyDataSetChanged();
			return;
		}
		
	}*/
}