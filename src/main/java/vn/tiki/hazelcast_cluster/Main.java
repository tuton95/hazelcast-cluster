package vn.tiki.hazelcast_cluster;

import com.hazelcast.config.Config;
import com.hazelcast.config.XmlConfigBuilder;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;


public class Main {
    public static void main(String[] args) {
        var main = new Main();
        main.run();
    }

    public void run() {
        try {
            var env = System.getenv("env_name");
            System.out.println("ENV_NAME: " + env);
            XmlConfigBuilder builder = null;
            if (env == null || env.equals("local")) {
                builder = new XmlConfigBuilder(resourcesPath("/thanos-hazelcast-local.xml"));
            } else if (env.equals("uat")) {
                builder = new XmlConfigBuilder(resourcesPath("/thanos-hazelcast-uat.xml"));
            } else {
                builder = new XmlConfigBuilder(resourcesPath("/thanos-hazelcast-production.xml"));
            }
            Config config = builder.build();
            HazelcastInstance instance = Hazelcast.newHazelcastInstance(config);
        } catch (FileNotFoundException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public String resourcesPath(String filename) throws URISyntaxException {
        var file = new File(this.getClass().getResource(filename).toURI());
        return file.getPath();
    }
}
