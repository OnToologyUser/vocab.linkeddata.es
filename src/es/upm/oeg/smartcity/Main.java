package es.upm.oeg.smartcity;

import java.io.FileNotFoundException;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BuildOntos buildOntos = new BuildOntos();
		
		BuildDatasets buildDatasets = new BuildDatasets();
		
		try {
			OntologyPages tryGenerateOnto = new OntologyPages(buildOntos.getModel());
			DatasetPages datasetPages = new DatasetPages(buildDatasets.datasets);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
