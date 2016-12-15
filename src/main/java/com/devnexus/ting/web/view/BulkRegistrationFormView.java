package com.devnexus.ting.web.view;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.View;

/**
 *
 * @author summers
 */
public class BulkRegistrationFormView implements View {

    private final Workbook wb;
    private final String filename;

    public BulkRegistrationFormView(Workbook wb, String filename) {
        this.wb = wb;
        this.filename = filename;
    }

    @Override
    public String getContentType() {
        return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType(getContentType());
        response.setHeader("Content-Disposition", "filename=\"" + filename + "\"");
        wb.write(response.getOutputStream());
    }
    
}
