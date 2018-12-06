package com.flp.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.flp.model.Answer;
import com.flp.model.Assessment;
import com.flp.model.AssessmentQuestion;
import com.flp.util.Utility;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
/**
 * 
 * @author Amresh
 *
 */
public class CreatePDF
{

	/** @param args
	 * @throws ParseException 
	 * @throws IOException */
	public static Document createPDF(String file, Assessment assObj) throws ParseException, IOException
	{
		Document document = null;
		try
		{
			document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();
			/* addMetaData(document); addTitlePage(document); */
			createTable(document, assObj);
			document.close();

		}
		catch (FileNotFoundException e)
		{

			e.printStackTrace();
		}
		catch (DocumentException e)
		{
			e.printStackTrace();
		}
		return document;

	}
/**
 * 
 * @param document
 * @param assObj
 * @throws DocumentException
 * @throws ParseException
 * @throws IOException
 */
	private static void createTable(Document document, Assessment assObj) throws DocumentException, ParseException, IOException
	{
		//BaseFont bf = BaseFont.createFont(BaseFont.COURIER, BaseFont.IDENTITY_H,BaseFont.EMBEDDED);
		Font font_16_bold = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
		Font font_10_bold = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
		font_10_bold.setColor(BaseColor.RED);
		Font font_9_bold = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
		Font font_8_bold = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD);

		Font font_8 = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL);

		DateFormat srcDf = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
		Date date = srcDf.parse(Utility.convertDateToString(assObj.getStartDate(), "MM-dd-yyyy hh:mm:ss"));
		DateFormat destDf = new SimpleDateFormat("dd MMM yy");
		String testStartDate = destDf.format(date);
		/* Paragraph paragraph = new Paragraph();
		 * 
		 * document.add(paragraph); */
		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100);
		 
		PdfPTable table1 = new PdfPTable(2);
		table1.setWidthPercentage(100);
		float[] widths = new float[] { 3f, 97f };
		table1.setWidths(widths);
		PdfPCell c1 = new PdfPCell(new Phrase("Test Name", font_16_bold));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setColspan(3);
		c1.setPaddingBottom(8);
		c1.setBorder(0);
		table.addCell(c1);
		c1 = new PdfPCell(new Phrase("Test Date : " + testStartDate, font_8_bold));
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		c1.setBorder(0);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase(assObj.getTestName(), font_9_bold));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBorder(0);
		table.addCell(c1);
		String testType = "";
		if (assObj.getType().equals(1))
		{
			testType = "E-Test";
		}
		else
		{
			testType = "Assignment";
		}
		c1 = new PdfPCell(new Phrase(testType, font_8));
		c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		c1.setBorder(0);
		table.addCell(c1);
		int hours = 0;
		int minute = 0;
		if (assObj.getDuration() >= 60)
		{
			hours = Math.round(assObj.getDuration() / 60);
		}
		minute = Math.round(assObj.getDuration() % 60);
		c1 = new PdfPCell(new Phrase("Time : " + hours + "." + minute, font_8));
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		c1.setBorder(0);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Grade : " + assObj.getGrade().getName(), font_8));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBorder(0);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Total Question : " + assObj.getTotalQuestion(), font_8));
		c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		c1.setBorder(0);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Total Marks : " + assObj.getTotalMarks(), font_8));
		c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		c1.setColspan(3);
		c1.setBorder(0);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("General Instruction :", font_10_bold));
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		c1.setColspan(3);
		c1.setBorder(0);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("1) " + assObj.getInstruction(), font_9_bold));
		c1.setHorizontalAlignment(Element.ALIGN_LEFT);
		c1.setColspan(3);
		c1.setBorder(0);
		table.addCell(c1);

		if (assObj.getAssessmentQuestion() != null && assObj.getAssessmentQuestion().size() > 0)
		{
			int i = 1;
			for (AssessmentQuestion aq : assObj.getAssessmentQuestion())
			{
				PdfPCell c2 = new PdfPCell(new Phrase("Question : " + i, font_8_bold));
				c2.setHorizontalAlignment(Element.ALIGN_LEFT);
				c2.setColspan(2);
				c2.setBorder(0);
				c2.setPaddingTop(10);
				table1.addCell(c2);
				c2 = new PdfPCell(new Phrase(aq.getQuestion().getDescription() + "?", font_8));
				
				c2.setHorizontalAlignment(Element.ALIGN_LEFT);
				c2.setColspan(2);
				c2.setBorder(0);
				table1.addCell(c2);
				for (Answer a : aq.getQuestion().getAnswers())
				{
					Image image;
					try
					{
						image = Image.getInstance(Utility.resourcesFolderPath + "/images/radio.png");
						c2 = new PdfPCell(image, false);
						c2.setHorizontalAlignment(Element.ALIGN_LEFT);
						c2.setBorder(0);
						table1.addCell(c2);
					}
					catch (Exception e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					c2 = new PdfPCell(new Phrase(a.getDescription(), font_8));
					c2.setHorizontalAlignment(Element.ALIGN_LEFT);
					c2.setBorder(0);
					table1.addCell(c2);
				}
				i++;
			}
		}

		document.add(table);
		document.add(table1);
	}

}
