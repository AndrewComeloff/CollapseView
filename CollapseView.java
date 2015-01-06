package com.example.komelovam.testapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by komelovam on 23.12.2014.
 */
public class CollapseView extends LinearLayout {

    private static final float ACCELERATION_COLLAPSE = 1.5f;
//    private final String TAG = getClass().getSimpleName();
//    private long speedAnimation = 1000 /3;

    private FrameLayout contentHeader;
    private LinearLayout contentItems;
    private boolean isCollapse;
    private List<View> listViewContent;
    private boolean dividerExist = true;
    private int dividerColor = Color.argb(255, 205, 205, 205); // Default color divider
    
    private Drawable bgHeaderColDraw;
    private Drawable bgHeaderExpDraw;
    private int bgHeaderColColor;
    private int bgHeaderExpColor;
    private int bgHeaderColRes;
    private int bgHeaderExpRes;

//    public ExpandView(Context context, AttributeSet attrs, int defStyle) {
//        super(context, attrs, defStyle);
//        if(!isInEditMode()){
//            init();
//        }
//    }

    public CollapseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if(!isInEditMode()){
            init();
        }
    }

    public CollapseView(Context context) {
        super(context);
        if(!isInEditMode()){
            init();
        }
    }

    private void init() {

        setOrientation(LinearLayout.VERTICAL);

        listViewContent = new ArrayList<>();

        contentHeader = new FrameLayout(getContext());
        FrameLayout.LayoutParams paramsHeader = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
        contentHeader.setLayoutParams(paramsHeader);
//        contentHeader.setBackgroundColor(Color.YELLOW);
        addView(contentHeader);

        contentItems = new LinearLayout(getContext());
        LinearLayout.LayoutParams paramsItems = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
        contentItems.setLayoutParams(paramsItems);
        contentItems.setOrientation(LinearLayout.VERTICAL);
//        contentItems.setBackgroundColor(Color.GREEN);
        addView(contentItems);

        contentHeader.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                isCollapse = !isCollapse;
                if(isCollapse) {
                    collapse(contentItems);
                    changeHeaderAfterAnim();
                } else {
                    expand(contentItems);
                    changeHeader();
                }
            }
        });

    }

    /**
     * Set view to header
     * @param view the view to set
     */
    public void setHeader(View view){
        contentHeader.removeAllViews();
        contentHeader.addView(view);
    }

    /**
     * Set to mait content.
     * @param list views for content
     */
    public void setContentViews(List<View> list){
        listViewContent = list;
        setContents();
    }

    /**
     * Set to main content.
     * @param view the view to set
     */
    public void setContentView(View view){
        contentItems.removeAllViews();
        contentItems.addView(view);
        listViewContent.clear();
        listViewContent.add(view);
    }

    /**
     * Add to main content.
     * @param view the view to add
     */
    public void addContentView(View view){
        listViewContent.add(view);
        setContents();
    }

    //  -  H E A D E R  ( collapse )  -
    /**
     * Set background for header.
     * @param background The Drawable to use as the background, or null to remove the
     *        background
     */
    public void setBackgroundHeaderColDraw(Drawable background) {
        bgHeaderColDraw = background;
        if (isCollapse)contentHeader.setBackgroundDrawable(background);
    }

    /**
     * Sets the background color for header.
     * @param color the color of the background
     */
    public void setBackgroundHeaderColColor(int color) {
        bgHeaderColColor = color;
        if (isCollapse)contentHeader.setBackgroundColor(color);
    }

    /**
     * Set the background header when items hided to a given resource. The resource should refer to
     * a Drawable object or 0 to remove the background.
     * @param resid The identifier of the resource.
     */
    public void setBackgroundHeaderColRes(int resid) {
        bgHeaderColRes = resid;
        if (isCollapse)contentHeader.setBackgroundResource(resid);
    }

    //  -  H E A D E R  ( expand )  -
    /**
     * Set background for header.
     * @param background The Drawable to use as the background, or null to remove the
     *        background
     */
    public void setBackgroundHeaderExpDraw(Drawable background) {
        bgHeaderExpDraw = background;
        if (!isCollapse)contentHeader.setBackgroundDrawable(background);
    }

    /**
     * Sets the background color for header.
     * @param color the color of the background
     */
    public void setBackgroundHeaderExpColor(int color) {
        bgHeaderExpColor = color;
        if (!isCollapse)contentHeader.setBackgroundColor(color);
    }

    /**
     * Set the background to a given resource. The resource should refer to
     * a Drawable object or 0 to remove the background.
     * @param resid The identifier of the resource.
     */
    public void setBackgroundHeaderExpRes(int resid) {
        bgHeaderExpRes = resid;
        if (!isCollapse)contentHeader.setBackgroundResource(resid);
    }

    //  -  I T E M S  -
    /**
     * Set background for items(collapsing).
     * @param background The Drawable to use as the background, or null to remove the
     *        background
     */
    public void setBackgroundItemsDraw(Drawable background) {
        contentItems.setBackgroundDrawable(background);
    }

    /**
     * Sets the background color for items(collapsing).
     * @param color the color of the background
     */
    public void setBackgroundItemsColor(int color) {
        contentItems.setBackgroundColor(color);
    }

    /**
     * Set the background to a given resource. The resource should refer to
     * a Drawable object or 0 to remove the background.
     * @param resid The identifier of the resource.
     */
    public void setBackgroundItemsRes(int resid) {
        contentItems.setBackgroundResource(resid);
    }

    /**
     * Set dividers between items
     * @param exist true to make dividers, false otherwise
     */
    public void setDivider(boolean exist){
        dividerExist = exist;
    }

    /**
     * Sets color for dividers.
     * @param color the color of dividers
     */
    public void setDividerColor(int color){
        dividerColor = color;
    }

    /**
     * Set items collapse
     * @param collapse true collapse, false expand
     */
    public void setCollapsed(boolean collapse) {
        if (collapse) {
            contentItems.setVisibility(View.GONE);
        } else {
            contentItems.setVisibility(View.VISIBLE);
        }
        isCollapse = collapse;
        changeHeader();
    }

    private void expand(final View v) {
        v.measure(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        final int targtetHeight = v.getMeasuredHeight();
//        Log.d(TAG, "expand targtetHeight " + targtetHeight);

//        v.getLayoutParams().height = 0;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime > 0) {
                    int height = interpolatedTime == 1
                            ? LayoutParams.WRAP_CONTENT
                            : (int)(targtetHeight * interpolatedTime);
//                    Log.e(TAG, "Expand height view " + height);
//
                    v.getLayoutParams().height = interpolatedTime == 1
                            ? LayoutParams.WRAP_CONTENT
                            : (int)(targtetHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() { return true; }
        };

        a.setDuration((int)getDurationAnimation(v));
        v.startAnimation(a);
    }

    private void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();
//        Log.d(TAG, "collapse initialHeight " + initialHeight);

        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if(interpolatedTime == 1){
                    v.setVisibility(View.GONE);
                }else{
                    v.getLayoutParams().height = initialHeight - (int)(initialHeight * interpolatedTime);
                    v.requestLayout();
                }
//                Log.e(TAG, " collapse height view " + (initialHeight - (int)(initialHeight * interpolatedTime)));
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };


        a.setDuration((int)getDurationAnimation(v));
//	    Log.d(TAG, "speed animation " + (int)(initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    // 1dp/ms +
    private float getDurationAnimation(View view){
        return view.getMeasuredHeight() / view.getContext().getResources().getDisplayMetrics().density * ACCELERATION_COLLAPSE;
    }

    private void setContents() {
        if(listViewContent != null){
//            LayoutInflater li = ((Activity) getContext()).getLayoutInflater();
            contentItems.removeAllViews();

            for (int i = 0; i < listViewContent.size(); i++) {
                if(i != 0 && dividerExist) {
                    contentItems.addView(createSeparator());
                }
                contentItems.addView(listViewContent.get(i));
            }
        }
    }

    private View createSeparator() {
        View separator = new View(getContext());
        LinearLayout.LayoutParams paramsItems = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
        separator.setLayoutParams(paramsItems);
        separator.setBackgroundColor(dividerColor);
        return separator;
    }

    private void changeHeader() {
        if (isCollapse){
            if(bgHeaderColDraw != null) contentHeader.setBackgroundDrawable(bgHeaderColDraw);
            if(bgHeaderColColor > 0) contentHeader.setBackgroundColor(bgHeaderColColor);
            if(bgHeaderColRes > 0) contentHeader.setBackgroundResource(bgHeaderColRes);
        } else {
            if(bgHeaderColDraw != null) contentHeader.setBackgroundDrawable(bgHeaderExpDraw);
            if(bgHeaderColColor > 0) contentHeader.setBackgroundColor(bgHeaderExpColor);
            if(bgHeaderColRes > 0) contentHeader.setBackgroundResource(bgHeaderExpRes);
        }
    }

    private void changeHeaderAfterAnim(){
        try {
            new Handler().postDelayed(new Runnable() {

                public void run() {
                    if(!isCollapse)return;
                    changeHeader();
                }

            }, (int)getDurationAnimation(contentItems));
        } catch (Exception e) {
        }
    }
}