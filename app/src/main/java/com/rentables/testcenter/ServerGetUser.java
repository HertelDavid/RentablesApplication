package com.rentables.testcenter;

import android.os.HandlerThread;
import android.util.JsonReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ServerGetUser extends NotifyingThread {

    private String GET_USER = "http://beeduck.ddns.net:8080/users/userInfo/";
    private int userId;
    private String username = "";
    private String firstName = "";
    private String lastName = "";
    private String createDate = "";
    private String lastEditDate = "";
    private boolean active = false;

    public ServerGetUser(int newUserId){

        setUserId(newUserId);
    }

    @Override
    public void doRun(){

        //Run method implemented when start() is called.
        parseResponse(getResponse());
    }

    public JsonReader getResponse(){

        try{

            URL url = new URL(GET_USER + getUserId());
            HttpURLConnection connect = (HttpURLConnection) url.openConnection();

            System.out.println("Reached");

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
                    username = reader.nextString();
                }else if(name.equals("firstName")){
                    firstName = reader.nextString();
                }else if(name.equals("lastName")){
                    lastName = reader.nextString();
                }else if(name.equals("createDate")){
                    createDate = reader.nextString();
                }else if(name.equals("lastEditDate")){
                    lastEditDate = reader.nextString();
                }else if(name.equals("active")){
                    active = reader.nextBoolean();
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

        System.out.println(GET_USER + getUserId());
    }

    public void setUserId(int newUserId){

        userId = newUserId;
    }

    public int getUserId(){

        return userId;
    }

    public String getUsername(){

        return username;
    }

    public String getFirstName(){

        return firstName;
    }

    public String getLastName(){

        return lastName;
    }

    public String getCreateDate(){

        return createDate;
    }

    public String getLastEditDate(){

        return lastEditDate;
    }

    public boolean getActive(){

        return active;
    }

    public void printProperties(){

        System.out.println("username: " + getUsername());
        System.out.println("firstName: " + getFirstName());
        System.out.println("lastName: " + getLastName());
        System.out.println("createDate: " + getCreateDate());
        System.out.println("lastEditDate: " + getLastEditDate());
        System.out.println("active: " + getActive());
    }
}
