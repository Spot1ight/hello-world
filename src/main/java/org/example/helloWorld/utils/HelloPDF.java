package org.example.helloWorld.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.scene.control.Cell;
import javafx.scene.text.TextAlignment;
import org.example.helloWorld.model.Hello;
import org.example.helloWorld.repository.HelloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Service
public class HelloPDF {

    private static final String ORGANIZATION = "АО <<НК>> КазАвтоЖол>>";
    private static final String DIRECTOR = "Тестов Тест Тестович";

    @Autowired
    private HelloRepository helloRepository;

    public void createPDF() throws IOException, DocumentException {

        PdfPTable table;
        PdfPCell cell;
        Paragraph paragraph;

        BaseFont times = BaseFont.createFont("src/main/resources/times.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

        Font headerFont = new Font(times, 12, Font.NORMAL);
        Font headerBold = new Font(times, 12, Font.BOLD);
        Font docNameFont = new Font(times, 14, Font.BOLD);

        Document document = new Document(PageSize.A4
                , Utilities.millimetersToPoints(20f)
                , Utilities.millimetersToPoints(10f)
                , Utilities.millimetersToPoints(10f)
                , Utilities.millimetersToPoints(10f));

        PdfWriter.getInstance(document, new FileOutputStream(new File("src/main/resources/Hello.pdf")));
        document.open();

        // левые параграфы вложить в таблицу

        paragraph = new Paragraph();
        paragraph.setAlignment(Element.ALIGN_LEFT);
        paragraph.add(new Phrase("Исходящий номер 4/ПП - исх", headerFont)); // тут должен быть привязан номер
        paragraph.setSpacingBefore(12);
        document.add(paragraph);

        paragraph = new Paragraph();
        paragraph.setAlignment(Element.ALIGN_LEFT);
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formatDateTime = now.format(formatter);
        paragraph.add(new Phrase("Дата " + formatDateTime, headerFont)); // тут должен быть дата подачи
        paragraph.setSpacingBefore(0);
        document.add(paragraph);

        paragraph = new Paragraph();
        paragraph.setAlignment(Element.ALIGN_LEFT);
        Random rnd = new Random();
        int n = 100000 + rnd.nextInt(900000);
        paragraph.add(new Phrase("Уникальный номер " + n, headerFont)); // тут должен быть номер
        paragraph.setSpacingBefore(0);
        document.add(paragraph);

        // правая таблица имя руко и имя инициатора

        PdfPTable rightPhraseTable = new PdfPTable(1);
        rightPhraseTable.setSpacingBefore(30);
        rightPhraseTable.getDefaultCell().setColspan(1);
        rightPhraseTable.setWidthPercentage(30);
        rightPhraseTable.setHorizontalAlignment(Element.ALIGN_RIGHT);
        rightPhraseTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        rightPhraseTable.addCell(new Phrase("Руководителю ", headerFont));
        rightPhraseTable.addCell(new Phrase(ORGANIZATION, headerFont));
        rightPhraseTable.addCell(new Phrase(DIRECTOR, headerFont));
        rightPhraseTable.addCell(new Phrase("от " + helloRepository.getHellos().get(0).getPosition(), headerFont)); // fix
        rightPhraseTable.addCell(new Phrase(helloRepository.getHellos().get(0).getFullName()));
        document.add(rightPhraseTable);


        // Заголовок

        paragraph = new Paragraph();
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.add(new Phrase("СЛУЖЕБНАЯ ЗАПИСКА", docNameFont));
        paragraph.setSpacingBefore(80);
        document.add(paragraph);

        // тело записи

        PdfPTable phraseTable = new PdfPTable(1);
        phraseTable.setSpacingBefore(20);
        PdfPCell cellOne = new PdfPCell(new Phrase(
                "В связи с " +
                        helloRepository.getHellos().get(0).getNote1() +
                        helloRepository.getHellos().get(0).getNote2(),
                headerFont));
        cellOne.setBorder(Rectangle.NO_BORDER);
        phraseTable.addCell(cellOne);
        document.add(phraseTable);

        PdfPTable phraseTable2 = new PdfPTable(1);
        phraseTable2.setSpacingBefore(60);
        PdfPCell cellTwo = new PdfPCell(new Phrase(
                "Считаю необходимым " +
                        helloRepository.getHellos().get(0).getNote3(),
                headerFont));
        cellTwo.setBorder(Rectangle.NO_BORDER);
        phraseTable2.addCell(cellTwo);
        document.add(phraseTable2);

        PdfPTable phraseTable3 = new PdfPTable(2);
        phraseTable3.setSpacingBefore(60);
        PdfPCell tab3CellOne = new PdfPCell(new Phrase("Должность\n\n " + helloRepository.getHellos().get(0).getPosition(), headerBold));
        PdfPCell tab3CellTwo = new PdfPCell(new Phrase("ФИО\n\n" + helloRepository.getHellos().get(0).getFullName(), headerBold));
        tab3CellOne.setBorder(Rectangle.NO_BORDER);
        tab3CellTwo.setBorder(Rectangle.NO_BORDER);
        tab3CellOne.setHorizontalAlignment(Element.ALIGN_LEFT);
        tab3CellTwo.setHorizontalAlignment(Element.ALIGN_RIGHT);
        phraseTable3.addCell(tab3CellOne);
        phraseTable3.addCell(tab3CellTwo);
        document.add(phraseTable3);

        document.close();

    }

}
