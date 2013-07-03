/**
 *
 * Copyright (c) 2011 Red Hat, Inc. <http://www.redhat.com>
 * This file is part of GlusterFS.
 *
 * Licensed under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License.
 *
 */

/**
 * Implements the Hadoop FileSystem Interface to allow applications to store
 * files on GlusterFS and run Map/Reduce jobs on the data.
 * 
 * 
 */
package org.apache.hadoop.fs.glusterfs;

import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.LocalFileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GlusterFileSystem extends LocalFileSystem{
    
    protected static final Logger log = LoggerFactory.getLogger(GlusterFileSystem.class);
    protected String glusterMount=null;
    public static final URI BASE_URI = URI.create("file:");
    
    public GlusterFileSystem(){
        super(new GlusterDebugFileSystem());
    }
    
    public void setConf(Configuration conf){

        log.info("Initializing GlusterFS");
        log.info("Gluster Config: " + conf);
        super.setConf(conf);
        
      
    }

}
