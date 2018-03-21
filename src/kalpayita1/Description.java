/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalpayita1;

/**
 *
 * @author Ashish
 */
public class Description {
    public String descriptionId, sceneId, description;
    public Description(String descriptionId, String description, String sceneId){
        this.descriptionId = descriptionId;
        this.sceneId = sceneId;
        this.description = description;
    }
}
