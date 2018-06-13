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

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * File Name Font
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-06-11
 * EMAIL     playboxjre@Gmail.com
 */
public class Font {
    Logger logger = LoggerFactory.getLogger(Font.class);
    private String fontName;
    private String fontFamilyPath;
    private PDFont font;
    private int fontSize;
    private float charsetSpace;

    public Font(String fontFamilyPath) throws IOException {
        this.fontFamilyPath = fontFamilyPath;
        File file = new File(this.fontFamilyPath);
        this.fontName=file.getName();
        font = PDType0Font.load(new PDDocument(), file);
    }

    public Font(String fontName,InputStream fontInputStream) throws IOException {
        this.fontName = fontName;
        font = PDType0Font.load(new PDDocument(),fontInputStream);
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public PDFont getFont() {
        return font;
    }

    public void setFont(PDFont font) {
        this.font = font;
        this.fontFamilyPath = font.getName();
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public float getCharsetSpace() {
        return charsetSpace;
    }

    public void setCharsetSpace(float charsetSpace) {
        this.charsetSpace = charsetSpace;
    }
}
