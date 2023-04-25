/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu2025.framework;

import java.util.HashMap;

/**
 *
 * @author tiavi
 */
public class ModelView {
    String view;
    HashMap<String, Object> data;

    public ModelView(String view) {
        setView(view);
        setData(new HashMap<String, Object>());
    }
    
    
    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }
    
    public void addItem(String key, Object value) {
        getData().put(key, value);
    }

}
