package noctem.batch.purchase.global.enumeration;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum MobileCarrier {
    SKT("SKT"),
    KT("KT"),
    LGUPLUS("LGU+");

    private final String value;

    MobileCarrier(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    private static final Map<String, MobileCarrier> VALUE_MAP = Stream.of(values()).collect(Collectors.toMap(MobileCarrier::getValue, e -> e));

    public static MobileCarrier findByValue(String value) {
        return VALUE_MAP.get(value);
    }
}
