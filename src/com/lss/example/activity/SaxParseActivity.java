package com.lss.example.activity;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.lss.example.activity.ServiceMessengerActivity.MyHandler;
import com.lss.example.bean.SaxBook;
import com.lss.example.lssdemo.R;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SaxParseActivity extends BaseActivity implements OnClickListener{

	private TextView tv_book = null;
	private Button btn_parse = null;
	private List<SaxBook> books = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_book);
		
		initView();
	}
	
	private void initView(){
		btn_parse = getView(R.id.btn_parse);
		tv_book = getView(R.id.tv_show_book);
		
		btn_parse.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		
		switch (view.getId()) {
		case R.id.btn_parse:
			try {
				InputStream is = getAssets().open("book.xml");
				books = readXML(is);
				SaxBook book = new SaxBook();
				//book = books.get(0);
				
				SaxBook book2 = new SaxBook();
				//book2 = books.get(1);
				
				//String str = book.getName()  + book.getId() + book.getPrice();
				//String str2 = book2.getName()  + book2.getId() + book2.getPrice();
				
				tv_book.setText("   " + books.size());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			break;

		default:
			break;
		}
	}
	
	
	public List<SaxBook> readXML(InputStream is) throws IOException{
		
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser parser = factory.newSAXParser();
			parser.setProperty("http://xml.org/sax/features/namespaces",true);
			xmlHandle handle = new xmlHandle();
			parser.parse(is, handle);
			is.close();
			 return handle.getBooks();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
	}
	
	public class xmlHandle extends DefaultHandler{
		
		List<SaxBook> books = null;
		SaxBook book = null;
		String tagName = null;
		
		private List<SaxBook> getBooks(){
			return books;
		}
		
		@Override
		public void startDocument() throws SAXException {
			// TODO Auto-generated method stub
			super.startDocument();
			books = new ArrayList<SaxBook>();
			
		}
		
		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			// TODO Auto-generated method stub
			super.startElement(uri, localName, qName, attributes);
			
			if(localName.equals("books")){
				 book = new SaxBook();
			}
			this.tagName = localName;
		}
		
		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {
			// TODO Auto-generated method stub
			super.characters(ch, start, length);
			
		     if(tagName!=null){
                 String data = new String(ch, start, length);
                 if(tagName.equals("id")){
                             this.book.setId(Integer.parseInt(data));
                 }else if(tagName.equals("name")){
                             this.book.setName(data);
                 }else if(tagName.equals("price")){
                	 this.book.setPrice(Double.parseDouble(data));
				}
     }
			
		}
		
		
		
		@Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException {
			// TODO Auto-generated method stub
			super.endElement(uri, localName, qName);
			
			  if(localName.equals("person")){
                  books.add(book);
                  book = null;
      }

      this.tagName = null;
		}
		
		@Override
		public void endDocument() throws SAXException {
			// TODO Auto-generated method stub
			super.endDocument();
			tagName = null;
		}
	}
	

}
