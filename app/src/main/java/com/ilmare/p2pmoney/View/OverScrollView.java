package com.ilmare.p2pmoney.View;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

/**
 * ===============================
 * 作者: ilmare:
 * 创建时间：6/9/2016 10:39 AM
 * 版本号： 1.0
 * 版权所有(C) 6/9/2016
 * 描述：自定义可以下拉的ScrollView
 * ===============================
 */
public class OverScrollView extends ScrollView {
    public OverScrollView(Context context) {
        super(context);
    }

    public OverScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OverScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private View child;
    private float y;
    private Rect normal = new Rect();
    private boolean animationFinish = true;

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if(getChildCount()>0){
            child = getChildAt(0);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (child == null) {
            return super.onTouchEvent(ev);
        } else {
            commonTouchEvent(ev);
        }
        return super.onTouchEvent(ev);
    }

    private void commonTouchEvent(MotionEvent ev) {
        if (animationFinish) {
            int action = ev.getAction();
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    y = ev.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    float preY = y == 0 ? ev.getY() : y;
                    float nowY = ev.getY();
                    int detailY = (int) (preY - nowY);
                    y = nowY;

                    //操作view进行拖动detailY的一半
                    if (isNeedMove()) {
                        //布局改变位置之前，记录一下正常状态的位置
                        if (normal.isEmpty()) {
                            normal.set(child.getLeft(), child.getTop(), child.getRight(), child.getBottom());
                        }
                        child.layout(child.getLeft(), child.getTop() - detailY / 3, child.getRight(), child.getBottom() - detailY / 3);
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    y = 0;
                    //布局回滚到原来的位置
                    if (isNeedAnimation()) {
                        animation();
                    }
                    break;
            }
        }
    }

    private boolean isNeedAnimation() {
        return !normal.isEmpty();
    }

    private void animation() {
        TranslateAnimation ta = new TranslateAnimation(0, 0, 0, normal.top - child.getTop());
        ta.setDuration(200);
        ta.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                animationFinish = false;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                child.clearAnimation();
                child.layout(normal.left, normal.top, normal.right, normal.bottom);
                normal.setEmpty();
                animationFinish = true;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        child.startAnimation(ta);

    }

    private boolean isNeedMove() {
        int offset = child.getMeasuredHeight() - getHeight();
        int scrollY = getScrollY();
        if (scrollY == 0 || scrollY == offset) {
            return true;
        }
        return false;
    }
}
