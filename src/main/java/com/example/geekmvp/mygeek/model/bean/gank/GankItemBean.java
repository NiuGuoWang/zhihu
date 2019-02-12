package com.example.geekmvp.mygeek.model.bean.gank;

import java.util.List;

public class GankItemBean {

    /**
     * error : false
     * results : [{"_id":"5ba206ec9d2122610aba3440","createdAt":"2018-09-19T08:21:00.295Z","desc":"2018-09-19","publishedAt":"2018-09-19T00:00:00.0Z","source":"web","type":"福利","url":"https://ws1.sinaimg.cn/large/0065oQSqly1fvexaq313uj30qo0wldr4.jpg","used":true,"who":"lijinshanmx"}]
     */

    private boolean error;
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
        /**
         * _id : 5ba206ec9d2122610aba3440
         * createdAt : 2018-09-19T08:21:00.295Z
         * desc : 2018-09-19
         * publishedAt : 2018-09-19T00:00:00.0Z
         * source : web
         * type : 福利
         * url : https://ws1.sinaimg.cn/large/0065oQSqly1fvexaq313uj30qo0wldr4.jpg
         * used : true
         * who : lijinshanmx
         */

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
