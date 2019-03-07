package com.oopsmails.springboot.backendmock.filedownload.controller;

import org.apache.logging.log4j.LogManager;
import org.owasp.esapi.ESAPI;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/backendmock")
//@CrossOrigin
@CrossOrigin(origins = "*",
        methods = {RequestMethod.POST, RequestMethod.OPTIONS},
        allowedHeaders = {"Content-Type",
                "X-Requested-With",
                "accept", "Origin",
                "Access-Control-Request-Method",
                "Access-Control-Request-Headers"},
        exposedHeaders = {"Access-Control-Allow-Origin",
                "Access-Control-Allow-Credentials"})
public class StreamingResponseBodyController {
    private static final org.slf4j.Logger slf4jLogger = LoggerFactory.getLogger(StreamingResponseBodyController.class);

    org.apache.logging.log4j.Logger log4jLogger = LogManager.getLogger(getClass());

    private static final org.owasp.esapi.Logger esapiLogger = ESAPI.getLogger(StreamingResponseBodyController.class);


    @RequestMapping(value = "/downloadFile/{filetype}", method = { RequestMethod.GET, RequestMethod.POST })
    public StreamingResponseBody getSteamingFile(@PathVariable("filetype") String fileType,
                                                 @RequestParam("filename") String fileName,
                                                 HttpServletResponse response) throws IOException {
        if (log4jLogger.isInfoEnabled()) {
            log4jLogger.info("log4jLogger, fileName is {}", () -> replaceCrlr(fileName));
        }

        slf4jLogger.info("slf4jLogger, fileName is {}", fileName);

        String testString = "\n\rabcd efg";
        esapiLogger.info(null, testString.toString());


        response.setContentType(getContentType(fileType)); //"application/pdf"
        response.setHeader("Content-Disposition", getContentDisposition(fileName));

        response.setHeader("Access-Control-Allow-Origin", "*"); //ok, without WebMvcConfigurer in SpringBootBackendMockApplication

        InputStream inputStream = new FileInputStream(new File(getFileNameWithPath(fileType, fileName)));
        return outputStream -> {
            int nRead;
            byte[] data = new byte[1024];
            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
//                System.out.println("Writing some bytes..");
                outputStream.write(data, 0, nRead);
            }
        };
    }

    private String getContentType(String fileType) {
        String result = "";

        if ("txt".equals(fileType)) {
            return MediaType.TEXT_PLAIN_VALUE;
        } else if ("pdf".equals(fileType)) {
            return MediaType.APPLICATION_PDF_VALUE;
        }
        return result;
    }

    private String getContentDisposition(String fileName) {
        return "attachment; filename=\"" + fileName + "\"";
    }

    private String getFileNameWithPath(String fileType, String fileName) {
        return "C:\\Temp\\MockbackendDownloadTest\\" + fileType + "\\" + fileName;
    }

    private String replaceCrlr(String fileName) {
        return fileName.toString();
    }
}
