package noctem.batch.purchase.domain.repository;

public interface RedisRepository {
    void setMonthGraphData(Long storeId, String data);

    void setDayGraphData(Long storeId, String data);

    void setHourGraphData(Long storeId, String data);

    void setPopularMenuTop3ByStore(Long storeId, String data);

    void setPopularMenuTop5(String data);

    void setRegularCustomerTop3ByStore(Long storeId, String data);
}
