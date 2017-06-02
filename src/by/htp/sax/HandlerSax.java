package by.htp.sax;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import by.htp.family.Child;
import by.htp.family.Children;
import by.htp.family.Family;
import by.htp.family.FamilyTag;
import by.htp.family.Father;
import by.htp.family.Mother;
import by.htp.family.Person;

public class HandlerSax extends DefaultHandler {

	private List<Family> famList = new ArrayList<Family>();
	private Family family;
	private StringBuilder text;
	private Person curent;
	private Mother mother;
	private Father father;
	private Child child;
	private Children children;

	public List<Family> getFamList() {
		return famList;
	}

	@Override
	public void startDocument() throws SAXException {
		System.out.println("Start parsing...");
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println("End parsing...");
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		text = new StringBuilder();
		if (qName.equals("family")) {
			family = new Family();
			family.setId(Integer.parseInt(attributes.getValue("id")));
		} else if (qName.equals("mother")) {
			mother = new Mother();
			curent = mother;
		} else if (qName.equals("father")) {
			father = new Father();
			curent = father;
		} else if (qName.equals("children")) {
			children = new Children();
			children.setCount(Integer.parseInt(attributes.getValue("count")));
		} else if (qName.equals("child")) {
			child = new Child();
			curent = child;
			child.setGender(attributes.getValue("gender"));
			child.setDate(attributes.getValue("date-of-birth"));

		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		text.append(ch, start, length);

	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		FamilyTag tagName = FamilyTag.valueOf(qName.toUpperCase().replace("-", "_"));

		switch (tagName) {

		case NAME:
			curent.setName(text.toString());
			break;
		case SURNAME:
			curent.setSurname(text.toString());
			break;
		case AGE:
			curent.setAge(Integer.valueOf(text.toString()));
			break;
		case MAIDEN_NAME:
			mother.setMaiden_name(text.toString());
			break;

		case MOTHER:
			family.setMother(mother);
			break;
		case FATHER:
			family.setFather(father);
			break;
		case CHILDREN:
			family.setChildren(children);
			break;
		case CHILD:
			family.addChild(child);
			break;
		case FAMILY:
			famList.add(family);
			family = null;
			child = null;
			break;
		default:
			break;
		}
	}
}