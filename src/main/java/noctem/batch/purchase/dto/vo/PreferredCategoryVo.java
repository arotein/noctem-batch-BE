package noctem.batch.purchase.dto.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import noctem.batch.purchase.global.enumeration.CategorySmall;

@Getter
@AllArgsConstructor
public class PreferredCategoryVo {
    private CategorySmall categorySmall;
    private Long count;
}
