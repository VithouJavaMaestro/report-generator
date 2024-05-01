package com.vtx.reportgenerator.configuration;

import com.vtx.reportgenerator.FileUtils;
import net.sf.jasperreports.data.excel.ExcelFormatEnum;
import net.sf.jasperreports.engine.query.AbstractXlsQueryExecuterFactory;
import net.sf.jasperreports.engine.query.ExcelQueryExecuterFactory;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class JRXlsxConfiguration extends AbstractJRFileConfiguration {
    private Workbook xlsWorkBook;
    private String excelPath;
    private String sheetSelection;
    private ExcelFormatEnum xlsFormat;
    private String[] columnNames;
    private String columnIndex;
    private boolean userFirstRowAsHeader;

    public JRXlsxConfiguration(String excelPath) {
        FileUtils.checkFileExtension(excelPath, "xlsx", "xls");
        this.excelPath = excelPath;
    }

    @Override
    protected void afterPropertiesSet() {

        try {

            File file = new File(excelPath);
            setValue(AbstractXlsQueryExecuterFactory.XLS_WORKBOOK, xlsWorkBook);
            setValue(AbstractXlsQueryExecuterFactory.XLS_SHEET_SELECTION, sheetSelection);
            setValue(ExcelQueryExecuterFactory.XLS_FORMAT, xlsFormat);
            setValue(AbstractXlsQueryExecuterFactory.XLS_DATE_FORMAT, dateFormat);
            setValue(AbstractXlsQueryExecuterFactory.XLS_DATE_PATTERN, datePattern);
            setValue(AbstractXlsQueryExecuterFactory.XLS_NUMBER_FORMAT, numberFormat);
            setValue(AbstractXlsQueryExecuterFactory.XLS_NUMBER_PATTERN, numberPattern);
            setValue(AbstractXlsQueryExecuterFactory.XLS_INPUT_STREAM, new FileInputStream(file));
            setValue(AbstractXlsQueryExecuterFactory.XLS_FILE, file);
            setValue(AbstractXlsQueryExecuterFactory.XLS_LOCALE_CODE, localCode);
            setValue(AbstractXlsQueryExecuterFactory.XLS_COLUMN_NAMES, columnNames);
            setValue(AbstractXlsQueryExecuterFactory.XLS_COLUMN_INDEXES, columnIndex);
            setValue(AbstractXlsQueryExecuterFactory.XLS_USE_FIRST_ROW_AS_HEADER, userFirstRowAsHeader);

        } catch (FileNotFoundException exception) {
            throw new IllegalArgumentException("File not found", exception);
        }
    }

    public Workbook getXlsWorkBook() {
        return xlsWorkBook;
    }

    public void setXlsWorkBook(Workbook xlsWorkBook) {
        this.xlsWorkBook = xlsWorkBook;
    }

    public String getExcelPath() {
        return excelPath;
    }

    public void setExcelPath(String excelPath) {
        this.excelPath = excelPath;
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
