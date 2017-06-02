package by.htp.sax;

import java.io.IOException;
import java.util.List;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import by.htp.family.Family;

public class Main {

	public static void main(String[] args) {

		HandlerSax handler = new HandlerSax();
		XMLReader reader;
		try {
			reader = XMLReaderFactory.createXMLReader();
			reader.setContentHandler(handler);
			reader.parse(new InputSource(new java.io.FileInputStream(
					new java.io.File("E:\\learn\\workspace\\XML\\resourse\\FamilyTree.xml"))));

		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<Family> familys = handler.getFamList();
		for (Family family2 : familys) {
			System.out.println(family2);
		}
	}
}
