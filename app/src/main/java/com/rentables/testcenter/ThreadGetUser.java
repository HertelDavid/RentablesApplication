package com.rentables.testcenter;

//Thread for getting information from the server and storing it in the designated User object.

import android.util.JsonReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import dataobjects.User;

public class ThreadGetUser extends NotifyingThread {

    private String GET_USER = "http://10.0.2.2:8080/users/userInfo/";
    private User currentUser;

    public ThreadGetUser(User theUser){

        currentUser = theUser;
    }

    @Override
    public void doRun(){

        //Run method implemented when start() is called.
        parseResponse(getResponse());
    }

    public JsonReader getResponse(){

        try{

            //Opening connection to the server and making it http.
            URL url = new URL(GET_USER + currentUser.getUserId());
            HttpURLConnection connect = (HttpURLConnection) url.openConnection();

            //Setting the properties of this connection
            connect.setRequestMethod("GET");
            connect.setRequestProperty("Accept", "application/json");

            if(connect.getResponseCode() != 200){

                throw new RuntimeException("HTTP response code: " + connect.getResponseCode());
            }

            return new JsonReader(new InputStreamReader(connect.getInputStream(), "UTF-8"));


        }catch(MalformedURLException malform){

            System.out.println("Error(malform): " + malform.toString());

        }catch(IOException IO){

            //TODO Handle the IO exception in a graceful manner.
            System.out.println("Error(IO): " + IO.toString());

        }catch(RuntimeException runtime){

            System.out.println("Error(runtime): " + runtime.toString());
        }

        return null;
    }

    public void parseResponse(JsonReader reader){

        try{

            reader.beginObject();
            while(reader.hasNext()){
                String name = reader.nextName();
                if(name.equals("id")){
                    reader.skipValue();
                }else if(name.equals("username")){
                    currentUser.setUsername(reader.nextString());
                }else if(name.equals("firstName")){
                    currentUser.setFirstName(reader.nextString());
                }else if(name.equals("lastName")){
                    currentUser.setLastName(reader.nextString());
                }else if(name.equals("createDate")){
                    currentUser.setCreateDate(reader.nextString());
                }else if(name.equals("lastEditDate")){
                    currentUser.setLastEditDate(reader.nextString());
                }else if(name.equals("active")){
                    currentUser.setActive(reader.nextBoolean());
                }else{
                    reader.skipValue();
                }
            }
            reader.endObject();
            reader.close();

        }catch(IOException IO){

            System.out.println(IO.toString());
            System.out.println(IO.getStackTrace());
        }
    }

    public void printURL(){

        System.out.println(GET_USER + currentUser.getUserId());
    }
}
