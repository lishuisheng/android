package com.lss.example.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.widget.Button;

import com.lss.example.bean.SongBanner;
import com.lss.example.lssdemo.R;

public class MyData {

	public MyData() {
		// TODO Auto-generated constructor stub
	}

	public static ArrayList<HashMap<String, Object>> getListBtnData() {

		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		for (int i = 1; i < 30; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("text", "item" + i);
			map.put("img", R.drawable.pxt);
			list.add(map);
		}

		return list;
	}

	public static ArrayList<HashMap<String, Object>> getListData() {

		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		list.add(getMap("AfinalDemo"));
		list.add(getMap("多线程断点下载"));
		list.add(getMap("自定义控件"));
		list.add(getMap("http编程获取网络图片"));
		list.add(getMap("Json解析"));
		list.add(getMap("序列化传递对象"));
		list.add(getMap("图片缓存机制"));
		list.add(getMap("BroacaseReceiver"));
		list.add(getMap("ListView item 按钮"));
		list.add(getMap("静态使用fragment"));
		list.add(getMap("动态使用fragment"));
		list.add(getMap("Service通信一"));
		list.add(getMap("Service通信二"));
		list.add(getMap("Service通信三"));
		list.add(getMap("数据库SQLite操作"));
		list.add(getMap("表格"));
		list.add(getMap("轮播"));
		list.add(getMap("SAX解析"));
		list.add(getMap("AsynTask异步加载"));
		list.add(getMap("XListView刷新加载更多"));
		list.add(getMap("ContentProvider"));
		list.add(getMap("Volley"));
		list.add(getMap("SwipeRefreshLayout"));
		list.add(getMap("自定义控件三角形"));
		list.add(getMap("ViewFlipper"));

		return list;
	}

	public static HashMap<String, Object> getMap(String string) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("str", string);
		return map;
	}

	public static List<Map<String, Object>> getMySelf() {

		List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("msg", R.id.iv_roudn_img);
		map.put("name", "李水胜");
		lists.add(map);

		return lists;

	}

	public static List<SongBanner> getBannerData() {

		List<SongBanner> list = new ArrayList<SongBanner>();

		SongBanner songBanner = new SongBanner();
		songBanner.setId("108078");
		songBanner.setDate("3月17日");
		songBanner.setTitle("真的爱你");
		songBanner.setTopicFrom("黄家驹");
		songBanner.setTopic("世上只有妈妈好");
		songBanner
				.setImgUrl("http://imgs.soufun.com/news/2011_06/30/news/1309419027065_000.jpg");
		songBanner.setAd(false);
		list.add(songBanner);

		SongBanner songBanner2 = new SongBanner();
		songBanner2.setId("108078");
		songBanner2.setDate("3月18日");
		songBanner2.setTitle("牵手");
		songBanner2.setTopicFrom("苏芮");
		songBanner2.setTopic("爱的箴言");
		songBanner2
				.setImgUrl("http://p0.so.qhimg.com/bdr/_240_/t01e59f497a8905d72a.jpg");
		songBanner2.setAd(false);
		list.add(songBanner2);

		SongBanner songBanner3 = new SongBanner();
		songBanner3.setId("108078");
		songBanner3.setDate("3月19日");
		songBanner3.setTitle("分分钟需要你");
		songBanner3.setTopicFrom("林子祥");
		songBanner3.setTopic("愿我会扎火箭");
		songBanner3
				.setImgUrl("http://img6.cache.netease.com/ent/2015/3/23/20150323081814204eb_550.jpg");
		songBanner3.setAd(false);
		list.add(songBanner3);

		SongBanner songBanner4 = new SongBanner();
		songBanner4.setId("108078");
		songBanner4.setDate("3月20日");
		songBanner4.setTitle("好久不见");
		songBanner4.setTopicFrom("陈奕迅");
		songBanner4.setTopic("对往日情人的感慨");
		songBanner4
				.setImgUrl("http://picm.bbzhi.com/mingxingbizhi/chenyixun/star_starhk_139449_m.jpg");
		songBanner4.setAd(false);
		list.add(songBanner4);

		SongBanner songBanner5 = new SongBanner();
		songBanner5.setId("108078");
		songBanner5.setDate("3月21日");
		songBanner5.setTitle("千年等一回");
		songBanner5.setTopicFrom("高胜美");
		songBanner5.setTopic("新白娘子传奇");
		songBanner5
				.setImgUrl("http://m.cnr.cn/ent/20151109/W020151109290519874215.jpg");
		songBanner5.setAd(false);
		list.add(songBanner5);

		return list;
	}

}
