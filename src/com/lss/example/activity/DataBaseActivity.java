package com.lss.example.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.lss.example.lssdemo.R;
import com.lss.example.server.Singer;
import com.lss.example.util.DataBaseDao;

public class DataBaseActivity extends BaseActivity implements OnClickListener {

	private ListView listView = null;
	private Button btn_add = null;
	private Button btn_del = null;
	private Button btn_upd = null;
	private Button btn_query = null;
	private Button btn_sure = null;
	private Button btn_cancel = null;
	private EditText et_name = null;
	private EditText et_song = null;

	private EditText et_del_song = null;
	private EditText et_update_pre = null;
	private EditText et_update_hou = null;
	private Button btn_sure_del = null;
	private Button btn_cancel_del = null;
	private Button btn_sure_update = null;
	private Button btn_cancel_update = null;

	private LinearLayout layout_menu = null;
	private LinearLayout layout_add = null;
	private LinearLayout layout_delete = null;
	private LinearLayout layout_update = null;
	private DataBaseDao dao = null;
	private ArrayList<Map<String, Object>> data = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.database_activity);

		dao = new DataBaseDao(this);
		initViews();
		initListView();

	}

	private void initListView() {

		List<Singer> singers = new ArrayList<Singer>();
		singers = dao.query(null, null);

		data = new ArrayList<Map<String, Object>>();

		if (singers != null) {

			for (int i = 0; i < singers.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("name", singers.get(i).getName());
				map.put("song", singers.get(i).getSong());
				data.add(map);
			}

			SimpleAdapter adapter = new SimpleAdapter(DataBaseActivity.this,
					data, R.layout.list_sql, new String[] { "name", "song" },
					new int[] { R.id.tv_sql_name, R.id.tv_sql_song });
			listView.setAdapter(adapter);
		}

	}

	private void initViews() {
		listView = (ListView) findViewById(R.id.listView_sqlite);

		btn_add = (Button) findViewById(R.id.btn_sql_add);
		btn_del = (Button) findViewById(R.id.btn_sql_del);
		btn_upd = (Button) findViewById(R.id.btn_sql_update);
		btn_query = (Button) findViewById(R.id.btn_sql_query);
		btn_sure = (Button) findViewById(R.id.btn_sql_sure);
		btn_cancel = (Button) findViewById(R.id.btn_sql_cancel);
		btn_sure_del = (Button) findViewById(R.id.btn_sql_sure_del);
		btn_cancel_del = (Button) findViewById(R.id.btn_sql_cancel_del);
		btn_sure_update = (Button) findViewById(R.id.btn_sql_sure_update);
		btn_cancel_update = (Button) findViewById(R.id.btn_sql_cancel_update);

		et_name = (EditText) findViewById(R.id.et_sql_name);
		et_song = (EditText) findViewById(R.id.et_sql_number);

		et_del_song = (EditText) findViewById(R.id.et_sql_del);
		et_update_pre = (EditText) findViewById(R.id.et_sql_update_pre);
		et_update_hou = (EditText) findViewById(R.id.et_sql_update_hou);

		
		btn_add.setOnClickListener(this);
		btn_del.setOnClickListener(this);
		btn_upd.setOnClickListener(this);
		btn_query.setOnClickListener(this);
		btn_sure.setOnClickListener(this);
		btn_cancel.setOnClickListener(this);
		btn_sure_del.setOnClickListener(this);
		btn_sure_update.setOnClickListener(this);
		btn_cancel_del.setOnClickListener(this);
		btn_cancel_update.setOnClickListener(this);


		layout_menu = (LinearLayout) findViewById(R.id.layout_menu);
		layout_add = (LinearLayout) findViewById(R.id.layout_add);
		layout_delete = (LinearLayout) findViewById(R.id.layout_del);
		layout_update = (LinearLayout) findViewById(R.id.layout_update);
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.btn_sql_add:

			changeViews(false, true, false, false);

			break;
		case R.id.btn_sql_del:

			changeViews(false, false, true, false);

			break;
		case R.id.btn_sql_update:

			changeViews(false, false, false, true);

			break;
		case R.id.btn_sql_query:

			changeViews(true, false, false, false);
			initListView();

			break;

		case R.id.btn_sql_sure:

			Singer singer = new Singer();

			String name = et_name.getText().toString();
			String song = et_song.getText().toString();

			singer.setName(name);
			singer.setSong(song);

			dao.insert(singer);

			changeViews(true, false, false, false);

			break;
		case R.id.btn_sql_cancel:

			et_name = null;
			et_song = null;
			changeViews(true, false, false, false);

			break;

		case R.id.btn_sql_sure_del:

			Singer singer_del = new Singer();
			String song_del = et_del_song.getText().toString();
			singer_del.setSong(song_del);
			
			dao.delete(singer_del);
			initListView();
			changeViews(true, false, false, false);

			break;

		case R.id.btn_sql_sure_update:
			
			Singer singer_update = new Singer();
			String song_update_pre = et_update_pre.getText().toString();
			String song_update_hou = et_update_hou.getText().toString();
			singer_update.setSong(song_update_pre);
			singer_update.setName(song_update_hou);
			
			dao.update(singer_update);
			initListView();
			changeViews(true, false, false, false);
			break;

		case R.id.btn_sql_cancel_del:
			et_del_song = null;
			changeViews(true, false, false, false);
			break;

		case R.id.btn_sql_cancel_update:
			et_update_pre = null;
			et_update_hou = null;
			changeViews(true, false, false, false);
			break;

		default:
			break;
		}
	}

	private void changeViews(boolean menu, boolean add, boolean del,
			boolean update) {

		if (menu) {
			layout_menu.setVisibility(View.VISIBLE);
		} else {
			layout_menu.setVisibility(View.GONE);
		}

		if (add) {
			layout_add.setVisibility(View.VISIBLE);
		} else {
			layout_add.setVisibility(View.GONE);
		}

		if (del) {
			layout_delete.setVisibility(View.VISIBLE);
		} else {
			layout_delete.setVisibility(View.GONE);
		}

		if (update) {
			layout_update.setVisibility(View.VISIBLE);
		} else {
			layout_update.setVisibility(View.GONE);
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

		// ¹Ø±ÕÊý¾Ý¿â
		dao.closeDataBase();
	}

}
