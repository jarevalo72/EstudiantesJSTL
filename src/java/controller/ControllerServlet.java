/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceCmyk;
import com.itextpdf.kernel.colors.DeviceGray;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.WriterProperties;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.UnitValue;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Curso;
import model.Estudiante;
import model.GestionDatos;

/**
 * Servlet para controlar la aplicación
 * @author Ing. Jorge A. Arévalo A.
 */
@WebServlet(name="ControllerServlet", 
        loadOnStartup = 1,
        urlPatterns={"/agregarCurso", 
            "/agregarEstudiante", 
            "/verCursos", 
            "/verEstudiante", 
            "/verEstudiantes"})
public class ControllerServlet extends HttpServlet {
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. 
    // Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String url = "";
        String userPath = request.getServletPath();
        GestionDatos gd = new GestionDatos();
        if (userPath.equals("/verEstudiantes")) {
            java.util.List<Estudiante> estudiantes = gd.listarEstudiantes();
            request.setAttribute("estudiantes", estudiantes);
            url = "/WEB-INF/vistas" + userPath + ".jsp";
        }
        else if(userPath.equals("/verCursos")) {
            java.util.List<Curso> cursos = gd.listarCursos();
            request.setAttribute("cursos", cursos);
            url = "/WEB-INF/vistas" + userPath + ".jsp";
        }
        else if(userPath.equals("/verEstudiante")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Estudiante est = gd.getEstudianteId(id);
            request.setAttribute("estudiante", est);
            url = "/WEB-INF/vistas" + userPath + ".jsp";
        }
        else if(userPath.equals("/agregarEstudiante")) {
            Estudiante estudiante = new Estudiante();
            java.util.List<Curso> cursos = gd.listarCursos();
            request.setAttribute("estudiante", estudiante);
            request.setAttribute("cursos", cursos);
            url = "/WEB-INF/vistas" + userPath + ".jsp";
        }
        else if(userPath.equals("/agregarCurso")) {
            url = "/WEB-INF/vistas" + userPath + ".jsp";
        }
        else if(userPath.equals("/reporteEstudiantes")) {
            response.setContentType("application/pdf");
            try {
                File file = new File("C:/reports/Reporte Estudiantes.pdf");
                file.getParentFile().mkdirs();
                OutputStream outStream = response.getOutputStream();
                WriterProperties writerProperties = new WriterProperties();
                PdfWriter writer = new PdfWriter(outStream, writerProperties);
                PdfDocument pdf = new PdfDocument(writer);
                PageSize ps = PageSize.LETTER;
                Document document = new Document(pdf, ps);
                document.setMargins(20, 20, 20, 20);
                PdfFont titleFont = PdfFontFactory.createFont(
                        StandardFonts.COURIER_BOLDOBLIQUE);
                PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
                PdfFont bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
                PdfFont footer = PdfFontFactory.createFont(StandardFonts.TIMES_ITALIC);
                Image itc = new Image(ImageDataFactory.create("C:/images/LogoHorizontal.jpg"));
                document.add(itc);
                Paragraph p1 = new Paragraph("\nEstudiantes Registrados")
                        .setFont(titleFont)
                        .setFontSize(24)
                        .setFontColor(ColorConstants.DARK_GRAY)
                        .setHorizontalAlignment(HorizontalAlignment.RIGHT);
                document.add(p1);
                Paragraph p2 = new Paragraph("\n ");
                document.add(p2);
                Table table = new Table(UnitValue.createPointArray(
                        new float[]{1, 4, 4, 6, 10, 10}))
                        .useAllAvailableWidth();
                Cell c1 = new Cell().add(new Paragraph("Id")
                        .setFont(bold)
                        .setFontSize(11))
                        .setBorder(Border.NO_BORDER)
                        .setBackgroundColor(new DeviceGray(0.75f));
                table.addHeaderCell(c1);
                Cell c2 = new Cell().add(new Paragraph("Nombre")
                        .setFont(bold)
                        .setFontSize(11))
                        .setBorder(Border.NO_BORDER)
                        .setBackgroundColor(new DeviceGray(0.75f));
                table.addHeaderCell(c2);
                Cell c3 = new Cell().add(new Paragraph("Apellido")
                        .setFont(bold)
                        .setFontSize(11))
                        .setBorder(Border.NO_BORDER)
                        .setBackgroundColor(new DeviceGray(0.75f));
                table.addHeaderCell(c3);
                Cell c4 = new Cell().add(new Paragraph("Email")
                        .setFont(bold)
                        .setFontSize(11))
                        .setBorder(Border.NO_BORDER)
                        .setBackgroundColor(new DeviceGray(0.75f));
                table.addHeaderCell(c4);
                Cell c5 = new Cell().add(new Paragraph("Curso 1")
                        .setFont(bold)
                        .setFontSize(11))
                        .setBorder(Border.NO_BORDER)
                        .setBackgroundColor(new DeviceGray(0.75f));
                table.addHeaderCell(c5);
                Cell c6 = new Cell().add(new Paragraph("Curso 2")
                        .setFont(bold)
                        .setFontSize(11))
                        .setBorder(Border.NO_BORDER)
                        .setBackgroundColor(new DeviceGray(0.75f));
                table.addHeaderCell(c6);
                java.util.List<Estudiante> estudiantes = gd.listarEstudiantes();
                int i = 1;
                for (Estudiante e : estudiantes) {
                    if(i % 2 == 0) {
                        Cell ca = new Cell().add(new Paragraph(e.getId() + "")
                                .setFont(font)
                                .setFontSize(10))
                                .setBorder(Border.NO_BORDER)
                                .setBackgroundColor(new DeviceGray(0.9f));
                        table.addCell(ca);
                        Cell cb = new Cell().add(new Paragraph(e.getNombre())
                                .setFont(font)
                                .setFontSize(10))
                                .setBorder(Border.NO_BORDER)
                                .setBackgroundColor(new DeviceGray(0.9f));
                        table.addCell(cb);
                        Cell cc = new Cell().add(new Paragraph(e.getApellido())
                                .setFont(font)
                                .setFontSize(10))
                                .setBorder(Border.NO_BORDER)
                                .setBackgroundColor(new DeviceGray(0.9f));
                        table.addCell(cc);
                        Cell cd = new Cell().add(new Paragraph(e.getEmail())
                                .setFont(font)
                                .setFontSize(10))
                                .setBorder(Border.NO_BORDER)
                                .setBackgroundColor(new DeviceGray(0.9f));
                        table.addCell(cd);
                        for (Curso c : e.getCursos()) {
                            Cell ce = new Cell().add(new Paragraph(c.getNombre())
                                    .setFont(font)
                                    .setFontSize(10))
                                    .setBorder(Border.NO_BORDER)
                                    .setBackgroundColor(new DeviceGray(0.9f));
                            table.addCell(ce);
                        }
                    }
                    else {
                        Cell ca = new Cell().add(new Paragraph(e.getId() + "")
                                .setFont(font)
                                .setFontSize(10))
                                .setBorder(Border.NO_BORDER)
                                .setBackgroundColor(DeviceGray.WHITE);
                        table.addCell(ca);
                        Cell cb = new Cell().add(new Paragraph(e.getNombre())
                                .setFont(font)
                                .setFontSize(10))
                                .setBorder(Border.NO_BORDER)
                                .setBackgroundColor(DeviceGray.WHITE);
                        table.addCell(cb);
                        Cell cc = new Cell().add(new Paragraph(e.getApellido())
                                .setFont(font)
                                .setFontSize(10))
                                .setBorder(Border.NO_BORDER)
                                .setBackgroundColor(DeviceGray.WHITE);
                        table.addCell(cc);
                        Cell cd = new Cell().add(new Paragraph(e.getEmail())
                                .setFont(font)
                                .setFontSize(10))
                                .setBorder(Border.NO_BORDER)
                                .setBackgroundColor(DeviceGray.WHITE);
                        table.addCell(cd);
                        for (Curso c : e.getCursos()) {
                            Cell ce = new Cell().add(new Paragraph(c.getNombre())
                                    .setFont(font)
                                    .setFontSize(10))
                                    .setBorder(Border.NO_BORDER)
                                    .setBackgroundColor(DeviceGray.WHITE);
                            table.addCell(ce);
                        }
                    }
                    i++;
                }
                document.add(table);
                Paragraph p3 = new Paragraph("\n\u00a9 Ejemplo desarrollado por: Ing. Jorge A. Arévalo A.")
                        .setFont(footer)
                        .setFontSize(10);
                document.add(p3);
                document.close();;
            }
            catch(Exception ex) {
                ex.getMessage();
                ex.printStackTrace();
            }
            url = "/WEB-INF/vistas" + userPath + ".jsp";
        }
        else if(userPath.equals("/reporteCursos")) {
            response.setContentType("application/pdf");
            try {
                File file = new File("C:/reports/Reporte Cursos.pdf");
                file.getParentFile().mkdirs();
                OutputStream outStream = response.getOutputStream();
                WriterProperties writerProperties = new WriterProperties();
                PdfWriter writer = new PdfWriter(outStream, writerProperties);
                PdfDocument pdf = new PdfDocument(writer);
                PageSize ps = PageSize.LETTER;
                Document document = new Document(pdf, ps);
                document.setMargins(40, 40, 40, 40);
                PdfFont titleFont = PdfFontFactory.createFont(
                        StandardFonts.COURIER_BOLDOBLIQUE);
                PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
                PdfFont bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
                PdfFont footer = PdfFontFactory.createFont(StandardFonts.TIMES_ITALIC);
                Image itc = new Image(ImageDataFactory.create("C:/images/LogoHorizontal.jpg"));
                document.add(itc);
                Paragraph p1 = new Paragraph("\nCursos Registrados")
                        .setFont(titleFont)
                        .setFontSize(24)
                        .setFontColor(ColorConstants.DARK_GRAY)
                        .setHorizontalAlignment(HorizontalAlignment.RIGHT);
                document.add(p1);
                Paragraph p2 = new Paragraph("\n ");
                document.add(p2);
                Table table = new Table(UnitValue.createPointArray(new float[]{1, 10}))
                        .useAllAvailableWidth();
                Cell c1 = new Cell().add(new Paragraph("Id")
                        .setFont(bold)
                        .setFontSize(11))
                        .setBorder(Border.NO_BORDER)
                        .setBackgroundColor(new DeviceGray(0.75f));
                table.addHeaderCell(c1);
                Cell c2 = new Cell().add(new Paragraph("Nombre")
                        .setFont(bold)
                        .setFontSize(11))
                        .setBorder(Border.NO_BORDER)
                        .setBackgroundColor(new DeviceGray(0.75f));
                table.addHeaderCell(c2);
                java.util.List<Curso> cursos = gd.listarCursos();
                int i = 1;
                for (Curso curso : cursos) {
                    if(i % 2 == 0) {
                        Cell ca = new Cell().add(new Paragraph(curso.getId() + "")
                                .setFont(font)
                                .setFontSize(10))
                                .setBorder(Border.NO_BORDER)
                                .setBackgroundColor(new DeviceGray(0.9f));
                        table.addCell(ca);
                        Cell cb = new Cell().add(new Paragraph(curso.getNombre())
                                .setFont(font)
                                .setFontSize(10))
                                .setBorder(Border.NO_BORDER)
                                .setBackgroundColor(new DeviceGray(0.9f));
                        table.addCell(cb);
                    }
                    else {
                        Cell ca = new Cell().add(new Paragraph(curso.getId() + "")
                                .setFont(font)
                                .setFontSize(10))
                                .setBorder(Border.NO_BORDER)
                                .setBackgroundColor(DeviceGray.WHITE);
                        table.addCell(ca);
                        Cell cb = new Cell().add(new Paragraph(curso.getNombre())
                                .setFont(font)
                                .setFontSize(10))
                                .setBorder(Border.NO_BORDER)
                                .setBackgroundColor(DeviceGray.WHITE);
                        table.addCell(cb);
                    }
                    i++;
                }
                document.add(table);
                Paragraph p3 = new Paragraph("\n\u00a9 Ejemplo desarrollado por: Ing. Jorge A. Arévalo A.")
                        .setFont(footer)
                        .setFontSize(10);
                document.add(p3);
                document.close();
            }
            catch(Exception ex) {
                ex.getMessage();
                ex.printStackTrace();
            }
            url = "/WEB-INF/vistas" + userPath + ".jsp";
        }
        try {
            request.getRequestDispatcher(url).forward(request, response);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String url = "";
        String userPath = request.getServletPath();
        GestionDatos gd = new GestionDatos();
        if (userPath.equals("/verEstudiantes")) {
            java.util.List<Estudiante> estudiantes = gd.listarEstudiantes();
            request.setAttribute("estudiantes", estudiantes);
            url = "/WEB-INF/vistas" + userPath + ".jsp";
        }
        else if(userPath.equals("/editarEstudiante")) {
            int id = Integer.parseInt(request.getParameter("id"));
            java.util.List<Curso> cursos = gd.listarCursos();
            Estudiante estudiante = gd.getEstudianteId(id);
            if(estudiante != null)
                estudiante.setCursos((ArrayList<Curso>)gd.listarCursosEstudiante(id));
            request.setAttribute("estudiante", estudiante);
            request.setAttribute("cursos", cursos);
            url = "/WEB-INF/vistas" + userPath + ".jsp";
        }
        else if(userPath.equals("/registrarEstudiante")) {
            Estudiante estudiante = new Estudiante();
            estudiante.setId(Integer.parseInt(request.getParameter("id")));
            estudiante.setNombre(request.getParameter("nombre"));
            estudiante.setApellido(request.getParameter("apellido"));
            estudiante.setEmail(request.getParameter("email"));
            estudiante.setFoto(request.getParameter("foto"));
            String[] cur = request.getParameterValues("cursos");
            for(String s : cur) {
                estudiante.getCursos().add(new Curso(Integer.parseInt(s)));
            }
            if(request.getParameter("submit").equals("Guardar")) {
                if(gd.actualizarEstudiante(estudiante)) {
                    url="/index.jsp";
                }
            }
        }
        else if(userPath.equals("/verEstudiante")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Estudiante est = gd.getEstudianteId(id);
            request.setAttribute("estudiante", est);
            url = "/WEB-INF/vistas" + userPath + ".jsp";
        }
        else if(userPath.equals("/reporteEstudiante")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Estudiante est = gd.getEstudianteId(id);
            response.setContentType("application/pdf");
            try {
                File file = new File("C:/reports/Reporte Estudiante.pdf");
                file.getParentFile().mkdirs();
                OutputStream outStream = response.getOutputStream();
                WriterProperties writerProperties = new WriterProperties();
                PdfWriter writer = new PdfWriter(outStream, writerProperties);
                PdfDocument pdf = new PdfDocument(writer);
                PageSize ps = PageSize.LETTER;
                Document document = new Document(pdf, ps);
                document.setMargins(40, 40, 40, 40);
                PdfFont titleFont = PdfFontFactory.createFont(
                        StandardFonts.COURIER_BOLDOBLIQUE);
                PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
                PdfFont bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
                PdfFont footer = PdfFontFactory.createFont(StandardFonts.TIMES_ITALIC);
                Image itc = new Image(ImageDataFactory.create("C:/images/LogoHorizontal.jpg"));
                document.add(itc);
                Paragraph p1 = new Paragraph("\nRegistro Estudiante")
                        .setFont(titleFont)
                        .setFontSize(24)
                        .setFontColor(ColorConstants.DARK_GRAY)
                        .setHorizontalAlignment(HorizontalAlignment.CENTER);
                document.add(p1);
                Paragraph p2 = new Paragraph("\nInformación Personal")
                        .setFont(font)
                        .setBold()
                        .setFontSize(11);
                document.add(p2);
                Image estImage = new Image(ImageDataFactory.create("C:/" + est.getFoto()))
                        .setHeight(100)
                        .setWidth(100);
                Paragraph p3 = new Paragraph("Identificación: " + est.getId() 
                        + "\nNombre: " + est.getNombre() 
                        + "\nApellido: " + est.getApellido() 
                        + "\nEmail: " + est.getEmail()
                        + "\nFoto: ")
                        .add(estImage)
                        .add("\nCursos: ")
                        .setFont(font)
                        .setFontSize(11)
                        .setFontColor(ColorConstants.DARK_GRAY)
                        .setHorizontalAlignment(HorizontalAlignment.RIGHT);
                document.add(p3);
                List list = new List()
                        .setSymbolIndent(12)
                        .setListSymbol("\u2022")
                        .setFont(font)
                        .setFontSize(11);
                for (Curso curso : est.getCursos()) {
                    list.add(new ListItem(curso.getNombre()));
                }
                document.add(list);
                Paragraph p4 = new Paragraph("\n\u00a9 Ejemplo desarrollado por: Ing. Jorge A. Arévalo A.")
                        .setFont(footer)
                        .setFontSize(10);
                document.add(p4);
                document.close();;
            }
            catch(Exception ex) {
                ex.getMessage();
                ex.printStackTrace();
            }
            url = "/WEB-INF/vistas" + userPath + ".jsp";
        }
        else if(userPath.equals("/agregarCurso")) {
            Curso curso = new Curso();
            if(request.getParameter("submit").equals("Guardar")) {
                curso.setId(Integer.parseInt(request.getParameter("id")));
                curso.setNombre(request.getParameter("nombre"));
                if(gd.registrarCurso(curso)) {
                    java.util.List<Curso> cursos = gd.listarCursos();
                    request.setAttribute("cursos", cursos);
                    url = "/WEB-INF/vistas/verCursos.jsp";
                }
            }
            else {
                url = "/WEB-INF/vistas" + userPath + ".jsp";
            }
        }
        try {
            request.getRequestDispatcher(url).forward(request, response);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

}
