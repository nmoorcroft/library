package com.zuhlke.library.helpers;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static org.apache.commons.io.FileUtils.readFileToByteArray;

public class ArtworkHelper {

    static final String imgStore = "tmp";

    public static String saveArtwork(byte[] artwork) throws IOException {
        String uuid = UUID.randomUUID().toString();
        FileUtils.writeByteArrayToFile(new File(imgStore, uuid), artwork);
        return uuid;
    }
    
    public static byte[] loadArtwork(String uuid) throws IOException {
        return readFileToByteArray(new File(imgStore, uuid));
    }
    
}
