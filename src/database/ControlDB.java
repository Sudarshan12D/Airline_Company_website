package database;

import java.util.ArrayList;


import Entity.*;

public class ControlDB {
	private static ControlDB object;

	//Array Lists that will store the data


	//Create all the new array lists
	private ControlDB() {

	}

	//SINGLETON PATTERN: Create only ONE instance of ControlDB.
	public static ControlDB getobject() {
		if (object == null) {
			object = new ControlDB();
		}
		return object;
	}

	//Set only instance of ControlDB
	public static void setobject(ControlDB object) {
		ControlDB.object = object;
	}

	//SETTERS AND GETTERS

	
	//ADD FUNCTIONS


	//REMOVE FUNCTIONS


}
