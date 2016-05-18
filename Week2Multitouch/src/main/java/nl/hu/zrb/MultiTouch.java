package nl.hu.zrb;

import android.app.Activity;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.FloatMath;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class MultiTouch extends Activity implements OnTouchListener {
	// Matrix instances to move and zoom image
	Matrix matrix = new Matrix();
	Matrix eventMatrix = new Matrix();

	// possible touch states
	final static int NONE = 0;
	final static int DRAG = 1;
	final static int ZOOM = 2;
	private PointF start = new PointF();
	private PointF mid = new PointF();
	int touchState = NONE;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ImageView view = (ImageView) findViewById(R.id.imageView);
		view.setOnTouchListener(this);
	}

	final static float MIN_DIST = 10f;
	float eventDistance = 1f;
	float eventX =0, eventY = 0;
	float d = 0f;
	float newRot = 0f;
	float[] lastEvent = null;
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		ImageView view = (ImageView) v;

		switch (event.getAction() & MotionEvent.ACTION_MASK) {
			case MotionEvent.ACTION_DOWN:
				//primary touch event starts: remember touch down location
				eventMatrix.set(matrix);
				start.set(event.getX(),event.getY());
				touchState = DRAG;
				lastEvent=null;
				break;

			case MotionEvent.ACTION_POINTER_DOWN:
				//secondary touch event starts: remember distance and center
				eventDistance = calcDistance(event);

				if (eventDistance > 10f) {
					eventMatrix.set(matrix);
					calcMidpoint(mid,event);
					touchState = ZOOM;
				}
				lastEvent = new float[4];
				lastEvent[0] = event.getX(0);
				lastEvent[1] = event.getX(1);
				lastEvent[2] = event.getX(0);
				lastEvent[3] = event.getX(1);
				d = rotation(event);
				break;
			case MotionEvent.ACTION_MOVE:
				if (touchState == DRAG) {
					//single finger drag, translate accordingly
					matrix.set(eventMatrix);
					float dx = event.getX() - start.x;
					float dy = event.getY() - start.y;
					matrix.postTranslate(dx,dy);

				} else if (touchState == ZOOM) {
					//multi-finger zoom, scale accordingly around center
					float dist = calcDistance(event);

					if (dist > 10f) {
						matrix.set(eventMatrix);
						float scale = dist / eventDistance;
						matrix.postScale(scale, scale, mid.x, mid.y);
					}
					if (lastEvent != null && event.getPointerCount() == 2) {
						newRot = rotation(event);
						float r = newRot - d;
						float[] values = new float[9];
						matrix.getValues(values);

						matrix.postRotate(r,mid.x,mid.y);
					}
				}
				break;

			case MotionEvent.ACTION_UP:
				eventMatrix.set(matrix);
				touchState = NONE;
				break;
			case MotionEvent.ACTION_POINTER_UP:
				eventMatrix.set(matrix);
				touchState = NONE;
				break;
		}
		view.setImageMatrix(matrix);
		return true;
	}

	private float calcDistance(MotionEvent event) {
		float x = event.getX(0) - event.getX(1);
		float y = event.getY(0) - event.getY(1);
		return FloatMath.sqrt(x * x + y * y);

	}

	private void calcMidpoint(PointF point,MotionEvent event) {
		float x = event.getX(0) + event.getX(1);
		float y = event.getY(0) + event.getY(1);
		point.set(x/2,y/2);
	}
	private float rotation(MotionEvent event){
		double delta_x = (event.getX(0) - event.getX(1));
		double delta_y = (event.getY(0) - event.getY(1));
		double radians = Math.atan2(delta_y,delta_x);
		return (float) Math.toDegrees(radians);
	}
}


