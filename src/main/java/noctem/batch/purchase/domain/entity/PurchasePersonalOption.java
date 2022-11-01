package noctem.batch.purchase.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import noctem.batch.purchase.global.common.BaseEntity;
import noctem.batch.purchase.global.enumeration.Amount;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PurchasePersonalOption extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_personal_option_id")
    private Long id;
    private Long personalOptionId;
    private String personalOptionName;
    @Enumerated(EnumType.STRING)
    private Amount amount;
    private Integer totalSurcharge; // 총 추가금(샷 추가만 amount의 영향을 받음)
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "purchase_menu_id")
    private PurchaseMenu purchaseMenu;
}

