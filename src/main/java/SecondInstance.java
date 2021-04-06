import data.SensorData;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sensors.HeatSensor;
import servers.HttpServer;
import utils.Listener;

public class SecondInstance {
    private static final Logger logger = LoggerFactory.getLogger(SecondInstance.class);

    public static void main(String[] args) {
        Vertx.clusteredVertx(new VertxOptions(),ar -> {
            if (ar.succeeded()){
                logger.info("Second instance has been started");
                Vertx vertx = ar.result();
                vertx.deployVerticle("sensors.HeatSensor",new DeploymentOptions().setInstances(4));
                vertx.deployVerticle("utils.Listener");
                vertx.deployVerticle("data.SensorData");
                JsonObject conf = new JsonObject().put("port",8081);
                vertx.deployVerticle(HttpServer.class.getName(),
                        new DeploymentOptions().setConfig(conf));
            }else {
                logger.error("Couldn't start",ar.cause());
            }
        });
    }

}
