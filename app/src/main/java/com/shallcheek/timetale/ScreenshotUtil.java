package com.shallcheek.timetale;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Environment;
import android.util.Log;
import android.widget.ScrollView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 截图操作
 * Created by Shall on 2015-07-22.
 */
public class ScreenshotUtil {

    private final static String FILE_SAVEPATH = Environment
            .getExternalStorageDirectory().getAbsolutePath() ;
    public static String pathfile = FILE_SAVEPATH + "/ScreenshotUtil.png";
    public static int h = 0;

    /**
     * 因为课表是可以滑动 的所以截取
     * 截取scrollview的屏幕
     **/
    public static void getBitmapByView(Context mContext, final ScrollView scrollView) {
        // 获取listView实际高度
        h = 0;
        for (int i = 0; i < scrollView.getChildCount(); i++) {
            h += scrollView.getChildAt(i).getHeight();
            scrollView.getChildAt(i).setBackgroundResource(android.R.color.white);
        }
        // 创建对应大小的bitmap
        Bitmap bitmap = Bitmap.createBitmap(scrollView.getWidth(), h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        scrollView.draw(canvas);

        Bitmap head = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.share_term_table_header);
        Bitmap foot = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.share_term_table_footer);
        Bitmap v = toConformBitmap(head, bitmap, foot);

        File savedir = new File(FILE_SAVEPATH);
        if (!savedir.exists()) {
            savedir.mkdirs();
        }

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(pathfile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(mContext,"保存失败",Toast.LENGTH_SHORT).show();
        }
        try {
            if (null != out) {
                v.compress(Bitmap.CompressFormat.PNG, 100, out);
                out.flush();
                out.close();
            }
            Toast.makeText(mContext,"保存成功",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(mContext,"保存失败",Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * 合并图片
     *
     * @param head
     * @param kebiao
     * @param san
     * @return
     */
    public static Bitmap toConformBitmap(Bitmap head, Bitmap kebiao, Bitmap san) {
        if (head == null) {
            return null;
        }
        int headWidth = head.getWidth();
        int kebianwidth = kebiao.getWidth();
        int fotwid = san.getWidth();

        int headHeight = head.getHeight();
        int kebiaoheight = kebiao.getHeight();
        int footerheight = san.getHeight();
        //生成三个图片合并大小的Bitmap
        Bitmap newbmp = Bitmap.createBitmap(kebianwidth, headHeight + kebiaoheight + footerheight, Bitmap.Config.ARGB_8888);
        Canvas cv = new Canvas(newbmp);
        cv.drawBitmap(head, 0, 0, null);// 在 0，0坐标开始画入headBitmap

        //因为手机不同图片的大小的可能小了 就绘制白色的界面填充剩下的界面
        if (headWidth < kebianwidth) {
            System.out.println("绘制头");
            Bitmap ne = Bitmap.createBitmap(kebianwidth - headWidth, headHeight, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(ne);
            canvas.drawColor(Color.WHITE);
            cv.drawBitmap(ne, headWidth, 0, null);
        }
        cv.drawBitmap(kebiao, 0, headHeight, null);// 在 0，headHeight坐标开始填充课表的Bitmap
        cv.drawBitmap(san, 0, headHeight + kebiaoheight, null);// 在 0，headHeight + kebiaoheight坐标开始填充课表的Bitmap
        //因为手机不同图片的大小的可能小了 就绘制白色的界面填充剩下的界面
        if (fotwid < kebianwidth) {
            System.out.println("绘制");
            Bitmap ne = Bitmap.createBitmap(kebianwidth - fotwid, footerheight, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(ne);
            canvas.drawColor(Color.WHITE);
            cv.drawBitmap(ne, fotwid, headHeight + kebiaoheight, null);
        }
        cv.save(Canvas.ALL_SAVE_FLAG);// 保存
        cv.restore();// 存储
        //回收
        head.recycle();
        kebiao.recycle();
        san.recycle();
        return newbmp;
    }


}
