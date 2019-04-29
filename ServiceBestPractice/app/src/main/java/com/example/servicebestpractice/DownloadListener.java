package com.example.servicebestpractice;

public interface DownloadListener {
    /**
     * 用来通知当前的下载进度
     * @param progress
     */
    void onProgress(int progress);

    /**
     * 用来通知下载成功事件
     */
    void onSuccess();

    /**
     * 用来通知下载失败
     */
    void onFailed();

    /**
     * 用来通知下载暂停时事件
     */
    void onPaused();

    /**
     * 用来通知下载取消事件
     */
    void onCanceled();
}
