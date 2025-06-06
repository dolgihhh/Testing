package models;

import lombok.Getter;
import utils.ConfigReader;

@Getter
public enum SortValue {
    PRICE_LOW_TO_HIGH("lohi"),
    PRICE_HIGH_TO_LOW("hilo"),
    NAME_A_TO_Z("az"),
    NAME_Z_TO_A("za");

    private final String value;

    SortValue(String value) {
        this.value = value;
    }

    public static SortValue getSortValueFromConfig() {
        String value = ConfigReader.get("sort_value");
        try {
            return SortValue.valueOf(value);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(value + " ENUM name doesn't exist in ENUM SortValue");
        }
    }
}
