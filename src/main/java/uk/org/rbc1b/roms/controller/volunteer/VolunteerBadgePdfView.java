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

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfStamperView;
import uk.org.rbc1b.roms.db.volunteer.Volunteer;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.Barcode39;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfStamper;

/**
 * View that builds the Volunteer Badge as a PDF.
 *
 * @author rahulsingh
 */
public class VolunteerBadgePdfView extends AbstractPdfStamperView {

    private static final String BADGE_RBC_REGION = "RBC London and Home Counties";

    /**
     * Builds the PDF document of the badge.
     *
     * @param model model
     * @param stamper pdf stamper allows us to edit existing pdfs
     * @param request http request
     * @param response http response
     * @throws DocumentException if not formed
     * @throws IOException if unable to read and render data
     */
    @Override
    protected void mergePdfDocument(Map<String, Object> model, PdfStamper stamper, HttpServletRequest request,
            HttpServletResponse response) throws DocumentException, IOException {

        Volunteer volunteer = (Volunteer) model.get("volunteer");
        PdfContentByte cb = stamper.getOverContent(1);
        String assignment = (String) model.get("assignment");

        // Get image
        BufferedImage bufferedImage = (BufferedImage) model.get("bufferedImage");
        Image img = Image.getInstance(bufferedImage, null);

        addImage(cb, img);
        addBarcode(cb, volunteer.getPersonId());
        addVolunteerName(cb, volunteer.getPerson().formatDisplayName());
        addDepartment(cb, assignment);
        addRBCRegionFooter(cb);
    }

    /**
     * Adds the volunteer's image to the badge.
     *
     * @param content add content
     * @param img the image
     */
    private static void addImage(PdfContentByte content, Image img) throws DocumentException {
        content.addImage(img, 75, 0, 0, 88, 338, 383);
    }

    /**
     * Adds the Volunteer's name to the Badge.
     *
     * @param content to be added
     * @param name concatenated String of forename and surname
     */
    private static void addVolunteerName(PdfContentByte content, String name) throws DocumentException, IOException {
        content.beginText();
        content.moveText(183, 470);

        BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1257, false);
        content.setFontAndSize(bf, 16);
        content.setColorFill(Color.BLACK);
        content.showText(name);
        content.endText();
    }

    /**
     * Adds the RBC Region's title (e.g. London and the Home Counties) to the
     * bottom of the badge.
     *
     * @param content to be added
     * @param rbcRegion RBC region
     */
    private static void addRBCRegionFooter(PdfContentByte content) throws DocumentException, IOException {
        content.beginText();
        content.moveText(183, 345);

        BaseFont bf = BaseFont.createFont();
        content.setFontAndSize(bf, 9);
        content.setColorFill(Color.DARK_GRAY);
        content.showText(VolunteerBadgePdfView.BADGE_RBC_REGION);
        content.endText();
    }

    /**
     * Adds a Barcode39 of the id number of a Volunteer to the Badge.
     *
     * @param content to be added
     * @param id volunteer id
     * @throws DocumentException
     * @throws IOException
     */
    private static void addBarcode(PdfContentByte contentByte, Integer id) throws IOException, DocumentException {
        Barcode39 barcode = new Barcode39();
        BaseFont baseFont = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.EMBEDDED);
        barcode.setFont(baseFont);
        barcode.setSize(6);
        barcode.setCode(id.toString());
        contentByte.setColorFill(Color.BLACK);
        Image baecodeImg = barcode.createImageWithBarcode(contentByte, null, null);
        contentByte.addImage(baecodeImg, 75, 0, 0, 35, 338, 344);
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
        content.moveText(183, 453);

        BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1257, false);
        content.setFontAndSize(bf, 14);
        content.showText(department);
        content.endText();
    }
}
