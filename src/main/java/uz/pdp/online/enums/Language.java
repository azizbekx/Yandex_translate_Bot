package uz.pdp.online.enums;

public enum  Language {
    ENGLISH("en","\uD83C\uDDEC\uD83C\uDDE7"),
    RUSSIAN("ru","\uD83C\uDDF7\uD83C\uDDFA"),
    TURKISH("tr","\uD83C\uDDF9\uD83C\uDDF7");
    public final String code;
    public final String flagEmoji;

    Language(String code, String flagEmoji) {
        this.code = code;
        this.flagEmoji = flagEmoji;
    }

    public static Language findByCode(String code){
        for (Language value : values()) {
            if (value.code.equals(code)){
                return value;
            }
        }
        throw new IllegalArgumentException("Unknown language code"+code);
    }
}
