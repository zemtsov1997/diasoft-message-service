package ru.diasoft.service.api.utils;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.regex.Pattern;

public class ImageUtils {

    private final static Logger logger = LogManager.getLogger(ImageUtils.class);

    public static byte[] scaleToSize(byte[] img, int width, int height) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(img);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            Thumbnails.of(inputStream).size(width, height).toOutputStream(outputStream);
        } catch (IOException e) {
            logger.debug("Error parse image...", e);
        }
        return outputStream.toByteArray();
    }

    public static byte[] convertBase64ToImage(String base64Text) {
        try {
            if (ImageUtils.isValidImageBase64(base64Text)) {
                String[] parts = base64Text.split(",");
                return Base64.getDecoder().decode(parts[1]);
            } else {
                logger.error("Data base64 not valid...");
                return null;
            }
        } catch (Exception ex) {
            logger.error("Error from decode image-base64", ex);
            return null;
        }
    }

    public static Boolean isValidImageBase64(String text) {
        Pattern pattern = Pattern.compile("^(data:image/(.*);)(.*)base64(.*)(,)(.*)$", Pattern.DOTALL);
        return text != null && !text.isEmpty() && text.length() > 0 && pattern.matcher(text).matches();
    }

}
