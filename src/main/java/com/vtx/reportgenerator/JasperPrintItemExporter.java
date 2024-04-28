package com.vtx.reportgenerator;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.ExporterInputItem;
import net.sf.jasperreports.export.SimpleExporterInputItem;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.ArrayList;
import java.util.List;

/**.
 * @author Then chanthavithou
 */
public class JasperPrintItemExporter implements ExporterInput {
    private final List<ExporterInputItem> items;

    public JasperPrintItemExporter() {
        this.items = new ArrayList<>();
    }

    @Override
    public List<ExporterInputItem> getItems() {
        return this.items;
    }

    public void add(JasperPrint jasperPrint) {
        this.items.add(new SimpleExporterInputItem(jasperPrint));
    }
}