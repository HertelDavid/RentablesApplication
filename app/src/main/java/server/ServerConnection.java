package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import com.google.gson.*;

import dataobject.*;

public class ServerConnection<DataObject> extends NotifyingThread {

    //Generic calls to the server if needed for whatever reason
    public final static String CREATE_USER = "http://rentoauth.us-west-2.elasticbeanstalk.com/users/createUser";
    public final static String USER_INFO = "http://rentoauth.us-west-2.elasticbeanstalk.com/users/userInfo/";

    //The object in question
    private DataObject dataObject;

    public ServerConnection(DataObject data){

        dataObject = data;
    }

    @Override
    public void doRun() {

        decision();

    }

    public DataObject getDataObject(){

        return dataObject;
    }

    private void decision(){

        //Deciding what to do based off the object

        if(dataObject.getClass() == dataobject.CreateUser.class){

            registerUser();

        }else if(dataObject.getClass() == Integer.class){

            System.out.println("You put in an integer?");

        }else{

            throw new RuntimeException("Currently no implementation for the class: " + dataObject.getClass());
        }
    }

    private void registerUser(){

        //Register the user. Note: data should already be included in the data object that was handed
        //to this class

        CreateUser createUser = (CreateUser) dataObject;

        try{

            //Converting the CreateUser object into json
            Gson converter = new Gson();
            String json = converter.toJson(createUser);
            System.out.println(json);

            //Converting url
            URL url = new URL(this.CREATE_USER);
            HttpURLConnection connect = (HttpURLConnection) url.openConnection();

            //Setting the connection properties
            connect.setDoInput(true);
            connect.setDoOutput(true);
            connect.setRequestMethod("POST");
            connect.setRequestProperty("Content-Type", "application/json");
            connect.setRequestProperty("charset", "utf-8");

            //Getting the output stream
            DataOutputStream outputStream = new DataOutputStream(connect.getOutputStream());

            //Writing to the output stream
            outputStream.write(json.getBytes());
            outputStream.flush();

            //Catching any unsuccessful response codes
            if(connect.getResponseCode() != 200){

                BufferedReader buffReader = new BufferedReader(new InputStreamReader(connect.getErrorStream()));
                String error;

                while((error = buffReader.readLine()) != null){

                    this.addError(error);
                    System.out.println(error);
                }

                throw new RuntimeException("HTTP error with response code: " + connect.getResponseCode());

            }else{

                System.out.println("Connection successful!");
            }

            //Disconnecting from the server
            connect.disconnect();


        }catch(MalformedURLException malformed){

            System.out.println("Error(MalformedURLException): " + malformed.toString());
            malformed.printStackTrace();

        }catch(IOException IO){

            System.out.println("Error(IOException): " + IO.toString());
            IO.printStackTrace();

        }catch(RuntimeException run){

            System.out.println("Error(RuntimeException): " + run.toString());
            run.printStackTrace();

        }
    }
}
