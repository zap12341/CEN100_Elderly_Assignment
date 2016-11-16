import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
public class DB {
	int lines = 0;
	HashMap patient = new HashMap();
	static String username;
	
	public void addPatient(String name) throws IOException{
		UUID id = UUID.randomUUID();
		patient.put(name,id);
		PatientFile();
	}
	
	public Set returnPatientSet(){
		Set set = patient.entrySet();
		return set;
	}
	
	public void PatientFile() throws IOException{
		try{
			FileWriter txt=new FileWriter(("Patients.txt"), true);
			PrintWriter pw=new PrintWriter(txt);
			BufferedReader reader=new BufferedReader(new FileReader("Patients.txt"));
			while(reader.readLine()!=null){
				lines++;
			}
			Set patients = returnPatientSet();
			Iterator i = patients.iterator();
			while(i.hasNext()){
				Map.Entry me = (Map.Entry)i.next();
				String id = me.getValue().toString();
				if(!searchList(id)){
					pw.println("Name: "+me.getKey()+" ID: "+me.getValue());
					System.out.println("Master List Updated");
				}
			}
			pw.close();
			reader.close();
		} catch(IOException e){
			System.out.println("ERROR READING FILE! CREATING FILE");
			File file=new File("Patients.txt");
		}
	}
	
	public Set returnPatients() throws IOException{
		Set set = patient.entrySet();
		try{

		FileWriter txt=new FileWriter(("Patients.txt"), true);
		BufferedReader reader=new BufferedReader(new FileReader("Patients.txt"));
		String eh=reader.readLine();
		StringTokenizer t = new StringTokenizer(eh);
		while(reader.readLine()!=null){
			if (t.nextToken().equalsIgnoreCase("Name:")){
				String name = t.nextToken();
				if(t.nextToken().equalsIgnoreCase("ID:")){
					String id = t.nextToken();
					patient.put(name,id);
				}

			}
		}
		txt.close();
		reader.close();
		return set;

		}catch(IOException e){
			System.out.println("ERROR!");
			return set;
		}
	}
	
	public String returnName(){
		return username;
	}
	
	public boolean searchList(String id) throws IOException{
		boolean found = false;
		try{

			FileWriter txt=new FileWriter(("Patients.txt"), true);
			BufferedReader reader=new BufferedReader(new FileReader("Patients.txt"));
			String eh=reader.readLine();
			StringTokenizer t = new StringTokenizer(eh);
			StringTokenizer u = new StringTokenizer(eh);
			while(reader.readLine()!=null){
				if(u.nextToken().equalsIgnoreCase("Name:")){
					username = u.nextToken();
				}
				if(t.nextToken().equalsIgnoreCase("ID:")){
					if(t.nextToken().equalsIgnoreCase(id)){
						found = true;
					}
				}
					
					
			}
		}catch(IOException e){
			System.out.println("ERROR!");
		}
		if(!found){
			username = "";
		}
		return found;
	}

}
