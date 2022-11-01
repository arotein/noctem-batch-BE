package noctem.batch.purchase.global.enumeration;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Sex {
    MALE("남자"),
    FEMALE("여자");

    private final String value;

    Sex(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    private static final Map<String, Sex> VALUE_MAP = Stream.of(values()).collect(Collectors.toMap(Sex::getValue, e -> e));

    public static Sex findByValue(String value) {
        return VALUE_MAP.get(value);
    }
}
