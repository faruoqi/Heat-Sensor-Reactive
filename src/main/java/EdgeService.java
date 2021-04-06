import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class EdgeService {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle("sensors.HeatSensor",
                new DeploymentOptions().setConfig(new JsonObject().put("http.port",3000)));

        vertx.deployVerticle("sensors.HeatSensor",
                new DeploymentOptions().setConfig(new JsonObject().put("http.port",3001)));

        vertx.deployVerticle("sensors.HeatSensor",
                new DeploymentOptions().setConfig(new JsonObject().put("http.port",3002)));

        vertx.deployVerticle("services.SnapshotService");
        vertx.deployVerticle("services.CollectorService");

    }
}
