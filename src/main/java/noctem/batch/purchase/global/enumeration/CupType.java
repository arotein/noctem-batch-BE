package noctem.batch.purchase.global.enumeration;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum CupType {
    STORE_CUP("매장컵"),
    PERSONAL_CUP("개인컵"),
    DISPOSABLE_CUP("일회용컵");

    private final String value;

    CupType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    private static final Map<String, CupType> VALUE_MAP = Stream.of(values()).collect(Collectors.toMap(CupType::getValue, e -> e));

    public static CupType findByValue(String value) {
        return VALUE_MAP.get(value);
    }
}
