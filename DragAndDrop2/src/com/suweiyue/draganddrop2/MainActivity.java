package com.suweiyue.draganddrop2;


import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipDescription;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.widget.*;

public class MainActivity extends Activity{
   ImageView ima;
   private static final String IMAGEVIEW_TAG = "Android Logo";
   String msg;

   private android.widget.RelativeLayout.LayoutParams layoutParams;

	private void openOptionsDialog() {
		// TODO Auto-generated method stub
		new AlertDialog.Builder(MainActivity.this)
		.setTitle("Title")
		.setMessage("Message")
		.show();
	}
	
	
   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      ima = (ImageView)findViewById(R.id.iv_logo);
      // Sets the tag
      ima.setTag(IMAGEVIEW_TAG);

      ima.setOnLongClickListener(new View.OnLongClickListener() {
         @Override
         public boolean onLongClick(View v) {
            ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());

            String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
            ClipData dragData = new ClipData(v.getTag().toString(), 
            mimeTypes, item);

            // Instantiates the drag shadow builder.
            View.DragShadowBuilder myShadow = new DragShadowBuilder(ima);

            // Starts the drag
            v.startDrag(dragData,  // the data to be dragged
            myShadow,  // the drag shadow builder
            null,      // no need to use local data
            0          // flags (not currently used, set to 0)
            );
            return true;
         }
      });

      // Create and set the drag event listener for the View
      ima.setOnDragListener( new OnDragListener(){
         @Override
         public boolean onDrag(View v,  DragEvent event){
         switch(event.getAction())                   
         {
            case DragEvent.ACTION_DRAG_STARTED:
               layoutParams = (RelativeLayout.LayoutParams) 
               v.getLayoutParams();
               Log.d(msg, "Action is DragEvent.ACTION_DRAG_STARTED");
               // Do nothing
               break;
            case DragEvent.ACTION_DRAG_ENTERED:
               Log.d(msg, "Action is DragEvent.ACTION_DRAG_ENTERED");
               int x_cord = (int) event.getX();
               int y_cord = (int) event.getY();  
               break;
            case DragEvent.ACTION_DRAG_EXITED :
               Log.d(msg, "Action is DragEvent.ACTION_DRAG_EXITED");
               x_cord = (int) event.getX();
               y_cord = (int) event.getY();
               layoutParams.leftMargin = x_cord;
               layoutParams.topMargin = y_cord;
               v.setLayoutParams(layoutParams);
               break;
            case DragEvent.ACTION_DRAG_LOCATION  :
               Log.d(msg, "Action is DragEvent.ACTION_DRAG_LOCATION");
               x_cord = (int) event.getX();
               y_cord = (int) event.getY();
               break;
            case DragEvent.ACTION_DRAG_ENDED   :
               Log.d(msg, "Action is DragEvent.ACTION_DRAG_ENDED");
               // Do nothing
               openOptionsDialog() ; 
               break;
            case DragEvent.ACTION_DROP:
               Log.d(msg, "ACTION_DROP event");
               // Do nothing
//               openOptionsDialog() ; 
               break;
            default: break;
            }
            return true;
         }
      });
   }
}