import data.SensorData;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import sensors.HeatSensor;
import servers.HttpServer;
import utils.Listener;

public class Main {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(HeatSensor.class.getName(),new DeploymentOptions().setInstances(4));
        vertx.deployVerticle(Listener.class.getName());
        vertx.deployVerticle(SensorData.class.getName());
        vertx.deployVerticle(HttpServer.class.getName());

    }
}
