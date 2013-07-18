package com.zuhlke.library.artwork;

import static org.apache.commons.io.FileUtils.deleteQuietly;
import static org.apache.commons.io.IOUtils.toByteArray;
import static org.junit.Assert.assertArrayEquals;
import static org.mockito.Mockito.when;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.zuhlke.library.LibraryConfiguration;

@RunWith(MockitoJUnitRunner.class)
public class ArtworkServiceTest {

    @Mock LibraryConfiguration mockConfiguration;
    @InjectMocks ArtworkService artworkService = new ArtworkServiceImpl();
    
    @Test
    public void shouldSaveAndLoadArtwork() throws Exception {
        when(mockConfiguration.getArtworkDirectory()).thenReturn("target");
        byte[] artwork = toByteArray(getClass().getResourceAsStream("/img/domain.jpg"));
        String uuid = artworkService.saveArtwork(artwork);
        byte[] loaded = artworkService.loadArtwork(uuid);
        
        assertArrayEquals(artwork, loaded);
        
        deleteQuietly(new File("target/"+uuid));
        
    }
}
