package com.ebrightmoon.opengl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

/**
 * Time: 2021/9/21
 * Author:wyy
 * Description:
 */
public class AirHockeyGLSurfaceView extends GLSurfaceView {
    private final AirHockeyRenderer renderer;

    public AirHockeyGLSurfaceView(Context context) {
        super(context, null);
        setEGLContextClientVersion(2);
        renderer = new AirHockeyRenderer(context);
        setRenderer(renderer);
//        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    public void handleTouchPress(float normalizedX, float normalizedY) {
        queueEvent(new Runnable() {
            @Override
            public void run() {
                renderer.handleTouchPress(
                        normalizedX, normalizedY);
            }
        });

    }

    public void handleTouchDrag(float normalizedX, float normalizedY) {
        queueEvent(new Runnable() {
            @Override
            public void run() {
                renderer.handleTouchDrag(
                        normalizedX, normalizedY);
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Convert touch coordinates into normalized device
        // coordinates, keeping in mind that Android's Y
        // coordinates are inverted.
        final float normalizedX =
                (event.getX() / (float) getWidth()) * 2 - 1;
        final float normalizedY =
                -((event.getY() / (float) getHeight()) * 2 - 1);

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            queueEvent(new Runnable() {
                @Override
                public void run() {
                    renderer.handleTouchPress(
                            normalizedX, normalizedY);
                }
            });
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            queueEvent(new Runnable() {
                @Override
                public void run() {
                    renderer.handleTouchDrag(
                            normalizedX, normalizedY);
                }
            });
        }
        return true;

    }
}
