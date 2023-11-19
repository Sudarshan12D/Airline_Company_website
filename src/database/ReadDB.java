/*
 * ENSF 480: Term Project - Movie App
 * 2022-12-05
 * Authors: Group 9-L01
 * Version: FINAL
 */

package database;

import Entity.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStreamReader;


public class ReadDB{
	ControlDB database_control;
	

	public ReadDB() {
		database_control = ControlDB.getobject();
	}

	public void loadDatabase() throws IOException{
		
	}
	public void gettingUsers() throws IOException{

		FileInputStream fstream = new FileInputStream("Data/RU.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		String line;
		String[] argList = new String[10];

		while((line = br.readLine()) != null) {
			argList = line.split(";");
			if(argList[0].compareTo("") == 0){
				break;
			}

			Date foundDate = new Date(Integer.parseInt(argList[7]),Integer.parseInt(argList[8]),Integer.parseInt(argList[9]));
			database_control.addUser(new RegisteredUser(Integer.parseInt(argList[0]),argList[1],argList[2],argList[3],argList[4],argList[5],database_control.searchBankingInfo(Integer.parseInt(argList[6])),foundDate));
		}
		fstream.close();
	}



	
}
