package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameInfoData {
    private String title;
    private Double price;
}
