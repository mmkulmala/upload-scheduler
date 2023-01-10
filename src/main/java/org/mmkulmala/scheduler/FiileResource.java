package org.mmkulmala.scheduler;

import io.smallrye.mutiny.Uni;
import io.vertx.core.file.OpenOptions;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.core.file.AsyncFile;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;

@Path("/file") class FileResource {

    @Inject
    Vertx vertx;

    @GET
    @Path("/{fileName}")
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<Response> streamDataFromFile1(@PathParam("fileName") String fileName) {
        final OpenOptions openOptions = (new OpenOptions()).setCreate(false).setWrite(false);

        Uni<AsyncFile> uni1 = vertx.fileSystem()
                .open("src/main/resources/out_files/" + fileName, openOptions);

        return uni1.onItem()
                .transform(asyncFile -> Response.ok(asyncFile)
                        .header("Content-Disposition", "attachment;filename=\"" + fileName + "\"")
                        .build());
    }
}
