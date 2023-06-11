package org.BookNBook.utilidades.pdfCreator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.BookNBook.persistence.dao.Libro;
import org.BookNBook.persistence.conector.MySQLConnector;
import org.BookNBook.persistence.manager.ManagerLibro;

/**
 * Clase PDFCreator para generar un pdf con los datos de las lecturas realizadas por el usuario
 * @author maria.escribano.verde
 * @author laura.cabrera.mora
 */
@AllArgsConstructor
@NoArgsConstructor
public class PdfCreator {

    /**
     * Manejador de libros
     */
    ManagerLibro managerLibro;

    /**
     * Generar pdf y añadirle los datos de lecturas del usuario
     * @throws URISyntaxException Error
     * @throws IOException Error
     * @throws MalformedURLException Error
     */
    public String generarPDF(MySQLConnector con, int idUsuario)
            throws URISyntaxException, MalformedURLException, IOException {
        Document documento = new Document();
        try {
            Connection con2 = con.getMySQLConnection();

            String ruta = System.getProperty("user.home");
            String rutaCompleta = ruta + "/Documents/listado" + idUsuario + ".pdf";
            PdfWriter.getInstance(documento, new FileOutputStream(rutaCompleta));
            documento.open();

            /**
             * Imagen a usar como header
             */
            Path pathHeader = Paths.get(getClass().getResource("/imagen/banner.jpg").toURI());
            Image header = Image.getInstance(pathHeader.toAbsolutePath().toString());

            header.scaleAbsoluteWidth(510);
            header.scaleAbsoluteHeight(180);

            documento.add(header);

            /**
             * Espacios en blanco
             */
            Paragraph espacio = new Paragraph(" ");

            /**
             * Texto
             */
            Paragraph parrafo = new Paragraph();

            parrafo.add(espacio);
            parrafo.setAlignment(Paragraph.ALIGN_JUSTIFIED);
            parrafo.add(
                    "PRÓXIMAS LECTURAS: ");
            parrafo.setFont(FontFactory.getFont("Arial", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add(espacio);

            documento.add(parrafo);

            /**
             * Lista con los libros aún no leídos por el usuario
             */
            List listaLibros = new List(List.ORDERED);

            ArrayList<Libro> libros = (ArrayList<Libro>) managerLibro.listarLibrosNoLeidos(con,idUsuario);
            for (Libro l : libros) {
                listaLibros.add(new ListItem(String.valueOf(l)));
            }


            /**
             * Cerramos el documento
             */
            documento.close();
            return rutaCompleta;
        } catch (IOException | DocumentException | ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
