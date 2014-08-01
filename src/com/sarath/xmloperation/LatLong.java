/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sarath.xmloperation;

/**
 *
 * @author sarath
 */
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sarath.xmloperation.JsonPack.JsonPojo;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import org.apache.commons.io.IOUtils;
//import org.codehaus.jackson.map.ObjectMapper;

import com.sarath.xmloperation.JsonPack.*;
/**
 * This class will get the lat long values.
 * @author SANTHOSH REDDY MANDADI
 * @version 1.0
 * @since 20-Sep-2012
 */
public class LatLong
{
  private static final String URL = "http://maps.googleapis.com/maps/api/geocode/json";

 /*
  * Here the fullAddress String is in format like
  * "address,city,state,zipcode". Here address means "street number + route"
  * .
  */
 public JsonPojo convertToLatLong(String fullAddress) throws IOException {

 
  URL url = new URL(URL + "?address="+ URLEncoder.encode(fullAddress, "UTF-8") + "&sensor=false");
  // Open the Connection
  URLConnection conn = url.openConnection();

  InputStream in = conn.getInputStream() ;
  ObjectMapper mapper = new ObjectMapper();
     JsonPojo response = (JsonPojo)mapper.readValue(in,JsonPojo.class);
  in.close();
  return response;
 }
 
 
 
 public JsonPojo convertFromLatLong(String latlongString) throws IOException {

  URL url = new URL(URL + "?latlng="
    + URLEncoder.encode(latlongString, "UTF-8") + "&sensor=false");
  // Open the Connection
  URLConnection conn = url.openConnection();

  InputStream in = conn.getInputStream() ;
  ObjectMapper mapper = new ObjectMapper();
  JsonPojo response = (JsonPojo)mapper.readValue(in,JsonPojo.class);
  in.close();
  return response;
  

 }


 
 
 
 
 
 public static void main(String[] args) throws IOException {
  
  JsonPojo res = new LatLong().convertToLatLong("Apollo Bunder,Mumbai ,Maharashtra, India");
  if(res.getStatus().equals("OK"))
  {
   for(Results result : res.getResults())
   {
    System.out.println("Lattitude of address is :"  +result.getGeometry().getLocation().getLat());
    System.out.println("Longitude of address is :" + result.getGeometry().getLocation().getLng());
    System.out.println("Location is " + result.getGeometry().getLocation_type());
   }
  }
  else
  {
   System.out.println(res.getStatus());
  }
  
  System.out.println("\n");
  JsonPojo res1 = new LatLong().convertFromLatLong("18.92038860,72.83013059999999");
  if(res1.getStatus().equals("OK"))
  {
   for(Results result : res1.getResults())
   {
    System.out.println("address is :"  +result.getFormatted_address());
   }
  }
  else
  {
   System.out.println(res1.getStatus());
  }
  
  
  
  
  
 }
}