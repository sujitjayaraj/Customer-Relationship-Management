package tech.sujitjayaraj.crm.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.sujitjayaraj.crm.entity.Client;
import tech.sujitjayaraj.crm.repository.CompanyRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PdfService {

    private final CompanyRepository companyRepository;

    private static final String TYPE = ".pdf";

    private static final List<String> TITLE_LIST = List.of("Id", "Name", "Status", "Created", "Email", "City", "State", "Responsible Employee");

    @Autowired
    public PdfService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public byte[] printClientList(String fileName, List<Client> clients) throws IOException{
        List<List<String>> data = new ArrayList<>();

        data.add(TITLE_LIST);

        for (Client client : clients) {
            List<String> row = new ArrayList<>();

            row.add(client.getId().toString());
            row.add(client.getName());
            row.add(client.getStatus().toString());
            row.add(client.getCreated().toString());
            row.add(client.getEmail());
            row.add(client.getAddress().getCity());
            row.add(client.getAddress().getState());
            row.add(client.getUser().getName());

            data.add(row);
        }

        return makeTablePdf(fileName, data);
    }

    private byte[] makeTablePdf(String fileName, List<List<String>> data) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter pdfWriter = new PdfWriter(byteArrayOutputStream);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument);
        document.add(new Paragraph(fileName));
        Table table = new Table(data.getFirst().size());

        for (List<String> row : data) {
            for (String field : row) {
                Cell cell = new Cell().add(new Paragraph(field));
                table.addCell(cell);
            }
        }

        document.add(table);
        document.close();

        return byteArrayOutputStream.toByteArray();
    }
}
