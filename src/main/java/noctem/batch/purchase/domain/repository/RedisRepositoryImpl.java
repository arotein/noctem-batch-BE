package noctem.batch.purchase.domain.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RedisRepositoryImpl implements RedisRepository {
    private final RedisTemplate<String, String> redisStringTemplate;
    private final String STATISTICS_STORE_KEY_PREFIX = "statistics:store";

    public void setMonthGraphData(Long storeId, String data) {
        String key = String.format("%s:%d:month", STATISTICS_STORE_KEY_PREFIX, storeId);
        redisStringTemplate.opsForValue().set(key, data);
    }

    public void setDayGraphData(Long storeId, String data) {
        String key = String.format("%s:%d:day", STATISTICS_STORE_KEY_PREFIX, storeId);
        redisStringTemplate.opsForValue().set(key, data);
    }

    public void setHourGraphData(Long storeId, String data) {
        String key = String.format("%s:%d:hour", STATISTICS_STORE_KEY_PREFIX, storeId);
        redisStringTemplate.opsForValue().set(key, data);
    }

    @Override
    public void setPopularMenuTop3ByStore(Long storeId, String data) {
        String key = String.format("%s:%d:popularMenuTop3", STATISTICS_STORE_KEY_PREFIX, storeId);
        redisStringTemplate.opsForValue().set(key, data);
    }

    @Override
    public void setPopularMenuTop5(String data) {
        String key = String.format("%s:popularMenuTop5", STATISTICS_STORE_KEY_PREFIX);
        redisStringTemplate.opsForValue().set(key, data);
    }

    @Override
    public void setRegularCustomerTop3ByStore(Long storeId, String data) {
        String key = String.format("%s:%d:regularCustomerTop3", STATISTICS_STORE_KEY_PREFIX, storeId);
        redisStringTemplate.opsForValue().set(key, data);
    }
}
