/*
 * Copyright [2018] [ kong&xiang ]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kong.support.toolbox;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.util.Matrix;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * File Name PDFToolTest
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-06-11
 * EMAIL     playboxjre@Gmail.com
 */
public class PDFToolTest {
    Logger logger = LoggerFactory.getLogger(PDFToolTest.class);

    @Test
    public void testCreatePDF() throws IOException {
        PDDocument pdDocument =  null;
        PDPage pdPage = null;

        pdDocument = new PDDocument();
        pdPage = new PDPage(PDRectangle.A4);

        pdDocument.addPage(pdPage);
        PDType0Font load = PDType0Font.load(pdDocument, new File("/Users/lantoev/Documents/git/github/me/common-web/kongsupports/src/test/resources/new.ttf"));
        PDFont font = load;//PDType1Font.COURIER_BOLD;
        //byte[] stream = font.encode("我叫孔翔 那是谁");
        PDPageContentStream contentStream = new PDPageContentStream(pdDocument,pdPage);
        contentStream.beginText();

        //SetLineCapStyle setLineCapStyle = new SetLineCapStyle();
        float fontSize = 12.0f;
        // contentStream.setLineWidth(PDRectangle.A4.getWidth());
        contentStream.setFont(font,fontSize);
        float height = PDRectangle.A4.getHeight();
        logger.debug("a4 {} : {} ",PDRectangle.A4.getWidth(),height);

        String line = "";
        int lines = 0;
        for (int i = 1,j= 0 ;i < 200;i++,j++){
            line += i +"";
            String newline = line;
            float stringWidth = font.getStringWidth(line)/1000f;
            float charHeight = font.getFontDescriptor().getFontBoundingBox().getHeight()/1000f;
            logger.debug("  string width : {} height :{}",stringWidth,charHeight);
            float messageSize = fontSize * stringWidth;
            logger.debug(" char size {}",messageSize);
            //if (stringWidth)
            PDRectangle mediaBox = pdPage.getMediaBox();
            float pageHeight = mediaBox.getHeight();
            final float pageWidth = mediaBox.getWidth();
            logger.debug("page width :{} page height :{}",pageWidth,pageHeight);
            while (true){
                float space = fontSize * 4;
                if(messageSize > pageWidth -space) {
                    if (newline.length() == 0)
                        break;
                    int num = (int) ((pageWidth - space) / (messageSize / line.length()));
                    if (num > newline.length())
                        num = newline.length();
                    String fillLine = newline.substring(0, num);
                    newline = newline.substring(num);
                    messageSize = font.getStringWidth(newline) / 1000f * fontSize;
                    float lineHeight = pageHeight - charHeight * fontSize * (j + lines);
                    if (lineHeight <= 0) {
                        contentStream.endText();
                        contentStream.close();
                        pdPage = new PDPage(PDRectangle.A4);
                        pdDocument.addPage(pdPage);
                        j = 0;
                        lines = 0;
                        contentStream = new PDPageContentStream(pdDocument, pdPage, PDPageContentStream.AppendMode.OVERWRITE, false);
                        contentStream.beginText();
                        contentStream.setFont(font, fontSize);
                    }
                    contentStream.setTextMatrix(Matrix.getTranslateInstance(0, lineHeight));
                    contentStream.showText(i +"---" + fillLine);
                    lines += 1;
                }else{
                    float lineHeight = pageHeight - charHeight * fontSize *(j+lines) ;
                    logger.debug(" current line position = {}",lineHeight);
                    contentStream.setTextMatrix(Matrix.getTranslateInstance(0,lineHeight));

                    contentStream.showText(i+ "-- "+ newline);
                    if (  lineHeight <=0 ){
                        contentStream.endText();
                        contentStream.close();
                        pdPage = new PDPage(PDRectangle.A4);
                        pdDocument.addPage(pdPage);
                        j=0;
                        lines = 0;
                        contentStream = new PDPageContentStream(pdDocument, pdPage, PDPageContentStream.AppendMode.OVERWRITE, false);
                        contentStream.beginText();
                        contentStream.setFont(font,fontSize);

                    }
                    break;
                }
            }
        }



        logger.debug(" height :{}",height);
        contentStream.newLineAtOffset(10,799);
        contentStream.showText("-\u0001\\&lt;我叫孔翔 那是谁cccc\nhellow rhtosi我却让连接阿sfgsfdgsdfregsfgsfdsdfsdf个世界的哦\n..。哦恶化");
        contentStream.newLine();
        contentStream.setNonStrokingColor(120,42,123);
        contentStream.setFont(font,18);
        contentStream.showText("-\u0001\\&lt;我叫孔翔 那是谁cccc\nhellow rhtosi我却让连接阿sfgsfdgsdfregsfgsfdsdfsdf个世界的哦\n..。哦恶化");

        contentStream.endText();
        contentStream.close();
        pdDocument.save("/Users/lantoev/Documents/git/github/me/common-web/kongsupports/target/hello.pdf");
        pdDocument.close();
    }

    @Test
    public  void test1() throws IOException {
        //doIt("hellwowoowwow","/Users/lantoev/Documents/git/github/me/common-web/kongsupports/target/t1.pdf");
        PDDocument doc = new PDDocument();
        PDPage pdPage = new PDPage();
        doc.addPage(pdPage);


        PDImageXObject imageXObject = PDImageXObject.createFromFile(
                "/Users/lantoev/Documents/git/github/me/common-web/kongsupports/src/test/resources/IMG_0321.JPG",doc);
        PDPageContentStream contentStream = new PDPageContentStream(doc,pdPage  );

        contentStream.drawImage(imageXObject,Matrix.getScaleInstance((float) (0.4 * imageXObject.getWidth()), (float) (0.4*imageXObject.getHeight())));

        contentStream.close();
        doc.save("/Users/lantoev/Documents/git/github/me/common-web/kongsupports/target/image.pdf");
        doc.close();
    }



    public void doIt( String message, String  outfile ) throws IOException
    {
        // the document
        PDDocument doc = null;
        try
        {
            doc = new PDDocument();

            // Page 1
            PDFont font = PDType1Font.HELVETICA;
            PDPage page = new PDPage(PDRectangle.A4);
            doc.addPage(page);
            float fontSize = 12.0f;

            PDRectangle pageSize = page.getMediaBox();
            float centeredXPosition = (pageSize.getWidth() - fontSize/1000f)/2f;
            float stringWidth = font.getStringWidth( message );
            float centeredYPosition = (pageSize.getHeight() - (stringWidth*fontSize)/1000f)/3f;

            PDPageContentStream contentStream = new PDPageContentStream(doc, page, PDPageContentStream.AppendMode.OVERWRITE, false);
            contentStream.setFont( font, fontSize );
            contentStream.beginText();
            // counterclockwise rotation
            for (int i=0;i<8;i++)
            {
                contentStream.setTextMatrix(Matrix.getRotateInstance(i * Math.PI * 0.25,
                        centeredXPosition, pageSize.getHeight() - centeredYPosition));
                contentStream.showText(message + " " + i);
            }
            // clockwise rotation
            for (int i=0;i<8;i++)
            {
                contentStream.setTextMatrix(Matrix.getRotateInstance(-i*Math.PI*0.25,
                        centeredXPosition, centeredYPosition));
                contentStream.showText(message + " " + i);
            }

            contentStream.endText();
            contentStream.close();

            // Page 2
            page = new PDPage(PDRectangle.A4);
            doc.addPage(page);
            fontSize = 1.0f;

            contentStream = new PDPageContentStream(doc, page, PDPageContentStream.AppendMode.OVERWRITE, false);
            contentStream.setFont( font, fontSize );
            contentStream.beginText();

            // text scaling and translation
            for (int i=0;i<10;i++)
            {
                contentStream.setTextMatrix(new Matrix(12 + (i * 6), 0, 0, 12+(i*6), 100, 100+i*50));
                contentStream.showText(message + " " + i);
            }
            contentStream.endText();
            contentStream.close();

            // Page 3
            page = new PDPage(PDRectangle.A4);
            doc.addPage(page);
            fontSize = 1.0f;

            contentStream = new PDPageContentStream(doc, page, PDPageContentStream.AppendMode.OVERWRITE, false);
            contentStream.setFont( font, fontSize );
            contentStream.beginText();

            int i = 0;
            // text scaling combined with rotation
            contentStream.setTextMatrix(new Matrix(12, 0, 0, 12, centeredXPosition, centeredYPosition*1.5f));
            contentStream.showText(message + " 12 0 0 12" + i++);

            contentStream.setTextMatrix(new Matrix(0, 18, -18, 0, centeredXPosition, centeredYPosition*1.5f));
            contentStream.showText(message + " " + i++);

            contentStream.setTextMatrix(new Matrix(-24, 0, 0, -24, centeredXPosition, centeredYPosition*1.5f));
            contentStream.showText(message + " " + i++);

            contentStream.setTextMatrix(new Matrix(0, -30, 30, 0, centeredXPosition, centeredYPosition*1.5f));
            contentStream.showText(message + " " + i++);

            contentStream.endText();
            contentStream.close();

            doc.save( outfile );
        }
        finally
        {
            if( doc != null )
            {
                doc.close();
            }
        }
    }
}
