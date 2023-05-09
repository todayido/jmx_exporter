//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package io.prometheus.jmx.mo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MyThreadPoll extends Mediator {
    public double count = 0.0D;
    public double size = 0.0D;
    public List list = new ArrayList();
    public Map map = new HashMap();
    public int a = 10;

    public MyThreadPoll() {
        (new Thread(() -> {
            try {
                while(true) {
                    TimeUnit.SECONDS.sleep(5L);
                    this.count = Math.random() * 10.0D;
                    this.size = Math.random() * 10.0D;
                    this.a = Math.toIntExact((long)(Math.random() * 10.0D));
                    if (this.size > this.count) {
                        this.list.clear();
                        this.map.put(0, this.size);
                    } else {
                        this.list.add(this.count);
                        this.list.add(this.size);
                        this.map.clear();
                    }

                    System.out.println("当前数据为：" + this.count);
                }
            } catch (InterruptedException var2) {
                var2.printStackTrace();
            }
        })).start();
        super.Register("my_thread_pool_size_MyThreadPoll", this, "count", "MyThreadPoll_count");
        super.Register("my_thread_pool_size_MyThreadPoll", this, "size", "MyThreadPoll_size");
        super.Register("my_thread_pool_size_MyThreadPoll", this, "list", "MyThreadPoll_list_size");
        super.Register("my_thread_pool_size_MyThreadPoll", this, "map", "MyThreadPoll_map_size");
        super.Register("my_thread_pool_size_MyThreadPoll", this, "a", "MyThreadPoll_int");
    }
}
