package com.wenli.bookbrowse.bean;

import com.wenli.bookbrowse.R;

import java.util.ArrayList;
import java.util.List;

public class Book {
    public int redIcon;
    public long id;
    public String bookname;
    public String boolAuthor;
    public String bookPubish;
    public String shortdes;

    public Book() {
    }

    public static List<Book> getBookList() {
        List<Book> mlist = new ArrayList<>();
        Book mBook1 = new Book();
        mBook1.redIcon = R.mipmap.book1;
        mBook1.bookname = "史记 青少版插图本";
        mBook1.boolAuthor = "司马迁";
        mBook1.bookPubish = "民主与建设出版社";
        mBook1.shortdes = "黄帝，姓公孙，名轩辕。黄帝从小就表……";
        mlist.add(mBook1);
        Book mBook2 = new Book();
        mBook2.redIcon = R.mipmap.book2;
        mBook2.bookname = "人生海海";
        mBook2.boolAuthor = "麦家";
        mBook2.bookPubish = "北京十月文艺出版社";
        mBook2.shortdes = "黄帝，姓公孙，名轩辕。黄帝从小就表现……";
        mlist.add(mBook2);
        Book mBook3 = new Book();
        mBook3.redIcon = R.mipmap.book3;
        mBook3.bookname = "活着（余华经典著作）";
        mBook3.boolAuthor = "余华";
        mBook3.bookPubish = "作家出版社";
        mBook3.shortdes = "《活着》是一部充满血泪的小说。……";
        mlist.add(mBook3);
        Book mBook5 = new Book();
        mBook5.redIcon = R.mipmap.book5;
        mBook5.bookname = "《正面管教》修订版";
        mBook5.boolAuthor = "[美] 简·尼尔森";
        mBook5.bookPubish = "北京联合出版公司";
        mBook5.shortdes = "正面管教是一种既不惩罚也不娇纵的……";
        mlist.add(mBook5);
        Book mBook6 = new Book();
        mBook6.redIcon = R.mipmap.book6;
        mBook6.bookname = "解密";
        mBook6.boolAuthor = "麦家";
        mBook6.bookPubish = "北京十月文艺出版社";
        mBook6.shortdes = "小黎黎是第二天晌午走进梨园的……";
        mlist.add(mBook6);

        Book mBook7 = new Book();
        mBook7.redIcon = R.mipmap.book1;
        mBook7.bookname = "史记 青少版插图本";
        mBook7.boolAuthor = "司马迁";
        mBook7.bookPubish = "民主与建设出版社";
        mBook7.shortdes = "黄帝，姓公孙，名轩辕。黄帝从小就表……";
        mlist.add(mBook7);
        Book mBook8 = new Book();
        mBook8.redIcon = R.mipmap.book2;
        mBook8.bookname = "人生海海";
        mBook8.boolAuthor = "麦家";
        mBook8.bookPubish = "北京十月文艺出版社";
        mBook8.shortdes = "黄帝，姓公孙，名轩辕。黄帝从小就表现……";
        mlist.add(mBook8);
        Book mBook9 = new Book();
        mBook9.redIcon = R.mipmap.book4;
        mBook9.bookname = "活着（余华经典著作）";
        mBook9.boolAuthor = "余华";
        mBook9.bookPubish = "作家出版社";
        mBook9.shortdes = "《活着》是一部充满血泪的小说。……";
        mlist.add(mBook9);
        Book mBook11 = new Book();
        mBook11.redIcon = R.mipmap.book5;
        mBook11.bookname = "《正面管教》修订版";
        mBook11.boolAuthor = "[美] 简·尼尔森";
        mBook11.bookPubish = "北京联合出版公司";
        mBook11.shortdes = "正面管教是一种既不惩罚也不娇纵的……";
        mlist.add(mBook11);
        Book mBook12 = new Book();
        mBook12.redIcon = R.mipmap.book6;
        mBook12.bookname = "解密";
        mBook12.boolAuthor = "麦家";
        mBook12.bookPubish = "北京十月文艺出版社";
        mBook12.shortdes = "小黎黎是第二天晌午走进梨园的……";
        mlist.add(mBook12);

        return mlist;
    }
}
