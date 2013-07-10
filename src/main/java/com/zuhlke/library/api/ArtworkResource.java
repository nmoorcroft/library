package com.zuhlke.library.api;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import com.yammer.dropwizard.jersey.caching.CacheControl;
import com.zuhlke.library.helpers.ArtworkHelper;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;

@Path("/artwork")
public class ArtworkResource {
    
    final Logger logger = LoggerFactory.getLogger(ArtworkResource.class);

    @GET @Path("/{filename}") @Produces("image/jpg")
    @CacheControl(immutable = true)
    public Response getArtwork(@PathParam("filename") String filename) {
        try {
            return Response.ok(ArtworkHelper.loadArtwork(filename)).build();
        } catch (IOException e) {
            logger.warn(e.getMessage());
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }
    
    @POST @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public String saveArtwork(@FormDataParam("files[]") InputStream in, @FormDataParam("files[]") FormDataContentDisposition file) throws Exception {
        return ArtworkHelper.saveArtwork(IOUtils.toByteArray(in));
    }

}

