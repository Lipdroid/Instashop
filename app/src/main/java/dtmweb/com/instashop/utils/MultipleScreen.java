/**
 * Desciption: Correct Multiple screen with screen size  = 480
 * Version: 1.1
 * Author: Tran Quang Long
 * Create date: May 14, 2014
 * 
 * Note: screen size height include status bar
 */

/*
 * Log:
 * -v1.1: 2014.05.15 : resize all view, show log screen size  
 * 
 */

package dtmweb.com.instashop.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author Lipu Hossain
 * 
 */
public class MultipleScreen {

	private static int SCREEN_WIDTH_SIZE = 1080;
	private static float scale = 0f;
	private static Activity mActivity = null;

	/*--
	 * Desciption: create MultipleScreen Ultility for get scale value
	 * Must create every time when it was used for force close in android
	 * Author: Tran Quang Long
	 * Create date: Sep 3, 2013
	 * @param activity current activity
	 *
	 */
	public MultipleScreen(Activity activity) {
		mActivity = activity;
		scale = screenScale();
	}

	/*--
	 * Desciption: create MultipleScreen Ultility
	 * Author: Tran Quang Long
	 * Create date: Sep 3, 2013
	 * @param activity current context
	 *
	 */
	public MultipleScreen(Context context) {
		mActivity = (Activity) context;
		scale = screenScale();
	}

	/*--
	 * Function: get Desity of current device
	 * @return desity
	 * Author: Tran Quang Long
	 * Create date: Sep 3, 2013
	 */
	public static float getDensity() {
		return mActivity.getResources().getDisplayMetrics().density;
	}

	/*--
	 * Function: get size view from pixel to dp
	 * @param size
	 * @return
	 * Author: Tran Quang Long
	 * Create date: Sep 3, 2013
	 */
	public static int getSizeDP(int size) {
		return Math.round((size / getDensity()));
	}

	/*--
	 * Function: get scale of screen device
	 * @return
	 * Author: Tran Quang Long
	 * Create date: Sep 3, 2013
	 */
	@SuppressWarnings("deprecation")
	public static float screenScale() {

		if (scale != 0) {
			return scale;
		}

		WindowManager windowmanager = mActivity.getWindowManager();
		Display disp = windowmanager.getDefaultDisplay();
		//Log.e("screenScale", "Width" + disp.getWidth() + ",\tHeight" + disp.getHeight() + ",(include status bar)(no navigation)");
		int actualSreenWidth = SCREEN_WIDTH_SIZE;
		return disp.getWidth() * 1.0f / actualSreenWidth;
	}

	/*--
	 * Function: set size, margin, padding of view depend on screen resolution 
	 * @param v: view 
	 * Author: Tran Quang Long
	 * Create date: Oct 8, 2013
	 */
	public static void reSizeViewPx(View v) {
		try {
			int width = 0;
			int height = 0;

			try {
				width = v.getLayoutParams().width;
				height = v.getLayoutParams().height;

				if (width > 0)
					v.getLayoutParams().width = Math.round(width * scale);
				if (height > 0)
					v.getLayoutParams().height = Math.round(height * scale);
			} catch (Exception e) {
				// Log.e("-error:", "--resize root error");
				// e.printStackTrace();
				// Log.e("-error:", "--end resize root error");
			}

			int left = 0, right = 0, top = 0, bottom = 0;

			left = Math.round(v.getPaddingLeft() * scale);
			top = Math.round(v.getPaddingTop() * scale);
			right = Math.round(v.getPaddingRight() * scale);
			bottom = Math.round(v.getPaddingBottom() * scale);

			v.setPadding(left, top, right, bottom);

			left = 0;
			top = 0;
			right = 0;
			bottom = 0;

			// check type of LayoutParams for set margin
			if (v.getLayoutParams() instanceof LinearLayout.LayoutParams) {
				LinearLayout.LayoutParams layout = (LinearLayout.LayoutParams) v.getLayoutParams();
				left = Math.round(layout.leftMargin * scale);
				top = Math.round(layout.topMargin * scale);
				right = Math.round(layout.rightMargin * scale);
				bottom = Math.round(layout.bottomMargin * scale);

				layout.setMargins(left, top, right, bottom);

				v.setLayoutParams(layout);
			} else if (v.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
				RelativeLayout.LayoutParams layout = (RelativeLayout.LayoutParams) v.getLayoutParams();
				left = Math.round(layout.leftMargin * scale);
				top = Math.round(layout.topMargin * scale);
				right = Math.round(layout.rightMargin * scale);
				bottom = Math.round(layout.bottomMargin * scale);
				layout.setMargins(left, top, right, bottom);

				v.setLayoutParams(layout);
			} else if (v.getLayoutParams() instanceof FrameLayout.LayoutParams) {
				FrameLayout.LayoutParams layout = (FrameLayout.LayoutParams) v.getLayoutParams();
				left = Math.round(layout.leftMargin * scale);
				top = Math.round(layout.topMargin * scale);
				right = Math.round(layout.rightMargin * scale);
				bottom = Math.round(layout.bottomMargin * scale);
				layout.setMargins(left, top, right, bottom);

				v.setLayoutParams(layout);
			}

			try {
				if (v.getTag() != null) {
					float sizetext = Float.parseFloat(v.getTag().toString());
					if (v instanceof TextView)
						((TextView) v).setTextSize(TypedValue.COMPLEX_UNIT_PX, sizetext * scale);

					if (v instanceof Button)
						((Button) v).setTextSize(TypedValue.COMPLEX_UNIT_PX, sizetext * scale);

					if (v instanceof EditText)
						((EditText) v).setTextSize(TypedValue.COMPLEX_UNIT_PX, sizetext * scale);

					//v.setTag(null);
				}
			} catch (Exception e) {
				// v.setTag(null);
				// maybe tag in v is null
				Log.e("reSizeViewPx > text", "name: " + v.getId() + ",type:" + v.getClass());
			}

		} catch (Exception e) {
			// e.printStackTrace();
		}
	}

	public static void setTextSize(View view, int size) {
		try {
			if (view instanceof TextView)
				((TextView) view).setTextSize(TypedValue.COMPLEX_UNIT_PX, size * scale);

			if (view instanceof Button)
				((Button) view).setTextSize(TypedValue.COMPLEX_UNIT_PX, size * scale);

			if (view instanceof EditText)
				((EditText) view).setTextSize(TypedValue.COMPLEX_UNIT_PX, size * scale);

		} catch (Exception e) {
			Log.e("--setTextSize:", e.toString());
		}
	}

	public static void resizeViewAndFont(View view, int size) {
		setTextSize(view, size);
		reSizeViewPx(view);
	}

	public static void resizeAllView(Activity activity) {
		resizeAllView((ViewGroup) activity.findViewById(android.R.id.content).getRootView());
	}

	public static void resizeAllView(ViewGroup root) {
		if (root.getTag() == null){
			// set tag changed for viewgroup
			root.setTag("changed");
			for (int i = 0; i < root.getChildCount(); i++) {
				reSizeViewPx(root.getChildAt(i));
				if (root.getChildAt(i) instanceof ViewGroup) {
					resizeAllView((ViewGroup) root.getChildAt(i));
				}
			}
		} else if (root.getTag().equals("changed")) {
			//do something when get tag value is "changed"
			//Toast.makeText(mActivity, "Viewgroup was resized!", Toast.LENGTH_SHORT).show();
		}
	}
	
	/*
	 * Function: return value after resize
	 * Author: Phan Tri
	 * Create date: June 19, 2014
	 */	
	public static int getValueAfterResize(int value){
		int return_value = Math.round(value * scale);
		return return_value;
	}
}
