import com.app.objects.Task;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;

public class MapperTest {

    public static void main(String[] args) {

        createPdf();

    }

    public static void createPdf() {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        try {
            PDPageContentStream content = new PDPageContentStream(document, page);
            content.setFont(PDType1Font.COURIER, 14);
            content.beginText();
            content.newLineAtOffset(25, 700);
            content.setLeading(14.5f);

            content.showText("You tech task content:");
            content.newLine();

            content.showText("World");


            content.endText();
            content.close();

            document.save("test.pdf");
            document.close();

            System.out.println("Document created.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Task getSampleTask() {
        return null;//new Task("Ulmart", "200x400", );
    }

}
