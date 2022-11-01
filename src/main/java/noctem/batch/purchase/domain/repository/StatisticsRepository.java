package noctem.batch.purchase.domain.repository;

import noctem.batch.purchase.dto.response.PopularMenuResDto;
import noctem.batch.purchase.dto.response.RegularCustomerResDto;
import noctem.batch.purchase.dto.vo.PreferredCategoryVo;
import noctem.batch.purchase.dto.vo.PurchaseStatisticsDayBaseVo;
import noctem.batch.purchase.dto.vo.PurchaseStatisticsHourBaseVo;
import noctem.batch.purchase.dto.vo.PurchaseStatisticsMonthBaseVo;
import noctem.batch.purchase.domain.entity.Purchase;

import java.time.LocalDateTime;
import java.util.List;

public interface StatisticsRepository {

    Purchase test();

    List<PreferredCategoryVo> getPreferredCategoryByUser(Long userAccountId);

    List<PopularMenuResDto> findPopularMenuTop3ByStore(Long storeId);

    List<PopularMenuResDto> findPopularMenuTop5();

    List<RegularCustomerResDto> findRegularCustomerTop3ByStore(Long storeId);

    PurchaseStatisticsMonthBaseVo findPurchaseStatisticsForMonth(Long storeId, LocalDateTime baseDttm);

    PurchaseStatisticsDayBaseVo findPurchaseStatisticsForDay(Long storeId, LocalDateTime baseDttm);

    PurchaseStatisticsHourBaseVo findPurchaseStatisticsForHour(Long storeId, LocalDateTime baseDttm);
}
