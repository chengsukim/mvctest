package org.example;

public class Article {
    long id;
    String title;
    String content;

    Article(long id , String title , String content){
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public long getId(){
        return this.id;

    }
    public String getTitle(){
        return this.title;
    }
    public String getContent(){
        return this.content;
    }

    public void SetId(long id){
        this.id = id;
    }

    public void Settitle(String title){
        this.title = title;
    }
    public void Setcontent(String content){
        this.content = content;
    }
}
