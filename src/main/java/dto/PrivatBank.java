package dto;

import lombok.Getter;


public class PrivatBank {
    @Getter
    private String ccy;
    @Getter
    private String base_ccy;
    @Getter
    private int buy;
    @Getter
    private int sale;

    @Override
    public String toString() {
        return "PrivatBank{" +
                "ccy='" + ccy + '\'' +
                ", base_ccy='" + base_ccy + '\'' +
                ", buy=" + buy +
                ", sale=" + sale +
                '}';
    }
}
