/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entity.currencyDailyService.DailyExchangeRate;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import xml.XMLHandler;
import static org.junit.Assert.*;

/**
 *
 * @author terziev
 */
public class XMLHandlerTest {

    XMLHandler handler;

    public XMLHandlerTest() {
        handler = new XMLHandler();
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getDailyCurrencyRate_RowDataList_NotNullTest() {

        List<String> rowdata = handler.getDailyExchangeRate_RowData();

        assertTrue("Expected list not null, got: " + rowdata, rowdata != null);

        assertTrue("Expected list of size more than one, got: " + rowdata.size(), rowdata.size() > 0);

    }

    @Test
    public void getDailyCurrencyRate_ProcessedDataList_NotNullTest() {

        handler.processData();

        List<DailyExchangeRate> processedData = handler.getDailyExchangeRateList();

        assertTrue("Expected list not null, got: " + processedData, processedData != null);

        assertTrue("Expected list of size more than one, got: " + processedData.size(), processedData.size() > 0);

    }

    @Test
    public void getDailyCurrencyRate_ProcessesDataList_ValidFormat() {

        //get the row data scraped from the National Banken
        List<String> rowDataList = handler.getDailyExchangeRate_RowData();

        //process the rowDataList
        handler.processData();

        List<DailyExchangeRate> processedList = handler.getDailyExchangeRateList();

        //check if the date in the processed list is equal to the actual date
        assertTrue(rowDataList.get(4).equals(processedList.get(0).getDate_()));

        //check if the currency code in the processed list is equal to the actual one
        assertTrue(rowDataList.get(5).equals(processedList.get(0).getCurrency().getCode()));

        //check if the description in the processed list is equal to the actual one
        assertTrue(rowDataList.get(6).equals(processedList.get(0).getCurrency().getDescription()));

        //check if the rate in the processed list is equal to the actual one
        assertTrue(rowDataList.get(7).equals(processedList.get(0).getRate()));
    }
}
