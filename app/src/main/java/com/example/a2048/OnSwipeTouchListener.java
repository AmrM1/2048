package com.example.a2048;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class OnSwipeTouchListener implements View.OnTouchListener
{
    private final GestureDetector gestureDetector;
    private Context context;

    OnSwipeTouchListener(Context ctx, View mainView)
    {
        gestureDetector = new GestureDetector(ctx, new GestureListener());
        mainView.setOnTouchListener(this);
        context = ctx;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        return gestureDetector.onTouchEvent(event);
    }

    public class GestureListener extends GestureDetector.SimpleOnGestureListener
    {

        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
        {
            boolean result = false;

            try
            {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if (Math.abs(diffX) > Math.abs(diffY))
                {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD)
                    {
                        if (diffX > 0)
                            onSwipeRight();

                        else
                            onSwipeLeft();

                        result = true;
                    }
                }
                else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD)
                {
                    if (diffY > 0)
                        onSwipeBottom();

                    else
                        onSwipeTop();

                    result = true;
                }
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
            }

            return result;
        }
    }

    private void onSwipeRight()
    {
        this.onSwipe.swipeRight();
    }

    private void onSwipeLeft()
    {
        this.onSwipe.swipeLeft();
    }

    private void onSwipeTop()
    {
        this.onSwipe.swipeTop();
    }

    private void onSwipeBottom()
    {
        this.onSwipe.swipeBottom();
    }

    interface onSwipeListener
    {
        void swipeRight();
        void swipeTop();
        void swipeBottom();
        void swipeLeft();
    }

    onSwipeListener onSwipe;
}
