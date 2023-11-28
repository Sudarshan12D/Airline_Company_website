# Run this python script to run the java gui

import os


#add run commands to easily compile and run.
#os.system("javac -cp .;lib/mysql-connector-java-8.0.23/mysql-connector-java-8.0.23.jar edu/ucalgary/oop/Animal.java edu/ucalgary/oop/Clinic.java edu/ucalgary/oop/SpeciesData.java edu/ucalgary/oop/SQLData.java edu/ucalgary/oop/Task.java edu/ucalgary/oop/TimeSlot.java edu/ucalgary/oop/Treatment.java edu/ucalgary/oop/gui.java")
#os.system("java -cp .;lib/mysql-connector-java-8.0.23/mysql-connector-java-8.0.23.jar edu/ucalgary/oop/gui")
os.system("javac *.java")
os.system("java MainTestFile")