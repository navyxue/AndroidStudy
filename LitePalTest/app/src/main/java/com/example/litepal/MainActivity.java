package com.example.litepal;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.litepal.LitePal;
import org.litepal.LitePalBase;
import org.litepal.LitePalDB;
import org.litepal.crud.DeleteHandler;
import org.litepal.crud.LitePalSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "d-MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button createButton = (Button) findViewById(R.id.create_database);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connector.getDatabase();
            }
        });
        Button addData = (Button) findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                /*book.setName("The Da Vinci Code");
                book.setAuthor("Dan Brown");
                book.setPages(454);
                book.setPrice(16.96);
                book.setPress("Unknow");*/
                book.setName("The Lost Symbol");
                book.setAuthor("Dan Brown");
                book.setPages(510);
                book.setPrice(19.95);
                book.setPress("Unknow");
                // 持久化
                book.save();
            }
        });

        Button updateData = (Button) findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setPrice(14.95);
                book.setPress("Anchor");
                book.updateAll("name = ? and author = ?", "The Lost Symbol", "Dan Brown");
                // 更新默认值
               /* book.setToDefault("pages");
                book.updateAll();*/
            }
        });
        Button deleteData = (Button) findViewById(R.id.delete_data);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.deleteAll(Book.class, "price < ?", "15");
            }
        });

        Button queryData = (Button) findViewById(R.id.query_data);
        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // LitePal.findAll查询所有数据
                List<Book> books = LitePal.findAll(Book.class);
                for (Book book : books) {
                    Log.d(TAG, "book name is " + book.getName());
                    Log.d(TAG, "book author is " + book.getAuthor());
                    Log.d(TAG, "book pages is " + book.getPages());
                    Log.d(TAG, "book price is " + book.getPrice());
                    Log.d(TAG, "book press is " + book.getPress());
                }
                // 查询第一条数据
                Book firstBook = LitePal.findFirst(Book.class);
                // 查询最后一条数据
                Book lastBook = LitePal.findLast(Book.class);
                // 查询指定列的数据
                List<Book> bookList = LitePal.select("name", "author").find(Book.class);
                // 查询指定约束条件
                bookList = LitePal.where("pages > ?", "400").find(Book.class);
                // 指定结果集的排序
                bookList = LitePal.order("price desc").find(Book.class);
                // 指定查询结果集的数量
                bookList = LitePal.limit(3).find(Book.class);
                // 指定查询结果的偏移量，如查询表中的第2条，3条，4条数据
                bookList = LitePal.limit(3).offset(1).find(Book.class);
                // 使用多个方法任意组合
                bookList = LitePal.select("name", "author").where("pages > ?", "400").order("price desc").find(Book.class);
                // LitePal支持原生的sql
                Cursor c = LitePal.findBySQL("select * from Book where pages > ? and price < ?", "400", "20");
            }
        });
    }
}
