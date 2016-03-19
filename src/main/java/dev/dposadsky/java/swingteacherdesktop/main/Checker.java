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
        
        ArrayList<CheckerResult> resultArray = new ArrayList<CheckerResult>();
        
        if ((rSolution == null) ? uSolution == null : rSolution.equals(uSolution))
            return resultArray;
        
        String[] rSolutionArray = rSolution.split(";");
        String[][] rSolutionParsArray = new String[rSolutionArray.length][2];
        String[] uSolutionArray = uSolution.split(";");
        String[] uSolutionCr = new String[rSolutionArray.length];
        
        for (int i = 0; i < rSolutionArray.length; ++i) {
            uSolutionCr[i] = "";
            rSolutionParsArray[i] = rSolutionArray[i].split("-");
            rSolutionParsArray[i][0] = StringUtils.removeSpace(rSolutionParsArray[i][0]);
            rSolutionParsArray[i][1] = StringUtils.removeSpace(rSolutionParsArray[i][1]);
        }
        
        for (int i = 0; i < uSolutionArray.length; ++i) 
            uSolutionArray[i] = StringUtils.removeSpace(uSolutionArray[i]);
        //System.out.println(Arrays.toString(uSolutionArray));
        
        for (int i = 0; i < rSolutionArray.length; ++i)
            for (int j = 0; j < uSolutionArray.length; ++j) {
                if (checkComponentOperators(getItemName(rSolutionParsArray[i][0]),uSolutionArray[j]) == 0) {
                    uSolutionArray[j] = uSolutionArray[j].replaceAll(getItemName(rSolutionParsArray[i][0]) + ".", "");
                    uSolutionCr[i] += uSolutionArray[j] + ";";
                }
            }
        //System.out.println(Arrays.toString(uSolutionArray));
        System.out.println(Arrays.toString(uSolutionCr));

        
        //System.out.println(Arrays.toString(rSolutionParsArray[0]));
        //System.out.println(Arrays.toString(uSolutionCr));
        for (int i = 0; i < rSolutionArray.length; ++i) {
            resultArray.add(new CheckerResult(rSolutionParsArray[i][0], checkMini(rSolutionParsArray[i][1], uSolutionCr[i])));
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
                    //System.out.println(checkHelper(rSolutionArray[i], uSolutionArray[j]));
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
}
