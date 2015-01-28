package com.suweiyue.draganddrop3;
import android.annotation.SuppressLint;  
import android.app.Activity;  
import android.content.ClipData;  
import android.content.ClipDescription;  
import android.graphics.Canvas;  
import android.graphics.Color;  
import android.graphics.Point;  
import android.graphics.drawable.ColorDrawable;  
import android.graphics.drawable.Drawable;  
import android.os.Bundle;  
import android.util.Log;  
import android.view.DragEvent;  
import android.view.View;  
import android.view.View.OnDragListener;  
import android.view.View.OnLongClickListener;  
import android.widget.ImageView;  
import android.widget.TextView;  
import android.widget.Toast;  
  
public class MainActivity extends Activity {  
    /** 
     * ΪImageView����һ���ַ���ǩ 
     */  
    private static final String IMAGEVIEW_TAG = "icon bitmap";  
    /** 
     * ���������϶���ImageView 
     */  
    private ImageView imageView;  
    /** 
     * �϶��¼����� 
     */  
    private myDragEventListener mDragListen;  
    /** 
     * �϶�ImageView�ĳ����¼����� 
     */  
    private OnLongClickListener mLongClick = new OnLongClickListener() {  
  
        @SuppressLint("NewApi")  
        @Override  
        public boolean onLongClick(View v) {  
            // ����һ��ClipData����  
            // �����Ϊ��������һ���з���ClipData.newPlainText()���Դ���һ�����ı�ClipData  
  
            // ����ImageView�ı�ǩ����һ��ClipData.Item����  
            ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());  
  
            // ʹ�ñ�ǩ�����ı����Ѿ�������item������һ��ClipData����  
            // ���ｫ��ClipData�д���һ���µ�ClipDescription������������MIME����Ϊ"text/plain"  
            ClipData dragData = new ClipData((CharSequence) v.getTag(),  
                    new String[] { ClipDescription.MIMETYPE_TEXT_PLAIN }, item);  
  
            // ʵ������קӰ��.  
            View.DragShadowBuilder myShadow = new MyDragShadowBuilder(imageView);  
  
            // ��ʼ��ק  
  
            return v.startDrag(dragData, // ����ק������  
                    myShadow, // ��ק��Ӱ��  
                    null, // ����Ҫʹ�ñ�������  
                    0 // ��ǣ�Ŀǰ�ò���������Ϊ0��  
            );  
        }  
    };  
  
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_main);  
  
        init();  
    }  
    /** 
     * ��������: ��ʼ��<br> 
     */  
    public void init() {  
        imageView = new ImageView(this);  
        imageView = (ImageView) findViewById(R.id.drag_image);  
        // ���ñ�ǩ  
        imageView.setTag(IMAGEVIEW_TAG);  
        //��ӳ����¼�  
        imageView.setOnLongClickListener(mLongClick);  
        // ����һ����ק���¼�������  
        mDragListen = new myDragEventListener();  
        // ��ק��Ӧ����  
        View view = findViewById(R.id.drag_room);  
        // Ϊ����������϶��¼�������  
        view.setOnDragListener(mDragListen);  
    }  
    /** 
     * ���϶���Ӱ��<br>  
     */  
    @SuppressLint("NewApi")  
    private static class MyDragShadowBuilder extends View.DragShadowBuilder {  
        // �϶���Ӱ��ͼ�� ��Ϊһ��drawable������  
        private static Drawable shadow;  
        // ���캯��  
        public MyDragShadowBuilder(View v) {  
            // ͨ��myDragShadowBuilder�洢View����  
            super(v);  
            // ����һ������ק��ͼ�񣬴�ͼ�����ͨ��ϵͳ��Canvas�����  
            shadow = new ColorDrawable(Color.LTGRAY);  
        }  
  
        // ����һ���ص�����������Ӱ��ά�Ⱥʹ����㷵�ظ�ϵͳ  
        @Override  
        public void onProvideShadowMetrics(Point size, Point touch) {  
            // ���嵱�صı���  
            int width;  
            int height;  
            // ������Ӱ�Ŀ��Ϊ��ͼһ��  
            width = getView().getWidth() / 2;  
            // ������Ӱ�ĸ߶�Ϊ��ͼһ��  
            height = getView().getHeight() / 2;  
            // ��ק��Ӱ��һ��ColorDrawable. ������ϵ�ά�Ⱥ�ϵͳ���ṩ��Canvas��һ����  
            // ��ˣ���ק��Ӱ���ᱻCanvas����  
            shadow.setBounds(0, 0, width, height);  
            // ���ò�����Ⱥ͸߶ȵĴ�С.ͨ����С�������ظ�ϵͳ  
            size.set(width, height);  
            // ���ô������λ��Ϊ��ק��Ӱ������  
            touch.set(width / 2, height / 2);  
        }  
  
        // �ڻ���Canvas�ж���һ���ص�������������ק����Ӱ���û�����ͨ������onProvideShadowMetrics()�ṩ��ά��  
        // ��ϵͳ����  
        @Override  
        public void onDrawShadow(Canvas canvas) {  
            // ����ϵͳ���ݵ�Canvas�ϻ���ColorDrawable  
            shadow.draw(canvas);  
        }  
    }  
  
    @SuppressLint("NewApi")  
    protected class myDragEventListener implements OnDragListener {  
  
        // �÷�����ϵͳ���ã�������ק�¼�����ʱ  
        @SuppressLint("ShowToast")  
        public boolean onDrag(View v, DragEvent event) {  
            // ����һ���������洢ͨ���¼����ݵ�action����  
            final int action = event.getAction();  
            // ÿ���¼��Ĵ���  
            switch (action) {  
  
                case DragEvent.ACTION_DRAG_STARTED:  
                    System.out.println("ACTION_DRAG_STARTED----------------");  
                    // ȷ���Ƿ������ͼ��View�����Խ�����ק����������  
                    if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {  
                        // ������һ�����ӣ�ͨ������TextView�ı�����ɫ������  
                        // ��֤�������Խ�������  
                        v.setBackgroundColor(Color.BLUE);  
                        ((TextView) v).setText("drag");  
                        // ǿ���ػ���ͼ����ʾ�µ�����  
                        v.invalidate();  
                        // ͨ������true������View���Խ�����ק����  
                        return (true);  
  
                    } else {  
                        // ����false. �ڵ�ǰ����ק�����²���ʱ����ͼ��View�������ٽ���  
                        // �¼�ֱ������ACTION_DRAG_ENDED  
                        return (false);  
                    }  
  
                case DragEvent.ACTION_DRAG_ENTERED:  
                    System.out.println("ACTION_DRAG_ENTERED----------------");  
                    // ��ק����Ӱ�Ѿ�����ָ��������TextView������Ϊ��ɫ������true���÷���ֵû������  
                    v.setBackgroundColor(Color.GREEN);  
                    ((TextView) v).setText("in");  
                    // ǿ���ػ���ͼ����ʾ�µ�����  
                    v.invalidate();  
                    return (true);  
  
                case DragEvent.ACTION_DRAG_LOCATION:  
                    // ���Ը��¼�  
                    return (true);  
  
                case DragEvent.ACTION_DRAG_EXITED:  
                    System.out.println("ACTION_DRAG_EXITED----------------");  
                    // ��ק��Ӱ�����ƶ���������TextView����ɫΪ��ɫ  
                    v.setBackgroundColor(Color.BLUE);  
                    ((TextView) v).setText("drag");  
                    // ǿ���ػ���ͼ����ʾ�µ�����  
                    v.invalidate();  
                    return (true);  
  
                case DragEvent.ACTION_DROP:  
                    System.out.println("ACTION_DROP----------------");  
                    // ���item������ק����  
                    ClipData.Item item = event.getClipData().getItemAt(0);  
                    // ��item����ı�����  
                    CharSequence dragData = item.getText();  
                    // ��ʾ��ק�����а�������Ϣ.  
                    Toast.makeText(MainActivity.this, "Dragged data is: " + dragData, Toast.LENGTH_SHORT).show();  
                    // ����������ɫ������  
                    v.setBackgroundColor(Color.WHITE);  
                    ((TextView) v).setText("get");  
                    // ǿ���ػ���ͼ����ʾ�µ�����  
                    v.invalidate();  
  
                    // ����true. DragEvent.getResult()���᷵��true.  
                    return (true);  
  
                case DragEvent.ACTION_DRAG_ENDED:  
                    System.out.println("ACTION_DRAG_ENDED----------------");  
                    // ����������ɫ������  
                    v.setBackgroundColor(Color.WHITE);  
                    ((TextView) v).setText("room");  
                    // ǿ���ػ���ͼ����ʾ�µ�����  
                    v.invalidate();  
                    //ͨ��getResult()�����ķ���ֵ�жϷ�����ʲô  
                    if (event.getResult()) {  
                        Toast.makeText(MainActivity.this, "The drop was handled.", Toast.LENGTH_LONG).show();  
                    } else {  
                        Toast.makeText(MainActivity.this, "The drop didn't work.", Toast.LENGTH_LONG).show();  
  
                    };  
                    return (true);  
  
                    // ����δ֪��action.  
                default:  
                    Log.e("DragDrop Example", "Unknown action type received by OnDragListener.");  
  
                    break;  
            }  
            ;  
            return true;  
        };  
    }  
  
} 
