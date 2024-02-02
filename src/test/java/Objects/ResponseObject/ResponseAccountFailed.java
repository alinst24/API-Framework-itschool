package Objects.ResponseObject;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResponseAccountFailed {

    @JsonProperty("code")
    private String code;

    @JsonProperty("message")
    private String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
