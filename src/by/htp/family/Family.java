package by.htp.family;

import java.util.ArrayList;
import java.util.List;

public class Family {
	private Mother mother;
	private Father father;
	private Children children;
	private List<Child> childrens = new ArrayList<Child>();
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Mother getMother() {
		return mother;
	}

	public void setMother(Mother mother) {
		this.mother = mother;
	}

	public Father getFather() {
		return father;
	}

	public void setFather(Father father) {
		this.father = father;
	}

	public void addChild(Child ch) {
		this.childrens.add(ch);
	}

	public Children getChildren() {
		return children;
	}

	public void setChildren(Children children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "Family id = " + id + " mother=" + mother + ", father=" + father + ", children " + children + ", child="
				+ childrens.get(0) + ", child= " + childrens.get(1);
	}

}
