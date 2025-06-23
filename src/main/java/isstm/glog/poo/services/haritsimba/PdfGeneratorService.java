package isstm.glog.poo.services.haritsimba;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.DayOfWeek;
import java.util.Base64;
import java.util.Map;
import java.util.Objects;

@Service
public class PdfGeneratorService {

    @Autowired
    @Qualifier(value = "pdfTemplateEngine")
    private TemplateEngine templateEngine;

    public byte[] generatePdfFromTemplate(String templateName, Map<String, Object> data) throws IOException {

        for (Map.Entry<String, Object> entry : data.entrySet()) {
            if(!Objects.equals(entry.getKey(), "logoIsstmBase64") && !Objects.equals(entry.getKey(), "logoUnivBase64")){
                System.out.println(entry.getKey() + " : " + entry.getValue().toString());
            }

        }
        // Générer le HTML depuis Thymeleaf
        Context context = new Context();
        context.setVariables(data);
        String htmlContent = templateEngine.process(templateName, context);

        // Configuration du PDF avec openhtmltopdf
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        PdfRendererBuilder builder = new PdfRendererBuilder();
        builder.withHtmlContent(htmlContent, null); // Le deuxième paramètre est la base URL
        builder.toStream(os);
        builder.useFont(() -> {
            try {
                return new ClassPathResource("fonts/arial.ttf").getInputStream();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, "Arial");
        builder.useFont(() -> {
            try {
                return new ClassPathResource("fonts/arialbd.ttf").getInputStream();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, "Arial", 700, null, true);
        builder.run();

        return os.toByteArray();
    }
}