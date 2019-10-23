package com.example.OrderProject.util;

import com.example.OrderProject.domain.CustomerOrderRepository;
import com.example.OrderProject.domain.ItemRepository;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class GeneratePdfReport {
	
	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
	
	public static Font bold = new Font(Font.FontFamily.TIMES_ROMAN, 35);
	public static Font total = new Font(Font.FontFamily.TIMES_ROMAN, 20);
	

    private static final Logger logger = LoggerFactory.getLogger(GeneratePdfReport.class);

    public static ByteArrayInputStream itemsReport(Long testid, ItemRepository itemRepository, CustomerOrderRepository orderrepository) {
    	

    	String customerAddress = orderrepository.findById(testid).get().getCustomer().getCustomerAddress();
    	String customerEmail = orderrepository.findById(testid).get().getCustomer().getCustomerEmail();
    	String customerName = orderrepository.findById(testid).get().getCustomer().getCustomerName();
    	String customerNumber = orderrepository.findById(testid).get().getCustomer().getCustomerNumber();
    	String customerPhone = orderrepository.findById(testid).get().getCustomer().getCustomerPhone();
    	
    	
    	ArrayList<String> itemlist = orderrepository.findById(testid).get().getOrderItems();
    	ArrayList<Integer> amountlist = orderrepository.findById(testid).get().getItemAmount();
    
        Document document = new Document();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
       
    
        try {
        	Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        
            PdfPTable orderTable = new PdfPTable(5);
            orderTable.setWidthPercentage(90);
            orderTable.setPaddingTop(200);
                
            PdfPTable buyerTable = new PdfPTable(2);
            buyerTable.setWidthPercentage(90);
            

            PdfPCell hcell;
            
            hcell = new PdfPCell(new Phrase("QTY", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
            hcell.setBorder(Rectangle.NO_BORDER);
            hcell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
            hcell.setPadding(5);
            orderTable.addCell(hcell);
    
            hcell = new PdfPCell(new Phrase("ITEM NUMBER", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
            hcell.setBorder(Rectangle.NO_BORDER);
            hcell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
            hcell.setPadding(5);
            orderTable.addCell(hcell);
            
            hcell = new PdfPCell(new Phrase("DESCRIPTION", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
            hcell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
            hcell.setPadding(5);
            orderTable.addCell(hcell);
           
            
            hcell = new PdfPCell(new Phrase("UNIT PRICE", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
            hcell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
            hcell.setPadding(5);
            orderTable.addCell(hcell);
            
            hcell = new PdfPCell(new Phrase("AMOUNT", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
            hcell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
            hcell.setPadding(5);
            orderTable.addCell(hcell);
         

                PdfPCell Namecell;
                PdfPCell PriceCell;
                PdfPCell EanCell;
                
                double totalSum = 0;
                ArrayList preventDouble = new ArrayList();
                
                for (int i = 0; i < itemlist.size(); i++) {
                	String item = new String(itemlist.get(i).toString());
                	int amount = amountlist.get(i);
                	amountlist.removeIf(Objects::isNull);
                		  
                	String itemName = itemRepository.FindByParamName(item);
                	String itemPrice = itemRepository.FindByParamPrice(item);
                	
                	double toDouble = Double.parseDouble(itemPrice);
                	toDouble = toDouble * amount;
                	totalSum = totalSum + toDouble;
                	
                	String AmountToString = Double.toString(toDouble);
                	String occurrencesToString = Integer.toString(amount);
                	
                	String itemNumber = itemRepository.FindByParamNumber(item);
                	
                	if (!preventDouble.contains(item) ) {
                	
                    Namecell = new PdfPCell(new Paragraph(occurrencesToString));
                	Namecell.setBorder(Rectangle.NO_BORDER);
                	Namecell.setPaddingTop(15);
                	orderTable.addCell(Namecell);
                
                	Namecell = new PdfPCell(new Paragraph(itemNumber));
                	Namecell.setBorder(Rectangle.NO_BORDER);
                	Namecell.setPaddingTop(15);
                	orderTable.addCell(Namecell);
                	
                	PriceCell = new PdfPCell(new Paragraph(itemName));
                	PriceCell.setBorder(Rectangle.NO_BORDER);
                	PriceCell.setPaddingTop(15);
                	orderTable.addCell(PriceCell);
                	
                	EanCell = new PdfPCell(new Paragraph((itemPrice)));
                	EanCell.setBorder(Rectangle.NO_BORDER);
                	EanCell.setPaddingTop(15);
                	orderTable.addCell(EanCell);
                	
                	EanCell = new PdfPCell(new Paragraph((AmountToString)));
                	EanCell.setBorder(Rectangle.NO_BORDER);
                	EanCell.setPaddingTop(15);
                	orderTable.addCell(EanCell);
                	}
                	preventDouble.add(item);
            
                }
                
                PdfPTable totaltable = new PdfPTable(2);
                totaltable.setWidthPercentage(48);
                totaltable.setPaddingTop(200);
                
                PdfPCell totalCell;
                
            totalCell = new PdfPCell(new Paragraph());
           totalCell.setBorder(Rectangle.NO_BORDER);
            totalCell.setPaddingTop(20);
            totaltable.addCell(totalCell);

                
                totalCell = new PdfPCell(new Paragraph(String.valueOf("Total: " + totalSum), total));
             totalCell.setBorder(Rectangle.NO_BORDER);
               totalCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                totalCell.setPaddingTop(20);
                totaltable.addCell(totalCell);
                

            PdfPCell buyerCell;    
  
            buyerCell = new PdfPCell(new Paragraph(
            "To:"  + "\n"
          + customerNumber + "\n"
          + customerName + "\n"
          + customerAddress + "\n"
          + customerPhone + "\n"
          + customerEmail + "\n"));
          buyerCell.setBorder(PdfPCell.NO_BORDER);
            buyerCell.setPaddingRight(50);
            buyerCell.setPaddingBottom(50);
            buyerCell.setPaddingTop(20);
            buyerTable.addCell(buyerCell);
            
            buyerCell = new PdfPCell(new Paragraph(
           "From:"  + "\n"
          + "Tilaukset OY "  + "\n"
          + "Kukkahatunraitti 24" +  "\n"
          + "06660 Tampere" + "\n"
          + "+358 40725053" + "\n"
          + "Tilaukset@ostalisaa.fi" + "\n"));
            buyerCell.setBorder(PdfPCell.NO_BORDER);
            buyerCell.setPaddingLeft(100);
            buyerCell.setPaddingBottom(50);
            buyerCell.setPaddingTop(20);
            buyerTable.addCell(buyerCell);
            
            
            Paragraph header;
            header = new Paragraph(new Paragraph("Invoice", bold));
            header.setAlignment(Element.ALIGN_LEFT);
            
            double ref1 = Math.random();
            double ref2 = Math.random(); 
            ref2 = ref2 * 1000;
            ref1= ref1 * 10000;
            int intref2 = (int)ref2;
            int intref1 = (int)ref1;

            Paragraph paymentinfo = new Paragraph("PAYMENT INFORMATION: ");
            paymentinfo.add(Chunk.NEWLINE);
            paymentinfo.add("Online banking reference: " + intref1 + "BW" + intref2 + "DX");
            paymentinfo.add("");
            
            
            
            Paragraph footer = new Paragraph(String.format(
                    "Please wire the amount due to our bank account using the following banking information:"));
            footer.add(Chunk.NEWLINE);
            footer.add("BIC: OKOYFIHH  - IBAN: FI12 3456 789 0123 45");
  
          
            PdfWriter.getInstance(document, out);

            document.open(); 
            document.add(header);
            document.add(buyerTable);
          
            document.add(orderTable);
            document.add(totaltable);
            document.add( Chunk.NEWLINE );
            document.add(paymentinfo);
            document.add(footer);
            document.close();
                      
            }
         catch (DocumentException ex) {

            logger.error("Error occurred: {0}", ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

	}