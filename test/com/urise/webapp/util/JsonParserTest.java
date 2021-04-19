package com.urise.webapp.util;

import com.urise.webapp.ResumeTestData;
import com.urise.webapp.model.Resume;
import com.urise.webapp.model.Section;
import com.urise.webapp.model.TextSection;
import org.junit.Assert;
import org.junit.Test;

public class JsonParserTest {
    @Test
    public void testResume() throws Exception {
        Resume resume1 = ResumeTestData.defaultResume("uuid1", "fullName");
        String json = JsonParser.write(resume1);
        System.out.println(json);
        Resume resume = JsonParser.read(json, Resume.class);
        Assert.assertEquals(resume1, resume);
    }

    @Test
    public void write() throws Exception {
        Section section1 = new TextSection("Objective1");
        String json = JsonParser.write(section1, Section.class);
        System.out.println(json);
        Section section2 = JsonParser.read(json, Section.class);
        Assert.assertEquals(section1, section2);
    }
}
