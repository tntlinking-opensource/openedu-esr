package com.ckfinder.connector.handlers.command;

import org.apache.commons.dbcp.BasicDataSource;

public class TeradataConnection extends BasicDataSource{
	public TeradataConnection() {
        // TODO Auto-generated constructor stub
        super();
    }
    
    @Override
    public void setPassword(String password){
        try{
            this.password = Aes.Decrypt("AkJI24CA9S1TFyiZitx5tw==");
            
        }catch(Exception e){
            
        }
    }
}
