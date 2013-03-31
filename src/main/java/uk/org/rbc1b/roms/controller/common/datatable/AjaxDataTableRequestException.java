/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.org.rbc1b.roms.controller.common.datatable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when unexpected data table request parameters are provided.
 *
 * @author oliver.elder.esq
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AjaxDataTableRequestException extends RuntimeException {

    /**
     * @param message exception message
     */
    public AjaxDataTableRequestException(String message) {
        super(message);
    }

    /**
     * @param message message
     * @param cause root cause
     */
    public AjaxDataTableRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
