package controller;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class SenderController {
    private String username,
            host,
            queueName;
    private Connection connection;
    private Channel channel;
    private ConnectionFactory factory;

    public SenderController(String username, String host,String queueName) {
        this.username = username;
        this.host = host;
        this.queueName = queueName;
        connect();
    }

    private boolean connect() {
        try
        {
            return createFactory() && newConnection() && createChannel();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        catch(TimeoutException e)
        {
            e.printStackTrace();
        }

        return false;
    }

    public boolean close(){
        try {
            channel.close();
            connection.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean createFactory(){
        if(factory == null){factory = new ConnectionFactory();}

        factory.setHost(host);
        factory.setUsername(username);

        return factory.getHost().equals(host);
    }

    private boolean newConnection() throws IOException, TimeoutException {

        if( connection == null )
            connection = factory.newConnection();

        return connection.isOpen();
    }

    private boolean createChannel() throws IOException, TimeoutException {
        if( channel == null )
            channel = connection.createChannel();

        return channel.isOpen();
    }

    public String SendMSG(String msg){
        String response;
        response =  Publish( msg );

        return response;
    }

    private String Publish(String msg){

        try {
            channel.queueDeclare( queueName, false, false, false, null );
            channel.basicPublish("",queueName,null,msg.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "[Sent] --> '" + msg + "'";
    }

}
