package com.zuhlke.library.artwork;

import java.io.IOException;

public interface ArtworkService {

    String saveArtwork(byte[] artwork) throws IOException;
    byte[] loadArtwork(String uuid) throws IOException;
    
}
