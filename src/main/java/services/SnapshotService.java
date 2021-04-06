package services;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SnapshotService extends AbstractVerticle {
    private final Logger logger = LoggerFactory.getLogger(SnapshotService.class);

    @Override
    public void start(){
        vertx.createHttpServer().requestHandler(req -> {
            if (badRequest(req)){
                req.response().setStatusCode(400).end();
            }

            req.bodyHandler(buffer -> {
                logger.info("Latest temperatures: {}",buffer.toJsonObject().encodePrettily());
                req.response().end();
            });
        }).listen(config().getInteger("http.port",4000));
    }


    private boolean badRequest(HttpServerRequest request){
        return !request.method().equals(HttpMethod.POST)
                || !"application/json".equals(request.getHeader("Content-Type"));
    }
}