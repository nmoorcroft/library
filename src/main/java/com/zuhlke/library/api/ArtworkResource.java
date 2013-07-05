package com.zuhlke.library.api;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import com.zuhlke.library.helpers.ArtworkHelper;

@Path("/artwork")
public class ArtworkResource {
    
    final Logger logger = LoggerFactory.getLogger(ArtworkResource.class);

    @GET @Path("/{filename}") @Produces("image/jpg")
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

