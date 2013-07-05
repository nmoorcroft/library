package com.zuhlke.library.helpers;

import static org.apache.commons.io.FileUtils.readFileToByteArray;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FileUtils;

public class ArtworkHelper {

    private static String dir = System.getProperty("java.io.tmp");
    
    public static String saveArtwork(byte[] artwork) throws IOException {
        String uuid = UUID.randomUUID().toString();
        FileUtils.writeByteArrayToFile(new File(dir, uuid), artwork);
        return uuid;
    }
    
    public static byte[] loadArtwork(String uuid) throws IOException {
        return readFileToByteArray(new File(dir, uuid));
    }
    
}
