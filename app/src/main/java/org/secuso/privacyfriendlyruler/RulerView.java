package org.secuso.privacyfriendlyruler;

import android.content.Context;
import android.graphics.Canvas;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.View;

import static org.secuso.privacyfriendlyruler.R.color.darkblue;

/**
 * Created by roberts on 02.06.16.
 */
public class RulerView extends View {

    double ydpi;
    double ydpmm;
    double heightPx;
    double heightmm;
    double heightin;
    double widthPx;
    float lineWidth;
    int textSize;
    int db;

    public RulerView(Context context, Activity activity) {
        super(context);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

//        heightPx = this.getMeasuredHeight();
//        widthPx = this.getMeasuredWidth();
        ydpi = displayMetrics.ydpi;
        ydpmm = ydpi / 25.4;
        heightPx = displayMetrics.heightPixels;
        heightmm = heightPx/ydpmm;
        heightin = ydpi;
        widthPx = displayMetrics.widthPixels;
        db = ContextCompat.getColor(context, R.color.darkblue);
        textSize = (int)(ydpmm *2.5);
        lineWidth = (float)(ydpmm*0.15);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(db);
        paint.setAlpha(255);
        paint.setTextSize(textSize);
        paint.setStrokeWidth(lineWidth);

        drawLeftCm(canvas, paint);
        drawRightCm(canvas, paint);

//        drawLeftIn(canvas, paint);
//        drawRightIn(canvas, paint);
    }

    private void drawLeftCm(Canvas canvas, Paint paint){
        for (int i = 0; i < heightmm; i++){
            if (i%10 == 0) {
                //draw 8mm line every cm
                canvas.drawLine(0, (float)ydpmm*i, (float)ydpmm*8, (float)ydpmm*i, paint);
                //draw a number every cm
                canvas.drawText(""+i/10, (float)ydpmm*8+(textSize/5), (float)(ydpmm*i+textSize), paint);
            } else if (i%5 == 0) {
                //draw 5mm line every 5mm
                canvas.drawLine(0, (float)ydpmm*i, (float)ydpmm*5, (float)ydpmm*i, paint);
            } else {
                //draw 3mm line every mm
                canvas.drawLine(0, (float)ydpmm*i, (float)ydpmm*3, (float)ydpmm*i, paint);
            }
        }
    }

    private void drawLeftIn(Canvas canvas, Paint paint){
        for (int i = 0; i < (heightin*32); i++){
            if (i%32 == 0) {
                //draw 8mm line every inch
                canvas.drawLine(0, (float)ydpmm*i, (float)(ydpmm*8), (float)ydpmm*i, paint);
                //draw a number every inch
                canvas.drawText(""+i/32, (float)ydpmm*8+(textSize/5), (float)(ydpmm*i+textSize), paint);
            } else if (i%16 == 0) {
                //draw 6mm line every 1/2 inch
                canvas.drawLine(0, (float)ydpmm*i, (float)(ydpmm*6), (float)ydpmm*i, paint);
            } else if (i%8 == 0) {
                //draw 4mm line every 1/4 inch
                canvas.drawLine(0, (float)ydpmm*i, (float)(ydpmm*4), (float)ydpmm*i, paint);
            } else if (i%4 == 0) {
                //draw 3mm line every 1/8 inch
                canvas.drawLine(0, (float)ydpmm*i, (float)(ydpmm*3), (float)ydpmm*i, paint);
            } else if (i%2 == 0) {
                //draw 2mm line every 1/16 inch
                canvas.drawLine(0, (float)ydpmm*i, (float)(ydpmm*2), (float)ydpmm*i, paint);
            } else {
                //draw 1.5mm line every 1/32 inch
                canvas.drawLine(0, (float)ydpmm*i, (float)(ydpmm*1.5), (float)ydpmm*i, paint);
            }
        }
    }

    private void drawRightCm(Canvas canvas, Paint paint){
        Path path = new Path();

        for (int i = 0; i < heightmm; i++){
            if (i%10 == 0) {
                //draw 8mm line every cm
                canvas.drawLine((float)(widthPx-ydpmm*(8+5)), (float)(heightPx-ydpmm*(i+18)),
                        (float)(widthPx-ydpmm*5), (float)(heightPx-ydpmm*(i+18)), paint);
                //draw a number every cm
                path.reset();
                path.moveTo((float)(widthPx-ydpmm*(8+5)-textSize/5), (float)(heightPx-ydpmm*(i+16)-textSize));
                path.lineTo((float)(widthPx-ydpmm*(8+5)-textSize/5), (float)(heightPx-ydpmm*(i+16)-2*textSize));
                canvas.drawTextOnPath(""+i/10, path, 0, 0, paint);
            } else if (i%5 == 0) {
                //draw 5mm line every 5mm
                canvas.drawLine((float)(widthPx-ydpmm*(5+5)), (float)(heightPx-ydpmm*(i+18)),
                        (float)(widthPx-ydpmm*5), (float)(heightPx-ydpmm*(i+18)), paint);
            } else {
                //draw 3mm line every mm
                canvas.drawLine((float)(widthPx-ydpmm*(3+5)), (float)(heightPx-ydpmm*(i+18)),
                        (float)(widthPx-ydpmm*5), (float)(heightPx-ydpmm*(i+18)), paint);
            }
        }
    }

    private void drawRightIn(Canvas canvas, Paint paint){
        Path path = new Path();

        for (int i = 0; i < (heightin*32); i++){
            if (i%32 == 0) {
                //draw 8mm line every inch
                canvas.drawLine((float)(widthPx-ydpmm*(8+5)), (float)(heightPx-ydpmm*(i+18)),
                        (float)(widthPx-ydpmm*5), (float)(heightPx-ydpmm*(i+18)), paint);
                //draw a number every inch
                path.reset();
                path.moveTo((float)(widthPx-ydpmm*(8+5)-textSize/5), (float)(heightPx-ydpmm*(i+16)-textSize));
                path.lineTo((float)(widthPx-ydpmm*(8+5)-textSize/5), (float)(heightPx-ydpmm*(i+16)-2*textSize));
                canvas.drawTextOnPath(""+i/32, path, 0, 0, paint);
            } else if (i%16 == 0) {
                //draw 6mm line every 1/2 inch
                canvas.drawLine((float)(widthPx-ydpmm*(6+5)), (float)(heightPx-ydpmm*(i+18)),
                        (float)(widthPx-ydpmm*5), (float)(heightPx-ydpmm*(i+18)), paint);
            } else if (i%8 == 0) {
                //draw 4mm line every 1/4 inch
                canvas.drawLine((float)(widthPx-ydpmm*(4+5)), (float)(heightPx-ydpmm*(i+18)),
                        (float)(widthPx-ydpmm*5), (float)(heightPx-ydpmm*(i+18)), paint);
            } else if (i%4 == 0) {
                //draw 3mm line every 1/8 inch
                canvas.drawLine((float)(widthPx-ydpmm*(3+5)), (float)(heightPx-ydpmm*(i+18)),
                        (float)(widthPx-ydpmm*5), (float)(heightPx-ydpmm*(i+18)), paint);
            } else if (i%2 == 0) {
                //draw 2mm line every 1/16 inch
                canvas.drawLine((float)(widthPx-ydpmm*(2+5)), (float)(heightPx-ydpmm*(i+18)),
                        (float)(widthPx-ydpmm*5), (float)(heightPx-ydpmm*(i+18)), paint);
            } else {
                //draw 1.5mm line every 1/32 inch
                canvas.drawLine((float)(widthPx-ydpmm*(1.5+5)), (float)(heightPx-ydpmm*(i+18)),
                        (float)(widthPx-ydpmm*5), (float)(heightPx-ydpmm*(i+18)), paint);
            }
        }
    }
}