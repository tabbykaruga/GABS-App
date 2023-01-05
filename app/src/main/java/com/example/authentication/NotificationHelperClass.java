package com.example.authentication;

public class NotificationHelperClass {
    String time;
    String ServiceProvider;
    String client;


    public NotificationHelperClass() {
    }

    public NotificationHelperClass(String time, String ServiceProvider,String client) {
        this.time = time;
        this.ServiceProvider = ServiceProvider;
        this.client=client;
    }
    public String getServiceProvider() {
        return ServiceProvider;
    }

    public void setServiceProvider(String ServiceProvider) {
        this.ServiceProvider = ServiceProvider;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

}
