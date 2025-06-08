package org.example.interfaces;
import org.example.db.Env;


public interface DataBaseManage {
    String URL = new Env().url;
    String USER = new Env().user;
    String PASS = new  Env().passeWord;
}
