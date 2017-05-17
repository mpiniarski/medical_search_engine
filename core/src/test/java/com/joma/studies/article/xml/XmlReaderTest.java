package com.joma.studies.article.xml;

import com.joma.studies.article.dto.ArticleDto;
import com.joma.studies.article.xml.exception.InvalidXmlException;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class XmlReaderTest {

    private Charset CHARSET = Charset.forName("UTF-8");
    private XmlReader xmlReader;
    private List<ArticleDto> result;

    @Before
    public void setUp() throws Exception {
        xmlReader = new XmlReader(new ArticleMapper());
        result = new ArrayList<>();
        xmlReader.addObserver((observable, item) -> result.add(item));
    }

    @Test(expected = InvalidXmlException.class)
    public void whenEmptyString_shouldThrowException() throws Exception {
        String inputXml = "";
        xmlReader.read(toInputStream(inputXml));
    }

    @Test(expected = InvalidXmlException.class)
    public void whenInvalidXml_shouldThrowException() throws Exception {
        String inputXml = "<dasdas><dasd>";
        xmlReader.read(toInputStream(inputXml));
    }

    @Test
    public void whenEmptyXml_shouldReturnEmptyList() throws Exception {
        String inputXml = "<a></a>";
        xmlReader.read(toInputStream(inputXml));
        assertTrue(result.isEmpty());
    }

    @Test
    public void whenContainsOneArticle_shouldReturnListWithOneArticle() throws Exception {
        InputStream inputStreamXml = getClass().getClassLoader()
                .getResourceAsStream("xml/singleArticle.xml");
        xmlReader.read(inputStreamXml);
        assertTrue(result.size() == 1);
        ArticleDto articleDto = result.get(0);
        assertEquals("Letter to Editor. Depression and cytokines - a different perspective. Author's response.",
                articleDto.getTitle());
        assertEquals("no summary.",
                articleDto.getAbstractText());
    }

    @Test
    public void whenContainsArticleWithMissingValues_shouldNotCreateIt() throws Exception {
        InputStream inputStreamXml = getClass().getClassLoader()
                .getResourceAsStream("xml/singleArticleWithoutTitle.xml");
        xmlReader.read(inputStreamXml);
        assertTrue(result.size() == 0);
    }

    @Test
    public void whenContainsArticleWithMultipleValues_shouldNotCreateIt() throws Exception {
        InputStream inputStreamXml = getClass().getClassLoader()
                .getResourceAsStream("xml/singleArticleWithoutTitle.xml");
        xmlReader.read(inputStreamXml);
        assertTrue(result.size() == 0);
    }

    private InputStream toInputStream(String inputXml) {
        return new ByteArrayInputStream(inputXml.getBytes(CHARSET));
    }
}
