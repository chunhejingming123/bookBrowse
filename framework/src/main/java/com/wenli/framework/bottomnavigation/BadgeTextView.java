package com.wenli.framework.bottomnavigation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * Class description
 *
 * @author ashokvarma
 * @version 1.0
 * @since 23 Jun 2017
 */
@SuppressLint("Instantiatable")
class BadgeTextView extends AppCompatTextView {

    private ShapeBadgeItem mShapeBadgeItem;

    private boolean mAreDimensOverridden;
    private int mDesiredWidth = 100;
    private int mDesiredHeight = 100;

    public BadgeTextView(Context context) {
        this(context, null);
    }

    public BadgeTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BadgeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // method stub
    }

    /**
     * clear's all previous set values
     */
    void clearPrevious() {
        mAreDimensOverridden = false;
        mShapeBadgeItem = null;
    }

    /**
     * @param shapeBadgeItem that can draw on top of the this view
     */
    void setShapeBadgeItem(ShapeBadgeItem shapeBadgeItem) {
        mShapeBadgeItem = shapeBadgeItem;
    }

    /**
     * if width and height of the view needs to be changed
     *
     * @param width new width that needs to be set
     * @param height new height that needs to be set
     */
    void setDimens(int width, int height) {
        mAreDimensOverridden = true;
        mDesiredWidth = width;
        mDesiredHeight = height;
        requestLayout();
    }

    /**
     * invalidate's view so badgeItem can draw again
     */
    void recallOnDraw() {
        invalidate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mShapeBadgeItem != null) {
            mShapeBadgeItem.draw(canvas);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mAreDimensOverridden) {
            int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
            int widthSize = View.MeasureSpec.getSize(widthMeasureSpec);
            int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);
            int heightSize = View.MeasureSpec.getSize(heightMeasureSpec);

            int width;
            int height;

            //Measure Width
            if (widthMode == View.MeasureSpec.EXACTLY) {
                //Must be this size
                width = widthSize;
            } else if (widthMode == View.MeasureSpec.AT_MOST) {
                //Can't be bigger than...
                width = Math.min(mDesiredWidth, widthSize);
            } else {
                //Be whatever you want
                width = mDesiredWidth;
            }

            //Measure Height
            if (heightMode == View.MeasureSpec.EXACTLY) {
                //Must be this size
                height = heightSize;
            } else if (heightMode == View.MeasureSpec.AT_MOST) {
                //Can't be bigger than...
                height = Math.min(mDesiredHeight, heightSize);
            } else {
                //Be whatever you want
                height = mDesiredHeight;
            }

            //MUST CALL THIS
            setMeasuredDimension(width, height);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
