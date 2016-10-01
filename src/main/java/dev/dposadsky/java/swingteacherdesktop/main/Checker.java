/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dev.dposadsky.java.swingteacherdesktop.main;

import dev.dposadsky.java.swingteacherdesktop.utils.CheckerResult;
import dev.dposadsky.java.swingteacherdesktop.utils.StringUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author DPosadsky
 */
public class Checker {
    
    public ArrayList<CheckerResult> check(String rSolution, String uSolution) {
        ArrayList<CheckerResult> resultArray = new ArrayList<>();
        
        if (rSolution == null)
            if (uSolution == null)
                return resultArray;
 
        if (rSolution != null)
            rSolution = StringUtils.removeSpace(rSolution);
        if (uSolution != null)
            uSolution = uSolution.trim();
        
        if (rSolution.isEmpty())
            if (uSolution.isEmpty())
                return resultArray;

        
        String[] rSolutionComponents = rSolution.split(";");
        String[][] rSolutionClassesAndMethods = new String[rSolutionComponents.length][2];
        String[] uSolutionOperators = uSolution.split(";");
        String[] uSolutionMethods = new String[rSolutionComponents.length];
        String[] uSolutionComponentsName = new String[rSolutionComponents.length]; //?
        
        for (int i = 0; i < rSolutionComponents.length; ++i) 
            rSolutionClassesAndMethods[i] = rSolutionComponents[i].split("-");

        for (int i = 0; i < rSolutionComponents.length; ++i) 
            if (checkConstructor(rSolutionClassesAndMethods[i][0], uSolution) == 1) {
                resultArray.add(new CheckerResult(rSolutionClassesAndMethods[i][0], 3));
                return resultArray;
            }
        
        for (int i = 0; i < rSolutionComponents.length; ++i) {
            String className = rSolutionClassesAndMethods[i][0];
            int length = uSolution.indexOf(className) + className.length();
            uSolutionComponentsName[i] = uSolution.substring(length, length + 
                    uSolution.substring(length).indexOf('=')).trim();
            uSolution = uSolution.replaceFirst(className + "[\\s]+" + uSolutionComponentsName[i] + "[\\s]*=[\\s]*new[\\s]+"+className+"\\(\\);", "");
        }
        
        for (int i = 0; i < uSolutionOperators.length; ++i) 
            uSolutionOperators[i] = StringUtils.removeSpace(uSolutionOperators[i]);
        
        for (int i = 0; i < rSolutionComponents.length; ++i)
            for (int j = 0; j < uSolutionOperators.length; ++j) 
                if (checkComponentOperators(uSolutionComponentsName[i],uSolutionOperators[j]) == 0) {
                    uSolutionOperators[j] = uSolutionOperators[j].replaceAll(uSolutionComponentsName[i] + ".", "");
                    if (uSolutionMethods[i] == null)
                        uSolutionMethods[i] = uSolutionOperators[j] + ";";
                    else
                        uSolutionMethods[i] += uSolutionOperators[j] + ";";
                }

        for (int i = 0; i < rSolutionComponents.length; ++i) {
            if (rSolutionClassesAndMethods[i].length == 2) {
                if (uSolutionMethods[i] == null) {
                    resultArray.add(new CheckerResult(rSolutionClassesAndMethods[i][0], 2));
                    return resultArray;
                }
                resultArray.add(new CheckerResult(rSolutionClassesAndMethods[i][0], 
                        checkMini(rSolutionClassesAndMethods[i][1], uSolutionMethods[i])));
            }
            else
                if (uSolutionMethods[i] != null)
                    resultArray.add(new CheckerResult(rSolutionClassesAndMethods[i][0], 1));
        }
        
        return resultArray;
    }
    
    private int checkComponentOperators(String compName, String uSolution) {
        Pattern p = Pattern.compile("^"+compName+"\\.[\\s\\S]+");  
        Matcher m = p.matcher(uSolution); 
        if (!m.matches())
            return 1;
        return 0;
    }
    
    private int checkConstructor(String compName, String userCode) {
        String regExp = "[\\s]*"+compName+"[\\s]+[\\S]+[\\s]*=[\\s]*new[\\s]+"+compName+"\\([\\s\\S]*\\);[\\s]*";
        Pattern p = Pattern.compile(regExp);  
        Matcher m = p.matcher(userCode); 
        if (!m.matches())
            return 1;
        return 0;
    }
 
    public int checkMini(String rSolution, String uSolution) {
        //rSolution = StringUtils.removeSpace(rSolution);
        uSolution = StringUtils.removeSpace(uSolution);
        String[] rSolutionArray = rSolution.split(",");
        String[] uSolutionArray = uSolution.split(";");
        
        System.out.println(Arrays.toString(rSolutionArray));
        System.out.println(Arrays.toString(uSolutionArray));
        
        if (rSolutionArray.length != uSolutionArray.length)
            return 1;
        
        int[] rightOperatorIsExist = new int[uSolutionArray.length];
        
        for (int i = 0; i < rSolutionArray.length; ++i)     
            for (int j = 0; j < uSolutionArray.length; ++j) 
                if (checkHelper(rSolutionArray[i], uSolutionArray[j]))
                    rightOperatorIsExist[i] = 1;                         

        for (int i = 0; i < rSolutionArray.length; ++i) 
            if (rightOperatorIsExist[i] != 1)
                return 2; 
    
        return 0;
    }
    
    public boolean checkHelper(String r, String u) {
        Pattern p = Pattern.compile("^"+r+"\\([\\s\\S]*\\)$");  
        Matcher m = p.matcher(u); 
        return m.matches();
    }
    
    public boolean checkLogin(String login) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_]{3,20}$");  
        Matcher m = pattern.matcher(login); 
        return m.matches();
    }
    
    public boolean checkPassword(String password, String passwordRepeat) {
        Pattern pattern = Pattern.compile("^[\\S]{8,30}$");  
        Matcher m = pattern.matcher(password); 
        if (!m.matches())
            return false;        
        return password.equals(passwordRepeat);
    }
    
    public boolean checkEMail(String eMail) {
        Pattern pattern = Pattern.compile("^.+@.+\\..+$");  
        Matcher m = pattern.matcher(eMail); 
        return m.matches();
    }
    
    public boolean isNumeric(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
