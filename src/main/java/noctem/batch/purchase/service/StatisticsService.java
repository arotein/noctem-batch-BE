package noctem.batch.purchase.service;

import noctem.batch.purchase.dto.response.*;

import java.util.List;

public interface StatisticsService {
    List<PreferredCategoryResDto> getPreferredCategoryByUser(Long userAccountId);

    List<PopularMenuResDto> getPopularMenuTop3ByStore(Long storeId);

    List<PopularMenuResDto> getPopularMenuTop5();

    List<RegularCustomerResDto> getRegularCustomerTop3ByStore(Long storeId);

    MonthGraphResDto getMonthGraph(Long storeId);

    DayGraphResDto getDayGraph(Long storeId);

    HourGraphResDto getHourGraph(Long storeId);
}
