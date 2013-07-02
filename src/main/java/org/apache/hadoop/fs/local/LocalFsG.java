package org.apache.hadoop.fs.local;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.AbstractFileSystem;
import org.apache.hadoop.fs.ChecksumFs;

public class LocalFsG  extends ChecksumFs{

    public LocalFsG(AbstractFileSystem fs) throws IOException, URISyntaxException{
        super(fs);
    }

    
    /* ----------------- everything from here is c + p from LocalFs ---------------- */
    
    /**
     * This constructor has the signature needed by
     * {@link AbstractFileSystem#createFileSystem(URI, Configuration)}.
     * 
     * @param theUri which must be that of localFs
     * @param conf
     * @throws IOException
     * @throws URISyntaxException 
     */
    LocalFsG(final URI theUri, final Configuration conf) throws IOException,
        URISyntaxException {
      this(conf);
    }
    LocalFsG(final Configuration conf) throws IOException, URISyntaxException {
        super(new RawLocalFs(conf));
      }

}
