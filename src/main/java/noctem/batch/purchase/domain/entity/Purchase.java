package noctem.batch.purchase.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import noctem.batch.purchase.global.common.BaseEntity;
import noctem.batch.purchase.global.enumeration.Sex;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Purchase extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_id")
    private Long id;
    private String noctemCeo = "박찬우";
    private String purchaseSerialNumber;
    private Long storeId;
    private Integer storeOrderNumber;
    private String storeName;
    private String storeAddress;
    private String storeContactNumber;
    private Long userAccountId;
    private String userNickname;
    private String anonymousName;
    private String anonymousPhoneNumber;
    private Integer age;
    @Enumerated(EnumType.STRING)
    private Sex sex;
    private Integer purchaseTotalPrice;
    private Long giftId;
    @ElementCollection
    private List<Long> usedGifticonList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "purchase", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<PurchaseMenu> purchaseMenuList = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "purchase", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<UsedGiftCard> usedGiftCardList = new ArrayList<>();
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "payment_info_id")
    private PaymentInfo paymentInfo;
}
