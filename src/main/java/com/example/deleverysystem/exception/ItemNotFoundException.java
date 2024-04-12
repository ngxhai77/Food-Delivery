package com.example.deleverysystem.exception;

public class ItemNotFoundException   extends  RuntimeException{
    private Integer id ;
    public ItemNotFoundException(int id){
        super("Could not find this "+ id);
    }
}
