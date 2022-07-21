package com.xlf.common.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;


/**
 * @author
 */

public enum CurrencyTypeEnums {

    AED("AED", "د.إ.‏", 2),
    AFN("AFN", "؋", 2),
    ALL("ALL", "Lek", 2),
    AMD("AMD", "֏", 2),
    ANG("ANG", "ƒ", 2),
    AOA("AOA", "Kz", 2),
    ARS("ARS", "$", 2),
    AUD("AUD", "$", 2),
    AWG("AWG", "ƒ", 2),
    AZN("AZN", "₼", 2),
    BAM("BAM", "КМ", 2),
    BBD("BBD", "$", 2),
    BDT("BDT", "৳", 0),
    BGN("BGN", "лв.", 2),
    BHD("BHD", "د.ب.‏", 3),
    BIF("BIF", "FBu", 0),
    BMD("BMD", "$", 2),
    BND("BND", "$", 0),
    BOB("BOB", "Bs", 2),
    BRL("BRL", "R$", 2),
    BSD("BSD", "$", 2),
    BTC("BTC", "Ƀ", 8),
    BTN("BTN", "Nu.", 1),
    BWP("BWP", "P", 2),
    BYR("BYR", "р.", 2),
    BZD("BZD", "BZ$", 2),
    CAD("CAD", "$", 2),
    CDF("CDF", "FC", 2),
    CHF("CHF", "CHF", 2),
    CLP("CLP", "$", 2),
    CNY("CNY", "¥", 2),
    COP("COP", "$", 2),
    CRC("CRC", "₡", 2),
    CUC("CUC", "CUC", 2),
    CUP("CUP", "$MN", 2),
    CVE("CVE", "$", 2),
    CZK("CZK", "Kč", 2),
    DJF("DJF", "Fdj", 0),
    DKK("DKK", "kr.", 2),
    DOP("DOP", "RD$", 2),
    DZD("DZD", "د.ج.‏", 2),
    EGP("EGP", "ج.م.‏", 2),
    ERN("ERN", "Nfk", 2),
    ETB("ETB", "ETB", 2),
    EUR("EUR", "€", 2),
    FJD("FJD", "$", 2),
    FKP("FKP", "£", 2),
    GBP("GBP", "£", 2),
    GEL("GEL", "Lari", 2),
    GHS("GHS", "₵", 2),
    GIP("GIP", "£", 2),
    GMD("GMD", "D", 2),
    GNF("GNF", "FG", 0),
    GTQ("GTQ", "Q", 2),
    GYD("GYD", "$", 2),
    HKD("HKD", "HK$", 2),
    HNL("HNL", "L.", 2),
    HRK("HRK", "kn", 2),
    HTG("HTG", "G", 2),
    HUF("HUF", "Ft", 2),
    IDR("IDR", "Rp", 0),
    ILS("ILS", "₪", 2),
    INR("INR", "₹", 2),
    IQD("IQD", "د.ع.‏", 2),
    IRR("IRR", "﷼", 2),
    ISK("ISK", "kr.", 0),
    JMD("JMD", "J$", 2),
    JOD("JOD", "د.ا.‏", 3),
    JPY("JPY", "¥", 0),
    KES("KES", "KSh", 2),
    KGS("KGS", "сом", 2),
    KHR("KHR", "៛", 0),
    KMF("KMF", "CF", 2),
    KPW("KPW", "₩", 0),
    KRW("KRW", "₩", 0),
    KWD("KWD", "د.ك.‏", 3),
    KYD("KYD", "$", 2),
    KZT("KZT", "₸", 2),
    LAK("LAK", "₭", 0),
    LBP("LBP", "ل.ل.‏", 2),
    LKR("LKR", "₨", 0),
    LRD("LRD", "$", 2),
    LSL("LSL", "M", 2),
    LYD("LYD", "د.ل.‏", 3),
    MAD("MAD", "د.م.‏", 2),
    MDL("MDL", "lei", 2),
    MGA("MGA", "Ar", 0),
    MKD("MKD", "ден.", 2),
    MMK("MMK", "K", 2),
    MNT("MNT", "₮", 2),
    MOP("MOP", "MOP$", 2),
    MRO("MRO", "UM", 2),
    MTL("MTL", "₤", 2),
    MUR("MUR", "₨", 2),
    MVR("MVR", "MVR", 1),
    MWK("MWK", "MK", 2),
    MXN("MXN", "$", 2),
    MYR("MYR", "RM", 2),
    MZN("MZN", "MT", 0),
    NAD("NAD", "$", 2),
    NGN("NGN", "₦", 2),
    NIO("NIO", "C$", 2),
    NOK("NOK", "kr", 2),
    NPR("NPR", "₨", 2),
    NZD("NZD", "$", 2),
    OMR("OMR", "﷼", 3),
    PAB("PAB", "B/.", 2),
    PEN("PEN", "S/.", 2),
    PGK("PGK", "K", 2),
    PHP("PHP", "₱", 2),
    PKR("PKR", "₨", 2),
    PLN("PLN", "zł", 2),
    PYG("PYG", "₲", 2),
    QAR("QAR", "﷼", 2),
    RON("RON", "L", 2),
    RSD("RSD", "Дин.", 2),
    RUB("RUB", "₽", 2),
    RWF("RWF", "RWF", 2),
    SAR("SAR", "﷼", 2),
    SBD("SBD", "$", 2),
    SCR("SCR", "₨", 2),
    SDD("SDD", "LSd", 2),
    SDG("SDG", "£‏", 2),
    SEK("SEK", "kr", 2),
    SGD("SGD", "$", 2),
    SHP("SHP", "£", 2),
    SLL("SLL", "Le", 2),
    SOS("SOS", "S", 2),
    SRD("SRD", "$", 2),
    STD("STD", "Db", 2),
    SVC("SVC", "₡", 2),
    SYP("SYP", "£", 2),
    SZL("SZL", "E", 2),
    THB("THB", "฿", 2),
    TJS("TJS", "TJS", 2),
    TMT("TMT", "m", 0),
    TND("TND", "د.ت.‏", 3),
    TOP("TOP", "T$", 2),
    TRY("TRY", "₺", 2),
    TTD("TTD", "TT$", 2),
    TVD("TVD", "$", 2),
    TWD("TWD", "NT$", 2),
    TZS("TZS", "TSh", 2),
    UAH("UAH", "₴", 2),
    UGX("UGX", "USh", 2),
    USD("USD", "$", 2),
    UYU("UYU", "$U", 2),
    UZS("UZS", "сўм", 2),
    VEB("VEB", "Bs.", 2),
    VEF("VEF", "Bs. F.", 2),
    VND("VND", "₫", 0),
    VUV("VUV", "VT", 0),
    WST("WST", "WS$", 2),
    XAF("XAF", "F", 2),
    XCD("XCD", "$", 2),
    XBT("XBT", "Ƀ", 2),
    XOF("XOF", "F", 2),
    XPF("XPF", "F", 2),
    YER("YER", "﷼", 2),
    ZAR("ZAR", "R", 2),
    ZMW("ZMW", "ZK", 2),
    WON("WON", "₩", 2),
    ;

    private String code;
    private String symbol;
    private Integer decimalDigits;


    public String getCode() {
        return code;
    }

    public String getSymbol() {
        return symbol;
    }

    public Integer getDecimalDigits() {
        return decimalDigits;
    }


    CurrencyTypeEnums(String code, String symbol, Integer decimalDigits) {

        this.code = code;
        this.symbol = symbol;
        this.decimalDigits = decimalDigits;
    }

    private static final Map<String, CurrencyTypeEnums> TYPE_MAP = new HashMap<>();

    static {
        for (CurrencyTypeEnums currency : CurrencyTypeEnums.values()) {
            TYPE_MAP.put(currency.code, currency);
        }
    }

    public static CurrencyTypeEnums parse(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        return TYPE_MAP.get(code);
    }


}
