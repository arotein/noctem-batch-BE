package noctem.batch.purchase.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import noctem.batch.purchase.global.common.BaseEntity;
import noctem.batch.purchase.global.enumeration.CategorySmall;
import noctem.batch.purchase.global.enumeration.CupType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PurchaseMenu extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_menu_id")
    private Long id;
    private Long sizeId;
    @Enumerated(EnumType.STRING)
    private CategorySmall categorySmall;
    private String menuFullName;
    private String menuShortName;
    private String temperature;
    private String size;
    @Enumerated(EnumType.STRING)
    private CupType cupType;
    private Integer qty;
    private Integer menuTotalPrice;
    @JsonIgnore
    @OneToMany(mappedBy = "purchaseMenu", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PurchasePersonalOption> purchasePersonalOptionList = new ArrayList<>();

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    private String extractTemperature(String menuShortName) {
        char temperatureChar = menuShortName.charAt(0);
        switch (temperatureChar) {
            case 'I':
                return "ICED";
            default:
                return "HOT";
        }
    }

    private String extractSize(String menuShortName) {
        char sizeChar = menuShortName.charAt(2);
        switch (sizeChar) {
            case 'T':
                return "Tall";
            case 'G':
                return "Grande";
            default:
                return "Venti";
        }
    }
}

