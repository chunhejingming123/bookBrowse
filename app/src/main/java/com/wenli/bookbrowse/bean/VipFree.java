package com.wenli.bookbrowse.bean;

import java.util.ArrayList;
import java.util.List;

public class VipFree {
    public String time;
    public String monmey;
    public String datatime;

    public VipFree(String time, String monmey) {
        this.time = time;
        this.monmey = monmey;
    }

    public static List<VipFree> getVipFreeList() {
        List<VipFree> mlist = new ArrayList<>();
        VipFree vip1 = new VipFree("续一年", "858元");
        vip1.datatime="一年";
        VipFree vip2 = new VipFree("续半年", "480元");
        vip2.datatime="半年";
        VipFree vip3 = new VipFree("续一季", "300元");
        vip3.datatime="一季";
        VipFree vip4 = new VipFree("续一个月", "108元");
        vip4.datatime="一个月";
        mlist.add(vip1);
        mlist.add(vip2);
        mlist.add(vip3);
        mlist.add(vip4);
        return mlist;

    }
}
