package service.bankservices;

import dto.EnumPrivat;
import parser.BankParser;

import java.util.ArrayList;

public class PrivatBankService {
    public static ArrayList<EnumPrivat> getPrivatValues(){
        return new BankParser<EnumPrivat>().getValue(
                "https://api.privatbank.ua/p24api/pubinfo?exchange&coursid=5");
    }
}
