package noctem.batch.purchase.global.enumeration;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum OrderStatus {
    NOT_CONFIRM("주문확인중"),
    MAKING("제조중"),
    COMPLETED("제조완료"),
    CANCELED("취소됨");

    private String value;

    OrderStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    private static final Map<String, OrderStatus> VALUE_MAP = Stream.of(values()).collect(Collectors.toMap(OrderStatus::getValue, e -> e));

    public static OrderStatus findByValue(String value) {
        return VALUE_MAP.get(value);
    }
}
