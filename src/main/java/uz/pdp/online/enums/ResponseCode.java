package uz.pdp.online.enums;

public enum ResponseCode {
    OK(200, "Operation completed successfully."),
    KEY_INVALID(401, "Invalid API key."),
    KEY_BLOCKED(402, "This API key has been blocked."),
    DAILY_REQ_LIMIT_EXCEEDED(403, "Exceeded the daily limit on the number of requests."),
    TEXT_TOO_LONG(413, "The text size exceeds the maximum."),
    LANG_NOT_SUPPORTED(501, "The specified translation direction is not supported.");

    public final int code ;
    public final String description;

    ResponseCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static ResponseCode findByCode(int code){
        for (ResponseCode value : values()) {
            if (value.code==code){
                return value;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "ResponseCode{" +
                "code=" + code +
                ", description='" + description + '\'' +
                '}';
    }
}
