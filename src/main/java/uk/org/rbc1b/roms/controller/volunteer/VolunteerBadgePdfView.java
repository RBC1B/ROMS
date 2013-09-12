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
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import uk.org.rbc1b.roms.db.volunteer.Volunteer;
import uk.org.rbc1b.roms.db.volunteer.VolunteerDao;
import uk.org.rbc1b.roms.db.volunteer.department.Assignment;
import uk.org.rbc1b.roms.db.volunteer.department.Department;
import uk.org.rbc1b.roms.db.volunteer.department.DepartmentDao;
import uk.org.rbc1b.roms.db.volunteer.skill.Skill;
import uk.org.rbc1b.roms.db.volunteer.skill.SkillDao;
import uk.org.rbc1b.roms.db.volunteer.skill.VolunteerSkill;

/**
 * View that creates the Volunteer Badge as a pdf.
 *
 * @author rahulsingh
 */
public class VolunteerBadgePdfView extends AbstractPdfView {

    private VolunteerDao volunteerDao;
    private DepartmentDao departmentDao;
    private SkillDao skillDao;

    /**
     * Builds the PDF document.
     *
     * @param model model
     * @param document document
     * @param writer writer
     * @param request http request
     * @param response http response
     * @throws Exception if not formed
     */
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Volunteer volunteer = (Volunteer) model.get("volunteer");
        document.open();

        document.setPageSize(PageSize.ID_1);

        PdfContentByte cb = writer.getDirectContent();

        addBand(cb, Color.RED);
        addSkillsTable(cb);
        Set<String> skillsSet = new HashSet<String>();
        List<VolunteerSkill> volunteerSkills = volunteerDao.findSkills(volunteer.getPersonId());
        if (!volunteerSkills.isEmpty()) {
            for (VolunteerSkill volunteerSkill : volunteerSkills) {
                Skill skill = skillDao.findSkill(volunteerSkill.getSkillId());
                if (skillsSet.size() < 8) {
                    skillsSet.add(skill.getName());
                }
            }
        }
        addSkils(cb, skillsSet);

        ServletContext context = request.getServletContext();
        String contextPath = context.getRealPath("/");
        Image img = Image.getInstance(contextPath + "/images/oli-lion.jpg");

        addImage(cb, img);
        addBarcode(cb, volunteer.getPersonId());
        addVolunteerName(cb, volunteer.getForename() + " " + volunteer.getSurname());
        addRBCRegionTitle(cb, "RBC London and Home Counties");

        // Find the volunteer's primary assignment
        Assignment primaryAssignment = volunteerDao.findPrimaryAssignment(volunteer.getPersonId());
        Department department = departmentDao.findDepartment(primaryAssignment.getDepartmentId());
        addDepartment(cb, department.getName());
        addBigRectangle(cb);
        addBadgeTitle(cb, "Kingdom Hall Construction");
    }

    /**
     * Creates a rectangular band to represent the access a volunteer has to the
     * sites. Red means he is allowed to be in high-risk areas.
     *
     * @param content to added to the pdf
     * @param color of the volunteer's badge
     */
    private void addBand(PdfContentByte content, Color color) {
        content.roundRectangle(80, 700, 250, 7, 2.5f);
        content.setColorFill(Color.RED);
        content.fill();
    }

    /**
     * Creates the perimeter of the Rectangular Badge.
     *
     * @param content the Rectangle
     */
    private void addBigRectangle(PdfContentByte content) {
        content.rectangle(80, 700, 250, -168);
        content.setColorStroke(Color.BLACK);
        content.closePathStroke();
    }

    /**
     * Adds the volunteer's image to the badge.
     *
     * @param content add content
     * @param img the image
     */
    private void addImage(PdfContentByte content, Image img) {
        try {
            content.addImage(img, 75, 0, 0, 90, 247, 577);
        } catch (DocumentException ex) {
            Logger.getLogger(VolunteerBadgePdfView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Adds a title to the badge.
     *
     * @param content to be added
     * @param title the title
     */
    private void addBadgeTitle(PdfContentByte content, String title) {
        try {
            content.beginText();
            content.moveText(141, 685);
            BaseFont bf = BaseFont.createFont();
            content.setFontAndSize(bf, 12);
            content.setColorFill(Color.BLACK);
            content.setTextRenderingMode(PdfContentByte.TEXT_RENDER_MODE_FILL_STROKE_CLIP);
            content.showText(title);
            content.endText();
        } catch (DocumentException ex) {
            Logger.getLogger(VolunteerBadgePdfView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(VolunteerBadgePdfView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Creates a geometric table for a skills matrix.
     *
     * @param content to be added
     */
    private void addSkillsTable(PdfContentByte content) {
        // Horizontal lines of table
        int y = 0;
        for (int i = 0; i <= 8; i++) {
            content.moveTo(90, 635 - y);
            content.lineTo(180, 635 - y);
            content.closePathStroke();

            y += 12;
        }
        content.setColorStroke(Color.BLACK);
        // left side
        content.moveTo(90, 635);
        content.lineTo(90, 539);
        content.closePathStroke();

        // right side
        content.moveTo(180, 635);
        content.lineTo(180, 539);
        content.setColorStroke(Color.BLACK);
        content.closePathStroke();
    }

    /**
     * Adds the volunteer's skills to the Badge. Uses the y-axis to position
     * each individual skill.
     *
     * @param content to be added
     */
    private void addSkils(PdfContentByte content, Set<String> skills) {
        int y = 0;
        for (String skill : skills) {
            try {
                content.beginText();
                content.moveText(91, 625 - y);
                BaseFont bf = BaseFont.createFont();
                content.setFontAndSize(bf, 8);
                content.setColorFill(Color.BLACK);
                content.showText(skill);
                content.endText();
                y += 12;
            } catch (DocumentException ex) {
                Logger.getLogger(VolunteerBadgePdfView.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(VolunteerBadgePdfView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Adds the Volunteer's name to the Badge.
     *
     * @param content to be added
     * @param name concatenated String of forename and surname
     */
    private void addVolunteerName(PdfContentByte content, String name) {
        content.beginText();
        content.moveText(90, 670);
        BaseFont bf = null;
        try {
            bf = BaseFont.createFont();
        } catch (DocumentException ex) {
            Logger.getLogger(VolunteerBadgePdfView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(VolunteerBadgePdfView.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    private void addRBCRegionTitle(PdfContentByte content, String rbcRegion) {
        content.beginText();
        content.moveText(90, 641);
        BaseFont bf = null;

        try {
            bf = BaseFont.createFont();
        } catch (DocumentException ex) {
            Logger.getLogger(VolunteerBadgePdfView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(VolunteerBadgePdfView.class.getName()).log(Level.SEVERE, null, ex);
        }

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
    private void addBarcode(PdfContentByte content, Integer id) {
        Barcode39 barcode = new Barcode39();
        barcode.setCode(id.toString());

        content.setColorFill(Color.BLACK);
        Image barcodeImage = barcode.createImageWithBarcode(content, null, null);
        try {
            content.addImage(barcodeImage, 100, 0, 0, 35, 220, 536);
        } catch (DocumentException ex) {
            Logger.getLogger(VolunteerBadgePdfView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Adds a department to the Badge. The badge only shows the first
     * departmental assignment of a Volunteer, not all their departmental
     * assignments.
     *
     * @param content to be added
     * @param department of the volunteer
     */
    private void addDepartment(PdfContentByte content, String department) {
        content.beginText();
        content.moveText(90, 653);
        BaseFont bf = null;

        try {
            bf = BaseFont.createFont();
        } catch (DocumentException ex) {
            Logger.getLogger(VolunteerBadgePdfView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(VolunteerBadgePdfView.class.getName()).log(Level.SEVERE, null, ex);
        }

        content.setFontAndSize(bf, 10.5f);
        content.setColorFill(Color.BLACK);
        content.showText(department);
        content.endText();
    }

    @Autowired
    public void setVolunteerDao(VolunteerDao volunteerDao) {
        this.volunteerDao = volunteerDao;
    }

    @Autowired
    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Autowired
    public void setSkillDao(SkillDao skillDao) {
        this.skillDao = skillDao;
    }
}
