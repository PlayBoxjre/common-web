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

package com.kong.support.toolboxes.pdf;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * File Name PDFTool
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-06-11
 * EMAIL     playboxjre@Gmail.com
 * <h1>PDF 工具</h1>
 * <p>使用的是apache pdfbox library</p>
 * <pre>
 *     <dependency>
 *          <groupId>org.apache.pdfbox</groupId>
 *          <artifactId>pdfbox</artifactId>
 *          <version>2.0.4</version>
 *      </dependency>
 * </pre>
 * <pre>
 *     PDFBox和Java 8
 *      使用PDFBox和Java 8时的重要通知
 *
 *      由于Java颜色管理模块向“LittleCMS”方向发生变化，用户在颜色操作中可能会遇到性能下降的问题。一种解决方案是通过以下方式禁用LittleCMS以支持旧的KCMS（柯达色彩管理系统）：
 *
 *      以-Dsun.java2d.cmm=sun.java2d.cmm.kcms.KcmsServiceProvider或开头
 *      调用 System.setProperty("sun.java2d.cmm", "sun.java2d.cmm.kcms.KcmsServiceProvider")
 *      来源：
 * https ：//bugs.openjdk.java.net/browse/JDK-8041125
 *
 * 渲染性能
 * 自PDFBox 2.0.4以来
 *
 * PDFBox 2.0.4引入了新的命令行设置
 *
 *  -Dorg.apache.pdfbox.rendering.UsePureJavaCMYKConversion=true
 * 这可能会提高在某些系统上渲染PDF的性能，尤其是在页面上有很多图像的情况下
 * </pre>
 */
public class PDFTool {
    Logger logger = LoggerFactory.getLogger(PDFTool.class);




    /**
     * 创建pdf
     */
    public static void createTextPDF() throws IOException {
        PDDocument pdDocument =  null;
        PDPage pdPage = null;

        pdDocument = new PDDocument();
        pdPage = new PDPage(PDRectangle.A4);
        pdDocument.addPage(pdPage);
        PDFont font = PDType1Font.HELVETICA_BOLD;
        PDPageContentStream contentStream = new PDPageContentStream(pdDocument,pdPage);
        contentStream.beginText();
        contentStream.setFont(font,12);
        contentStream.moveTo(100,700);
        contentStream.showText("hello world");
        contentStream.endText();
        contentStream.close();
        pdDocument.save("");

    }



}
