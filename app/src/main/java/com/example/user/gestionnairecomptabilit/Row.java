package com.example.user.gestionnairecomptabilit;


public class Row
    {
    private String date;
    private String description;
    private String debit;
    private String credit;

    public String getDate()
        {
        return date;
        }

    public void setDate(String date)
        {
        this.date = date;
        }

    public String getDescription()
        {
        return description;
        }

    public void setDescription(String description)
        {
        this.description = description;
        }

    public String getDebit()
        {
        return debit;
        }

    public void setDebit(String debit)
        {
        this.debit = debit;
        }

    public String getCredit()
        {
        return credit;
        }

    public void setCredit(String credit)
        {
        this.credit = credit;
        }

    public String toString(){
    return this.getDate()+" "+this.getDescription()+" "+this.getDebit()+" "+this.getCredit();
    }


    public Row (String date, String description, String debit, String credit)
        {
        this.setDate(date);
        this.setDescription(description);
        this.setDebit(debit);
        this.setCredit(credit);
        }

    }
