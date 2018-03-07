/**
 * Desciption:Class use for correct size of view to mobile screen<br>
 *
 * @author L.Hien
 * @date Dec 11, 2014
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * The Class CorrectSizeUtil.
 */
public class CorrectSizeUtil {

    private static CorrectSizeUtil sCorrectSizeUtil;
    /**
     * The m activity.
     */
    private Activity mActivity;

    /**
     * xxx The screen width original.
     */
    private int screenWidthOriginal = 1080;

    /**
     * The screen rate.
     */
    private static float screenRate = 0;

    /**
     * TODO Function:Instantiates a new correct size util.<br>
     *
     * @param activity the activity
     * @author: L.Hien
     * @date: Dec 11, 2014
     */
    public CorrectSizeUtil(Activity activity) {
        this.mActivity = activity;
        if (sCorrectSizeUtil != null) {
            if (sCorrectSizeUtil.mActivity != activity) {
                sCorrectSizeUtil = this;
            }
        } else {
            sCorrectSizeUtil = this;
        }
    }

    public static CorrectSizeUtil getInstance(Activity activity) {
        if (sCorrectSizeUtil == null) {
            sCorrectSizeUtil = new CorrectSizeUtil(activity);
        }

        if (sCorrectSizeUtil.mActivity != activity) {
            sCorrectSizeUtil = new CorrectSizeUtil(activity);
        }
        return sCorrectSizeUtil;
    }

    /**
     * TODO Function:xxx Sets the original width.<br>
     *
     * @param width the new width original
     * @author: L.Hien
     * @date: Dec 11, 2014
     */
    public void setWidthOriginal(int width) {
        screenWidthOriginal = width;
        screenRate = 0;
    }

    /**
     * TODO Function:xxx Correct size of current activity from root.<br>
     *
     * @author: L.Hien
     * @date: Dec 11, 2014
     */
    public void correctSize() {
        View root = mActivity.findViewById(android.R.id.content);
        correctSize(root);
    }

    /**
     * TODO Function:xxx Correct size of input view.<br>
     *
     * @param root the root view that need to correct size
     * @author: L.Hien
     * @date: Dec 11, 2014
     */
    public void correctSize(View root) {
        // xxx stop correct action when view is viewgroup and corrected size
        if (root instanceof ViewGroup) {
            if (root.getTag() != null) {
                String tag = "correctSize";
                try {
                    tag = (String) root.getTag();
                } catch (Exception e) {

                }
                if (tag.equals("correctSize")) {
                    return;
                }
            }
        }

        // xxx correct size of current view
        correctSizeByLayout(root);

        // xxx correct size for each child view when current view is viewgroup
        if (root instanceof ViewGroup) {
            root.setTag("correctSize");
            ViewGroup group = (ViewGroup) root;
            for (int i = 0; i < group.getChildCount(); i++) {
                correctSize(group.getChildAt(i));
            }
        }

    }

    /**
     * TODO Function:xxx Gets the screen rate.<br>
     *
     * @return the screen rate
     * @author: L.Hien
     * @date: Dec 11, 2014
     */
    @SuppressWarnings("deprecation")
    public float getScreenRate() {
        // xxx return current rate if existed
        if (screenRate != 0) {
            return screenRate;
        }

        // xxx get current screen data
        Display display = ((WindowManager) mActivity
                .getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();

        // xxx only use shortest edge to calculate
        if (width > height) {
            int tmp = width;
            width = height;
            height = tmp;
        }

        // xxx calculate screen rate base on current screen size and origal
        // screen size
        screenRate = width * 1.0f / screenWidthOriginal;
        return screenRate;
    }

    /**
     * TODO Function:Correct size by layout.<br>
     *
     * @param v the v
     * @author: L.Hien
     * @date: Dec 11, 2014
     */
    public void correctSizeByLayout(View v) {
        float screenRate = getScreenRate();
        boolean isRoundDown = false;

        // xxx if view not attact to any other view , stop correct action
        if (v.getLayoutParams() == null) {
            return;
        }

        // xxx only do correct size for suitable width value
        if (v.getLayoutParams().width > 0) {
            // xxx for fiction value , increase to 1 or decrease to 0 base on
            // rounddown flag
            if (isRoundDown) {
                v.getLayoutParams().width = (int) Math.floor(v
                        .getLayoutParams().width * screenRate);
            } else {
                v.getLayoutParams().width = (int) Math
                        .ceil(v.getLayoutParams().width * screenRate);
            }
        }
        // xxx only do correct size for suitable height value
        if (v.getLayoutParams().height > 0) {
            // xxx for fiction value , increase to 1 or decrease to 0 base on
            // rounddown flag
            if (isRoundDown) {
                v.getLayoutParams().height = (int) Math.floor(v
                        .getLayoutParams().height * screenRate);
            } else {
                v.getLayoutParams().height = (int) Math.ceil(v
                        .getLayoutParams().height * screenRate);
            }
        }
        // xxx correct margin of LinearLayout
        if (v.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) v
                    .getLayoutParams();
            lp.setMargins(Math.round(lp.leftMargin * screenRate),
                    Math.round(lp.topMargin * screenRate),
                    Math.round(lp.rightMargin * screenRate),
                    Math.round(lp.bottomMargin * screenRate));
            v.setLayoutParams(lp);
        } else {
            // xxx correct margin of RelativeLayout
            if (v.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) v
                        .getLayoutParams();
                lp.setMargins(Math.round(lp.leftMargin * screenRate),
                        Math.round(lp.topMargin * screenRate),
                        Math.round(lp.rightMargin * screenRate),
                        Math.round(lp.bottomMargin * screenRate));
                v.setLayoutParams(lp);
            } else {
                // xxx correct margin of FrameLayout
                if (v.getLayoutParams() instanceof FrameLayout.LayoutParams) {
                    FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) v
                            .getLayoutParams();
                    lp.setMargins(Math.round(lp.leftMargin * screenRate),
                            Math.round(lp.topMargin * screenRate),
                            Math.round(lp.rightMargin * screenRate),
                            Math.round(lp.bottomMargin * screenRate));
                    v.setLayoutParams(lp);
                }
            }
        }

        // xxx correct Padding size
        v.setPadding(Math.round(v.getPaddingLeft() * screenRate),
                Math.round(v.getPaddingTop() * screenRate),
                Math.round(v.getPaddingRight() * screenRate),
                Math.round(v.getPaddingBottom() * screenRate));

        // xxx check exited tag for correct text size
        if (v.getTag() != null) {

            // xxx check existed of size from tag
            String tag = (String) v.getTag();
            int tagSize = 0;
            try {
                tagSize = Integer.parseInt(tag);
            } catch (Exception e) {

            }

            // xxx change text size for TextView to suit mobile screen
            if (v instanceof TextView) {
                ((TextView) v).setTextSize(TypedValue.COMPLEX_UNIT_PX, tagSize
                        * screenRate);
                if (v.getContentDescription() != null) {
                    try {

                        String contentDescription = (String) v.getContentDescription();
                        float lineSpacing = Float.parseFloat(contentDescription);
                        ((TextView) v).setLineSpacing(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, lineSpacing * screenRate, mActivity.getResources().getDisplayMetrics()), 1.0f);
//                    ((TextView) v).setLineSpacing(1);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e("CorrectSize", "can not get content description for resize line spacing of view " + v.getId());
                    }
                }

            }

            // xxx change text size for EditText to suit mobile screen
            if (v instanceof EditText) {
                ((EditText) v).setTextSize(TypedValue.COMPLEX_UNIT_PX, tagSize
                        * screenRate);
            }

            // xxx change text size for Button to suit mobile screen
            if (v instanceof Button) {
                ((Button) v).setTextSize(TypedValue.COMPLEX_UNIT_PX, tagSize
                        * screenRate);
            }
            if (v instanceof ImageView) {

            } else {
                // xxx remove tag
                v.setTag(null);
            }
        }
    }

    /**
     * TODO Function:xxx Gets the value after resize.<br>
     *
     * @param value the value
     * @return the value after resize
     * @author: L.Hien
     * @date: Dec 11, 2014
     */
    public static int getValueAfterResize(int value) {
        int return_value = Math.round(value * screenRate);
        return return_value;
    }
}
