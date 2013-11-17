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
package uk.org.rbc1b.roms.scheduled;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.MessagingException;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import uk.org.rbc1b.roms.security.ROMSUserDetailsService;
import uk.org.rbc1b.roms.service.EdificeMailer;

/**
 * Sends email periodically.
 *
 */
@Component
public class MailerJob extends QuartzJobBean {

    /**
     * Scheduled job.
     *
     * @param context
     *            the job execution context
     * @throws JobExecutionException
     *             the job exception
     */
    @Override
    protected void executeInternal(JobExecutionContext context)
            throws JobExecutionException {
        ROMSUserDetailsService userDetailsService = (ROMSUserDetailsService) context
                .getJobDetail().getJobDataMap().get("userDetailsService");
        UserDetails system = userDetailsService.loadUserByUsername("System");
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                system, system.getUsername(), system.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        EdificeMailer edificeMailer = (EdificeMailer) context.getJobDetail()
                .getJobDataMap().get("edificeMailer");
        try {
            edificeMailer.prepareAndSendEmail();
        } catch (MessagingException ex) {
            Logger.getLogger(MailerJob.class.getName()).log(Level.SEVERE, null,
                    ex);
        } catch (IOException ex) {
            Logger.getLogger(MailerJob.class.getName()).log(Level.SEVERE, null,
                    ex);
        } catch (SQLException ex) {
            Logger.getLogger(MailerJob.class.getName()).log(Level.SEVERE, null,
                    ex);
        }
    }
}
