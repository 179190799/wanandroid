package com.chf.wanandroid.mvp.model.bean;

import java.util.List;

/**
 * 收藏bean
 *
 * @author 17919
 * @date 2018/4/9
 */

public class MyCollectionBean {

    /**
     * curPage : 1
     * datas : [{"author":"张明云","chapterId":296,"chapterName":"阅读","courseId":13,"desc":"","envelopePic":"","id":7553,"link":"https://www.jianshu.com/p/2f93483b8b39","niceDate":"刚刚","origin":"","originId":2798,"publishTime":1523286016000,"title":"如何衡量一个Android应用开发人员的能力？","userId":4492,"visible":0,"zan":0},{"author":"scsfwgy","chapterId":339,"chapterName":"K线图","courseId":13,"desc":"各种金融类的自定义View,基金走势图、分时图、蜡烛图、各种指标等，一步一步构建庞大的基金自定View... http://blog.csdn.net/wgyscsf\r\n","envelopePic":"http://www.wanandroid.com/blogimgs/3a9d2cbb-24d7-4c85-8497-9e1af6b64d23.png","id":7552,"link":"http://www.wanandroid.com/blog/show/2105","niceDate":"刚刚","origin":"","originId":2799,"publishTime":1523286015000,"title":"走势图、分时图、蜡烛图 FinancialCustomerView","userId":4492,"visible":0,"zan":0},{"author":"GcsSloop","chapterId":314,"chapterName":"RV列表动效","courseId":13,"desc":"具有分页功能的 Recyclerview 布局管理器，主打分页，可以替代部分场景下的网格布局，线性布局，以及一些简单的ViewPager，但也有一定的局限性，请选择性使用。\r\n\r\n","envelopePic":"http://www.wanandroid.com/blogimgs/d65890f3-0a09-4b9c-93b0-778caa31b2aa.gif","id":7551,"link":"http://www.wanandroid.com/blog/show/2106","niceDate":"刚刚","origin":"","originId":2800,"publishTime":1523286014000,"title":"Android 网格分页布局 pager-layoutmanager","userId":4492,"visible":0,"zan":0},{"author":"listenzz","chapterId":344,"chapterName":"Fragment","courseId":13,"desc":"方便管理Fragment、StatusBar 、Toolbar的库 AndroidNavigation","envelopePic":"http://www.wanandroid.com/blogimgs/01b8f237-4a80-4841-972c-de9a34d557c4.png","id":7550,"link":"http://www.wanandroid.com/blog/show/2107","niceDate":"刚刚","origin":"","originId":2801,"publishTime":1523286013000,"title":"方便管理Fragment、StatusBar 、Toolbar的库 AndroidNavigation","userId":4492,"visible":0,"zan":0},{"author":"xiaoyang","chapterId":249,"chapterName":"干货资源","courseId":13,"desc":"","envelopePic":"","id":7549,"link":"http://www.wanandroid.com/blog/show/2090","niceDate":"刚刚","origin":"","originId":2683,"publishTime":1523286012000,"title":"给大家整理了一些学习资料-[20180321更新]","userId":4492,"visible":0,"zan":0}]
     * offset : 0
     * over : true
     * pageCount : 1
     * size : 20
     * total : 5
     */

    private int curPage;
    private int offset;
    private boolean over;
    private int pageCount;
    private int size;
    private int total;
    private List<DatasBean> datas;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * author : 张明云
         * chapterId : 296
         * chapterName : 阅读
         * courseId : 13
         * desc :
         * envelopePic :
         * id : 7553
         * link : https://www.jianshu.com/p/2f93483b8b39
         * niceDate : 刚刚
         * origin :
         * originId : 2798
         * publishTime : 1523286016000
         * title : 如何衡量一个Android应用开发人员的能力？
         * userId : 4492
         * visible : 0
         * zan : 0
         */

        private String author;
        private int chapterId;
        private String chapterName;
        private int courseId;
        private String desc;
        private String envelopePic;
        private int id;
        private String link;
        private String niceDate;
        private String origin;
        private int originId;
        private long publishTime;
        private String title;
        private int userId;
        private int visible;
        private int zan;

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public int getChapterId() {
            return chapterId;
        }

        public void setChapterId(int chapterId) {
            this.chapterId = chapterId;
        }

        public String getChapterName() {
            return chapterName;
        }

        public void setChapterName(String chapterName) {
            this.chapterName = chapterName;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getEnvelopePic() {
            return envelopePic;
        }

        public void setEnvelopePic(String envelopePic) {
            this.envelopePic = envelopePic;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getNiceDate() {
            return niceDate;
        }

        public void setNiceDate(String niceDate) {
            this.niceDate = niceDate;
        }

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public int getOriginId() {
            return originId;
        }

        public void setOriginId(int originId) {
            this.originId = originId;
        }

        public long getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(long publishTime) {
            this.publishTime = publishTime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getVisible() {
            return visible;
        }

        public void setVisible(int visible) {
            this.visible = visible;
        }

        public int getZan() {
            return zan;
        }

        public void setZan(int zan) {
            this.zan = zan;
        }
    }
}
