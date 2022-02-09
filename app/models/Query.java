package models;

import play.libs.Files.TemporaryFile;
import play.mvc.Http.MultipartFormData.FilePart;
public class Query {
    public String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
