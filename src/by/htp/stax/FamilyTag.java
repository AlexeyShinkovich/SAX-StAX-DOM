package by.htp.stax;

public enum FamilyTag {
	NAME, SURNAME, AGE, FATHER, MOTHER, CHILD, FAMILY, MAIDEN_NAME, CHILDREN, FAMILIES;
	
	public static FamilyTag getElementFamiltTag(String element){
		
		switch(element){
		case "name":
			return NAME;
		case "surname":
			return SURNAME;
		case "age":
			return AGE;
		case "father":
			return FATHER;
		case "mother":
			return MOTHER;
		case "child":
			return CHILD;
		case "family":
			return FAMILY;
		case "maiden-name":
			return MAIDEN_NAME;
		case "children":
			return CHILDREN;
		case "families":
			return FAMILIES;
			default:
			throw new EnumConstantNotPresentException(FamilyTag.class, element);
		}
	}
}
