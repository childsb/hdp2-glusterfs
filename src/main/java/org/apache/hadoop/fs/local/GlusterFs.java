package org.apache.hadoop.fs.local;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;

public class GlusterFs extends LocalFsG{

    GlusterFs(Configuration conf) throws IOException, URISyntaxException{
        super(new GlusterVol(conf));
        
    }

  

}
