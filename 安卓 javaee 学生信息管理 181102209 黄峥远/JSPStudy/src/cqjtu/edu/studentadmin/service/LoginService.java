package cqjtu.edu.studentadmin.service;

import cqjtu.edu.studentadmin.dao.DatabaseOperation;

public class LoginService {
    DatabaseOperation dbOp=new DatabaseOperation();
    public boolean loginCheck(String name,String password) {
    	
    	return  dbOp.findUser(name, password);
    }
}
