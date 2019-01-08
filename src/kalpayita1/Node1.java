/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalpayita1;

import java.util.ArrayList;

/**
 *
 * @author Ashish
 */
public class Node1 {
    String name;
    String attribute;
    String attributeType;
    int level=0;
    ArrayList<Node1> next;
    ArrayList<String> connectedAs;
    public Node1( String name, String attribute,String attributeType)
    {
        this.name=name;
        this.attribute=attribute;
        this.attributeType=attributeType;
        next= new ArrayList<>();
        connectedAs= new ArrayList<>();
    }
}
