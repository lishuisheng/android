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
		list.add(getMap("���̶߳ϵ�����"));
		list.add(getMap("�Զ���ؼ�"));
		list.add(getMap("http��̻�ȡ����ͼƬ"));
		list.add(getMap("Json����"));
		list.add(getMap("���л����ݶ���"));
		list.add(getMap("ͼƬ�������"));
		list.add(getMap("BroacaseReceiver"));
		list.add(getMap("ListView item ��ť"));
		list.add(getMap("��̬ʹ��fragment"));
		list.add(getMap("��̬ʹ��fragment"));
		list.add(getMap("Serviceͨ��һ"));
		list.add(getMap("Serviceͨ�Ŷ�"));
		list.add(getMap("Serviceͨ����"));
		list.add(getMap("���ݿ�SQLite����"));
		list.add(getMap("���"));
		list.add(getMap("�ֲ�"));
		list.add(getMap("SAX����"));
		list.add(getMap("AsynTask�첽����"));
		list.add(getMap("XListViewˢ�¼��ظ���"));
		list.add(getMap("ContentProvider"));
		list.add(getMap("Volley"));
		list.add(getMap("SwipeRefreshLayout"));
		list.add(getMap("�Զ���ؼ�������"));
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
		map.put("name", "��ˮʤ");
		lists.add(map);

		return lists;

	}

	public static List<SongBanner> getBannerData() {

		List<SongBanner> list = new ArrayList<SongBanner>();

		SongBanner songBanner = new SongBanner();
		songBanner.setId("108078");
		songBanner.setDate("3��17��");
		songBanner.setTitle("��İ���");
		songBanner.setTopicFrom("�ƼҾ�");
		songBanner.setTopic("����ֻ�������");
		songBanner
				.setImgUrl("http://imgs.soufun.com/news/2011_06/30/news/1309419027065_000.jpg");
		songBanner.setAd(false);
		list.add(songBanner);

		SongBanner songBanner2 = new SongBanner();
		songBanner2.setId("108078");
		songBanner2.setDate("3��18��");
		songBanner2.setTitle("ǣ��");
		songBanner2.setTopicFrom("����");
		songBanner2.setTopic("��������");
		songBanner2
				.setImgUrl("http://p0.so.qhimg.com/bdr/_240_/t01e59f497a8905d72a.jpg");
		songBanner2.setAd(false);
		list.add(songBanner2);

		SongBanner songBanner3 = new SongBanner();
		songBanner3.setId("108078");
		songBanner3.setDate("3��19��");
		songBanner3.setTitle("�ַ�����Ҫ��");
		songBanner3.setTopicFrom("������");
		songBanner3.setTopic("Ը�һ������");
		songBanner3
				.setImgUrl("http://img6.cache.netease.com/ent/2015/3/23/20150323081814204eb_550.jpg");
		songBanner3.setAd(false);
		list.add(songBanner3);

		SongBanner songBanner4 = new SongBanner();
		songBanner4.setId("108078");
		songBanner4.setDate("3��20��");
		songBanner4.setTitle("�þò���");
		songBanner4.setTopicFrom("����Ѹ");
		songBanner4.setTopic("���������˵ĸп�");
		songBanner4
				.setImgUrl("http://picm.bbzhi.com/mingxingbizhi/chenyixun/star_starhk_139449_m.jpg");
		songBanner4.setAd(false);
		list.add(songBanner4);

		SongBanner songBanner5 = new SongBanner();
		songBanner5.setId("108078");
		songBanner5.setDate("3��21��");
		songBanner5.setTitle("ǧ���һ��");
		songBanner5.setTopicFrom("��ʤ��");
		songBanner5.setTopic("�°����Ӵ���");
		songBanner5
				.setImgUrl("http://m.cnr.cn/ent/20151109/W020151109290519874215.jpg");
		songBanner5.setAd(false);
		list.add(songBanner5);

		return list;
	}

}
