package com.kong.support.toolboxes;

import java.util.Objects;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则工具
 * <pre>
 *
 *
 * </pre>
 */
public class RegexTool {
    private volatile String regexRecord;
    private volatile Pattern regexPatterRecord;
    private  Properties regexConfig;

    public RegexTool(){}

    public RegexTool(Properties regexConfig){
        this.regexConfig = regexConfig;
    }

    public boolean checkDecimal(String decimalString){
        return checkRegex(regexConfig.getProperty("reg_decimal"),decimalString,0);
    }


    public boolean checkDigit(String digitString){
        return checkRegex(regexConfig.getProperty("reg_digit"),digitString,0);
    }

    public boolean checkChineseContent(String chineseStr){
        return checkRegex(regexConfig.getProperty("reg_character_chinese"),chineseStr,0);
    }

    public boolean checkHtmlTag(String htmlContent){
        return checkRegex(regexConfig.getProperty("reg_html"),htmlContent,0);

    }

    public boolean checkEmail(String email){
        return checkRegex(regexConfig.getProperty("reg_email"),email,0);

    }

    public boolean checkUrl(String url){
        return checkRegex(regexConfig.getProperty("reg_url"),url,0);

    }

    public boolean checkUrlDomain(String domain){
        return checkRegex(regexConfig.getProperty("reg_url_domain"),domain,0);

    }

    public boolean checkMobile(String mobile){
        return checkRegex(regexConfig.getProperty("reg_mobile"),mobile,0);
    }


    public boolean checkPhone(String phone){
        return checkRegex(regexConfig.getProperty("reg_phone"),phone,0);
    }

    public boolean checkIp(String ip){
        return checkRegex(regexConfig.getProperty("reg_ip"),ip,0);
    }

    public  boolean checkIdcard(String idCard){
       return checkRegex(regexConfig.getProperty("reg_idcard"),idCard,0);
    };


    public synchronized boolean checkRegex(String regex,String content,int flag){
        Pattern compile = null;
        if (regexRecord!=null && regexRecord.equals(regex))
            compile = regexPatterRecord;
        else{
            Objects.requireNonNull(regex,"regex is not null args");
            regexPatterRecord = Pattern.compile(regex,flag);
            compile = regexPatterRecord;
        }
        Matcher matcher = compile.matcher(content);
        return matcher.matches();
    }

    public Properties getRegexConfig() {
        return regexConfig;
    }

    public void setRegexConfig(Properties regexConfig) {
        this.regexConfig = regexConfig;
    }
}
