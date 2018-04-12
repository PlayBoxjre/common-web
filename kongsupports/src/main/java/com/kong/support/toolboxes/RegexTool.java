package com.kong.support.toolboxes;

public interface RegexTool {

    public int setFlags(int flag);

    public String[] findEmails(String text);

    public String[] findPhones(String text);

    public String[] findUrls(String text);

    public String[] findChar(String text);

    public String[] findNumbers(String text);

    public boolean matchConstraint(String text,String regex);


    public void reset();
}
