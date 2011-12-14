package com.finproj;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class OnePhoto extends Activity{
	private String ftID;
	private Bitmap bm;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.one_photo1);
        
        ftID = this.getIntent().getExtras().getString( "_ID" );
        
        Cursor c = FinProj.mDbHelper.ftStoryFetchByID( ftID );
        c.moveToFirst();
        
        EditText edittextOPStory    = (EditText) findViewById( R.id.edittextOPStory );
        TextView edittextOPLocation = (TextView) findViewById( R.id.editTextOPLocation );
        TextView edittextOPTime     = (TextView) findViewById( R.id.editTextOPTime );
        ImageView imageViewOPImage  = (ImageView)findViewById( R.id.imageViewOPImage);
        
        
		try {
			bm = MediaStore.Images.Media.getBitmap( this.getContentResolver(), Uri.parse( c.getString( 2 ) ) );
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        imageViewOPImage.setImageBitmap( bm );
        
        edittextOPStory.setText( c.getString( 3 ) );
        edittextOPLocation.setText( c.getString( 4 ) );
        edittextOPTime.setText( new Date(Long.parseLong(c.getString( 5 ))).toLocaleString() );
        
        c.close();
    }
}
