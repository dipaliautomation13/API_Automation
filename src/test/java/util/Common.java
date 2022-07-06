package util;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import java.io.File;
import java.io.IOException;

public class Common {

    public DocumentContext documentcontext;

    public DocumentContext updateJson(String jsonFilePath) throws IOException {

        File file = new File(System.getProperty("user.dir") + jsonFilePath);
        documentcontext = JsonPath.parse(file);
        return documentcontext;
    }
}
