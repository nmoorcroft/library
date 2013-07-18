package com.zuhlke.library.artwork;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import com.zuhlke.library.LibraryConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.inject.Inject;

import static org.apache.commons.io.FileUtils.readFileToByteArray;

@Service
public class ArtworkServiceImpl implements ArtworkService {

    @Inject
    private LibraryConfiguration configuration;

    public String saveArtwork(byte[] artwork) throws IOException {
        String imgStore = configuration.getArtworkDirectory();
        String uuid = UUID.randomUUID().toString();
        FileUtils.writeByteArrayToFile(new File(imgStore, uuid), artwork);
        return uuid;
    }
    
    public byte[] loadArtwork(String uuid) throws IOException {
        String imgStore = configuration.getArtworkDirectory();
        return readFileToByteArray(new File(imgStore, uuid));
    }
    
}

