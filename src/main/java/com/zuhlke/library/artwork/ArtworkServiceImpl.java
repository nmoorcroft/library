package com.zuhlke.library.artwork;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static org.apache.commons.io.FileUtils.readFileToByteArray;

@Service
public class ArtworkServiceImpl implements ArtworkService {

    static final String imgStore = "tmp/content";

    public String saveArtwork(byte[] artwork) throws IOException {
        String uuid = UUID.randomUUID().toString();
        FileUtils.writeByteArrayToFile(new File(imgStore, uuid), artwork);
        return uuid;
    }
    
    public byte[] loadArtwork(String uuid) throws IOException {
        return readFileToByteArray(new File(imgStore, uuid));
    }
    
}

