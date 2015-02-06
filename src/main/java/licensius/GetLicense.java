/*
 * Copyright 2012-2013 Ontology Engineering Group, Universidad Polit�cnica de Madrid, Spain
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package licensius;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 *
 * @author Victor Rodriguez Doncel
 */
public class GetLicense {
    
    
    //@Dani: this method can be improved with http client.
 /**
     * Invokes the getLicense method of the Licensius service, so that
     * the license in a RDF resource is found.
     * @param uriToScan URI to scan, for example http://purl.org/goodrelations/v1.owl
     */
    public static String getLicense(String uriToScan) {
        String output="unknown";
        try {
            String uri="http://licensius.appspot.com/getLicense?content=";
            String encodedData = URLEncoder.encode(uriToScan);
            uri+=encodedData;
            URL url = new URL(uri);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Length", String.valueOf(encodedData.length()));
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("HTTP error code : "+ conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String linea = "";
            while ((linea = br.readLine()) != null) {
                output=linea;
            }
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String license=getLicense("http://purl.org/goodrelations/v1.owl");
        System.out.println(license);
    }
}
    
