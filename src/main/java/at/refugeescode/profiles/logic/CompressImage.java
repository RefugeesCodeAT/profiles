package at.refugeescode.profiles.logic;

//import org.springframework.stereotype.Service;
//
//import javax.imageio.*;
//import javax.imageio.stream.ImageOutputStream;
//import java.awt.image.RenderedImage;
//import java.io.File;
//import java.io.IOException;
//import java.nio.ByteOrder;
//
//@Service
//public class CompressImage {
//    public static byte[] compressJPeGImage(File orginalImage ,File compressedImage, float quality ){
//        byte[] imageSize=null;
//        try {
//            RenderedImage image = ImageIO.read(orginalImage);
//            ImageWriter jpegWriter = ImageIO.getImageWritersByFormatName("jpg").next();
//            ImageWriteParam imageWriteParam = jpegWriter.getDefaultWriteParam();
//            imageWriteParam.setCompressionMode(imageWriteParam.MODE_EXPLICIT);
//            imageWriteParam.setCompressionQuality(quality);
//            ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(compressedImage);
//            jpegWriter.setOutput(imageOutputStream);
//            IIOImage iioImage = new IIOImage(image, null, null);
//            while(imageOutputStream.readByte()>0){
//                imageSize = imageOutputStream.readByte();
//            }
//
//
//            jpegWriter.dispose();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//}
