package bankservices;

import dto.PrivatBank;
import parser.BankParser;

import java.util.ArrayList;

public class PrivatBankService {
    public static ArrayList<PrivatBank> getPrivatValues(){
        ArrayList<PrivatBank> value = new BankParser<PrivatBank>().getValue(
                "https://api.privatbank.ua/p24api/pubinfo?exchange&coursid=5");
        System.out.println("value = " + value);
        return value;
    }
}
