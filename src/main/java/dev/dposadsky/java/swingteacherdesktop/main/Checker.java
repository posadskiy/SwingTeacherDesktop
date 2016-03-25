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
        rSolution = StringUtils.removeSpace(rSolution);
        uSolution = StringUtils.removeSpace(uSolution);
        
        ArrayList<CheckerResult> resultArray = new ArrayList<>();
        
        if (rSolution == null)
            if (uSolution == null)
                return resultArray;
            else {
                resultArray.add(new CheckerResult("Решение", 1));
                return resultArray;         
            }
        
        String[] rSolutionComponents = rSolution.split(";");
        String[][] rSolutionClassesAndMethods = new String[rSolutionComponents.length][2];
        String[] uSolutionOperators = uSolution.split(";");
        String[] uSolutionCr = new String[rSolutionComponents.length];
        
        for (int i = 0; i < rSolutionComponents.length; ++i) {
            uSolutionCr[i] = "";
            rSolutionClassesAndMethods[i] = rSolutionComponents[i].split("-");
            rSolutionClassesAndMethods[i][0] = StringUtils.removeSpace(rSolutionClassesAndMethods[i][0]);
            rSolutionClassesAndMethods[i][1] = StringUtils.removeSpace(rSolutionClassesAndMethods[i][1]);
        }
        
        for (int i = 0; i < uSolutionOperators.length; ++i) 
            uSolutionOperators[i] = StringUtils.removeSpace(uSolutionOperators[i]);
        //System.out.println(Arrays.toString(uSolutionOperators));
        
        for (int i = 0; i < rSolutionComponents.length; ++i)
            for (int j = 0; j < uSolutionOperators.length; ++j) {
                if (checkComponentOperators(getItemName(rSolutionClassesAndMethods[i][0]),uSolutionOperators[j]) == 0) {
                    uSolutionOperators[j] = uSolutionOperators[j].replaceAll(getItemName(rSolutionClassesAndMethods[i][0]) + ".", "");
                    uSolutionCr[i] += uSolutionOperators[j] + ";";
                }
            }
        //System.out.println(Arrays.toString(uSolutionOperators));
        System.out.println(Arrays.toString(uSolutionCr));

        
        //System.out.println(Arrays.toString(rSolutionClassesAndMethods[0]));
        //System.out.println(Arrays.toString(uSolutionCr));
        for (int i = 0; i < rSolutionComponents.length; ++i) {
            resultArray.add(new CheckerResult(rSolutionClassesAndMethods[i][0], checkMini(rSolutionClassesAndMethods[i][1], uSolutionCr[i])));
        }

        //System.out.println(Arrays.toString(isRight));
        
        return resultArray;
    }
    
    private int checkComponentOperators(String compName, String uSolution) {
        Pattern p = Pattern.compile("^"+compName+"\\.[\\s\\S]+");  
        Matcher m = p.matcher(uSolution); 
        if (!m.matches())
            return 1;
        return 0;
    }

    private String getItemName(String compName) {
        if (compName.isEmpty())
            return "";
        return compName.toLowerCase().charAt(1) + compName.substring(2);
    }
 
    public int checkMini(String rSolution, String uSolution) {
        rSolution = StringUtils.removeSpace(rSolution);
        uSolution = StringUtils.removeSpace(uSolution);
        //System.out.println(rSolution);
        //System.out.println(uSolution);
        String[] rSolutionArray = rSolution.split(",");
        String[] uSolutionArray = uSolution.split(";");
        
        System.out.println(Arrays.toString(rSolutionArray));
        System.out.println(Arrays.toString(uSolutionArray));
        
        if (rSolutionArray.length != uSolutionArray.length)
            return 1;
        
        int[] rightOperatorIsExist = new int[uSolutionArray.length];
        
        for (int i = 0; i < rSolutionArray.length; ++i) {
            
            for (int j = 0; j < uSolutionArray.length; ++j) {
                if (checkHelper(rSolutionArray[i], uSolutionArray[j])) {
                    //System.out.println(checkHelper(rSolutionComponents[i], uSolutionOperators[j]));
                    rightOperatorIsExist[i] = 1;
                }            
            }           
        }

        for (int i = 0; i < rSolutionArray.length; ++i) {
            if (rightOperatorIsExist[i] != 1)
                return 2; // Не все нужные определения использованы
        }
        
        return 0;
    }
    
    public boolean checkHelper(String r, String u) {
        Pattern p = Pattern.compile("^"+r+"\\([\\s\\S]*\\)$");  
        Matcher m = p.matcher(u); 
        if (!m.matches())
            return false;        
        return true;
    }
    
    public boolean checkLogin(String login) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_]{3,20}$");  
        Matcher m = pattern.matcher(login); 
        if (!m.matches())
            return false;        
        return true;
    }
    
    public boolean checkPassword(String password, String passwordRepeat) {
        Pattern pattern = Pattern.compile("^[\\S]{8,30}$");  
        Matcher m = pattern.matcher(password); 
        if (!m.matches())
            return false;        
        if (checkPasswordMatch(password, passwordRepeat))
            return true;
        return false;
    }
    
    private boolean checkPasswordMatch(String password, String passwordRepeat) {
        return password.equals(passwordRepeat);
    }
    
    public boolean checkEMail(String eMail) {
        Pattern pattern = Pattern.compile("^.+@.+\\..+$");  
        Matcher m = pattern.matcher(eMail); 
        if (!m.matches())
            return false;        
        return true;
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
