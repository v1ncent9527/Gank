package com.v1ncent.io.gank.find.pojo;

import java.util.List;

/**
 * Created by v1ncent on 2017/5/23.
 */

public class RandomResult {

    /**
     * error : false
     * results : [{"_id":"5719a5e267765974fbfcf94e","createdAt":"2016-04-22T12:17:38.977Z","desc":"4.22","publishedAt":"2016-04-22T12:18:52.507Z","source":"chrome","type":"福利","url":"http://ww2.sinaimg.cn/large/610dc034gw1f35cxyferej20dw0i2789.jpg","used":true,"who":"代码家"},{"_id":"56cc6d1d421aa95caa707653","createdAt":"2015-05-22T09:52:22.418Z","desc":"Material design风格的EditText ，带图标和验证功能","publishedAt":"2015-05-25T03:37:08.537Z","type":"Android","url":"https://github.com/webpartners/WPEditText","used":true,"who":"Jason"},{"_id":"56cc6d23421aa95caa707aa1","createdAt":"2015-06-10T01:12:09.876Z","desc":"一个可以监测scrollable view滚动事件的的库，可以实现 Android 5.0 Lollipop中很多特殊效果。","publishedAt":"2015-06-10T04:12:07.803Z","type":"Android","url":"https://github.com/ksoichiro/Android-ObservableScrollView","used":true,"who":"大城小黄"},{"_id":"57027b12677659633ff71cd1","createdAt":"2016-04-04T22:32:50.803Z","desc":"Apple 网络服务加速配置","publishedAt":"2016-04-05T10:45:46.487Z","source":"chrome","type":"瞎推荐","url":"https://github.com/gongjianhui/AppleDNS","used":true,"who":"lxxself"},{"_id":"56cc6d22421aa95caa70790d","createdAt":"2015-10-10T03:16:11.784Z","desc":"markdown 的编辑器","publishedAt":"2015-10-10T03:58:17.774Z","type":"前端","url":"https://github.com/houfeng/mditor","used":true,"who":"Dear宅学长"}]
     */

    private boolean error;
    /**
     * _id : 5719a5e267765974fbfcf94e
     * createdAt : 2016-04-22T12:17:38.977Z
     * desc : 4.22
     * publishedAt : 2016-04-22T12:18:52.507Z
     * source : chrome
     * type : 福利
     * url : http://ww2.sinaimg.cn/large/610dc034gw1f35cxyferej20dw0i2789.jpg
     * used : true
     * who : 代码家
     */

    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;
        private List<String> images;

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }
    }
}
