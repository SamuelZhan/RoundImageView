package com.samuelzhan.roundimageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class RoundImageView extends ImageView {

	private Paint paint=new Paint();;
	
	public RoundImageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public RoundImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public RoundImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		
		//��û��ͼƬ�򷵻�
		if(getDrawable()==null){
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
			return;
		}
	
		int widthMode=MeasureSpec.getMode(widthMeasureSpec);
		int width=MeasureSpec.getSize(widthMeasureSpec);
		//����Ӧwrap_content,����ȡͼƬ�Ŀ�ȣ�����ȡviewgroup��������ֵ
		if(widthMode==MeasureSpec.AT_MOST){
			//ȡͼƬ��viewgroupk�������view�����ֵ֮�е���Сֵ
			width=Math.min(getDrawable().getIntrinsicWidth(), width);
		}
		
		//ͬ��ȴ���
		int heightMode=MeasureSpec.getMode(heightMeasureSpec);
		int height=MeasureSpec.getSize(heightMeasureSpec);
		if(heightMode==MeasureSpec.AT_MOST){
			height=Math.min(getDrawable().getIntrinsicHeight(), height);
		}
		
		//���ȡ��Ⱥ͸߶����ߵ���С��ʹview�Ŀ�����
		setMeasuredDimension(Math.min(width, height), Math.min(width, height));
		
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		
		Drawable drawable=getDrawable();		
		if(drawable==null) return;
		
		//����ͼƬ��ʹ�����ȣ�����Ӧview�Ĵ�С
		drawable.setBounds(0, 0, getWidth(), getHeight());				
		
		//����ͼ�㣬��ͼ��ѹ��ջ��canvas�ĸ��ֻ�ͼ�������ڸ�ͼ��ʵ��
		int layer=canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
		
		//�Ƚ�ͼƬ����ͼ��
		drawable.draw(canvas);
		
		//����һ��bitmap,����bitmap�ϻ���һ��Բ�Σ���ʵ��ֹԲ�Σ�����������滭����ͼ�ζ����ԣ�������ԭʼͼƬ�н����Ϳ���
		Bitmap bitmap=Bitmap.createBitmap(getWidth(), getHeight(), Config.ARGB_8888);		
		Canvas bitmapCanvas=new Canvas(bitmap); 		
		bitmapCanvas.drawCircle(getWidth()/2, getHeight()/2, getWidth()/2, paint);
		
		//����xfermode,��ģʽ��ȡ���λ�ͼ�Ľ������֣���Բ�β��֣�����ʾ��һ�λ�ͼ�����ݣ���ԭʼͼƬ
		paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
		
		//������Բ�ε�bitmap������ȥ��ʵ�����൱�����
		canvas.drawBitmap(bitmap, 0, 0, paint);
		
		//��ͼ�㵯��ջ������ͼ��������ύ�����յ�canvas��
		canvas.restoreToCount(layer);		
		
	}

}
