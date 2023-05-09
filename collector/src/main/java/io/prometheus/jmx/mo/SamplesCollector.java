//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package io.prometheus.jmx.mo;

import io.prometheus.client.Collector;
import io.prometheus.client.Collector.MetricFamilySamples;
import io.prometheus.client.Collector.Type;
import io.prometheus.client.Collector.MetricFamilySamples.Sample;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SamplesCollector extends Collector {
    public SamplesCollector() {
    }

    @Override
    public List collect() {
        List mfs = new ArrayList();
        List<Map> gaugeList = MediatorRegister.matrix();
        if (gaugeList.size() != 0) {
            Iterator var3 = gaugeList.iterator();

            while(var3.hasNext()) {
                Map map = (Map)var3.next();
                List sampleList = new ArrayList();
                String gaugeName = (String)map.get("gaugeName");
                sampleList.add(new Sample(gaugeName, Arrays.asList("ln"), (List)map.get("labelName"), (Double)map.get("value")));
                MetricFamilySamples samples = new MetricFamilySamples(gaugeName, Type.GAUGE, "help", sampleList);
                mfs.add(samples);
            }
        }

        return mfs;
    }
}
