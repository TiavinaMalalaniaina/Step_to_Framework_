/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu2025.framework;

/**
 *
 * @author tiavi
 */
public class Mapping {
    String className;
    String method;

    public Mapping(String className, String method) {
        setClassName(className);
        setMethod(method);
    }

    
    
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    
}
