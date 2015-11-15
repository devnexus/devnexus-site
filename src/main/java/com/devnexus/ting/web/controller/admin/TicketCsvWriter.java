/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devnexus.ting.web.controller.admin;

import com.devnexus.ting.core.service.BusinessService;
import com.devnexus.ting.model.TicketOrderDetail;
import java.io.IOException;
import java.io.PrintWriter;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.AbstractCsvWriter;
import org.supercsv.io.ICsvBeanWriter;
import static org.supercsv.prefs.CsvPreference.STANDARD_PREFERENCE;
import org.supercsv.util.Util;

/**
 *
 * @author summers
 */
class TicketCsvWriter extends AbstractCsvWriter implements ICsvBeanWriter {
    private final BusinessService service;

    public TicketCsvWriter(PrintWriter writer, BusinessService service) {
        super(writer, STANDARD_PREFERENCE);
        this.service=service;
    }

    /**
     * {@inheritDoc}
     */
    public void write(final Object source, final String... nameMapping) throws IOException {

        TicketOrderDetail detail = (TicketOrderDetail) source;
        // update the current row/line numbers
        super.incrementRowAndLineNo();

        // write the list
        super.writeRow(
                detail.getFirstName(),
                detail.getLastName(),
                detail.getEmailAddress(),
                detail.getCity(),
                detail.getState(),
                detail.getCountry(),
                detail.getJobTitle(),
                detail.getCompany(),
                detail.gettShirtSize(),
                detail.getVegetarian(),
                detail.getSponsorMayContact(),
                detail.getCreatedDate(),
                detail.getLabel(),
                detail.getRegistration().getPaymentState().name()
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(final Object source, final String[] nameMapping, final CellProcessor[] processors)
            throws IOException {
        TicketOrderDetail detail = (TicketOrderDetail) source;
        // update the current row/line numbers
        super.incrementRowAndLineNo();

         // write the list
        super.writeRow(
                detail.getFirstName(),
                detail.getLastName(),
                detail.getEmailAddress(),
                detail.getCity(),
                detail.getState(),
                detail.getCountry(),
                detail.getJobTitle(),
                detail.getCompany(),
                detail.gettShirtSize(),
                detail.getVegetarian(),
                detail.getSponsorMayContact(),
                detail.getCreatedDate(),
                detail.getLabel(),
                detail.getRegistration().getPaymentState().name()
        );

    }

}
