package noctem.batch.batch.job;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import noctem.batch.purchase.domain.repository.RedisRepository;
import noctem.batch.purchase.dto.response.MonthGraphResDto;
import noctem.batch.purchase.service.StatisticsService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class StoreStatisticsJobConfig {
    private final JobBuilderFactory jobBuilderFactory; // Job 빌더 생성용
    private final StepBuilderFactory stepBuilderFactory; // Step 빌더 생성용
    private final StatisticsService statisticsService;
    private final RedisRepository redisRepository;

    @Bean
    public Job storeStatisticsJob() {
        return jobBuilderFactory.get("storeGraphJob") // job 이름 지정
                .start(monthGraphStep()).on("*") // firstStep의 결과와 상관없이 다음 step(to메서드)실행
                .to(dayGraphStep()).on("*")
                .to(hourGraphStep()).on("*")
                .to(popularMenuTop3ByStoreStep()).on("*")
                .to(popularMenuTop5Step()).on("*")
                .to(regularCustomerTop3ByStoreStep())
                .end()
                .build();
    }

    @Bean
    public Step monthGraphStep() {
        return stepBuilderFactory.get("monthGraph")
                .tasklet((contribution, chunkContext) -> {
                    Long storeId = 1L;
                    MonthGraphResDto monthGraph = statisticsService.getMonthGraph(storeId);
                    redisRepository.setMonthGraphData(
                            storeId,
                            objectMapper().writeValueAsString(monthGraph));
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step dayGraphStep() {
        return stepBuilderFactory.get("dayGraph")
                .tasklet((a, b) -> {
                    Long storeId = 1L;
                    redisRepository.setDayGraphData(
                            storeId,
                            objectMapper().writeValueAsString(statisticsService.getDayGraph(storeId)));
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step hourGraphStep() {
        return stepBuilderFactory.get("hourGraph")
                .tasklet((a, b) -> {
                    Long storeId = 1L;
                    redisRepository.setHourGraphData(
                            storeId,
                            objectMapper().writeValueAsString(statisticsService.getHourGraph(storeId)));
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step popularMenuTop3ByStoreStep() {
        return stepBuilderFactory.get("popularMenuTop3ByStore")
                .tasklet((a, b) -> {
                    Long storeId = 1L;
                    redisRepository.setPopularMenuTop3ByStore(
                            storeId,
                            objectMapper().writeValueAsString(statisticsService.getPopularMenuTop3ByStore(storeId)));
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step popularMenuTop5Step() {
        return stepBuilderFactory.get("popularMenuTop5")
                .tasklet((a, b) -> {
                    redisRepository.setPopularMenuTop5(
                            objectMapper().writeValueAsString(statisticsService.getPopularMenuTop5()));
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step regularCustomerTop3ByStoreStep() {
        return stepBuilderFactory.get("regularCustomerTop3ByStore")
                .tasklet((a, b) -> {
                    Long storeId = 1L;
                    redisRepository.setRegularCustomerTop3ByStore(
                            storeId,
                            objectMapper().writeValueAsString(statisticsService.getRegularCustomerTop3ByStore(storeId)));
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
