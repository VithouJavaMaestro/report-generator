package com.vtx.reportgenerator.configuration;

import com.vtx.reportgenerator.key.JRFileConfigurationKey;
import com.vtx.reportgenerator.key.Key;
import net.sf.jasperreports.data.excel.ExcelFormatEnum;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.query.AbstractXlsQueryExecuterFactory;
import net.sf.jasperreports.engine.query.ExcelQueryExecuterFactory;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.InputStream;

public class JRXlsxConfiguration extends AbstractJRFileConfiguration {

    private Workbook xlsWorkBook;
    private String sheetSelection;
    private ExcelFormatEnum xlsFormat;
    private String[] columnNames;
    private String columnIndex;
    private boolean userFirstRowAsHeader;

    public JRXlsxConfiguration(InputStream inputStream) {
        super(inputStream);
    }

    @Override
    protected void afterPropertiesSet() throws Exception {

        super.afterPropertiesSet();

        setValue(AbstractXlsQueryExecuterFactory.XLS_WORKBOOK, xlsWorkBook);
        setValue(AbstractXlsQueryExecuterFactory.XLS_SHEET_SELECTION, sheetSelection);
        setValue(ExcelQueryExecuterFactory.XLS_FORMAT, xlsFormat);
        setValue(AbstractXlsQueryExecuterFactory.XLS_DATE_FORMAT, dateFormat);
        setValue(AbstractXlsQueryExecuterFactory.XLS_DATE_PATTERN, datePattern);
        setValue(AbstractXlsQueryExecuterFactory.XLS_NUMBER_FORMAT, numberFormat);
        setValue(AbstractXlsQueryExecuterFactory.XLS_NUMBER_PATTERN, numberPattern);
        setValue(AbstractXlsQueryExecuterFactory.XLS_INPUT_STREAM, importedFile);
        setValue(AbstractXlsQueryExecuterFactory.XLS_LOCALE_CODE, localCode);
        setValue(AbstractXlsQueryExecuterFactory.XLS_COLUMN_NAMES, columnNames);
        setValue(AbstractXlsQueryExecuterFactory.XLS_COLUMN_INDEXES, columnIndex);
        setValue(AbstractXlsQueryExecuterFactory.XLS_USE_FIRST_ROW_AS_HEADER, userFirstRowAsHeader);

    }


    @Override
    public void prepareJasperDesignAndQueryInternal(JasperDesign jasperDesign, JRDesignQuery jrDesignQuery) {

    }

    @Override
    public Key getKey() {
        return JRFileConfigurationKey.XLS;
    }

    public Workbook getXlsWorkBook() {
        return xlsWorkBook;
    }

    public void setXlsWorkBook(Workbook xlsWorkBook) {
        this.xlsWorkBook = xlsWorkBook;
    }

    public String getSheetSelection() {
        return sheetSelection;
    }

    public void setSheetSelection(String sheetSelection) {
        this.sheetSelection = sheetSelection;
    }

    public ExcelFormatEnum getXlsFormat() {
        return xlsFormat;
    }

    public void setXlsFormat(ExcelFormatEnum xlsFormat) {
        this.xlsFormat = xlsFormat;
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
    }

    public String getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(String columnIndex) {
        this.columnIndex = columnIndex;
    }

    public boolean isUserFirstRowAsHeader() {
        return userFirstRowAsHeader;
    }

    public void setUserFirstRowAsHeader(boolean userFirstRowAsHeader) {
        this.userFirstRowAsHeader = userFirstRowAsHeader;
    }
}
