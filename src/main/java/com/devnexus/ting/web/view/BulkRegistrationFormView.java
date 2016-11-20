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

    public BulkRegistrationFormView(Workbook wb) {
        this.wb = wb;
    }

    @Override
    public String getContentType() {
        return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType(getContentType());
        response.setHeader("Content-Disposition", "filename=\"registrationForm.xlsx\"");
        wb.write(response.getOutputStream());
    }
    
}
