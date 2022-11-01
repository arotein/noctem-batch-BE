package noctem.batch.purchase.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import noctem.batch.purchase.dto.response.*;
import noctem.batch.purchase.dto.vo.PreferredCategoryVo;
import noctem.batch.purchase.dto.vo.PurchaseStatisticsDayBaseVo;
import noctem.batch.purchase.dto.vo.PurchaseStatisticsHourBaseVo;
import noctem.batch.purchase.dto.vo.PurchaseStatisticsMonthBaseVo;
import noctem.batch.purchase.domain.repository.StatisticsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {
    private final StatisticsRepository statisticsRepository;

    @Override
    public List<PreferredCategoryResDto> getPreferredCategoryByUser(Long userAccountId) {
        Map<String, PreferredCategoryResDto> dtoMap = new HashMap<>();
        dtoMap.put("caffeine", new PreferredCategoryResDto("카페인"));
        dtoMap.put("decaffeine", new PreferredCategoryResDto("디카페인"));
        dtoMap.put("smoothie", new PreferredCategoryResDto("스무디"));
        dtoMap.put("ade", new PreferredCategoryResDto("에이드"));
        dtoMap.put("tea", new PreferredCategoryResDto("티"));

        List<PreferredCategoryVo> voList = statisticsRepository.getPreferredCategoryByUser(userAccountId);
        voList.forEach(e -> {
            switch (e.getCategorySmall()) {
                case REFRESHER:
                case FIZZIO:
                    dtoMap.get("ade").plusCount(e.getCount());
                    break;
                case COLD_BREW:
                case BLONDE:
                case ESPRESSO:
                    dtoMap.get("caffeine").plusCount(e.getCount());
                    break;
                case DECAFFEINE:
                    dtoMap.get("decaffeine").plusCount(e.getCount());
                    break;
                case PRAPPUCCINO:
                case BLENDED:
                    dtoMap.get("smoothie").plusCount(e.getCount());
                    break;
                case TEAVANA:
                    dtoMap.get("tea").plusCount(e.getCount());
                    break;
                default:
                    break;
            }
        });
        return dtoMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public List<PopularMenuResDto> getPopularMenuTop3ByStore(Long storeId) {
        List<PopularMenuResDto> dtoList = statisticsRepository.findPopularMenuTop3ByStore(storeId);
        dtoList.forEach(e -> e.setRank(dtoList.indexOf(e) + 1));
        return dtoList;
    }

    @Override
    public List<PopularMenuResDto> getPopularMenuTop5() {
        List<PopularMenuResDto> dtoList = statisticsRepository.findPopularMenuTop5();
        dtoList.forEach(e -> e.setRank(dtoList.indexOf(e) + 1));
        return dtoList;
    }

    @Override
    public List<RegularCustomerResDto> getRegularCustomerTop3ByStore(Long storeId) {
        List<RegularCustomerResDto> dtoList = statisticsRepository.findRegularCustomerTop3ByStore(storeId);
        dtoList.forEach(e -> e.setRank(dtoList.indexOf(e) + 1));
        return dtoList;
    }

    @Override
    public MonthGraphResDto getMonthGraph(Long storeId) {
        LocalDateTime now = LocalDateTime.now();
        PurchaseStatisticsMonthBaseVo recent = statisticsRepository.findPurchaseStatisticsForMonth(
                storeId, now);

        PurchaseStatisticsMonthBaseVo old = statisticsRepository.findPurchaseStatisticsForMonth(
                storeId, now.minusYears(1));
        return new MonthGraphResDto()
                .inputDate(now, now.minusYears(1))
                .inputData(recent, old);
    }


    @Override
    public DayGraphResDto getDayGraph(Long storeId) {
        LocalDateTime now = LocalDateTime.now();
        PurchaseStatisticsDayBaseVo recent = statisticsRepository.findPurchaseStatisticsForDay(
                storeId, now);

        PurchaseStatisticsDayBaseVo old = statisticsRepository.findPurchaseStatisticsForDay(
                storeId, now.minusWeeks(1));
        return new DayGraphResDto()
                .inputDate(now, now.minusWeeks(1))
                .inputData(recent, old);
    }

    @Override
    public HourGraphResDto getHourGraph(Long storeId) {
        LocalDateTime now = LocalDateTime.now();
        PurchaseStatisticsHourBaseVo recent = statisticsRepository.findPurchaseStatisticsForHour(
                storeId, now);

        PurchaseStatisticsHourBaseVo old = statisticsRepository.findPurchaseStatisticsForHour(
                storeId, now.minusDays(1));
        return new HourGraphResDto()
                .inputDate(now, now.minusDays(1))
                .inputData(recent, old);
    }
}
