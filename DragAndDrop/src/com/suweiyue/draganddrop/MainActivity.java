package com.suweiyue.draganddrop;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnDragListener;
import android.widget.ImageView;
import android.widget.Toast;




public class MainActivity extends Activity {

	// Create a string for the ImageView label
	private static final String IMAGEVIEW_TAG = "icon bitmap";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
    	// Creates a new ImageView
    	ImageView imageView = new ImageView(this);
    	
    	ImageView image=(ImageView)findViewById(R.id.iv_logo);
    	BitmapDrawable drawable=(BitmapDrawable)image.getDrawable();
    	Bitmap mIconBitmap = drawable.getBitmap();
		// Sets the bitmap for the ImageView from an icon bit map (defined elsewhere)
    	imageView.setImageBitmap(mIconBitmap);
    	// Sets the tag
    	imageView.setTag(IMAGEVIEW_TAG);
    	
    	//Creates a new drag event listener
    	OnDragListener mDragListen = new myDragEventListener();

    	//Sets the drag event listener for the View
    	imageView.setOnDragListener(mDragListen);
    	
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    
}


class MyDragShadowBuilder extends View.DragShadowBuilder {

    // The drag shadow image, defined as a drawable thing
    private static Drawable shadow;

        // Defines the constructor for myDragShadowBuilder
        public MyDragShadowBuilder(View v) {

            // Stores the View parameter passed to myDragShadowBuilder.
            super(v);

            // Creates a draggable image that will fill the Canvas provided by the system.
            shadow = new ColorDrawable(Color.LTGRAY);
        }

        // Defines a callback that sends the drag shadow dimensions and touch point back to the
        // system.
        @Override
        public void onProvideShadowMetrics (Point size, Point touch){
            int width;
			int height;

            // Sets the width of the shadow to half the width of the original View
            width = getView().getWidth() / 2;

            // Sets the height of the shadow to half the height of the original View
            height = getView().getHeight() / 2;

            // The drag shadow is a ColorDrawable. This sets its dimensions to be the same as the
            // Canvas that the system will provide. As a result, the drag shadow will fill the
            // Canvas.
            shadow.setBounds(0, 0, width, height);

            // Sets the size parameter's width and height values. These get back to the system
            // through the size parameter.
            size.set(width, height);

            // Sets the touch point's position to be in the middle of the drag shadow
            touch.set(width / 2, height / 2);
        }

        // Defines a callback that draws the drag shadow in a Canvas that the system constructs
        // from the dimensions passed in onProvideShadowMetrics().
        @Override
        public void onDrawShadow(Canvas canvas) {

            // Draws the ColorDrawable in the Canvas passed in from the system.
            shadow.draw(canvas);
        }
}






class myDragEventListener implements View.OnDragListener {

 // This is the method that the system calls when it dispatches a drag event to the
 // listener.
 public boolean onDrag(View v, DragEvent event) {

     // Defines a variable to store the action type for the incoming event
     final int action = event.getAction();

     // Handles each of the expected events
     switch(action) {

         case DragEvent.ACTION_DRAG_STARTED:

             // Determines if this View can accept the dragged data
             if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {

                 // As an example of what your application might do,
                 // applies a blue color tint to the View to indicate that it can accept
                 // data.
                 ((ImageView) v).setColorFilter(Color.BLUE);

                 // Invalidate the view to force a redraw in the new tint
                 v.invalidate();

                 // returns true to indicate that the View can accept the dragged data.
                 return true;

             }

             // Returns false. During the current drag and drop operation, this View will
             // not receive events again until ACTION_DRAG_ENDED is sent.
             return false;

         case DragEvent.ACTION_DRAG_ENTERED:

             // Applies a green tint to the View. Return true; the return value is ignored.

             ((ImageView) v).setColorFilter(Color.GREEN);

             // Invalidate the view to force a redraw in the new tint
             v.invalidate();

             return true;

         case DragEvent.ACTION_DRAG_LOCATION:

             // Ignore the event
             return true;

         case DragEvent.ACTION_DRAG_EXITED:

             // Re-sets the color tint to blue. Returns true; the return value is ignored.
             ((ImageView) v).setColorFilter(Color.BLUE);

             // Invalidate the view to force a redraw in the new tint
             v.invalidate();

             return true;

         case DragEvent.ACTION_DROP:

             // Gets the item containing the dragged data
             ClipData.Item item = event.getClipData().getItemAt(0);

             // Gets the text data from the item.
             CharSequence dragData = item.getText();

             // Displays a message containing the dragged data.
//             Toast.makeText(this, "Dragged data is " + dragData, Toast.LENGTH_LONG);

             // Turns off any color tints
             ((ImageView) v).clearColorFilter();

             // Invalidates the view to force a redraw
             v.invalidate();

             // Returns true. DragEvent.getResult() will return true.
             return true;

         case DragEvent.ACTION_DRAG_ENDED:

             // Turns off any color tinting
             ((ImageView) v).clearColorFilter();

             // Invalidates the view to force a redraw
             v.invalidate();

             // Does a getResult(), and displays what happened.
             if (event.getResult()) {
//                 Toast.makeText(this, "The drop was handled.", Toast.LENGTH_LONG);

             } else {
//                 Toast.makeText(this, "The drop didn't work.", Toast.LENGTH_LONG);

             }

             // returns true; the value is ignored.
             return true;

         // An unknown action type was received.
         default:
             Log.e("DragDrop Example","Unknown action type received by OnDragListener.");
             break;
     }
     
     return false;
 }
};
