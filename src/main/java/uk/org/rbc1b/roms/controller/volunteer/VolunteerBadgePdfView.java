/*
 * The MIT License
 *
 * Copyright 2013 RBC1B.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package uk.org.rbc1b.roms.controller.volunteer;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.Barcode39;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import uk.org.rbc1b.roms.db.volunteer.Volunteer;

/**
 * View that builds the Volunteer Badge as a PDF.
 *
 * @author rahulsingh
 */
public class VolunteerBadgePdfView extends AbstractPdfView {

    /**
     * Builds the PDF document of the badge.
     *
     * @param model model
     * @param document document
     * @param writer writer
     * @param request http request
     * @param response http response
     * @throws DocumentException if not formed
     * @throws IOException if unable to read and render data
     */
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
            HttpServletRequest request, HttpServletResponse response) throws DocumentException, IOException {

        Volunteer volunteer = (Volunteer) model.get("volunteer");
        VolunteerBadgeColour colourBand = (VolunteerBadgeColour) model.get("colourBand");
        Set<String> skillsSet = (Set<String>) model.get("skillsSet");
        String assignment = (String) model.get("assignment");
        document.open();

        document.setPageSize(PageSize.ID_1);

        PdfContentByte cb = writer.getDirectContent();

        addBand(cb, colourBand);
        addSkillsTable(cb);

        addSkills(cb, skillsSet);

        // Get image
        BufferedImage bufferedImage = (BufferedImage) model.get("bufferedImage");
        Image img = Image.getInstance(bufferedImage, null);

        addImage(cb, img);
        addBarcode(cb, volunteer.getPersonId());
        addVolunteerName(cb, volunteer.formatDisplayName());
        addRBCRegionTitle(cb, "RBC London and Home Counties");

        addDepartment(cb, assignment);
        addBigRectangle(cb, colourBand);
        addBadgeTitle(cb, "Kingdom Hall Construction");
    }

    /**
     * Creates a rectangular band to represent the access a volunteer has to the
     * sites. Red means he is allowed to be in high-risk areas.
     *
     * @param content to added to the pdf
     * @param colourBand of the volunteer's badge
     */
    private static void addBand(PdfContentByte content, VolunteerBadgeColour colourBand) {
        content.rectangle(79, 700, 252, 9);
        switch (colourBand) {
            case GREEN:
                content.setColorFill(Color.GREEN);
                break;
            case ORANGE:
                content.setColorFill(Color.ORANGE);
                break;
            case RED:
                content.setColorFill(Color.RED);
                break;
            default:
                content.setColorFill(Color.GRAY);
                break;
        }
        content.fill();
    }

    /**
     * Creates the perimeter and colour of the Rectangular Badge.
     *
     * @param content the Rectangle
     * @param colour colour of the Rectangle
     */
    private static void addBigRectangle(PdfContentByte content, VolunteerBadgeColour colour) {
        content.rectangle(80, 700, 250, -168);
        switch (colour) {
            case GREEN:
                content.setColorStroke(Color.GREEN);
                break;
            case ORANGE:
                content.setColorStroke(Color.ORANGE);
                break;
            case RED:
                content.setColorStroke(Color.RED);
                break;
            default:
                content.setColorStroke(Color.GRAY);
                break;
        }
        content.closePathStroke();
    }

    /**
     * Adds the volunteer's image to the badge.
     *
     * @param content add content
     * @param img the image
     */
    private static void addImage(PdfContentByte content, Image img) throws DocumentException {
        content.addImage(img, 75, 0, 0, 98, 247, 577);
    }

    /**
     * Adds a title to the badge.
     *
     * @param content to be added
     * @param title the title
     */
    private static void addBadgeTitle(PdfContentByte content, String title) throws DocumentException, IOException {
        content.beginText();
        content.moveText(141, 685);

        BaseFont bf = BaseFont.createFont();
        content.setFontAndSize(bf, 12);
        content.setColorFill(Color.BLACK);
        content.setTextRenderingMode(PdfContentByte.TEXT_RENDER_MODE_FILL_STROKE_CLIP);
        content.showText(title);
        content.endText();
    }

    /**
     * Creates a geometric table for a skills matrix.
     *
     * @param content to be added
     */
    private static void addSkillsTable(PdfContentByte content) {
        // Horizontal lines of table
        int y = 0;
        for (int i = 0; i <= 8; i++) {
            content.moveTo(90, 635 - y);
            content.lineTo(205, 635 - y);
            content.closePathStroke();

            y += 12;
        }
        content.setColorStroke(Color.BLACK);
        // left side
        content.moveTo(90, 635);
        content.lineTo(90, 539);
        content.closePathStroke();

        // right side
        content.moveTo(205, 635);
        content.lineTo(205, 539);
        content.setColorStroke(Color.BLACK);
        content.closePathStroke();
    }

    /**
     * Adds the volunteer's skills to the Badge. Uses the y-axis to position
     * each individual skill.
     *
     * @param content to be added
     * @param skills set of volunteer skills
     */
    private static void addSkills(PdfContentByte content, Set<String> skills) throws DocumentException, IOException {
        int y = 0;
        for (String skill : skills) {
            content.beginText();
            content.moveText(91, 625 - y);

            BaseFont bf = BaseFont.createFont();
            content.setFontAndSize(bf, 8);
            content.setColorFill(Color.BLACK);
            content.showText(skill);
            content.endText();
            y += 12;
        }
    }

    /**
     * Adds the Volunteer's name to the Badge.
     *
     * @param content to be added
     * @param name concatenated String of forename and surname
     */
    private static void addVolunteerName(PdfContentByte content, String name) throws DocumentException, IOException {
        content.beginText();
        content.moveText(90, 670);

        BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, false);
        content.setFontAndSize(bf, 11);
        content.setColorFill(Color.BLACK);
        content.showText(name);
        content.endText();
    }

    /**
     * Adds the RBC Region's title (e.g. London and the Home Counties) to the
     * Badge.
     *
     * @param content to be added
     * @param rbcRegion RBC region
     */
    private static void addRBCRegionTitle(PdfContentByte content, String rbcRegion) throws DocumentException, IOException {
        content.beginText();
        content.moveText(90, 641);

        BaseFont bf = BaseFont.createFont();
        content.setFontAndSize(bf, 9);
        content.setColorFill(Color.DARK_GRAY);
        content.showText("-" + rbcRegion);
        content.endText();
    }

    /**
     * Adds a Barcode39 of the id number of a Volunteer to the Badge.
     *
     * @param content to be added
     * @param id volunteer id
     */
    private static void addBarcode(PdfContentByte content, Integer id) throws DocumentException {
        Barcode39 barcode = new Barcode39();
        barcode.setCode(id.toString());
        content.setColorFill(Color.BLACK);
        Image barcodeImage = barcode.createImageWithBarcode(content, null, null);
        content.addImage(barcodeImage, 100, 0, 0, 35, 220, 536);

    }

    /**
     * Adds a department to the Badge. The badge only shows the first
     * departmental assignment of a Volunteer, not all their departmental
     * assignments.
     *
     * @param content to be added
     * @param department of the volunteer
     */
    private static void addDepartment(PdfContentByte content, String department) throws DocumentException, IOException {
        content.beginText();
        content.moveText(90, 653);

        BaseFont bf = BaseFont.createFont();
        content.setFontAndSize(bf, 10.5f);
        content.setColorFill(Color.BLACK);
        content.showText(department);
        content.endText();
    }
}
