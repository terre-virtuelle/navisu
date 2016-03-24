/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.gpx.model;

/**
 *
 * @author Serge
 */
public class PersonBuilder {

    protected String name= "noname";
    protected Email email= new Email();
    protected Links link=new Links();

    private PersonBuilder() {
    }

    public static PersonBuilder create() {
        return new PersonBuilder();
    }

    public Person build() {
        return new Person(name, email, link);
    }

    public PersonBuilder name(String name) {
        this.name = name;
        return this;
    }

    public PersonBuilder email(Email email) {
        this.email = email;
        return this;
    }

    public PersonBuilder link(Links link) {
        this.link = link;
        return this;
    }

}
