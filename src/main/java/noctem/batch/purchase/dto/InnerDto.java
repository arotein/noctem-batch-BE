package noctem.batch.purchase.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class InnerDto {
    @Data
    @NoArgsConstructor
    public static class HourInnerDto {
        private String x; // 시간
        private Long y = 0L; // 매출량

        public HourInnerDto addHour(LocalDateTime dateTime) {
            int hour = dateTime.getHour();
            if (hour >= 10) {
                this.x = String.format("%d:00", hour);
            } else {
                this.x = String.format("0%d:00", hour);
            }
            return this;
        }

        public HourInnerDto addSales(long amount) {
            this.y += amount;
            return this;
        }
    }

    @Data
    @NoArgsConstructor
    public static class DayInnerDto {
        private String x; // 요일
        private Long y = 0L; // 판매량

        public DayInnerDto addDay(LocalDateTime dateTime) {
            switch (dateTime.getDayOfWeek().getValue()) {
                case 1:
                    x = "월";
                    break;
                case 2:
                    x = "화";
                    break;
                case 3:
                    x = "수";
                    break;
                case 4:
                    x = "목";
                    break;
                case 5:
                    x = "금";
                    break;
                case 6:
                    x = "토";
                    break;
                default:
                    x = "일";
            }
            return this;
        }

        public DayInnerDto addSales(long amount) {
            this.y += amount;
            return this;
        }
    }

    @Data
    @NoArgsConstructor
    public static class MonthInnerDto {
        private String x; // 월
        private Long y = 0L; // 판매량

        public MonthInnerDto addMonth(LocalDateTime dateTime) {
            x = String.format("%d월", dateTime.getMonth().getValue());
            return this;
        }

        public MonthInnerDto addSales(long amount) {
            this.y += amount;
            return this;
        }
    }
}