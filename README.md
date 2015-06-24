# TimeTable
自定义课表View
自己闲下来时间写的一个课表控件使用的自定义LinearLayout 里面View都是用代码实现的 最终效果如下图 写的可能有问题希望多多指点
![界面](http://img.blog.csdn.net/20150624095824175)
 创建一个自定义LinearLayout 控件用来装载课程的信息和课程的周数 和节数大概的布局三这样的
![这里写图片描述](http://img.blog.csdn.net/20150624100000615)
根据上面的看来觉得总体布局我分了两个 上面的星期是一个 下面的节数和格子是一个  总体使用Vertical  而单独内部者使用了Horizontal布局  中间使用了两种布局线条 是这样的
```
 /**
     * 横的分界线
     *
     * @return
     */
    private View getWeekTransverseLine() {
        TextView mWeekline = new TextView(getContext());
        mWeekline.setBackgroundColor(getResources().getColor(R.color.view_line));
        mWeekline.setHeight(TimeTableLineHeight);
        mWeekline.setWidth(LayoutParams.FILL_PARENT);
        return mWeekline;
    }

    /**
     * 竖向分界线
     *
     * @return
     */
    private View getWeekVerticalLine() {
        TextView mWeekline = new TextView(getContext());
        mWeekline.setBackgroundColor(getResources().getColor(R.color.view_line));
        mWeekline.setHeight(dip2px(TimeTableWeekNameHeight));
        mWeekline.setWidth((TimeTableLineHeight));
        return mWeekline;
    }

```


下面就看其它的View

 那就从上到下开始先看星期的布局
 

```
  private void initView() {

        mHorizontalWeekLayout = new LinearLayout(getContext());
        mHorizontalWeekLayout.setOrientation(HORIZONTAL);

        mVerticalWeekLaout = new LinearLayout(getContext());
        mVerticalWeekLaout.setOrientation(HORIZONTAL);
        //表格
        for (int i = 0; i <= WEEKNUM; i++) {
            switch (i) {
                case 0:
                    //课表出的0,0格子 空白的
                    TextView mTime = new TextView(getContext());
                    mTime.setHeight(dip2px(TimeTableWeekNameHeight));
                    mTime.setWidth((dip2px(TimeTableNumWidth)));
                    mHorizontalWeekLayout.addView(mTime);

                    //绘制1~MAXNUM
                    LinearLayout mMonday = new LinearLayout(getContext());
                    ViewGroup.LayoutParams mm = new ViewGroup.LayoutParams(dip2px(TimeTableNumWidth), dip2px(MAXNUM * TimeTableHeight) + MAXNUM * 2);
                    mMonday.setLayoutParams(mm);
                    mMonday.setOrientation(VERTICAL);
                    for (int j = 1; j <= MAXNUM; j++) {
                        TextView mNum = new TextView(getContext());
                        mNum.setGravity(Gravity.CENTER);
                        mNum.setTextColor(getResources().getColor(R.color.text_color));
                        mNum.setHeight(dip2px(TimeTableHeight));
                        mNum.setWidth(dip2px(TimeTableNumWidth));
                        mNum.setTextSize(14);
                        mNum.setText(j + "");
                        mMonday.addView(mNum);
                        mMonday.addView(getWeekTransverseLine());
                    }
                    mVerticalWeekLaout.addView(mMonday);
                    break;
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    // 设置显示星期一 到星期天
                    LinearLayout mHoriView = new LinearLayout(getContext());
                    mHoriView.setOrientation(VERTICAL);
                    TextView mWeekName = new TextView(getContext());
                    mWeekName.setTextColor(getResources().getColor(R.color.text_color));
                    mWeekName.setWidth(((getViewWidth() - dip2px(TimeTableNumWidth))) / WEEKNUM);
                    mWeekName.setHeight(dip2px(TimeTableWeekNameHeight));
                    mWeekName.setGravity(Gravity.CENTER);
                    mWeekName.setTextSize(16);
                    mWeekName.setText(weekname[i - 1]);
                    mHoriView.addView(mWeekName);
                    mHorizontalWeekLayout.addView(mHoriView);

                    List<TimeTableModel> mListMon = new ArrayList<>();
                    //遍历出星期1~7的课表
                    for (TimeTableModel timeTableModel : mListTimeTable) {
                        if (timeTableModel.getWeek() == i) {
                            mListMon.add(timeTableModel);
                        }
                    }
                    //添加
                    LinearLayout mLayout = getTimeTableView(mListMon, i);
                    mLayout.setOrientation(VERTICAL);
                    ViewGroup.LayoutParams linearParams = new ViewGroup.LayoutParams((getViewWidth() - dip2px(20)) / WEEKNUM, LayoutParams.FILL_PARENT);
                    mLayout.setLayoutParams(linearParams);
                    mLayout.setWeightSum(1);
                    mVerticalWeekLaout.addView(mLayout);
                    break;

                default:
                    break;
            }
            TextView l = new TextView(getContext());
            l.setHeight(dip2px(TimeTableHeight * MAXNUM) + MAXNUM * 2);
            l.setWidth(2);
            l.setBackgroundColor(getResources().getColor(R.color.view_line));
            mVerticalWeekLaout.addView(l);
            mHorizontalWeekLayout.addView(getWeekVerticalLine());
        }
        addView(mHorizontalWeekLayout);
        addView(getWeekTransverseLine());
        addView(mVerticalWeekLaout);
        addView(getWeekTransverseLine());
    }

```



博客地址：http://blog.csdn.net/shallcheek/article/details/44303197



