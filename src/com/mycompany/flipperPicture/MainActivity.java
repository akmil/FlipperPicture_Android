package com.mycompany.flipperPicture;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.os.Bundle.*;
import android.view.View.OnClickListener;
import android.widget.ViewFlipper;
import android.widget.Button.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.widget.AdapterView.*;
// имплементим OnClickListener для кнопок next, back
public class MainActivity extends Activity implements OnClickListener
{
	
Button bNext;
Button bBack;
ViewFlipper flipper;
TextView flippertextInfo;
//login screen
private EditText email, login;
private Button bSignIn;
private TextView infotext;
//list view
private ListView photolist;
	int i ;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		//setContentView(R.layout.login);
		bSignIn=(Button)findViewById(R.id.bSignIn);
		//bSignIn.setOnClickListener(this);
		
		//flipper initialization
		flipper=(ViewFlipper)findViewById(R.id.flipper);
		bNext=(Button)findViewById(R.id.bNext);
		bBack=(Button)findViewById(R.id.bBack);
		flippertextInfo = (TextView)findViewById(R.id.mainTextView);
		//Toast.makeText(this,"here is ok",Toast.LENGTH_SHORT);
		bNext.setOnClickListener(this);
		bBack.setOnClickListener(this);
		//photolist =(ListView)findViewById(R.id.lvGroups);
    }
	//login button
	public void onClickSignIn(View v){
		String userMail, userPass;
		login =(EditText)findViewById(R.id.ePasswordText);
		email =(EditText)findViewById(R.id.eEmailText);
		
		userMail = email.getText().toString();
		userPass = login.getText().toString();
		Toast.makeText(this,"you enter mail:",Toast.LENGTH_SHORT);
		infotext=(TextView)findViewById(R.id.info);
		infotext.setText("you enter mail: "+ userMail +" \n");
		infotext.setText(infotext.getText()+ "you enter pass: " + userPass);
		//connect to vkApi 
		
		//if(connection()) //если соединение ок то проверяем пароль
		
		if(userMail.equals("user") && userPass.equals("pass")){
			infotext.setText("its correct "+ userMail +" \n");	
		}else {
			infotext.setText("set name to :user, password to: pass");
			login.setText("pass");
			email.setText("user");
			return ;
		}
		setContentView(R.layout.main);
		//go to gallery screen
	}
	
	
	String[] names ;//={"one","two"};
	@Override
	public void onClick(View v){
		/*flipper=(ViewFlipper)findViewById(R.id.flipper);
		bNext=(Button)findViewById(R.id.bNext);
		bBack=(Button)findViewById(R.id.bBack);
		flippertextInfo = (TextView)findViewById(R.id.tvId01);
		Toast.makeText(this,"here is ok",Toast.LENGTH_SHORT);
		*/
		//bNext.setOnClickListener(this);
		//bBack.setOnClickListener(this);
		names = getResources().getStringArray(R.array.photoNames);
		photolist =(ListView)findViewById(R.id.lvGroups);
		photolist.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		//ArrayAdapter<String> adList = new ArrayAdapter<String>(this,R.layout.itemalbums,names);
		ArrayAdapter<CharSequence> adList = ArrayAdapter.createFromResource(
			this,
			R.array.photoNames,
			android.R.layout.simple_list_item_single_choice);
			
		/*
				TextView itemlistText =(TextView)findViewById(R.id.tvItemText);
				itemlistText.setText("some txt");
		*/
		//photolist.addView("string",3);
		//adList.add(photolist);
		photolist.setAdapter(adList);
		
		//при нажатии на item
		/*
		photolist.setOnClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> parent, View p2View, int p3Id, long p4Position)
				{
					// TODO: Implement this method
					//Toast.makeText(this,"id= "+p3Id,Toast.LENGTH_SHORT).show();
				}
			});
		*/
		
		//при нажатии на item через switch
		/*
		switch(photolist.getCheckedItemPosition()){
			case 0:	
				photolist.setItemChecked(0,true);
				Toast.makeText(this,"show pict 0",Toast.LENGTH_SHORT).show();
				break;
			case 1:	
				Toast.makeText(this,"show pict 1",Toast.LENGTH_SHORT).show();
			break;
			case 2:	
				Toast.makeText(this,"show pict 2",Toast.LENGTH_SHORT).show();
				break;
			case 3:	
			Toast.makeText(this,"show pict 3",Toast.LENGTH_SHORT).show();
			break;
			default: photolist.setItemChecked(3,true);
		}
		*/
		//int listItem = R.layout.itemalbums;
		try{
			Toast.makeText(this, "photolist.getCheckedItemPosition() ",Toast.LENGTH_SHORT).show();
			
		}catch(Exception ex){
			//Toast(this,"onClick check",Toast.LENGTH_SHORT).show();
				}
		
		if (bNext==v){
			flipper.showNext();
			 flippertextInfo.setText("you pressed" + bNext.getText() +" ");
			 //listItem;
			flipper.getWidth();
		}else if(bBack ==v){
		flipper.showPrevious();
		}
	}
	/*
	//создаем инстанс обьекта DisplayRotate при повороте екрана
	@Override
	public Object onRetainNonConfigurationInstance()
	{
		// displRotate = new DisplayRotate
		return displRotate;
	}
	*/
} 
