package utn.metodos_agiles;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDImmutableRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.JPEGFactory;

import org.vandeseer.easytable.TableDrawer;
import org.vandeseer.easytable.settings.VerticalAlignment;
import org.vandeseer.easytable.structure.Row;
import org.vandeseer.easytable.structure.Table;
import org.vandeseer.easytable.structure.cell.ImageCell;
import org.vandeseer.easytable.structure.cell.TextCell;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.pdfbox.pdmodel.font.Standard14Fonts.FontName.HELVETICA_BOLD;
import static org.vandeseer.easytable.settings.HorizontalAlignment.*;

public class LicenciaGenerator {

    private final static Color BLUE = new Color(0,122,187,255);
    private final static Color BLUE_DARK = new Color(12,79,149,255);
    private final static Color BLUE_LIGHT = new Color(112,210,225);
    private final static Color RED = new Color(117,6,4);

    private final static String MINISTERIO_TRANSPORTE_SPRITE_PATH = "src/main/resources/licencia_sprites/ministerio-transporte.png";
    private final static String ESCUDO_SPRITE_PATH = "src/main/resources/licencia_sprites/escudo.png";
    private final static String BANDERA_SPRITE_PATH = "src/main/resources/licencia_sprites/bandera.png";
    private final static String SEGURIDAD_VIAL_SPRITE_PATH = "src/main/resources/licencia_sprites/seguridad-vial.png";

    private LicenciaGenerator() {}

    public static void generar(LicenciaDto licenciaDto, String imagePath, String filePath) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(new PDImmutableRectangle(240.9448845F,152.50393866F));
            document.addPage(page);

            BufferedImage image = ImageIO.read(new File(imagePath));

            try(PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                Table table = Table.builder()
                        .addColumnsOfWidth(80, 80, 80)
                        .horizontalAlignment(LEFT)
                        .backgroundColor(Color.WHITE)
                        .borderColor(Color.LIGHT_GRAY)
                        .font(new PDType1Font(HELVETICA_BOLD))
                        .borderWidth(0)
                        .padding(2)
                        //.padding(3)
                        // HEADER
                        .addRow(Row.builder().add(
                                        TextCell.builder()
                                                .text("Licencia Nacional de Conducir")
                                                .textColor(BLUE_DARK)
                                                .horizontalAlignment(CENTER)
                                                .font(new PDType1Font(HELVETICA_BOLD))
                                                .paddingTop(4)
                                                .colSpan(3)
                                                .build())
                                .build())
                        .addRow(Row.builder().add(
                                        TextCell.builder()
                                                .text("Chaco - Fontana")
                                                .textColor(BLUE_DARK)
                                                .horizontalAlignment(CENTER)
                                                .borderColorBottom(BLUE_LIGHT)
                                                .fontSize(8)
                                                .borderWidthBottom(1)
                                                .paddingBottom(3)
                                                .colSpan(3)
                                                .build())
                                .build())
                        // BODY
                        .addRow(Row.builder()
                                .add(ImageCell.builder()
                                        .image(JPEGFactory.createFromImage(new PDDocument(), image))
                                        .rowSpan(12)
                                        .build())
                                .add(label("N° Licencia").build())
                            .add(label("Clase").horizontalAlignment(RIGHT).build())
                                .height(5f)
                                .build())
                        .addRow(Row.builder()
                                .add(dataCell(licenciaDto.number).colSpan(1).build())
                                .add(dataCell("A3 B1").colSpan(1).horizontalAlignment(RIGHT).build()).build())
                        .addRow(Row.builder().add(label("Apellido").colSpan(2).build()).height(5f).build())
                        .addRow(Row.builder().add(dataCell(licenciaDto.lastname).colSpan(2).build()).build())
                        .addRow(Row.builder().add(label("Nombre").colSpan(2).build()).height(5f).build())
                        .addRow(Row.builder().add(dataCell(licenciaDto.name).colSpan(2).build()).build())
                        .addRow(Row.builder().add(label("Domicilio").colSpan(2).build()).height(5f).build())
                        .addRow(Row.builder().add(dataCell(licenciaDto.address).colSpan(2).build()).build())
                        .addRow(Row.builder().add(label("Fecha de Nac.").colSpan(2).build()).height(5f).build())
                        .addRow(Row.builder().add(dataCell(licenciaDto.birth).colSpan(2).build()).build())
                        .addRow(Row.builder().add(label("Otorgamiento").build())
                                .add(label("Vencimiento").horizontalAlignment(RIGHT).build()).height(5f).build())
                        .addRow(Row.builder()
                                .add(dataCell(licenciaDto.emition).colSpan(1).build())
                                .add(dataCell(licenciaDto.expiration).textColor(RED).colSpan(1).horizontalAlignment(RIGHT).build())
                                .build())

                        // FOOTER
                        .addRow(footer())
                        .build();

                TableDrawer tableDrawer = TableDrawer.builder()
                        .contentStream(contentStream)
                        .startX(0f)
                        .startY(page.getMediaBox().getUpperRightY() - 0f)
                        .table(table)
                        .build();

                tableDrawer.draw();
            }

            document.save(filePath);
        } catch (IOException e) {

        }
    }


    private static Row footer() throws IOException{
        return Row.builder()
                .add(ImageCell.builder()
                        .image(JPEGFactory.createFromImage(new PDDocument(), ImageIO.read(new File(SEGURIDAD_VIAL_SPRITE_PATH)))).maxHeight(20).build())
                .add(ImageCell.builder()
                        .image(JPEGFactory.createFromImage(new PDDocument(), ImageIO.read(new File(MINISTERIO_TRANSPORTE_SPRITE_PATH))))
                        .colSpan(2).maxHeight(20).horizontalAlignment(RIGHT).build())
                .backgroundColor(BLUE)
                .textColor(Color.WHITE)
                .build();
    }

    private static TextCell.TextCellBuilder label(String text) {
        return TextCell.builder().text(text).verticalAlignment(VerticalAlignment.BOTTOM).fontSize(4);
    }

    private static TextCell.TextCellBuilder dataCell(String text) {
        return TextCell.builder().text(text).verticalAlignment(VerticalAlignment.MIDDLE).fontSize(8);
    }
}

class LicenciaDto {
    public String number;
    public String lastname;
    public String name;
    public String address;
    public String birth;
    public String emition;
    public String expiration;
}
