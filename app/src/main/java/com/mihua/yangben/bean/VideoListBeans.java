package com.mihua.yangben.bean;

import java.util.List;

/**
 * Created by 李浩 on 2019/4/24.
 */

public class VideoListBeans {

    /**
     * msg : success
     * code : 0
     * videoList : [[{"videoId":10,"url":"/upload/video/1555572830797.mp4","name":"SDF"},{"videoId":11,"url":"/upload/video/1556004813681.mp4","name":"试试"},{"videoId":12,"url":"/upload/video/1556071908029.mp4","name":"强人锁龙"},{"videoId":13,"url":"/upload/video/1556087227435.mp4","name":"测试"}]]
     */

    private String msg;
    private int code;
    private List<List<VideoListBean>> videoList;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<List<VideoListBean>> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<List<VideoListBean>> videoList) {
        this.videoList = videoList;
    }

    public static class VideoListBean {
        /**
         * videoId : 10
         * url : /upload/video/1555572830797.mp4
         * name : SDF
         */

        private int videoId;
        private String url;
        private String name;

        public int getVideoId() {
            return videoId;
        }

        public void setVideoId(int videoId) {
            this.videoId = videoId;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
