package com.v1ncent.io.gank.daily.pojo;

import java.util.List;

/**
 * Created by v1ncent on 2017/4/21.
 */

public class DayDateResult {

    /**
     * category : ["Android","福利","休息视频","拓展资源","前端","瞎推荐","iOS"]
     * error : false
     * results : {"Android":[{"_id":"58f1a04a421aa9544825f879","createdAt":"2017-04-15T12:23:38.405Z","desc":"CNode社区非官方客户端","images":["http://img.gank.io/8d023609-2037-4964-9571-bbeec2c9beeb"],"publishedAt":"2017-04-17T11:32:14.674Z","source":"web","type":"Android","url":"https://github.com/shellljx/CNode-android","used":true,"who":"li jinxiang"},{"_id":"58f42881421aa9544ed8895b","createdAt":"2017-04-17T10:29:21.119Z","desc":"Ramotion 出品：Android 页面展开效果","images":["http://img.gank.io/39d8b142-1ee1-477f-812c-ff1509994929"],"publishedAt":"2017-04-17T11:32:14.674Z","source":"chrome","type":"Android","url":"https://github.com/Ramotion/expanding-collection-android","used":true,"who":"带马甲"},{"_id":"58f429a5421aa9544825f88a","createdAt":"2017-04-17T10:34:13.180Z","desc":"Android Tag Chip 效果，做的非常细腻，完全的 Material Design。","images":["http://img.gank.io/6e1eaf48-4277-46dc-b97b-f05a5261223b"],"publishedAt":"2017-04-17T11:32:14.674Z","source":"chrome","type":"Android","url":"https://github.com/pchmn/MaterialChipsInput","used":true,"who":"代码家"}],"iOS":[{"_id":"58f435c2421aa9544825f88b","createdAt":"2017-04-17T11:25:54.744Z","desc":"iOS 树形结构组件","images":["http://img.gank.io/28c60998-21ce-4b09-98e4-18ae663a1ea6"],"publishedAt":"2017-04-17T11:32:14.674Z","source":"chrome","type":"iOS","url":"https://github.com/partho-maple/PBTreeView","used":true,"who":"alex"}],"休息视频":[{"_id":"58f41d38421aa9544ed88959","createdAt":"2017-04-17T09:41:12.315Z","desc":"李达康早期作品，我不信我最后一个知道","publishedAt":"2017-04-17T11:32:14.674Z","source":"chrome","type":"休息视频","url":"http://www.kanbilibili.com/video/av9818563","used":true,"who":"lxxself"}],"前端":[{"_id":"58f43024421aa9544ed88960","createdAt":"2017-04-17T11:01:56.136Z","desc":"支持各种自定义行为的前端编辑器","images":["http://img.gank.io/04bd3fae-9628-4aa2-bc0f-a20a22c25a38"],"publishedAt":"2017-04-17T11:32:14.674Z","source":"chrome","type":"前端","url":"https://github.com/ianstormtaylor/slate","used":true,"who":"ak"}],"拓展资源":[{"_id":"58f427de421aa9544825f886","createdAt":"2017-04-17T10:26:38.397Z","desc":"专为程序员准备的笔记工具","publishedAt":"2017-04-17T11:32:14.674Z","source":"chrome","type":"拓展资源","url":"https://boostnote.io/#download","used":true,"who":"代码家"},{"_id":"58f428c4421aa9544ed8895e","createdAt":"2017-04-17T10:30:28.395Z","desc":"TensorFlow 优质教程合集","images":["http://img.gank.io/ba3b99cf-18e8-47e1-a71c-2b34d262896e"],"publishedAt":"2017-04-17T11:32:14.674Z","source":"chrome","type":"拓展资源","url":"https://github.com/wagamamaz/tensorflow-tutorial","used":true,"who":"带马甲"}],"瞎推荐":[{"_id":"58f430cc421aa9544ed88962","createdAt":"2017-04-17T11:04:44.517Z","desc":"所有跟区块链相关的论文集合。","publishedAt":"2017-04-17T11:32:14.674Z","source":"chrome","type":"瞎推荐","url":"https://github.com/decrypto-org/blockchain-papers","used":true,"who":"代码家"}],"福利":[{"_id":"58f3980c421aa9544b773ff1","createdAt":"2017-04-17T00:13:00.136Z","desc":"4-17","publishedAt":"2017-04-17T11:32:14.674Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-04-16-17934400_1738549946443321_2924146161843437568_n.jpg","used":true,"who":"daimajia"}]}
     */

    private boolean error;
    private ResultsBean results;
    private List<String> category;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public ResultsBean getResults() {
        return results;
    }

    public void setResults(ResultsBean results) {
        this.results = results;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public static class ResultsBean {
        /**
         * _id : 58f1a04a421aa9544825f879
         * createdAt : 2017-04-15T12:23:38.405Z
         * desc : CNode社区非官方客户端
         * images : ["http://img.gank.io/8d023609-2037-4964-9571-bbeec2c9beeb"]
         * publishedAt : 2017-04-17T11:32:14.674Z
         * source : web
         * type : Android
         * url : https://github.com/shellljx/CNode-android
         * used : true
         * who : li jinxiang
         */

        private List<AndroidBean> Android;
        /**
         * _id : 58f435c2421aa9544825f88b
         * createdAt : 2017-04-17T11:25:54.744Z
         * desc : iOS 树形结构组件
         * images : ["http://img.gank.io/28c60998-21ce-4b09-98e4-18ae663a1ea6"]
         * publishedAt : 2017-04-17T11:32:14.674Z
         * source : chrome
         * type : iOS
         * url : https://github.com/partho-maple/PBTreeView
         * used : true
         * who : alex
         */

        private List<IOSBean> iOS;
        /**
         * _id : 58f41d38421aa9544ed88959
         * createdAt : 2017-04-17T09:41:12.315Z
         * desc : 李达康早期作品，我不信我最后一个知道
         * publishedAt : 2017-04-17T11:32:14.674Z
         * source : chrome
         * type : 休息视频
         * url : http://www.kanbilibili.com/video/av9818563
         * used : true
         * who : lxxself
         */

        private List<休息视频Bean> 休息视频;
        /**
         * _id : 58f43024421aa9544ed88960
         * createdAt : 2017-04-17T11:01:56.136Z
         * desc : 支持各种自定义行为的前端编辑器
         * images : ["http://img.gank.io/04bd3fae-9628-4aa2-bc0f-a20a22c25a38"]
         * publishedAt : 2017-04-17T11:32:14.674Z
         * source : chrome
         * type : 前端
         * url : https://github.com/ianstormtaylor/slate
         * used : true
         * who : ak
         */

        private List<前端Bean> 前端;
        /**
         * _id : 58f427de421aa9544825f886
         * createdAt : 2017-04-17T10:26:38.397Z
         * desc : 专为程序员准备的笔记工具
         * publishedAt : 2017-04-17T11:32:14.674Z
         * source : chrome
         * type : 拓展资源
         * url : https://boostnote.io/#download
         * used : true
         * who : 代码家
         */

        private List<拓展资源Bean> 拓展资源;
        /**
         * _id : 58f430cc421aa9544ed88962
         * createdAt : 2017-04-17T11:04:44.517Z
         * desc : 所有跟区块链相关的论文集合。
         * publishedAt : 2017-04-17T11:32:14.674Z
         * source : chrome
         * type : 瞎推荐
         * url : https://github.com/decrypto-org/blockchain-papers
         * used : true
         * who : 代码家
         */

        private List<瞎推荐Bean> 瞎推荐;
        /**
         * _id : 58f3980c421aa9544b773ff1
         * createdAt : 2017-04-17T00:13:00.136Z
         * desc : 4-17
         * publishedAt : 2017-04-17T11:32:14.674Z
         * source : chrome
         * type : 福利
         * url : http://7xi8d6.com1.z0.glb.clouddn.com/2017-04-16-17934400_1738549946443321_2924146161843437568_n.jpg
         * used : true
         * who : daimajia
         */

        private List<福利Bean> 福利;

        public List<AndroidBean> getAndroid() {
            return Android;
        }

        public void setAndroid(List<AndroidBean> Android) {
            this.Android = Android;
        }

        public List<IOSBean> getIOS() {
            return iOS;
        }

        public void setIOS(List<IOSBean> iOS) {
            this.iOS = iOS;
        }

        public List<休息视频Bean> get休息视频() {
            return 休息视频;
        }

        public void set休息视频(List<休息视频Bean> 休息视频) {
            this.休息视频 = 休息视频;
        }

        public List<前端Bean> get前端() {
            return 前端;
        }

        public void set前端(List<前端Bean> 前端) {
            this.前端 = 前端;
        }

        public List<拓展资源Bean> get拓展资源() {
            return 拓展资源;
        }

        public void set拓展资源(List<拓展资源Bean> 拓展资源) {
            this.拓展资源 = 拓展资源;
        }

        public List<瞎推荐Bean> get瞎推荐() {
            return 瞎推荐;
        }

        public void set瞎推荐(List<瞎推荐Bean> 瞎推荐) {
            this.瞎推荐 = 瞎推荐;
        }

        public List<福利Bean> get福利() {
            return 福利;
        }

        public void set福利(List<福利Bean> 福利) {
            this.福利 = 福利;
        }

        public static class AndroidBean {
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

            public List<String> getImages() {
                return images;
            }

            public void setImages(List<String> images) {
                this.images = images;
            }
        }

        public static class IOSBean {
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

            public List<String> getImages() {
                return images;
            }

            public void setImages(List<String> images) {
                this.images = images;
            }
        }

        public static class 休息视频Bean {
            private String _id;
            private String createdAt;
            private String desc;
            private String publishedAt;
            private String source;
            private String type;
            private String url;
            private boolean used;
            private String who;

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

        public static class 前端Bean {
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

            public List<String> getImages() {
                return images;
            }

            public void setImages(List<String> images) {
                this.images = images;
            }
        }

        public static class 拓展资源Bean {
            private String _id;
            private String createdAt;
            private String desc;
            private String publishedAt;
            private String source;
            private String type;
            private String url;
            private boolean used;
            private String who;

            public List<String> getImages() {
                return images;
            }

            public void setImages(List<String> images) {
                this.images = images;
            }

            private List<String> images;

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

        public static class 瞎推荐Bean {
            private String _id;
            private String createdAt;
            private String desc;
            private String publishedAt;
            private String source;
            private String type;
            private String url;
            private boolean used;
            private String who;

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

        public static class 福利Bean {
            private String _id;
            private String createdAt;
            private String desc;
            private String publishedAt;
            private String source;
            private String type;
            private String url;
            private boolean used;
            private String who;

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

    @Override
    public String toString() {
        return "DayDateResult{" +
                "category=" + category +
                ", results=" + results +
                ", error=" + error +
                '}';
    }
}
