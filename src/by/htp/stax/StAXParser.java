package by.htp.stax;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import by.htp.family.Child;
import by.htp.family.Children;
import by.htp.family.Family;
import by.htp.family.Father;
import by.htp.family.Mother;
import by.htp.family.Person;

public class StAXParser {

	public static void main(String[] args) throws FileNotFoundException {

		XMLInputFactory inputFactory = XMLInputFactory.newInstance();

		try {
			InputStream input = new FileInputStream("E:\\learn\\workspace\\XML\\resourse\\FamilyTree.xml");
			XMLStreamReader reader = inputFactory.createXMLStreamReader(input);
			List<Family> famList = process(reader);
			for (Family family : famList) {
				System.out.println(family);
			}

		} catch (XMLStreamException e) {
			e.printStackTrace();
		}

	}

	private static List<Family> process(XMLStreamReader reader) throws XMLStreamException {
		List<Family> fam = new ArrayList<Family>();
		Family family = null;
		Person curent = null;
		Mother mother = null;
		Father father = null;
		Children children = null;
		Child child = null;
		FamilyTag elementName = null;
		while (reader.hasNext()) {
			int type = reader.next();
			switch (type) {
			case XMLStreamConstants.START_ELEMENT:
				elementName = FamilyTag.getElementFamiltTag(reader.getLocalName());
				switch (elementName) {
				case FAMILY:
					family = new Family();
					Integer id = Integer.parseInt(reader.getAttributeValue(null, "id"));
					family.setId(id);
					break;
				case FATHER:
					father = new Father();
					curent = father;
					break;
				case MOTHER:
					mother = new Mother();
					curent = mother;
					break;
				case FAMILIES:
					break;
				case CHILDREN:
					children = new Children();
					children.setCount(Integer.parseInt(reader.getAttributeValue(null, "count")));
					break;
				case CHILD:
					child = new Child();
					curent = child;
					String gender = reader.getAttributeValue(null, "gender");
					child.setGender(gender);
					String date = reader.getAttributeValue(null, "date-of-birth");
					child.setDate(date);
					break;
				}
				break;

			case XMLStreamConstants.CHARACTERS:
				String text = reader.getText().trim();
				if (text.isEmpty()) {
					break;
				}
				switch (elementName) {
				case NAME:
					curent.setName(text);
					break;
				case SURNAME:
					curent.setSurname(text);
					break;
				case AGE:
					curent.setAge(Integer.valueOf(text));
					break;
				case MAIDEN_NAME:
					mother.setMaiden_name(text);
					break;
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				elementName = FamilyTag.getElementFamiltTag(reader.getLocalName());
				switch (elementName) {
				case MOTHER:
					family.setMother(mother);
				case FATHER:
					family.setFather(father);
				case CHILDREN:
					family.setChildren(children);
					break;
				case CHILD:
					family.addChild(child);
					break;
				case FAMILY:
					fam.add(family);
				}
			}
		}
		return fam;
	}
}