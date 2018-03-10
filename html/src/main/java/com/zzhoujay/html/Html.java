package com.zzhoujay.html;

import android.text.SpannableStringBuilder;
import android.util.Log;

import org.ccil.cowan.tagsoup.HTMLSchema;
import org.ccil.cowan.tagsoup.Parser;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by zz on 2018/1/20 0020.
 */

public class Html {

    public static void fromHtml(String source) {
        Parser parser = new Parser();

        try {
            parser.setProperty(Parser.schemaProperty, new HTMLSchema());
        } catch (SAXNotRecognizedException e) {
            e.printStackTrace();
        } catch (SAXNotSupportedException e) {
            e.printStackTrace();
        }

        parser.setContentHandler(new HtmlHandler());
        try {
            parser.parse(new InputSource(new StringReader(source)));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    private static class HtmlHandler implements ContentHandler {

        private static final String TAG = "HtmlHandler";

        private static final String TAG_H1 = "h1";
        private static final String TAG_H2 = "h2";
        private static final String TAG_H3 = "h3";
        private static final String TAG_H4 = "h4";
        private static final String TAG_H5 = "h5";

        private SpannableStringBuilder ssb;

        @Override
        public void setDocumentLocator(Locator locator) {
            Log.d(TAG, "setDocumentLocator() called with: locator = [" + locator + "]");
        }

        @Override
        public void startDocument() throws SAXException {
            Log.d(TAG, "startDocument() called");
            ssb = new SpannableStringBuilder();
        }

        @Override
        public void endDocument() throws SAXException {
            Log.d(TAG, "endDocument() called");
        }

        @Override
        public void startPrefixMapping(String prefix, String uri) throws SAXException {
            Log.d(TAG, "startPrefixMapping() called with: prefix = [" + prefix + "], uri = [" + uri + "]");
        }

        @Override
        public void endPrefixMapping(String prefix) throws SAXException {
            Log.d(TAG, "endPrefixMapping() called with: prefix = [" + prefix + "]");
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
            Log.d(TAG, "startElement() called with: uri = [" + uri + "], localName = [" + localName + "], qName = [" + qName + "], atts = [" + atts + "]");
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            Log.d(TAG, "endElement() called with: uri = [" + uri + "], localName = [" + localName + "], qName = [" + qName + "]");
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            Log.d(TAG, "characters() called with: ch = [" + new String(ch) + "], start = [" + start + "], length = [" + length + "]");
        }

        @Override
        public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
            Log.d(TAG, "ignorableWhitespace() called with: ch = [" + new String(ch) + "], start = [" + start + "], length = [" + length + "]");
        }

        @Override
        public void processingInstruction(String target, String data) throws SAXException {
            Log.d(TAG, "processingInstruction() called with: target = [" + target + "], data = [" + data + "]");
        }

        @Override
        public void skippedEntity(String name) throws SAXException {
            Log.d(TAG, "skippedEntity() called with: name = [" + name + "]");
        }
    }

}
