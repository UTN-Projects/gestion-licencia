package utn.metodos_agiles.util;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.vandeseer.easytable.TableDrawer;
import org.vandeseer.easytable.structure.Row;
import org.vandeseer.easytable.structure.Table;
import org.vandeseer.easytable.structure.cell.TextCell;

import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.apache.pdfbox.pdmodel.font.Standard14Fonts.FontName.HELVETICA_BOLD;
import static org.apache.pdfbox.pdmodel.font.Standard14Fonts.FontName.HELVETICA_OBLIQUE;
import static org.vandeseer.easytable.settings.HorizontalAlignment.*;

public class FacturaGenerator {

    private final static Color GRAY_LIGHT = new Color(245, 245, 245);
    private final static String BUSINESS = "LICENCIA DE CONDUCIR";
    private final static String BUSINESS_ADDRESS = "Salvador Caputto al 3800 - Santa Fe - Santa Fe - 3000 - Argentina";

    private FacturaGenerator() {}

    public static void generar(Client client, List<Item> items, String filePath) {

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            try(PDPageContentStream contentStream = new PDPageContentStream(document, page)) {

                List<Row> rows = new ArrayList<>();
                rows.addAll(headerRows());
                rows.add(clienteRow(client));
                rows.addAll(itemsRows(items));

                Table table = Table.builder()
                        .addColumnsOfWidth(110, 110, 110, 110, 110)
                        .horizontalAlignment(LEFT)
                        .backgroundColor(Color.WHITE)
                        .borderColor(Color.LIGHT_GRAY)
                        .textColor(Color.black)
                        .borderWidth(1)
                        .padding(10)
                        .rows(rows)
                        .build();


                TableDrawer tableDrawer = TableDrawer.builder()
                        .contentStream(contentStream)
                        .startX(20f)
                        .startY(page.getMediaBox().getUpperRightY() - 20f)
                        .table(table).table(table)
                        .build();

                tableDrawer.draw();
            }

            document.save(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Row> headerRows() {
        String BUSINESS_DATA = "Nro: 00835-00183850 | Fecha: " + LocalDate.now() + "\nIVA Responsable Inscripto - CUIT: 30-71562186-6\nIIBB: 901- 30715621866 - Inicio actividades: 15/07/2023";

        List<Row> rows = new ArrayList<>();

        rows.add(Row.builder()
                .add(TextCell.builder().text(BUSINESS)
                        .font(new PDType1Font(HELVETICA_BOLD))
                        .colSpan(2)
                        .fontSize(15)
                        .build())
                .add(TextCell.builder().text("B").fontSize(30)
                        .font(new PDType1Font(HELVETICA_BOLD))
                        .horizontalAlignment(CENTER)
                        .borderColorLeft(Color.WHITE)
                        .build())
                .add(TextCell.builder().text("FACTURA")
                        .borderColorLeft(Color.WHITE).fontSize(15).colSpan(2).build())
                .build());

        rows.add(Row.builder()
                        .add(TextCell.builder().text(BUSINESS_ADDRESS).colSpan(2)
                                .borderColorTop(Color.WHITE)
                                .build())
                        .add(TextCell.builder().text(BUSINESS_DATA)
                                .borderColorTop(Color.WHITE)
                                .borderColorLeft(Color.WHITE)
                                .colSpan(3)
                                .build())
                .build());
        return rows;
    }

    private static Row clienteRow(Client client) {
        return Row.builder()
                .add(TextCell.builder().text("Cliente:\nDireccion:\nCond. IVA:\nDNI:")
                        .font(new PDType1Font(HELVETICA_BOLD))
                        .horizontalAlignment(RIGHT).build())
                .add(TextCell.builder().text(
                        client.name +"\n"+ client.address + "\nConsumidor Final\n" + client.dni)
                        .borderColorLeft(Color.WHITE)
                        .paddingLeft(0).build())
                .add(TextCell.builder().text("Referencia:")
                        .font(new PDType1Font(HELVETICA_BOLD))
                        .borderColorLeft(Color.WHITE)
                        .horizontalAlignment(RIGHT).build())
                .add(TextCell.builder().text("SLD-1433053396797-01")
                        .borderColorLeft(Color.WHITE)
                        .colSpan(2)
                        .paddingLeft(0).build())
                .build();
    }

    private static List<Row> itemsRows(List<Item> items) {
        List<Row> rows = new ArrayList<>();

        rows.add(Row.builder()
                        .add(TextCell.builder().text("Descripcion").colSpan(2).build())
                        .add(TextCell.builder().text("Cantidad").build())
                        .add(TextCell.builder().text("Precio Unitario").build())
                        .add(TextCell.builder().text("Importe").build())
                        .font(new PDType1Font(HELVETICA_BOLD))
                .backgroundColor(GRAY_LIGHT)
                .build());

        double grandTotal = 0;
        for (int i = 0; i < 18; i++) {
            if(i< items.size()) {
                final Item dataRow = items.get(i);
                final double total = dataRow.value;
                grandTotal += total;

                rows.add(Row.builder()
                        .add(TextCell.builder().text(dataRow.description).borderWidth(1).colSpan(2).build())
                        .add(TextCell.builder().text("1.0 Unidades").borderWidth(1).build())
                        .add(TextCell.builder().text("$ " + total ).borderWidth(1).build())
                        .add(TextCell.builder().text("$ " + total).borderWidth(1).build())
                        .backgroundColor(i % 2 == 0 ? Color.WHITE : GRAY_LIGHT)
                        .horizontalAlignment(RIGHT)
                        .build());
            } else {
                rows.add(Row.builder()
                        .add(TextCell.builder().text("").borderWidth(1).colSpan(2).build())
                        .add(TextCell.builder().text("").borderWidth(1).build())
                        .add(TextCell.builder().text("").borderWidth(1).build())
                        .add(TextCell.builder().text("").borderWidth(1).build())
                        .backgroundColor(i % 2 == 0 ? Color.WHITE : GRAY_LIGHT)
                        .build());
            }
        }

        rows.add(Row.builder()
                .add(TextCell.builder().text("Total")
                        .colSpan(4)
                        .font(new PDType1Font(HELVETICA_BOLD))
                        .build())
                .add(TextCell.builder().text("$ " + grandTotal).build())
                .font(new PDType1Font(HELVETICA_OBLIQUE))
                .horizontalAlignment(RIGHT)
                .build());

        return rows;
    }
}

class Client {
    public String name;
    public String address;
    public String dni;
}

class Item {
    public String description;
    public Float value;

    Item(String description, Float value) {
        this.description = description;
        this.value = value;
    }
}
