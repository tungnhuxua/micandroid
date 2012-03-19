package ningbo.media.util;

import java.io.File;
import java.util.Iterator;

import org.junit.Test;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifDirectory;
import com.drew.metadata.exif.GpsDirectory;

public class ImageExifTest {

	@Test
	public void testImage() throws JpegProcessingException{
		String path = "/Users/ning/upload/97.jpg" ;//photo.JPG
		 File jpegFile = new File(path);  
	     Metadata metadata = JpegMetadataReader.readMetadata(jpegFile);  
	     Directory exif = metadata.getDirectory(GpsDirectory.class);  
	     Iterator tags = exif.getTagIterator();  
	     while (tags.hasNext()) {  
	         Tag tag = (Tag)tags.next();  
	         System.out.println(tag);  
	     }  
	 }  
	
}
