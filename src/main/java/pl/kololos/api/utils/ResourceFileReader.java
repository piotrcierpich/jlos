package pl.kololos.api.utils;

import pl.kololos.api.controllers.IndexController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ResourceFileReader {
    public static String readFileContent(String resourceFileName) {
        try {
            return tryReadFileContent(resourceFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String tryReadFileContent(String resourceFileName) throws IOException {
        try(InputStream inputStream = IndexController.class.getResourceAsStream(resourceFileName)) {
            try(ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = inputStream.read(buffer)) != -1) {
                    byteArrayOutputStream.write(buffer, 0, length);
                }
                return byteArrayOutputStream.toString(StandardCharsets.UTF_8.toString());
            }
        }
    }
}
