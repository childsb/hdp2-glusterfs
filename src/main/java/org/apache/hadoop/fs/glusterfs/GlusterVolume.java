package org.apache.hadoop.fs.glusterfs;

import java.io.File;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RawLocalFileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GlusterVolume extends RawLocalFileSystem{

    protected static final Logger log = LoggerFactory.getLogger(GlusterFileSystem.class);
    protected String glusterMount=null;

    public GlusterVolume(){}
    
    public GlusterVolume(Configuration conf){
        this();
        this.setConf(conf);
    }
    
    public void setConf(Configuration conf){
        boolean ret=false;
        String volName=null;
        String remoteGFSServer=null;
        boolean autoMount=true;

        log.info("Initializing Gluster Volume..");
        log.info("Gluster Config: " + conf);
        super.setConf(conf);
        
        if(conf!=null){
            try{
                volName=conf.get("fs.glusterfs.volname", null);
                glusterMount=conf.get("fs.glusterfs.mount", null);
                remoteGFSServer=conf.get("fs.glusterfs.server", null);
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }else{
            /* use a temp directory until the file system is really configured */
            glusterMount = System.getProperty("java.io.tmpdir");
        }
        log.info("Working Directory:" + glusterMount);
        
        setWorkingDirectory(new Path(glusterMount));
        
    }
    public File pathToFile(Path path) {
        String workingDirectory =  getWorkingDirectory().toUri().getRawPath();
        String pathString = path.toUri().getRawPath();
     
        if (!pathString.startsWith(workingDirectory)) {
            if(pathString.startsWith(Path.SEPARATOR)){
                pathString = pathString.substring(1);
            }
            
            if(pathString.length()==0){
              /* case where path comes in as only / */
               path = new Path(workingDirectory);
            }else{
               path = new Path(workingDirectory, pathString);  
            }
        }
        return new File(path.toUri().getPath());
    }
    

    public long getBlockSize(Path path) throws IOException{
        long blkSz;
        File f=pathToFile(path);

        blkSz=GlusterFSXattr.getBlockSize(f.getPath());
        if(blkSz==0)
            blkSz=getLength(path);

        return blkSz;
    }
   
    public BlockLocation[] getFileBlockLocations(FileStatus file,long start,long len) throws IOException{
        File f=pathToFile(file.getPath());
        BlockLocation[] result=null;

        result=GlusterFSXattr.getPathInfo(f.getPath(), start, len);
        if(result==null){
            log.info("Problem getting destination host for file "+f.getPath());
            return null;
        }

        return result;
    }

}
