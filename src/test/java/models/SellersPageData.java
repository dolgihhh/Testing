package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SellersPageData {
    private String title;
    private Double price;
}
