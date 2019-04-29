package com.example.contacts;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

/**
 * 自定义内容提供器
 */
public class MyProvider extends ContentProvider {
    public static final int TABLE1_DIR = 0;
    public static final int TABLE1_ITEM = 1;
    public static final int TABLE2_DIR = 2;
    public static final int TABLE2_ITEM = 3;
    private static UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.example.app.provider", "table1", TABLE1_DIR);
        uriMatcher.addURI("com.example.app.provider", "table1/#", TABLE1_ITEM);
        uriMatcher.addURI("com.example.app.provider", "table2", TABLE2_DIR);
        uriMatcher.addURI("com.example.app.provider", "table2/#", TABLE2_ITEM);
    }
    /**
     * 注意：只有当存在ContentResolver尝试访问我们程序中的数据时，内容提供器才会被初始化
     * 初始化内容提供器的时候调用，通常在这里完成对数据库的创建和升级等操作
     * @return 返回ture表示初始化成功，返回false表示失败
     */
    @Override
    public boolean onCreate() {
        return false;
    }

    /**
     * 从内容提供器中查询数据
     * @param uri 指定查询哪张表
     * @param projection 指定查询哪些列
     * @param selection 查询条件
     * @param selectionArgs 查询条件参数
     * @param sortOrder 排序字段
     * @return 返回结果对象Cursor
     */
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        switch (uriMatcher.match(uri)) {
            case TABLE1_DIR:
                // 查询table1表中的所有数据
                break;
            case TABLE1_ITEM:
                // 查询table1表中的单条数据
                break;
            case TABLE2_DIR:
                // 查询table2表中的所有数据
                break;
            case TABLE2_ITEM:
                // 查询table2表中的单条数据
                break;
                default:
                    break;
        }
        return null;
    }

    /**
     * 根据传入的内容URI来返回相应的MIME类型
     * @param uri 要解析的URI
     * @return
     */
    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case TABLE1_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.app.provider.table1";
            case TABLE1_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.app.provider.table1";
            case TABLE2_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.app.provider.table2";
            case TABLE2_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.app.provider.table2";
                default:
                    break;
        }
        return null;
    }

    /**
     * 插入一条数据
     * @param uri 指定插入到哪张表
     * @param values 要插入的数据
     * @return 返回表示这条记录的URI
     */
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    /**
     * 删除数据
     * @param uri 指定删除哪一张表
     * @param selection 删除的条件
     * @param selectionArgs 删除条件的参数
     * @return 返回受影响的行数
     */
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    /**
     * 更新内数据
     * @param uri 指定要更新的哪张表
     * @param values 要更新的数据
     * @param selection 更新条件
     * @param selectionArgs 更新条件的参数
     * @return 返回受影响的行数
     */
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
