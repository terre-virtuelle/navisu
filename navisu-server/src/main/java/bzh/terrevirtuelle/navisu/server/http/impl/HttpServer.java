/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.server.http.impl;

import org.vertx.java.core.Vertx;
import org.vertx.java.core.http.HttpServerRequest;

/**
 *
 * @author Serge
 */
public class HttpServer {

    public HttpServer(Vertx vertx, String hostname, int port) {
        vertx.createHttpServer().requestHandler((HttpServerRequest req) -> {
            req.response().headers().set("Content-Type", "text/plain");
            req.response().end("Hello NaVisu");
        }).listen(port);
    }
}
