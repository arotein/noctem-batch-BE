package noctem.batch.purchase.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import noctem.batch.purchase.global.common.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentInfo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_info_id")
    private Long id;
    @Column(unique = true)
    private String tid;
    private String cardCorp;
    private Integer cardPaymentPrice;
    private Integer vat;
    private LocalDateTime approvedAt;

    @JsonIgnore
    @OneToOne(mappedBy = "paymentInfo", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Purchase purchase;
}

