package com.urise.webapp.storage.serialize;

import com.urise.webapp.model.*;
import com.urise.webapp.util.XmlParser;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class ObjectXmlImpl implements IOStrategy {

    private XmlParser xmlParser;

    public ObjectXmlImpl() {
        this.xmlParser = new XmlParser(ListSection.class,TextSection.class, Section.class,Resume.class, OrganizationSection.class, Link.class, Organization.class, Organization.Period.class);
    }

    @Override
    public void writeObj(Resume r, OutputStream os) throws IOException {
        try (Writer w = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
            xmlParser.marshall(r, w);
        }
    }

    @Override
    public Resume readObj(InputStream is) throws IOException {
        try (Reader r = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            return xmlParser.unmarshall(r);
        }
    }
}
