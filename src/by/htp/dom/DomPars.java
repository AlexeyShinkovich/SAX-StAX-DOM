package by.htp.dom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import by.htp.family.Child;
import by.htp.family.Children;
import by.htp.family.Family;
import by.htp.family.Father;
import by.htp.family.Mother;
import by.htp.family.Person;

public class DomPars {

	private static Element element;
	private static Element familis;
	private static Family family;
	private static List<Family> fam = new ArrayList<Family>();
	private static Person curent;
	private static Mother mother = null;
	private static Father father = null;
	private static Children children = null;
	private static Child child = null;

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {

		DOMParser parser = new DOMParser();
		parser.parse("E:\\learn\\workspace\\XML\\resourse\\FamilyTree.xml");
		Document document = parser.getDocument();
		Element root = document.getDocumentElement();
		NodeList nodeList = root.getChildNodes();

		for (int i = 1; i < nodeList.getLength(); i = i + 2) {
			Node node = nodeList.item(i);
			element = (Element) node;
			getFamily(element);
			fam.add(family);
		}
		for (Family fam2 : fam) {
			System.out.println(fam2);
		}
	}

	private static Family getFamily(Element element) {
		if (element.getTagName().equals("family")) {
			family = new Family();
			family.setId(Integer.parseInt(element.getAttribute("id")));
			NodeList familyChild = element.getChildNodes();
			for (int k = 1; k < familyChild.getLength(); k = k + 2) {
				Node nodeFam = familyChild.item(k);
				familis = (Element) nodeFam;
				if (familis.getTagName().equals("mother")) {
					mother = new Mother();
					curent = mother;
					getMember(familis);
					family.setMother(mother);
				} else if (familis.getTagName().equals("father")) {
					father = new Father();
					curent = father;
					getMember(familis);
					family.setFather(father);
				} else if (familis.getTagName().equals("children")) {
					children = new Children();
					children.setCount(Integer.parseInt(familis.getAttribute("count")));
					family.setChildren(children);
					NodeList children = familis.getChildNodes();
					for (int i = 1; i < children.getLength(); i = i + 2) {
						Node nodeChild = children.item(i);
						Element childMem = (Element) nodeChild;
						child = new Child();
						curent = child;
						child.setGender(childMem.getAttribute("gender"));
						child.setDate(childMem.getAttribute("date-of-birth"));
						getMember(childMem);
						family.addChild(child);
					}
				}
			}
		}
		return family;
	}

	private static void getMember(Element familis) {
		NodeList nodeMember = familis.getChildNodes();
		for (int j = 1; j < nodeMember.getLength(); j = j + 2) {
			Node nodeM = nodeMember.item(j);
			Element elementMemeber = (Element) nodeM;
			if (elementMemeber.getTagName().equals("name")) {
				curent.setName(elementMemeber.getTextContent());
			} else if (elementMemeber.getTagName().equals("surname")) {
				curent.setSurname(elementMemeber.getTextContent());
			} else if (elementMemeber.getTagName().equals("maiden-name")) {
				mother.setMaiden_name(elementMemeber.getTextContent());
			} else if (elementMemeber.getTagName().equals("age")) {
				curent.setAge(Integer.parseInt(elementMemeber.getTextContent()));
			}
		}
	}
}