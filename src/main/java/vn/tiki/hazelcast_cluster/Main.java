package vn.tiki.hazelcast_cluster;

import com.hazelcast.config.Config;
import com.hazelcast.config.XmlConfigBuilder;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;


public class Main {
    public static void main(String[] args) {
        Config config = new XmlConfigBuilder().build();
        HazelcastInstance instance = Hazelcast.newHazelcastInstance(config);
    }
}
