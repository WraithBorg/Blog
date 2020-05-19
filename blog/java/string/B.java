package com.example.share.string;

import com.example.demo.DemoUtils;

import static com.example.demo.DemoUtils.ONE_MILLION;

public class B {
    public static void main(String[] args) {
        DemoUtils.delay(0L);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < ONE_MILLION; i++) {
            String  sb =  "SELECT " +
                    " c.organCode, " +
                    " c.organName, " +
                    " c.storeCode, " +
                    " c.storeName, " +
                    " c.dlCode, " +
                    " c.dlName, " +
                    " c.lbCode, " +
                    " c.lbName, " +
                    " c.itemCode, " +
                    " c.itemName, " +
                    " c.itemSpec, " +
                    " c.mainUnit, " +
                    " c.expDays, " +
                    " c.jCCurrentAmount, " +
                    " c.lessOneAmount, " +
                    " c.lessOneMoney, " +
                    " c.oneAmount, " +
                    " c.oneMoney, " +
                    " c.twoAmount, " +
                    " c.twoMoney, " +
                    " c.threeAmount, " +
                    " c.threeMoney, " +
                    " c.fourAmount, " +
                    " c.fourMoney, " +
                    " c.fiveAmount, " +
                    " c.fiveMoney, " +
                    " c.sixAmount, " +
                    " c.sixMoney, " +
                    " c.sevenAmount, " +
                    " c.sevenMoney, " +
                    " c.eightToOneFourAmount, " +
                    " c.eightToOneFourMoney, " +
                    " c.oneFiveAmount, " +
                    " c.oneFiveMoney, " +
                    " c.oneSixToTwoOneAmount, " +
                    " c.oneSixToTwoOneMoney, " +
                    " c.twoTwoToTwoNineAmount, " +
                    " c.twoTwoToTwoNineMoney, " +
                    " c.threeZeroAmount, " +
                    " c.threeZeroMoney, " +
                    " c.threeOneToFiveNineAmount, " +
                    " c.threeOneToFiveNineMoney, " +
                    " c.sixZeroAmount, " +
                    " c.sixZeroMoney, " +
                    " c.sixOneToEightNineAmount, " +
                    " c.sixOneToEightNineMoney, " +
                    " c.nineZeroAmount, " +
                    " c.nineZeroMoney, " +
                    " c.nineOneToOneOneNineAmount, " +
                    " c.nineOneToOneOneNineMoney, " +
                    " c.oneTwoZeroAmount, " +
                    " c.oneTwoZeroMoney, " +
                    " c.oneTwoOneToOneFiveZeroAmount, " +
                    " c.oneTwoOneToOneFiveZeroMoney, " +
                    " c.oneFiveOneToOneEightZeroAmount, " +
                    " c.oneFiveOneToOneEightZeroMoney, " +
                    " c.oneEightOneToThreeSixZeroAmount, " +
                    " c.oneEightOneToThreeSixZeroMoney, " +
                    " c.moreThreeSixZeroAmount, " +
                    " c.moreThreeSixZeroMoney  " +
                    "FROM " +
                    " ( " +
                    " SELECT " +
                    "  b.organCode, " +
                    "  b.organName, " +
                    "  b.storeCode, " +
                    "  b.storeName, " +
                    "  b.dlCode, " +
                    "  b.dlName, " +
                    "  b.lbCode, " +
                    "  b.lbName, " +
                    "  b.itemCode, " +
                    "  b.itemName, " +
                    "  b.itemSpec, " +
                    "  b.mainUnit, " +
                    "  b.expDays, " +
                    "  sum( b.jCCurrentAmount ) AS jCCurrentAmount, " +
                    "  sum( b.lessOneAmount ) AS lessOneAmount, " +
                    "  sum( b.lessOneMoney ) AS lessOneMoney, " +
                    "  sum( b.oneAmount ) AS oneAmount, " +
                    "  sum( b.oneMoney ) AS oneMoney, " +
                    "  sum( b.twoAmount ) AS twoAmount, " +
                    "  sum( b.twoMoney ) AS twoMoney, " +
                    "  sum( b.threeAmount ) AS threeAmount, " +
                    "  sum( b.threeMoney ) AS threeMoney, " +
                    "  sum( b.fourAmount ) AS fourAmount, " +
                    "  sum( b.fourMoney ) AS fourMoney, " +
                    "  sum( b.fiveAmount ) AS fiveAmount, " +
                    "  sum( b.fiveMoney ) AS fiveMoney, " +
                    "  sum( b.sixAmount ) AS sixAmount, " +
                    "  sum( b.sixMoney ) AS sixMoney, " +
                    "  sum( b.sevenAmount ) AS sevenAmount, " +
                    "  sum( b.sevenMoney ) AS sevenMoney, " +
                    "  sum( b.eightToOneFourAmount ) AS eightToOneFourAmount, " +
                    "  sum( b.eightToOneFourMoney ) AS eightToOneFourMoney, " +
                    "  sum( b.oneFiveAmount ) AS oneFiveAmount, " +
                    "  sum( b.oneFiveMoney ) AS oneFiveMoney, " +
                    "  sum( b.oneSixToTwoOneAmount ) AS oneSixToTwoOneAmount, " +
                    "  sum( b.oneSixToTwoOneMoney ) AS oneSixToTwoOneMoney, " +
                    "  sum( b.twoTwoToTwoNineAmount ) AS twoTwoToTwoNineAmount, " +
                    "  sum( b.twoTwoToTwoNineMoney ) AS twoTwoToTwoNineMoney, " +
                    "  sum( b.threeZeroAmount ) AS threeZeroAmount, " +
                    "  sum( b.threeZeroMoney ) AS threeZeroMoney, " +
                    "  sum( b.threeOneToFiveNineAmount ) AS threeOneToFiveNineAmount, " +
                    "  sum( b.threeOneToFiveNineMoney ) AS threeOneToFiveNineMoney, " +
                    "  sum( b.sixZeroAmount ) AS sixZeroAmount, " +
                    "  sum( b.sixZeroMoney ) AS sixZeroMoney, " +
                    "  sum( b.sixOneToEightNineAmount ) AS sixOneToEightNineAmount, " +
                    "  sum( b.sixOneToEightNineMoney ) AS sixOneToEightNineMoney, " +
                    "  sum( b.nineZeroAmount ) AS nineZeroAmount, " +
                    "  sum( b.nineZeroMoney ) AS nineZeroMoney, " +
                    "  sum( b.nineOneToOneOneNineAmount ) AS nineOneToOneOneNineAmount, " +
                    "  sum( b.nineOneToOneOneNineMoney ) AS nineOneToOneOneNineMoney, " +
                    "  sum( b.oneTwoZeroAmount ) AS oneTwoZeroAmount, " +
                    "  sum( b.oneTwoZeroMoney ) AS oneTwoZeroMoney, " +
                    "  sum( b.OneTwoOneToOneFiveZeroAmount ) AS OneTwoOneToOneFiveZeroAmount, " +
                    "  sum( b.OneTwoOneToOneFiveZeroMoney ) AS OneTwoOneToOneFiveZeroMoney, " +
                    "  sum( b.OneFiveOneToOneEightZeroAmount ) AS OneFiveOneToOneEightZeroAmount, " +
                    "  sum( b.OneFiveOneToOneEightZeroMoney ) AS OneFiveOneToOneEightZeroMoney, " +
                    "  sum( b.oneEightOneToThreeSixZeroAmount ) AS oneEightOneToThreeSixZeroAmount, " +
                    "  sum( b.oneEightOneToThreeSixZeroMoney ) AS oneEightOneToThreeSixZeroMoney, " +
                    "  sum( b.moreThreeSixZeroAmount ) AS moreThreeSixZeroAmount, " +
                    "  sum( b.moreThreeSixZeroMoney) AS moreThreeSixZeroMoney  " +
                    " FROM " +
                    "  ( " +
                    "  SELECT " +
                    "   a.organCode, " +
                    "   a.organName, " +
                    "   a.storeCode, " +
                    "   a.storeName, " +
                    "   a.dlCode, " +
                    "   a.dlName, " +
                    "   a.lbCode, " +
                    "   a.lbName, " +
                    "   a.itemCode, " +
                    "   a.itemName, " +
                    "   a.itemSpec, " +
                    "   a.expDays, " +
                    "   a.mainUnit, " +
                    "   a.jCCurrentAmount, " +
                    "  IF( DATEDIFF( CURDATE( ), a.busdate ) < 1, a.jCCurrentAmount, NULL ) AS lessOneAmount, " +
                    "  IF( DATEDIFF( CURDATE( ), a.busdate ) < 1, a.jCCurrentMoney, NULL ) AS lessOneMoney, " +
                    "  IF( DATEDIFF( CURDATE( ), a.busdate ) = 1, a.jCCurrentAmount, NULL ) AS oneAmount, " +
                    "  IF( DATEDIFF( CURDATE( ), a.busdate ) = 1, a.jCCurrentMoney, NULL ) AS oneMoney, " +
                    "  IF( DATEDIFF( CURDATE( ), a.busdate ) = 2, a.jCCurrentAmount, NULL ) AS twoAmount, " +
                    "  IF( DATEDIFF( CURDATE( ), a.busdate ) = 2, a.jCCurrentMoney, NULL ) AS twoMoney, " +
                    "  IF( DATEDIFF( CURDATE( ), a.busdate ) = 3, a.jCCurrentAmount, NULL ) AS threeAmount, " +
                    "  IF( DATEDIFF( CURDATE( ), a.busdate ) = 3, a.jCCurrentMoney, NULL ) AS threeMoney, " +
                    "  IF( DATEDIFF( CURDATE( ), a.busdate ) = 4, a.jCCurrentAmount, NULL ) AS fourAmount, " +
                    "  IF( DATEDIFF( CURDATE( ), a.busdate ) = 4, a.jCCurrentMoney, NULL ) AS fourMoney, " +
                    "  IF( DATEDIFF( CURDATE( ), a.busdate ) = 5, a.jCCurrentAmount, NULL ) AS fiveAmount, " +
                    "  IF( DATEDIFF( CURDATE( ), a.busdate ) = 5, a.jCCurrentMoney, NULL ) AS fiveMoney, " +
                    "  IF( DATEDIFF( CURDATE( ), a.busdate ) = 6, a.jCCurrentAmount, NULL ) AS sixAmount, " +
                    "  IF( DATEDIFF( CURDATE( ), a.busdate ) = 6, a.jCCurrentMoney, NULL ) AS sixMoney, " +
                    "  IF( DATEDIFF( CURDATE( ), a.busdate ) = 7, a.jCCurrentAmount, NULL ) AS sevenAmount, " +
                    "  IF( DATEDIFF( CURDATE( ), a.busdate ) = 7, a.jCCurrentMoney, NULL ) AS sevenMoney, " +
                    "  IF(DATEDIFF( CURDATE( ), a.busdate ) >= 8 AND DATEDIFF( CURDATE( ), a.busdate ) <= 14,a.jCCurrentAmount,NULL ) AS eightToOneFourAmount, " +
                    "  IF(DATEDIFF( CURDATE( ), a.busdate ) >= 8 AND DATEDIFF( CURDATE( ), a.busdate ) <= 14,a.jCCurrentMoney,NULL ) AS eightToOneFourMoney, " +
                    "  IF( DATEDIFF( CURDATE( ), a.busdate ) = 15, a.jCCurrentAmount, NULL ) AS oneFiveAmount, " +
                    "  IF( DATEDIFF( CURDATE( ), a.busdate ) = 15, a.jCCurrentMoney, NULL ) AS oneFiveMoney, " +
                    "  IF(DATEDIFF( CURDATE( ), a.busdate ) >= 16 AND DATEDIFF( CURDATE( ), a.busdate ) <= 21, a.jCCurrentAmount, NULL ) AS oneSixToTwoOneAmount,  " +
                    "  IF(DATEDIFF( CURDATE( ), a.busdate ) >= 16 AND DATEDIFF( CURDATE( ), a.busdate ) <= 21, a.JCCurrentMoney, NULL ) AS oneSixToTwoOneMoney,  " +
                    "  IF ( DATEDIFF( CURDATE( ), a.busdate ) >= 22 AND DATEDIFF( CURDATE( ), a.busdate ) <= 29,a.jCCurrentAmount,NULL ) AS twoTwoToTwoNineAmount, " +
                    "  IF ( DATEDIFF( CURDATE( ), a.busdate ) >= 22 AND DATEDIFF( CURDATE( ), a.busdate ) <= 29,a.JCCurrentMoney,NULL ) AS twoTwoToTwoNineMoney, " +
                    "  IF( DATEDIFF( CURDATE( ), a.busdate ) = 30, a.jCCurrentAmount, NULL ) AS threeZeroAmount, " +
                    "  IF( DATEDIFF( CURDATE( ), a.busdate ) = 30, a.JCCurrentMoney, NULL ) AS threeZeroMoney, " +
                    "  IF(DATEDIFF( CURDATE( ), a.busdate ) >= 31 AND DATEDIFF( CURDATE( ), a.busdate ) <= 59,a.jCCurrentAmount,NULL ) AS threeOneToFiveNineAmount, " +
                    "  IF(DATEDIFF( CURDATE( ), a.busdate ) >= 31 AND DATEDIFF( CURDATE( ), a.busdate ) <= 59,a.jCCurrentMoney,NULL ) AS threeOneToFiveNineMoney, " +
                    "  IF( DATEDIFF( CURDATE( ), a.busdate ) = 60, a.jCCurrentAmount, NULL ) AS sixZeroAmount, " +
                    "  IF( DATEDIFF( CURDATE( ), a.busdate ) = 60, a.jCCurrentMoney, NULL ) AS sixZeroMoney, " +
                    "  IF(DATEDIFF( CURDATE( ), a.busdate ) >= 61 AND DATEDIFF( CURDATE( ), a.busdate ) <= 89,a.jCCurrentAmount,NULL ) AS sixOneToEightNineAmount, " +
                    "  IF(DATEDIFF( CURDATE( ), a.busdate ) >= 61 AND DATEDIFF( CURDATE( ), a.busdate ) <= 89,a.JCCurrentMoney,NULL ) AS sixOneToEightNineMoney, " +
                    "  IF( DATEDIFF( CURDATE( ), a.busdate ) = 90, a.jCCurrentAmount, NULL ) AS nineZeroAmount, " +
                    "  IF( DATEDIFF( CURDATE( ), a.busdate ) = 90, a.JCCurrentMoney, NULL ) AS nineZeroMoney, " +
                    "  IF(DATEDIFF( CURDATE( ), a.busdate ) >= 91 AND DATEDIFF( CURDATE( ), a.busdate ) <= 119,a.jCCurrentAmount,NULL ) AS nineOneToOneOneNineAmount, " +
                    "  IF(DATEDIFF( CURDATE( ), a.busdate ) >= 91 AND DATEDIFF( CURDATE( ), a.busdate ) <= 119,a.JCCurrentMoney,NULL ) AS nineOneToOneOneNineMoney, " +
                    "  IF( DATEDIFF( CURDATE( ), a.busdate ) = 120, a.jCCurrentAmount, NULL ) AS oneTwoZeroAmount, " +
                    "  IF( DATEDIFF( CURDATE( ), a.busdate ) = 120, a.jCCurrentMoney, NULL ) AS oneTwoZeroMoney, " +
                    "  IF(DATEDIFF( CURDATE( ), a.busdate ) >= 121 AND DATEDIFF( CURDATE( ), a.busdate ) <= 150, a.jCCurrentAmount, NULL ) AS OneTwoOneToOneFiveZeroAmount, " +
                    "  IF(DATEDIFF( CURDATE( ), a.busdate ) >= 121 AND DATEDIFF( CURDATE( ), a.busdate ) <= 150, a.JCCurrentMoney, NULL ) AS OneTwoOneToOneFiveZeroMoney, " +
                    "  IF ( DATEDIFF( CURDATE( ), a.busdate ) >= 151 AND DATEDIFF( CURDATE( ), a.busdate ) <= 180, a.jCCurrentAmount, NULL ) AS OneFiveOneToOneEightZeroAmount, " +
                    "  IF ( DATEDIFF( CURDATE( ), a.busdate ) >= 151 AND DATEDIFF( CURDATE( ), a.busdate ) <= 180, a.jCCurrentMoney, NULL ) AS OneFiveOneToOneEightZeroMoney,  " +
                    "  IF ( DATEDIFF( CURDATE( ), a.busdate ) >= 181 AND DATEDIFF( CURDATE( ), a.busdate ) <= 360, a.jCCurrentAmount, NULL ) AS oneEightOneToThreeSixZeroAmount, " +
                    "  IF ( DATEDIFF( CURDATE( ), a.busdate ) >= 181 AND DATEDIFF( CURDATE( ), a.busdate ) <= 360, a.jCCurrentMoney, NULL ) AS oneEightOneToThreeSixZeroMoney,  " +
                    "  IF ( DATEDIFF( CURDATE( ), a.busdate ) > 360,a.jCCurrentAmount,NULL ) AS moreThreeSixZeroAmount, " +
                    "  IF ( DATEDIFF( CURDATE( ), a.busdate ) > 360,a.jCCurrentMoney,NULL ) AS moreThreeSixZeroMoney   " +
                    "  FROM " +
                    "   ( " +
                    "   SELECT " +
                    "    os.CODE organCode, " +
                    "    os.NAME organName, " +
                    "    oi.id storeId, " +
                    "    oi.CODE storeCode, " +
                    "    oi.NAME StoreName, " +
                    "    bic.CODE dlCode, " +
                    "    bic.NAME dlName, " +
                    "    ic.CODE lbCode, " +
                    "    ic.NAME lbName, " +
                    "    i.CODE itemCode, " +
                    "    i.NAME itemName, " +
                    "    i.spec itemSpec, " +
                    "    i.expDays, " +
                    "    dt.BatchCode batchCode, " +
                    "   IF " +
                    "    ( iu.id IS NULL, dt.mainUnit, iu.unit ) AS mainUnit, " +
                    "   IF " +
                    "    ( " +
                    "     iu.id IS NULL, " +
                    "     sum( dt.InMainAmount ) - sum( dt.outMainAmount ), " +
                    "     ( sum( dt.InMainAmount ) - sum( dt.outMainAmount ) ) / iu.UnitRatio  " +
                    "    ) jCCurrentAmount, " +
                    "    SUM(dt.InStoreMoney) - SUM(dt.OutStoreMoney) AS jCCurrentMoney, " +
                    "    min( dt.busdate ) busdate  " +
                    "   FROM " +
                    "    ( " +
                    "    SELECT " +
                    "     dt.InStoreID storeId, " +
                    "     dt.ItemID, " +
                    "     dt.mainUnit, " +
                    "     dt.BatchCode, " +
                    "     dt.InMainAmount, " +
                    "     dt.InStoreMoney, " +
                    "     0 AS outMainAmount, " +
                    "     0 AS OutStoreMoney, " +
                    "     sb.busdate  " +
                    "    FROM " +
                    "     storebill sb, " +
                    "     storebilldt dt, " +
                    "     authority a  " +
                    "    WHERE " +
                    "     sb.id = dt.storebillid  " +
                    "     AND sb.BillState = 3  " +
                    "     AND dt.InStoreID IS NOT NULL  " +
                    "     AND a.id = sb.billtypeid  " +
                    "     AND sb.BillTypeID <> '0400_0050_0100'  " +
                    "     AND sb.billTypeId <> '0450_0030_0300'  " +
                    "     AND sb.billTypeId <> '0450_0030_0400'  " +
                    "     AND sb.billTypeId <> '0400_0030_0200'  " +
                    "     AND sb.billTypeId <> '0800_0100_0100'  " +
                    "     AND sb.billTypeId <> '0800_0400_0100'  " +
                    "     AND sb.billTypeId <> '0450_0025_0100'  " +
                    "     AND sb.billTypeId <> '0450_0025_0200'  " +
                    "     AND sb.billtypeid <> '0480_0100_0100'  " +
                    "     AND sb.billTypeId <> '0400_0020_0600'  " +
                    "     AND sb.billTypeId <> '0400_0050_0200'  " +
                    "     AND sb.BillTypeID <> '0430_0050_0100'  " +
                    "     AND sb.BillTypeID <> '0450_0030_0600'  " +
                    "     AND sb.billTypeId <> '0450_0030_0300'  " +
                    "     AND sb.billTypeId <> '0450_0030_0400'  " +
                    "     AND sb.billTypeId <> '0400_0030_0200'  " +
                    "     AND sb.billTypeId <> '0800_0100_0100'  " +
                    "     AND sb.billTypeId <> '0800_0400_0100'  " +
                    "     AND sb.billTypeId <> '0450_0025_0100'  " +
                    "     AND sb.billTypeId <> '0450_0025_0200'  " +
                    "     AND sb.billTypeId <> '0430_0020_0100'  " +
                    "     AND sb.billTypeId <> '0400_0020_0600'  " +
                    "     AND sb.billTypeId <> '0400_0050_0200'  " +
                    "     AND sb.billTypeId <> '0430_0050_0200'  " +
                    "     AND sb.billbusTypeId <> '0400_0010_0600_2'  " +
                    "     AND sb.billbusTypeId <> '0430_0010_0500_2'  " +
                    "     AND sb.billTypeId <> '0450_0025_0300' UNION ALL " +
                    "    SELECT " +
                    "     dt.InStoreID storeId, " +
                    "     dt.ItemID, " +
                    "     dt.mainUnit, " +
                    "     dt.BatchCode, " +
                    "     dt.InMainAmount, " +
                    "     dt.InStoreMoney, " +
                    "     0 AS outMainAmount, " +
                    "     0 AS OutStoreMoney, " +
                    "     sb.busdate  " +
                    "    FROM " +
                    "     storebillhistory sb, " +
                    "     storebilldthistory dt, " +
                    "     authority a  " +
                    "    WHERE " +
                    "     sb.id = dt.storebillid  " +
                    "     AND sb.BillState = 3  " +
                    "     AND dt.InStoreID IS NOT NULL  " +
                    "     AND a.id = sb.billtypeid  " +
                    "     AND sb.BillTypeID <> '0400_0050_0100'  " +
                    "     AND sb.billTypeId <> '0450_0030_0300'  " +
                    "     AND sb.billTypeId <> '0450_0030_0400'  " +
                    "     AND sb.billTypeId <> '0400_0030_0200'  " +
                    "     AND sb.billTypeId <> '0800_0100_0100'  " +
                    "     AND sb.billTypeId <> '0800_0400_0100'  " +
                    "     AND sb.billTypeId <> '0450_0025_0100'  " +
                    "     AND sb.billTypeId <> '0450_0025_0200'  " +
                    "     AND sb.billtypeid <> '0480_0100_0100'  " +
                    "     AND sb.billTypeId <> '0400_0020_0600'  " +
                    "     AND sb.billTypeId <> '0400_0050_0200'  " +
                    "     AND sb.BillTypeID <> '0430_0050_0100'  " +
                    "     AND sb.BillTypeID <> '0450_0030_0600'  " +
                    "     AND sb.billTypeId <> '0450_0030_0300'  " +
                    "     AND sb.billTypeId <> '0450_0030_0400'  " +
                    "     AND sb.billTypeId <> '0400_0030_0200'  " +
                    "     AND sb.billTypeId <> '0800_0100_0100'  " +
                    "     AND sb.billTypeId <> '0800_0400_0100'  " +
                    "     AND sb.billTypeId <> '0450_0025_0100'  " +
                    "     AND sb.billTypeId <> '0450_0025_0200'  " +
                    "     AND sb.billTypeId <> '0430_0020_0100'  " +
                    "     AND sb.billTypeId <> '0400_0020_0600'  " +
                    "     AND sb.billTypeId <> '0400_0050_0200'  " +
                    "     AND sb.billTypeId <> '0430_0050_0200'  " +
                    "     AND sb.billbusTypeId <> '0400_0010_0600_2'  " +
                    "     AND sb.billbusTypeId <> '0430_0010_0500_2'  " +
                    "     AND sb.billTypeId <> '0450_0025_0300' UNION ALL " +
                    "    SELECT " +
                    "     dt.outStoreID storeId, " +
                    "     dt.ItemID, " +
                    "     dt.mainUnit, " +
                    "     dt.BatchCode, " +
                    "     0 AS inMainAmount, " +
                    "     0 AS InStoreMoney, " +
                    "     dt.outMainAmount AS outMainAmount, " +
                    "     dt.OutStoreMoney, " +
                    "     '9999-12-31 00:00:00' AS busdate  " +
                    "    FROM " +
                    "     storebill sb, " +
                    "     storebilldt dt  " +
                    "    WHERE " +
                    "     sb.id = dt.storebillid  " +
                    "     AND sb.BillState = 3  " +
                    "     AND dt.outStoreID IS NOT NULL  " +
                    "     AND sb.BillTypeID <> '0400_0050_0100'  " +
                    "     AND sb.billTypeId <> '0450_0030_0300'  " +
                    "     AND sb.billTypeId <> '0450_0030_0400'  " +
                    "     AND sb.billTypeId <> '0400_0030_0200'  " +
                    "     AND sb.billTypeId <> '0800_0100_0100'  " +
                    "     AND sb.billTypeId <> '0800_0400_0100'  " +
                    "     AND sb.billTypeId <> '0450_0025_0100'  " +
                    "     AND sb.billTypeId <> '0450_0025_0200'  " +
                    "     AND sb.billtypeid <> '0480_0100_0100'  " +
                    "     AND sb.BillTypeID <> '0430_0050_0100'  " +
                    "     AND sb.BillTypeID <> '0450_0030_0600'  " +
                    "     AND sb.billTypeId <> '0450_0030_0300'  " +
                    "     AND sb.billTypeId <> '0450_0030_0400'  " +
                    "     AND sb.billTypeId <> '0400_0030_0200'  " +
                    "     AND sb.billTypeId <> '0800_0100_0100'  " +
                    "     AND sb.billTypeId <> '0800_0400_0100'  " +
                    "     AND sb.billTypeId <> '0450_0025_0100'  " +
                    "     AND sb.billTypeId <> '0450_0025_0200'  " +
                    "     AND sb.billTypeId <> '0400_0010_0100'  " +
                    "     AND sb.billTypeId <> '0430_0010_0400'  " +
                    "     AND sb.billTypeId <> '0400_0050_0300'  " +
                    "     AND sb.billTypeId <> '0430_0050_0300'  " +
                    "     AND sb.billTypeId <> '0450_0025_0300' UNION ALL " +
                    "    SELECT " +
                    "     dt.outStoreID storeId, " +
                    "     dt.ItemID, " +
                    "     dt.mainUnit, " +
                    "     dt.BatchCode, " +
                    "     0 AS inMainAmount, " +
                    "     0 AS InStoreMoney, " +
                    "     dt.outMainAmount AS outMainAmount, " +
                    "     dt.OutStoreMoney, " +
                    "     '9999-12-31 00:00:00' AS busdate  " +
                    "    FROM " +
                    "     storebillhistory sb, " +
                    "     storebilldthistory dt  " +
                    "    WHERE " +
                    "     sb.id = dt.storebillid  " +
                    "     AND sb.BillState = 3  " +
                    "     AND dt.outStoreID IS NOT NULL  " +
                    "     AND sb.BillTypeID <> '0400_0050_0100'  " +
                    "     AND sb.billTypeId <> '0450_0030_0300'  " +
                    "     AND sb.billTypeId <> '0450_0030_0400'  " +
                    "     AND sb.billTypeId <> '0400_0030_0200'  " +
                    "     AND sb.billTypeId <> '0800_0100_0100'  " +
                    "     AND sb.billTypeId <> '0800_0400_0100'  " +
                    "     AND sb.billTypeId <> '0450_0025_0100'  " +
                    "     AND sb.billTypeId <> '0450_0025_0200'  " +
                    "     AND sb.billtypeid <> '0480_0100_0100'  " +
                    "     AND sb.BillTypeID <> '0430_0050_0100'  " +
                    "     AND sb.BillTypeID <> '0450_0030_0600'  " +
                    "     AND sb.billTypeId <> '0450_0030_0300'  " +
                    "     AND sb.billTypeId <> '0450_0030_0400'  " +
                    "     AND sb.billTypeId <> '0400_0030_0200'  " +
                    "     AND sb.billTypeId <> '0800_0100_0100'  " +
                    "     AND sb.billTypeId <> '0800_0400_0100'  " +
                    "     AND sb.billTypeId <> '0450_0025_0100'  " +
                    "     AND sb.billTypeId <> '0450_0025_0200'  " +
                    "     AND sb.billTypeId <> '0400_0010_0100'  " +
                    "     AND sb.billTypeId <> '0430_0010_0400'  " +
                    "     AND sb.billTypeId <> '0400_0050_0300'  " +
                    "     AND sb.billTypeId <> '0430_0050_0300'  " +
                    "     AND sb.billTypeId <> '0450_0025_0300'  " +
                    "    ) dt " +
                    "    LEFT JOIN organ oi ON oi.id = dt.StoreID " +
                    "    LEFT JOIN organ os ON os.ID = oi.parentid " +
                    "    LEFT JOIN shop s ON s.ID = os.ShopID " +
                    "    JOIN item i ON dt.itemid = i.id " +
                    "    LEFT JOIN financetype ft ON i.financeTypeID = ft.ID " +
                    "    LEFT JOIN itemunit iu ON i.reportUnitID = iu.id, " +
                    "    itemclass ic, " +
                    "    itemclass bic  " +
                    "   WHERE " +
                    "    i.itemClassID = ic.id  " +
                    "    AND ic.ParentID = bic.ID  " +
                    "   GROUP BY " +
                    "    os.CODE, " +
                    "    os.NAME, " +
                    "    oi.CODE, " +
                    "    oi.NAME, " +
                    "    bic.CODE, " +
                    "    bic.NAME, " +
                    "    ic.CODE, " +
                    "    ic.NAME, " +
                    "    i.abc, " +
                    "    i.CODE, " +
                    "    i.NAME, " +
                    "    i.spec, " +
                    "    dt.BatchCode  " +
                    "   ) a  " +
                    "  ) b  " +
                    " GROUP BY " +
                    "  b.organCode, " +
                    "  b.organName, " +
                    "  b.storeCode, " +
                    "  b.storeName, " +
                    "  b.dlCode, " +
                    "  b.dlName, " +
                    "  b.lbCode, " +
                    "  b.lbName, " +
                    "  b.itemCode, " +
                    "  b.itemName, " +
                    "  b.itemSpec, " +
                    "  b.mainUnit, " +
                    "  b.expDays  " +
                    " ) c  " +
                    "WHERE " +
                    " c.jCCurrentAmount >0" ;
            sb += i;
        }
        // 2783 2747 2574 2804
        System.out.println(String.valueOf(System.currentTimeMillis() - startTime));
    }
}
