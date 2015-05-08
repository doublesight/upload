package com.doublesight.upload.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@WebServlet("/CreateServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 40,    // 40MB
        maxFileSize = 1024 * 1024 * 40,        // 40MB
        maxRequestSize = 1024 * 1024 * 50)    // 50MB
public final class UploadServlet extends HttpServlet {

    private static final String SAVE_DIR = "uploadedFiles";
    private static final String SUCCESS_MESSAGE = "Campaign created successfully";

    protected void doPost(final HttpServletRequest request,
                          final HttpServletResponse response) throws ServletException, IOException {
        // gets absolute path of the web application
        final String appPath = request.getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        final String savePath = appPath + File.separator + SAVE_DIR;

        // creates the save directory if it does not exists
        final File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            //noinspection ResultOfMethodCallIgnored
            fileSaveDir.mkdir();
        }

        for (Part part : request.getParts()) {
            final String fileName = extractFileName(part);
            part.write(savePath + File.separator + fileName);
        }

        request.setAttribute("message", SUCCESS_MESSAGE);
        getServletContext().getRequestDispatcher("/message.jsp").forward(
                request, response);

        //TODO Create database entry
        //TODO Remove file
    }

    private String extractFileName(final Part part) {
        final String contentDisp = part.getHeader("content-disposition");
        final String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }
}
